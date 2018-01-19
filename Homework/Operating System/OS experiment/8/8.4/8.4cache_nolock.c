#define ORANGE_MAX_VALUE 100000 //少一个零 
#define APPLE_MAX_VALUE 100000000
#define MEXCOND 1000000
#include <pthread.h>
#include<stdio.h>
#include<sys/time.h>
#include<unistd.h>

struct apple
{
    unsigned long long a;
    char c[128]; /*32,64,128*/
	unsigned long long b;
//	pthread_rwlock_t rwLock;
};
struct orange 
{
	int a[ORANGE_MAX_VALUE];
	int b[ORANGE_MAX_VALUE];
};

 
void InsertSort(long int a[], int n)  
{  
	int i;
    for(i= 1; i<n; i++){  
        if(a[i] < a[i-1]){               //若第i个元素大于i-1元素，直接插入。小于的话，移动有序表后插入  
            int j= i-1;   
            int x = a[i];        //复制为哨兵，即存储待排序元素  
            a[i] = a[i-1];           //先后移一个元素  
            while(x < a[j]){  //查找在有序表的插入位置  
                a[j+1] = a[j];  
                j--;         //元素后移  
            }  
            a[j+1] = x;      //插入到正确位置  
        }         //打印每趟排序的结果  
    }  
}  

void* addx(void* x)
{
	int sum;

//	pthread_rwlock_init(&((struct apple *)x)->rwLock ,NULL);
//	pthread_rwlock_wrlock(&((struct apple *)x)->rwLock);
	for(sum=0;sum<APPLE_MAX_VALUE;sum++)
	{
		((struct apple *)x)->a += sum;
	}	
//	pthread_rwlock_unlock(&((struct apple *)x)->rwLock);
//	pthread_rwlock_destroy(&((struct apple *)x)->rwLock);
	return NULL;
}

void* addy(void* y)
{
	int sum;

//	pthread_rwlock_init(&((struct apple *)y)->rwLock ,NULL);
//	pthread_rwlock_wrlock(&((struct apple *)y)->rwLock);
	for(sum=0;sum<APPLE_MAX_VALUE;sum++)
	{
		((struct apple *)y)->b += sum;
	}
//	pthread_rwlock_unlock(&((struct apple *)y)->rwLock);
//	pthread_rwlock_destroy(&((struct apple *)y)->rwLock);
	return NULL;
}

int main (int argc, const char * argv[]) {
    // insert code here...
	FILE *fp;
	fp = fopen("8.4cache_nolock_file","w");
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
	long int average;
	average = 0;
	sum = 0;
	count = 0;
	
	while(count <= 199)
 	{
 		gettimeofday(&tv,NULL);
 		
	//	pthread_rwlock_init(&test.rwLock,NULL);
 		
		pthread_create(&ThreadA,NULL,addx,&test);
		pthread_create(&ThreadB,NULL,addy,&test);
		for(index=0;index<ORANGE_MAX_VALUE;index++)
		{
			sum+=test1.a[index]+test1.b[index];
		}
	
	    pthread_join(ThreadA,NULL);
	    pthread_join(ThreadB,NULL);
	  
	    gettimeofday(&tv2,NULL);

		time_record[count] =  tv2.tv_usec - tv.tv_usec + (tv2.tv_sec - tv.tv_sec)*1000000;
		
	//	if(during < 0)
	//		during = 0 - during;
	//	time_record[count] = during;
	
		fprintf(fp,"tz_dsttime:%ld\n",time_record[count]);
	//	printf("%d\n",count);
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
	fclose(fp);
    return 0;

}
