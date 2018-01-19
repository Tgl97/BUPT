#define ORANGE_MAX_VALUE 100000 //��һ���� 
#define APPLE_MAX_VALUE 100000000
#define MEXCOND 1000000
#include <pthread.h>
#include<stdio.h>
#include<sys/time.h>
#include<unistd.h>

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
 
 
void InsertSort(long int a[], int n)  
{  
    for(int i= 1; i<n; i++){  
        if(a[i] < a[i-1]){               //����i��Ԫ�ش���i-1Ԫ�أ�ֱ�Ӳ��롣С�ڵĻ����ƶ����������  
            int j= i-1;   
            int x = a[i];        //����Ϊ�ڱ������洢������Ԫ��  
            a[i] = a[i-1];           //�Ⱥ���һ��Ԫ��  
            while(x < a[j]){  //�����������Ĳ���λ��  
                a[j+1] = a[j];  
                j--;         //Ԫ�غ���  
            }  
            a[j+1] = x;      //���뵽��ȷλ��  
        }         //��ӡÿ������Ľ��  
    }  
}  
 
void* add(void* x)
{		
	int sum;
	for(sum=0;sum<APPLE_MAX_VALUE;sum++)
	{
		((struct apple *)x)->a += sum;
		((struct apple *)x)->b += sum;	
	}
	//printf("used");
	return NULL;
}
	
int main (int argc, const char * argv[]) {
		// insert code here...
	FILE *fp;
	fp = fopen("8.2step2","w");
	struct apple test;
	struct orange test1;//={{0},{0}};
	pthread_t ThreadA;
	int index;	
	long int time_record[200] = {0};
 	int count = 0;
 	struct  timeval    tv;
 	struct  timeval    tv2;
//	struct  timezone   tz;
//	long int fore_time_usec;
//	long int fore_time_sec;
//	long int after_time_usec;
//	long int after_time_sec;
	long int during;
	long int average;
	int j;
	int sum=0; 
	
	while(count <= 199)
 	{
 		gettimeofday(&tv,NULL);
 		
		pthread_create(&ThreadA,NULL,add,&test);
			
		for(index=0;index<ORANGE_MAX_VALUE;index++)
		{
			sum += test1.a[index]+test1.b[index];
		}		
    	pthread_join(ThreadA,NULL);
    	
    	gettimeofday(&tv2,NULL);

		time_record[count] =  tv2.tv_usec - tv.tv_usec + (tv2.tv_sec - tv.tv_sec )*1000000;
		
	//	if(during < 0)
	//		during = 0 - during;
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
    fclose(fp);
	return 0;
}
