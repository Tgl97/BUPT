#include <stdio.h>
#include <time.h>
#define ORANGE_MAX_VALUE 1000000
#define APPLE_MAX_VALUE  100000000
#define MSECOND 	 1000000

struct apple
{
	unsigned long long a;
	unsigned long long b;
};

struct orange
{
	int a[ORANGE_MAX_VALUE];
	int b[ORANGE_MAX_VALUE];
};

int main(int argc,const char* argv[]) {
	clock_t begin = clock();

	struct apple test;
	struct orange test1;

	int sum,index;
	for (sum=1;sum<=APPLE_MAX_VALUE;sum++)
	{
		test.a+=sum;
		test.b+=sum;
	}
	
	sum=0;
	for (index=0;index<ORANGE_MAX_VALUE;index++)
		sum+=test1.a[index]+test1.b[index];
	
	printf("  1Thread:%ld\n",clock()-begin);
	return 0;
}
