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

	signal(SIGALRM,sighandle);		//SIGALRM������ʵ�ʵ�ʱ�� 
	signal(SIGVTALRM,sighandle);	//SIGVTALM������ý���ռ��CPU��ʱ�� 
	signal(SIGPROF,sighandle);		//SIGPROF�������ý����õ�CPUʱ���ϵͳ���õ�ʱ�� 

	v.it_interval.tv_sec = 10;
	v.it_interval.tv_usec = 0;
	v.it_value.tv_sec = 10;
	v.it_value.tv_usec = 0;

	setitimer(ITIMER_REAL,&v,NULL);		//��ϵͳ��ʵ��ʱ�������㣬���ͳ�SIGALRM�źš�
	setitimer(ITIMER_VIRTUAL,&v,NULL);	//�Ըý������û�̬�»��ѵ�ʱ�������㣬���ͳ�SIGVTALRM�źš�
	setitimer(ITIMER_PROF,&v,NULL);		//�Ըý������û�̬�º��ں�̬�����ѵ�ʱ�������㣬���ͳ�SIGPROF�źš�
   	for (j = 0; j < 1000; j ++)
	{
        for (i = 0; i < 500; i ++)
		{
			printf("********\r");
			fflush(stdout);
		}
	}
	//int getitimer(int which, struct itimerval *curr_value);
	//getitimer��ȡwhichָ���Ķ�ʱ����ֵ������curr_value�ֶ���
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

