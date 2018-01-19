#include<iostream>
#include<cctype> 
#include<cmath>
#include<cstdlib>
#include<vector>
#include<sstream>
#include<string>
#include<stack>

using namespace std;

vector<char> VN;	//���ս���ż��� 
vector<string> VT;	//�ս���ż��� 
vector<string> P;	//����ʽ���� 
int State[50];		//״̬ջ 
double Value[50];	 
int Type[40];		//integer:1	real:2
int top = 0;

//��ʼ��LR�������е�action�� 
string Action[20][20] = 
{
	{"e1","e1","e1","e1","S4","e2","e1","S5","e1"},
	{"S6","S7","e5","e5","e3","e2","e1","e3","ACC"},
	{"R3","R3","S8","S9","e3","R3","e1","e3","R3"},	
	{"R6","R6","R6","R6","e6","R6","e1","e6","R6"},
	{"e1","e1","e1","e1","S4","e2","e1","S5","e1"},
	{"R8","R8","R8","R8","e6","R8","S11","e6","R8"},
	{"e1","e1","e1","e1","S4","e2","e1","S5","e1"},		
	{"e1","e1","e1","e1","S4","e2","e1","S5","e1"},
	{"e1","e1","e1","e1","S4","e2","e1","S5","e1"},
	{"e1","e1","e1","e1","S4","e2","e1","S5","e1"},
	{"S6","S7","e5","e5","e3","S16","e1","e3","e4"},
	{"e7","e7","e7","e7","e7","e7","e7","S17","e7"},
	{"R1","R1","S8","S9","e3","R1","e1","e3","R1"},
	{"R2","R2","S8","S9","e3","R2","e1","e3","R2"},
	{"R4","R4","R4","R4","e6","R4","e1","e6","R4"},
	{"R5","R5","R5","R5","e6","R5","e1","e6","R5"},
	{"R7","R7","R7","R7","e6","R7","e1","e6","R7"},
	{"R9","R9","R9","R9","e6","R9","e1","e6","R9"}
};
	
//��ʼ��LR�������е�goto�� 
int Goto[20][4] = 
{
	{1,2,3},
	{-1,-1,-1},
	{-1,-1,-1},	
	{-1,-1,-1},
	{10,2,3},
	{-1,-1,-1},
	{-1,12,3},
	{-1,13,3},
	{-1,-1,14},
	{-1,-1,15},
	{-1,-1,-1},
	{-1,-1,-1},
	{-1,-1,-1},
	{-1,-1,-1},
	{-1,-1,-1},
	{-1,-1,-1},
};

void Init()
{
	VN.push_back('E');
	VN.push_back('T');
	VN.push_back('F');
	VT.push_back("+");
	VT.push_back("-");
	VT.push_back("*");
	VT.push_back("/");
	VT.push_back("(");
	VT.push_back(")");
	VT.push_back(".");
	VT.push_back("num");
	P.push_back("E->E+T");
	P.push_back("E->E-T");
	P.push_back("E->T");
	P.push_back("T->T*F");
	P.push_back("T->T/F");
	P.push_back("T->F");
	P.push_back("F->(E)");
	P.push_back("F->num");
	P.push_back("F->num.num");
}

int string_to_int(string str)
//��string��ת��Ϊint�� 
{
	int num;
	stringstream stream;
	stream << str;
	stream >> num;
	return num;
}

int findVN(vector<char> F, char c)
//��VN����Ѱ�ҷ��ս���������±� 
{
	vector<char>::iterator it;
	for (it = F.begin(); it != F.end(); it ++)
	{
		if ((*it) == c)
		{
			return it - F.begin();
		}
	}
	return -1;
} 

int finds(vector<string> F, string c)
//��VT����Ѱ���ս���������±�
{
	vector<string>::iterator it;
	for (it = F.begin(); it != F.end(); it ++)
	{
		if ((*it) == c)
		{
			return it - F.begin();
		}
	}
	return -1;
} 

