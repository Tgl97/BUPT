#include<iostream>
#include<cctype> 
#include<cmath>
#include<cstdlib>
#include<vector>
#include<sstream>
#include<string>
#include<stack>

using namespace std;

vector<char> VN;	//非终结符号集合 
vector<string> VT;	//终结符号集合 
vector<string> P;	//产生式集合 
int State[50];		//状态栈 
double Value[50];	 
int Type[40];		//integer:1	real:2
int top = 0;

//初始化LR分析表中的action表 
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
	
//初始化LR分析表中的goto表 
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
//将string型转换为int型 
{
	int num;
	stringstream stream;
	stream << str;
	stream >> num;
	return num;
}

int findVN(vector<char> F, char c)
//在VN集中寻找非终结符，返回下标 
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
//在VT集中寻找终结符，返回下标
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
//翻译方案          
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
            //计算小数点后有多少位 
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
//打印State和Value栈内容 
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
	cout << "输入符号串：" << endl;
	cin >> input;
	cout << endl;
	input += "$";
	State[top] = 0;	//将状态0压入State栈中
	
 	cout << "分析过程为：" << endl;
 	while(1)
 	{
 		S = State[top];
 		a = input[ip]; 
 		tmp.clear();
 		
 		cout << "State：" ;
		output(0);
		cout << endl; 
		cout << "Value：" ;
		output(1);
		cout << endl << "输入：" << input.substr(ip) << '\t' << "分析动作：";
		
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
		//移进 	
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
		//规约 
		{
			int r = string_to_int(Action[S][m].substr(1));
			int cnt = 0;
			
			Reduce(r);
			
			string str;
			str = P[r-1].substr(3);
			int k = 0;
			string temp1;
			//计算A->b中|b|的长度，存入cnt 
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
			//从栈顶弹出|b|个元素 
			for (int m = 0; m < cnt; m ++)
			{
				top --;
			}
			
			//_S为栈顶当前状态
			int _S = State[top]; 
			string A = P[r-1].substr(0,1);		
			//将goto[_S,A]压入State栈中	 
			int vn = findVN(VN,A[0]);
			top ++;
			State[top] = Goto[_S][vn];
			//cout << "State[top] = " << State[top] << endl;	 
			//输出A->b 
			cout << "reduce by ：" << P[r-1] << "\tvalue: " << Value[top]; 
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
			cout << "ACC" << "\t表达式的值: " << Value[top];
			cout << "\t表达式的类型: ";
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
			//状态0 2 4 6 7 8 9 期待(或者 num，但输入为+、-、*、/或$时	->缺少运算对象  
			{
				top ++;
				Value[top] = 0;
				State[top] = 5;
				cout << "缺少运算对象！将num压入栈" << endl; 
			}
			else if (Action[S][m][1] == '2')
			//状态0 1 2 4 6 7 8 9 期待运算对象首字符或运算符号，但输入为)	->括号不匹配
			{
				ip ++;
				cout << "括号不匹配！删掉“）”" << endl;
			}
			else if (Action[S][m][1] == '3')
			//状态1 2 10 11 12期待运算符号或)，但输入是(或num	->缺少运算符
			{
				Value[top] = '+';
				State[top] = 6;
				cout << "缺少运算符号！将+压入栈" << endl;
			}
			else if (Action[S][m][1] == '4')
			//状态10 期待运算符号或者)，但输入是$	->缺少右括号
			{
				Value[top] = ')';
				State[top] = 16;
				cout << "缺少右括号！将)压入栈" << endl;
			}
			else if (Action[S][m][1] == '5')
			//状态1 10 期待+或-号，但输入是*或/ 
			{
				Value[top] = '+';
				State[top] = 6;
				cout << "缺少+/-符号！将+压入栈" << endl;
			}
			else if (Action[S][m][1] == '6')
			//归约时输入为(或者num 
			{
				ip ++;
				cout << "缺少运算符号！跳过" << endl;
			}
			else
			//小数点后缺数字 
			{
				top ++;
				Value[top] = 0;
				State[top] = 17;
				cout << "小数点后缺数字！" << endl; 
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
