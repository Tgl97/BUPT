#define ORANGE_MAX_VALUE 100000 //��һ���� 
#define APPLE_MAX_VALUE 100000000
#define MEXCOND 1000000
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
	int i;
    for(i= 1; i<n; i++){  
        if(a[i] < a[i-1]){              //����i��Ԫ�ش���i-1Ԫ�أ�ֱ�Ӳ��롣С�ڵĻ����ƶ����������  
            int j= i-1;   
            int x = a[i];        		//����Ϊ�ڱ������洢������Ԫ��  
            a[i] = a[i-1];          	//�Ⱥ���һ��Ԫ��  
            while(x < a[j]){  			//�����������Ĳ���λ��  
                a[j+1] = a[j];  
                j--; 			        //Ԫ�غ���  
            }  
            a[j+1] = x;      			//���뵽��ȷλ��  
        }        						//��ӡÿ������Ľ��  
    }  
}  
 
 int main(int argc, const char *argv[])
 {//insert code here...
	FILE *fp;
	fp = fopen("8.2step1","w");
 	struct apple test;
 	test.a = 0;
 	test.b = 0;
 	struct orange test1;
 	int index;
	int sum;
 	long int time_record[200] = {0};
 	int count = 0;
 	struct  timeval    tv;
 	struct  timeval    tv2;
	struct  timezone   tz;
	long int fore_time_usec;
	long int fore_time_sec;
	long int after_time_usec;
	long int after_time_sec;
	long int during;
	long int average;
	int j;
	
	average = 0;
 	while(count <= 199)
 	{
 		gettimeofday(&tv,NULL);
 		fore_time_usec =  tv.tv_usec;
 		fore_time_sec = tv.tv_sec;
 		for(sum = 0; sum<APPLE_MAX_VALUE;sum++)
 		{
 			test.b+=sum;
 			test.a+=sum;
		}					
		sum = 0;
		for(index = 0;index<ORANGE_MAX_VALUE;index++)
	 	{
	 		test1.a[index] = 0;
	 		test1.b[index] = 0;
		 	sum+= test1.a[index]+test1.b[index];
		}
		gettimeofday(&tv2,NULL);
		after_time_sec = tv2.tv_sec;
		after_time_usec =  tv2.tv_usec;
		during =  after_time_usec - fore_time_usec + (after_time_sec - fore_time_sec)*1000000;
		
		time_record[count] = during;
		fprintf(fp,"tz_dsttime:%ld\n",during);
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
