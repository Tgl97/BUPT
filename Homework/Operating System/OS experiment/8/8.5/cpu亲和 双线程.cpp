#define ORANGE_MAX_VALUE 100000 //
#define APPLE_MAX_VALUE 100000000
#define MEXCOND 1000000
#define _GNU_SOURCE
#include <pthread.h>
#include<stdio.h>
#include<sys/time.h>
#include<unistd.h>
#include <sched.h>
#include <stdlib.h>
#include <sys/syscall.h>
#define gettid() syscall(__NR_gettid)

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
int set_cpu(int i)  
{  
	cpu_set_t mask;
    CPU_ZERO(&mask);  
      int cpu_nums = sysconf(_SC_NPROCESSORS_CONF);
    if(2 <= cpu_nums)  
    {  
        CPU_SET(i,&mask);  
          
        if(-1 == sched_setaffinity(gettid(),sizeof(&mask),&mask))  
        {  
            return -1;  
        }  
    }  
    return 0;  
}  
  	
void* add(void* x)  
{  
	int sum=0;
    if(-1 == set_cpu(1))  
    {  
        return NULL;  
    }   
          
    for(sum=0;sum<APPLE_MAX_VALUE;sum++)  
    {  
        ((struct apple *)x)->a += sum;  
        ((struct apple *)x)->b += sum;  
    }     
      
    return NULL;  
}  
      
void InsertSort(long int a[], int n)  
{  
	int i;
    for(i= 1; i<n; i++){  
        if(a[i] < a[i-1]){               
            int j= i-1;   
            int x = a[i];         
            a[i] = a[i-1];           
            while(x < a[j]){    
                a[j+1] = a[j];  
                j--;         
            }  
            a[j+1] = x;      
        }       
    }  
}  

int main (int argc, const char * argv[]) {
		// insert code here...
	struct apple test;
	struct orange test1={{0},{0}};
	//int sum; 
	int index;
	pthread_t ThreadA;
	long int time_record[200] = {0};
 	int count;
 	struct  timeval   tv;
	struct  timeval   tv2;
	int j;
	int sum;
	long int average;
	FILE *fp;
	fp = fopen("8.5_output.txt","w");
	average = 0;
	sum = 0;
	count = 0;
	index = 0;
	
	
	if(-1 == set_cpu(0))
	{
		return -1;
	} 

	while(count <= 199)
 	{
 		gettimeofday(&tv,NULL);
 		
		
		pthread_create(&ThreadA,NULL,add,&test);
				
		for(index=0;index<ORANGE_MAX_VALUE;index++)
		{
			sum+=test1.a[index]+test1.b[index];
		}		
		
		pthread_join(ThreadA,NULL);
	
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


