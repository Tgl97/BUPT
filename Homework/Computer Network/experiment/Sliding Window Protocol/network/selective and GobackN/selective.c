#include <stdio.h>
#include <string.h>

#include "protocol.h"

#define MAX_SEQ 15
#define NR_BUFS ((MAX_SEQ+1)/2)
#define DATA_TIMER 3000     //帧超时间隔 
#define ACK_TIMER 300        //ack超时间隔 
#define DATA 1
#define ACK 2
#define NAK 3

struct frame{
	unsigned char kind ;           //数据的类型 
	unsigned char ack ;            //确认帧 
	unsigned char seq ;            //帧的序列号 
	unsigned char info[PKT_LEN] ;  //数据内容 
	unsigned int padding;
} ;

static int no_nak = 1 ;    //标志是否已经发送过nak 
static int phl_ready = 0 ;

static int between(unsigned char a, unsigned char b, unsigned char c)
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

static void send_data_frame( unsigned char fk, unsigned char frame_nr, unsigned char frame_expected, unsigned char buffer[NR_BUFS][PKT_LEN] )
//发送数据帧、ack、nak
{
    struct frame s;

    s.kind = fk;
    s.seq = frame_nr;
    s.ack = ( frame_expected + MAX_SEQ ) % ( MAX_SEQ + 1 ) ;
	if ( fk == DATA )
	{
		memcpy( s.info, buffer[frame_nr % NR_BUFS], PKT_LEN ) ; 
		//复制分组到帧中
		dbg_frame( "Send DATA %d %d, ID %d\n", s.seq, s.ack, *(short *)s.info ) ;
		put_frame( (unsigned char *)&s, 3 + PKT_LEN ) ;
		//加入校验和
		start_timer( frame_nr % NR_BUFS, DATA_TIMER ) ;
	}
	else if ( fk == NAK )
	{
		no_nak = 0 ;
		put_frame( (unsigned char *)&s, 2 ) ;
	}
	else if (fk == ACK )
	{
		dbg_frame( "Send ACK %d\n", s.ack ) ;
		put_frame( (unsigned char *)&s, 2 ) ;
	}
    phl_ready = 0 ;
	stop_ack_timer() ;
}

int main(int argc, char **argv)
{
    int event, arg ;
    struct frame f ;
    int len = 0 ;
    int i ;

	static unsigned char ack_expected = 0, next_frame_to_send = 0 ;
    static unsigned char frame_expected = 0, too_far = NR_BUFS ;
	static unsigned char nbuffered = 0 ; 
	int arrived[NR_BUFS] ;
	unsigned char in_buf[NR_BUFS][PKT_LEN], out_buf[NR_BUFS][PKT_LEN] ;

    protocol_init(argc, argv); 

    lprintf("Designed by dao, build: " __DATE__"  "__TIME__"\n");
	
	for ( i = 0 ; i < NR_BUFS ; i ++ )
	{
		arrived[i] = 0 ;
	}

    //enable_network_layer();

    for (;;)
	{
        event = wait_for_event(&arg) ;

        switch (event) 
		{
			case NETWORK_LAYER_READY :
				get_packet( out_buf[next_frame_to_send % NR_BUFS] ) ;
				nbuffered ++ ;
				send_data_frame( DATA, next_frame_to_send, frame_expected, out_buf ) ;
				next_frame_to_send = ( next_frame_to_send + 1 ) % ( MAX_SEQ + 1 ) ;
				break;

			case PHYSICAL_LAYER_READY :
				phl_ready = 1 ;
				break ;

			case FRAME_RECEIVED :
				len = recv_frame( (unsigned char *)&f, sizeof f ) ;
				if ( len < 5 || crc32((unsigned char *)&f, len) != 0 )
				//校验和出错，发送nak请求重传
				{
					if ( no_nak == 1 )
					{
						send_data_frame( NAK, 0, frame_expected, out_buf ) ;
					}
					dbg_event("**** Receiver Error, Bad CRC Checksum\n") ;
					break;
				}

				if ( f.kind == DATA )
				{
					if ( (f.seq != frame_expected ) && no_nak == 1 )
					//序列号错误
					{
						send_data_frame( NAK, 0, frame_expected, out_buf ) ;
					}
					else
					{
						start_ack_timer( ACK_TIMER ) ;
					}
					if ( between( frame_expected, f.seq, too_far ) && arrived[f.seq % NR_BUFS] == 0 )
					{
						//log_printf("Received a frame, "); log_printf("%d bytes\n", len);
						dbg_frame( "Recv DATA %d %d, ID %d\n", f.seq, f.ack, *(short *)f.info ) ;
						arrived[f.seq % NR_BUFS] = 1 ;
						memcpy( in_buf[f.seq % NR_BUFS], f.info, len-7 ) ;
						while ( arrived[frame_expected % NR_BUFS] )
						{
							put_packet( in_buf[ frame_expected % NR_BUFS ], len-7 ) ;
							no_nak = 1 ;
							arrived[ frame_expected % NR_BUFS ] = 0 ;
							frame_expected = ( frame_expected + 1 ) % ( MAX_SEQ + 1 ) ;
							too_far = ( too_far + 1 ) % ( MAX_SEQ + 1 ) ;
							start_ack_timer( ACK_TIMER ) ;
						}
					}
				}

				if ( (f.kind == NAK) && between( ack_expected, ( f.ack + 1 ) % ( MAX_SEQ + 1 ), next_frame_to_send ) )
				//发送方收到nak，重传
				{
					dbg_frame( "Recv NAK  %d\n", f.ack ) ;
					send_data_frame( DATA, (f.ack + 1 ) % ( MAX_SEQ + 1 ), frame_expected, out_buf ) ;
				}
				while ( between( ack_expected, f.ack, next_frame_to_send ) )
				{
					nbuffered -- ;
					stop_timer( ack_expected % NR_BUFS ) ;
					ack_expected = ( ack_expected + 1 ) % ( MAX_SEQ + 1 ) ;
				}

				break ; 

			case DATA_TIMEOUT :
				//超时重传
				dbg_event( "---- DATA %d timeout\n", arg ) ; 
				send_data_frame( DATA, ack_expected, frame_expected, out_buf ) ;
				break ;

			case ACK_TIMEOUT :
				dbg_event( "---- ACK %d timeout\n", arg ) ; 
				send_data_frame( ACK, 0, frame_expected, out_buf ) ;
				break ;
        }

        if ( (nbuffered < NR_BUFS) && phl_ready )
            enable_network_layer() ;
        else
            disable_network_layer() ;
   }

}
