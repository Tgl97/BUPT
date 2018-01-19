#include <stdio.h>
#include <string.h>

#include "protocol.h"

#define MAX_SEQ 7
#define DATA_TIMER  2800
#define ACK_TIMER 240
#define DATA 1
#define ACK 2
#define NAK 3

struct frame{
	unsigned char kind ;
	unsigned char ack ;
	unsigned char seq ;
	unsigned char info[PKT_LEN] ;
	unsigned int padding;
} ;

static int phl_ready = 0 ;

static int between (unsigned char a, unsigned char b, unsigned char c)
//窗口函数，判断帧号（ack号是否在窗口内）
{
	if( ((a <= b) && (b < c)) || ((c < a) && (a <= b)) || ((b < c )&& (c < a)) )
		return 1 ;
	else
		return 0 ;
}

static void put_frame(unsigned char *frame, int len)
//加入校验和CRC
{
    *(unsigned int *)(frame + len) = crc32(frame, len);
    send_frame(frame, len + 4);
    phl_ready = 0 ;
}

static void send_data_frame( unsigned char frame_nr, unsigned char frame_expected, unsigned char buffer[MAX_SEQ + 1][PKT_LEN] )
//发送数据帧、ack、nak
{
    struct frame s;

    s.kind = DATA ;
    s.seq = frame_nr ;
    s.ack = ( frame_expected + MAX_SEQ ) % ( MAX_SEQ + 1 ) ;
	memcpy( s.info, buffer[frame_nr], PKT_LEN ) ; 
	//复制分组到帧中
	dbg_frame( "Send DATA %d %d, ID %d\n", s.seq, s.ack, *(short *)s.info ) ;
	put_frame( (unsigned char *)&s, 3 + PKT_LEN ) ;
	//加入校验和
	start_timer( frame_nr, DATA_TIMER ) ;
    phl_ready = 0 ;
	stop_ack_timer() ;
}

int main(int argc, char **argv)
{
    int event, arg;
    struct frame f ;
    int len = 0 ;
    int i ;

	static unsigned char ack_expected = 0, next_frame_to_send = 0 ;
    static unsigned char frame_expected = 0 ;
	static unsigned char nbuffered = 0 ; 
	unsigned char buffer[MAX_SEQ + 1][PKT_LEN] ;

    protocol_init(argc, argv); 

    lprintf("Designed by dao, build: " __DATE__"  "__TIME__"\n");

    //enable_network_layer();

    for (;;)
	{
        event = wait_for_event(&arg) ;

        switch (event) 
		{
			case NETWORK_LAYER_READY :
				get_packet( buffer[next_frame_to_send] ) ;
				nbuffered ++ ;
				send_data_frame( next_frame_to_send, frame_expected, buffer ) ;
				next_frame_to_send = ( next_frame_to_send + 1 ) % ( MAX_SEQ + 1 ) ;
				break;

			case PHYSICAL_LAYER_READY :
				phl_ready = 1 ;
				break ;

			case FRAME_RECEIVED :
				//一个数据帧到达
				len = recv_frame( (unsigned char *)&f, sizeof f ) ;
				if ( len < 5 || crc32((unsigned char *)&f, len) != 0 )
				//校验和出错，发送nak请求重传
				{
					dbg_event("**** Receiver Error, Bad CRC Checksum\n") ;
					break;
				}

				if ( f.kind == DATA )
				{
					dbg_frame( "Recv DATA %d %d, ID %d\n", f.seq, f.ack, *(short *)f.info ) ;

					if ( f.seq == frame_expected )
					{
						put_packet( f.info, len-7 ) ;
						frame_expected = ( frame_expected + 1 ) % ( MAX_SEQ + 1 ) ;
					}
					while ( between( ack_expected, f.ack, next_frame_to_send ) )
					{
						nbuffered -- ;
						stop_timer( ack_expected ) ;
						ack_expected = ( ack_expected + 1 ) % ( MAX_SEQ + 1 ) ;
					}
				}
				
				break ; 

			case DATA_TIMEOUT :
				//超时，重传发送窗口中所有的帧
				dbg_event( "---- DATA %d timeout\n", arg ) ; 
				next_frame_to_send = ack_expected ;
				for ( i = 1 ; i <= nbuffered ; i ++ )
				{
					send_data_frame( next_frame_to_send, frame_expected, buffer ) ;
				}
				break ;
        }

        if ( (nbuffered < MAX_SEQ) && phl_ready )
            enable_network_layer() ;
        else
            disable_network_layer() ;
   }

}