void Reduce(int chioce) 
//���뷽��          
{
	int i;
	double j;

	switch (chioce)
	{
		case 1:
			Value[top-2] = Value[top-2] + Value[top];
			if (Type[top] == 2 || Type[top-2] == 2)
			{
				Type[top-2] = 2;
			}
			else
			{
				Type[top-2] = 1;
			}
			break;

		case 2:
			Value[top-2] = Value[top-2] - Value[top];
			if (Type[top] == 2 || Type[top-2] == 2)
			{
				Type[top-2] = 2;
			}
			else
			{
				Type[top-2] = 1;
			}
			break;
			
		case 3:
			Value[top] = Value[top];
			Type[top] = Type[top];
			break;
			
		case 4:
			Value[top-2] = Value[top-2] * Value[top];
			if (Type[top] == 2 || Type[top-2] == 2)
			{
				Type[top-2] = 2;
			}
			else
			{
				Type[top-2] = 1;
			}
			break;
			
		case 5:
			if (Type[top] == 2 || Type[top-2] == 2 || (int)Value[top-2]%(int)Value[top] != 0)
			{
				Type[top-2] = 2;
			}
			else
			{
				Type[top-2] = 1;
			}
			Value[top-2] = Value[top-2] / Value[top];
			break;
			
		case 6:
			Value[top] = Value[top];
			Type[top] = Type[top];
			break;
			
		case 7:
			Value[top-2] = Value[top-1];
			Type[top-2] = Type[top-1];
			break;
			
		case 8:
			Value[top] = Value[top];
			Type[top] = 1;
			break;
			
		case 9:
            j = Value[top];
            //����С������ж���λ 
			for (i = 0; j >= 1; i ++)
			{
				j = j / 10;
			}
               
			Value[top-2] = Value[top-2] + Value[top] * pow(0.1,i);
			Type[top-2] = 2;
			break;
			
		default:
			break;
    }
}

void output(int choice)
//��ӡState��Valueջ���� 
{
	int i;
	if (choice == 0)
	{
		for (i = 0; i <= top; i ++)
		{
			cout << State[i] << " " ;
		}
	}
	else
	{
		for (i = 0; i <= top; i ++)
		{
			cout << Value[i] << " " ;
		}
	}
}

