#include <stdio.h>

#include <time.h>

#include <pthread.h>

#define ORANGE_MAX_VALUE 1000000

#define APPLE_MAX_VALUE  100000000

#define MSECOND 	 1000000


struct apple
{

	unsigned long long a;

	unsigned long long b;

	//pthread_rwlock_t rwLock;

};


struct orange
{

	int a[ORANGE_MAX_VALUE];

	int b[ORANGE_MAX_VALUE];

};



void* addx(void* x)
{

	int sum=0;

	//pthread_rwlock_wrlock(&((struct apple *)x)->rwLock);

	for (sum=0;sum<APPLE_MAX_VALUE;sum++)

		((struct apple *)x)->a += sum;


	//pthread_rwlock_unlock(&((struct apple *)x)->rwLock);

	return NULL;

}


void* addy(void* x)

{
	
	int sum;

	//pthread_rwlock_wrlock(&((struct apple *)x)->rwLock);

	for (sum=0;sum<APPLE_MAX_VALUE;sum++)

		((struct apple *)x)->b += sum;


	//pthread_rwlock_unlock(&((struct apple *)x)->rwLock);

	return NULL;

}


int main(int argc,const char* argv[])
 {
	   
	struct apple test;
	struct orange test1={{0},{0}};
	pthread_t ThreadA,ThreadB;
	int index;
	long int time_record[200] = {0};
 	int count;
 	struct  timeval   tv;
	struct  timeval   tv2;
	int j;
	int sum;
	FILE *fp = fopen("os8.3.txt","w");
	long int average;
	average = 0;
	sum = 0;
	count = 0;

	while(count <= 199)
 	{
 		gettimeofday(&tv,NULL);
		pthread_create(&ThreadA,NULL,addx,&test);

		pthread_create(&ThreadB,NULL,addy,&test);


		sum = 0;index = 0;
		for (index=0;index<ORANGE_MAX_VALUE;index++)

			sum+=test1.a[index]+test1.b[index];

		
pthread_join(ThreadA,NULL);

		pthread_join(ThreadB,NULL);
 
	    	gettimeofday(&tv2,NULL);
		time_record[count] =  tv2.tv_usec - tv.tv_usec + (tv2.tv_sec - tv.tv_sec)*1000000;
		fprintf(fp,"tz_dsttime:%ld\n",time_record[count]);
		count ++;
	}
	InsertSort(time_record,200);
	for(j= 0; j<10; j++)
	{  
       		fprintf(fp,"%ld\n", time_record[j]);  
       		average = average + time_record[j];
	}  

	average = average / 10;
    	fprintf(fp,"average = %ld",average);
	return 0;

}
	