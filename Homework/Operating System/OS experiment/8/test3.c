#include <stdio.h>
#include <time.h>
#include <pthread.h>
#define ORANGE_MAX_VALUE 1000000
#define APPLE_MAX_VALUE  100000000
#define MSECOND 	 1000000
struct apple{
	unsigned long long a;
	unsigned long long b;
	pthread_rwlock_t rwLock;
};
struct orange{
	int a[ORANGE_MAX_VALUE];
	int b[ORANGE_MAX_VALUE];
};
void* addx(void* x){
	int sum;
	pthread_rwlock_wrlock(&((struct apple *)x)->rwLock);
	for (sum=0;sum<APPLE_MAX_VALUE;sum++)
		((struct apple *)x)->a += sum;
	pthread_rwlock_unlock(&((struct apple *)x)->rwLock);
	return NULL;
}
void* addy(void* x){
	int sum;
	pthread_rwlock_wrlock(&((struct apple *)x)->rwLock);
	for (sum=0;sum<APPLE_MAX_VALUE;sum++)
		((struct apple *)x)->b += sum;
	pthread_rwlock_unlock(&((struct apple *)x)->rwLock);
	return NULL;
}

int main(int argc,const char* argv[]) {
	clock_t begin = clock();
	struct apple test;
	struct orange test1={{0},{0}};
	pthread_t ThreadA,ThreadB;
	pthread_create(&ThreadA,NULL,addx,&test);
	pthread_create(&ThreadB,NULL,addy,&test);
	int index=0;
	int sum=0;
	for (index=0;index<ORANGE_MAX_VALUE;index++)
		sum+=test1.a[index]+test1.b[index];
	pthread_join(ThreadA,NULL);
	pthread_join(ThreadB,NULL);

	printf("  3Thread Lock:%ld\n",clock()-begin);
	return 0;
}