void LR_analysis()
{
	int i, j, m;
	int ip = 0;
	int length = 0;
	int S;
	char a;
	string tmp;
	VT.push_back("$");
	
	string input;
	cout << "������Ŵ���" << endl;
	cin >> input;
	cout << endl;
	input += "$";
	State[top] = 0;	//��״̬0ѹ��Stateջ��
	
 	cout << "��������Ϊ��" << endl;
 	while(1)
 	{
 		S = State[top];
 		a = input[ip]; 
 		tmp.clear();
 		
 		cout << "State��" ;
		output(0);
		cout << endl; 
		cout << "Value��" ;
		output(1);
		cout << endl << "���룺" << input.substr(ip) << '\t' << "����������";
		
		if (a == '+' || a == '-' || a == '*' || a == '/' ||
			a == '(' || a == ')' || a == '.' || a == '$')
		{
			length = 1;
		}
		else
		{
			j = ip;
			length = 0;
			i = 0;
			while(isdigit(a))
			{
				i = i * 10 + a - '0';
				length ++;
				j ++;
				a = input[j];
			}
			a = 'n';
		}
		
		if (a == 'n')
		{
			tmp = "num";
		}
		else
		{
			tmp += a;
		}

		m = finds(VT,tmp);
	
		if (Action[S][m][0] == 'S')	
		//�ƽ� 	
		{
			top ++;
			if (tmp == "num")
			{
				Value[top] = i;
			}
			else
			{
				Value[top] = 0;
			}
			State[top] = string_to_int(Action[S][m].substr(1));
			ip += length;
			cout << "Shift " << Action[S][m].substr(1) << endl << endl;
			tmp.clear();
		}
		else if (Action[S][m][0] == 'R')
		//��Լ 
		{
			int r = string_to_int(Action[S][m].substr(1));
			int cnt = 0;
			
			Reduce(r);
			
			string str;
			str = P[r-1].substr(3);
			int k = 0;
			string temp1;
			//����A->b��|b|�ĳ��ȣ�����cnt 
			while (k < str.length())
			{
				temp1 += str[k];
				if (findVN(VN,str[k]) >= 0 || finds(VT,temp1) >= 0)
				{
					cnt ++;
					temp1.clear();
				}
				k ++;
			}
			temp1.clear();
			//cout << cnt << endl;
			//��ջ������|b|��Ԫ�� 
			for (int m = 0; m < cnt; m ++)
			{
				top --;
			}
			
			//_SΪջ����ǰ״̬
			int _S = State[top]; 
			string A = P[r-1].substr(0,1);		
			//��goto[_S,A]ѹ��Stateջ��	 
			int vn = findVN(VN,A[0]);
			top ++;
			State[top] = Goto[_S][vn];
			//cout << "State[top] = " << State[top] << endl;	 
			//���A->b 
			cout << "reduce by ��" << P[r-1] << "\tvalue: " << Value[top]; 
			cout << "\ttype: ";
			if (Type[top] == 1)
			{
				cout << "integer";
			}
			else
			{
				cout << "real";
			}
			cout << endl << endl ;	
			
		}
		else if (Action[S][m][0] == 'A')
		{
			cout << "ACC" << "\t���ʽ��ֵ: " << Value[top];
			cout << "\t���ʽ������: ";
			if (Type[top] == 1)
			{
				cout << "integer";
			}
			else
			{
				cout << "real";
			}
			cout << endl;
			break;
		}
		else if (Action[S][m][0] == 'e')
		{
			cout << "error!" << endl;
			
			if (Action[S][m][1] == '1')
			//״̬0 2 4 6 7 8 9 �ڴ�(���� num��������Ϊ+��-��*��/��$ʱ	->ȱ���������  
			{
				top ++;
				Value[top] = 0;
				State[top] = 5;
				cout << "ȱ��������󣡽�numѹ��ջ" << endl; 
			}
			else if (Action[S][m][1] == '2')
			//״̬0 1 2 4 6 7 8 9 �ڴ�����������ַ���������ţ�������Ϊ)	->���Ų�ƥ��
			{
				ip ++;
				cout << "���Ų�ƥ�䣡ɾ��������" << endl;
			}
			else if (Action[S][m][1] == '3')
			//״̬1 2 10 11 12�ڴ�������Ż�)����������(��num	->ȱ�������
			{
				Value[top] = '+';
				State[top] = 6;
				cout << "ȱ��������ţ���+ѹ��ջ" << endl;
			}
			else if (Action[S][m][1] == '4')
			//״̬10 �ڴ�������Ż���)����������$	->ȱ��������
			{
				Value[top] = ')';
				State[top] = 16;
				cout << "ȱ�������ţ���)ѹ��ջ" << endl;
			}
			else if (Action[S][m][1] == '5')
			//״̬1 10 �ڴ�+��-�ţ���������*��/ 
			{
				Value[top] = '+';
				State[top] = 6;
				cout << "ȱ��+/-���ţ���+ѹ��ջ" << endl;
			}
			else if (Action[S][m][1] == '6')
			//��Լʱ����Ϊ(����num 
			{
				ip ++;
				cout << "ȱ��������ţ�����" << endl;
			}
			else
			//С�����ȱ���� 
			{
				top ++;
				Value[top] = 0;
				State[top] = 17;
				cout << "С�����ȱ���֣�" << endl; 
			}
			
			cout << endl;
		} 
 	}
 	
 	return;
} 

int main()
{
	Init();
	LR_analysis();
	system("pause");
	return 0;
} 
