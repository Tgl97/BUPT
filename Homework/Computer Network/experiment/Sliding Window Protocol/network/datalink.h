
typedef enum { false, true } boolean ;
typedef enum { data, ack, nak } frame_kind ; /* FRAME kind */

typedef unsigned char seq_nr ;

typedef struct 
{
	frame_kind kind ;
	seq_nr seq ;
	seq_nr ack ;
	unsigned char info[PKT_LEN] ;
	unsigned int padding;
} frame ;




 
    DATA Frame
    +=========+========+========+===============+========+
    | KIND(1) | SEQ(1) | ACK(1) | DATA(240~256) | CRC(4) |
    +=========+========+========+===============+========+

    ACK Frame
    +=========+========+========+
    | KIND(1) | ACK(1) | CRC(4) |
    +=========+========+========+

    NAK Frame
    +=========+========+========+
    | KIND(1) | ACK(1) | CRC(4) |
    +=========+========+========+


