#include <stdio.h>
#include <linux/kernel.h>
#include <sys/syscall.h>
#include <linux/time.h>
#include <linux/unistd.h>

int main()
{
	struct timeval gettime;
	struct timeval mycalltime;
	gettimeofday(&gettime,NULL);
	long int amma = syscall(330,&mycalltime);
	printf("gettimeofday: %d %d\n",gettime.tv_sec,gettime.tv_usec);
	printf("mycalltime: %d %d\n",mycalltime.tv_sec,mycalltime.tv_usec);
	printf("System call sys_mycall returned %ld\n",amma);
	return 0;
} 
