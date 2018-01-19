#include <sys/time.h>
#include <stdio.h>
#include <signal.h>

static void sighandle(int);
static long realsecond = 0;
static long vtsecond = 0;
static long profsecond = 0;
static struct itimerval realt,virtt,proft;

int main()
{
	struct itimerval v;
	int i,j;
	long moresec,moremsec,t1,t2;

	signal(SIGALRM,sighandle);		//SIGALRM，计算实际的时间 
	signal(SIGVTALRM,sighandle);	//SIGVTALM，计算该进程占用CPU的时间 
	signal(SIGPROF,sighandle);		//SIGPROF，包括该进程用的CPU时间和系统调用的时间 

	v.it_interval.tv_sec = 10;
	v.it_interval.tv_usec = 0;
	v.it_value.tv_sec = 10;
	v.it_value.tv_usec = 0;

	setitimer(ITIMER_REAL,&v,NULL);		//以系统真实的时间来计算，它送出SIGALRM信号。
	setitimer(ITIMER_VIRTUAL,&v,NULL);	//以该进程在用户态下花费的时间来计算，它送出SIGVTALRM信号。
	setitimer(ITIMER_PROF,&v,NULL);		//以该进程在用户态下和内核态下所费的时间来计算，它送出SIGPROF信号。
   	for (j = 0; j < 1000; j ++)
	{
        for (i = 0; i < 500; i ++)
		{
			printf("********\r");
			fflush(stdout);
		}
	}
	//int getitimer(int which, struct itimerval *curr_value);
	//getitimer获取which指定的定时器的值并填入curr_value字段中
	getitimer(ITIMER_PROF,&proft);
	getitimer(ITIMER_REAL,&realt);
	getitimer(ITIMER_VIRTUAL,&virtt);

	printf("\n");
	moresec = 10 - realt.it_value.tv_sec;
	moremsec = (1000000 - realt.it_value.tv_usec)/1000;
	printf("realtime = %ld sec, %ld msec\n",realsecond+moresec,moremsec);
	
	moresec = 10 - proft.it_value.tv_sec;
	moremsec = (1000000 - proft.it_value.tv_usec)/1000;
	printf("cputime = %ld sec, %ld msec\n",profsecond+moresec,moremsec);
	
	moresec = 10 - virtt.it_value.tv_sec;
	moremsec = (1000000 - virtt.it_value.tv_usec)/1000;
	printf("usertime = %ld sec, %ld msec\n",vtsecond+moresec,moremsec);
	t1 = (10 - proft.it_value.tv_sec)*1000 + (1000000 - proft.it_value.tv_usec)/1000 + profsecond*1000;
	t2 = (10 - virtt.it_value.tv_sec)*1000 + (1000000 - virtt.it_value.tv_usec)/1000 + vtsecond*1000;
	moresec = (t1 - t2)/1000;
	moremsec = (t1 - t2) % 1000;
	printf("kerneltime = %ld sec, %ld msec\n",moresec,moremsec);
	fflush(stdout);
}

static void sighandle(int s)
{
	switch(s)
	{
		case SIGALRM:realsecond+=10;break;
	    case SIGVTALRM:vtsecond+=10;break;
	    case SIGPROF:profsecond+=10;break;
		default :break;
	}
}

