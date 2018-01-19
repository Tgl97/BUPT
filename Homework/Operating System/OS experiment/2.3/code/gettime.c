#include <stdio.h>
#include <sys/time.h>
#include <fcntl.h>

int main(void)
{
	struct timeval getSystemTime;
	char procClockTime[256];
	int infile,len;

	gettimeofday(&getSystemTime,NULL);

	infile = open("/proc/clock/my_clock",O_RDONLY);
	if (infile < 0)
	{
		printf("error!");
		return 0;
	}
	len = read(infile,procClockTime,256);
	close(infile);

	procClockTime[len] = '\0';

	printf("SystemTime is %ld %ld\nProcClockTime is %s\n",getSystemTime.tv_sec ,getSystemTime.tv_usec,procClockTime);
	sleep(1);
} 

