#include<iostream>
#include<cstdlib>
#include<vector>
#include<sstream>
#include<string>
#include<stack>

using namespace std;

vector<char> VN;	//非终结符号集合 
vector<string> VT;	//终结符号集合 
vector<string> P;	//产生式集合 
stack<int> State;
stack<string> Symble;

//初始化LR分析表中的action表 
string Action[20][20] = 
{
	{"e1","e1","e1","e1","S4","e2","S5","e1"},
	{"S6","S7","e5","e5","e3","e2","e3","ACC"},
	{"R3","R3","S8","S9","e3","R3","e3","R3"},
	{"R6","R6","R6","R6","e6","R6","e6","R6"},
	{"e1","e1","e1","e1","S4","e2","S5","e1"},
	{"R8","R8","R8","R8","e6","R8","e6","R8"},
	{"e1","e1","e1","e1","S4","e2","S5","e1"},
	{"e1","e1","e1","e1","S4","e2","S5","e1"},
	{"e1","e1","e1","e1","S4","e2","S5","e1"},
	{"e1","e1","e1","e1","S4","e2","S5","e1"},
	{"S6","S7","e5","e5","e3","S15","e3","e4"},
	{"R1","R1","S8","S9","e3","R1","e3","R1"},
	{"R2","R2","S8","S9","e3","R2","e3","R2"},
	{"R4","R4","R4","R4","e6","R4","e6","R4"},
	{"R5","R5","R5","R5","e6","R5","e6","R5"},
	{"R7","R7","R7","R7","e6","R7","e6","R7"}
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
	{-1,11,3},
	{-1,12,3},
	{-1,-1,13},
	{-1,-1,14},
	{-1,-1,-1},
	{-1,-1,-1},
	{-1,-1,-1},
	{-1,-1,-1},
	{-1,-1,-1},
	{-1,-1,-1},
};

string CharToString(char c)
//将char型转换为string 
{
	string str;
	stringstream stream;
	stream << c;
	str = stream.str();
	return str;
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

void addToVT(string vt)
//加入VT集 
{
	if (vt.length() > 0)
	{
		if (vt == "num")
		{
			if (finds(VT,vt) < 0)
			{
				VT.push_back(vt);
			}
		}
		else
		{
			for (int j = 0; j < vt.length(); j ++)
			{
				if (finds(VT,CharToString(vt[j])) < 0)
				{
					VT.push_back(CharToString(vt[j]));
				}
			}
		}
	}
}

void getP()
//输入产生式 
{
	string s;
	string temp;
	string vt;
	cout << "用~代替了ε！" << endl;
	cout << "输入产生式：（结束输入over）" << endl;
	cin >> s;
	while (s != "over")
	{
		temp.clear();
		vt.clear();
		if(s.length() > 1)
		{
			for (int i = 0; i < s.length(); i ++)
			{
				if(s[i] >= 'A' && s[i] <= 'Z')
				{
					if (findVN(VN,s[i]) < 0)
					{
						VN.push_back(s[i]);
					}
					if (i != 0)
					{
						temp += s[i];
						addToVT(vt);
						vt.clear();
						//cout << temp << endl;
					}
				}
				else if ((s[i] == '-' && s[i+1] == '>') || (s[i] == '>' && s[i-1] == '-'))
				{
					continue;
				}
				else if (s[i] == '|')
				{
					addToVT(vt);
					vt.clear();
					string j = "->";
					P.push_back(s[0] + j + temp);
					temp.clear();
				}
				else
				{
					vt += s[i];
					temp += s[i];
				}
				
				if (i == s.length() - 1)
				{
					addToVT(vt);
					vt.clear();
					string j = "->";
					P.push_back(s[0] + j + temp);
					temp.clear();
				}	
			}
		}
		cout << "输入产生式：（结束输入over）" << endl;
		cin >> s;
	}

	vector<char>::iterator it;
	vector<string>::iterator it1;
	
	cout << "VN:" ;
	for (it = VN.begin(); it != VN.end(); it ++)
	{
		cout << *it << " ";
	}
	cout << endl;
	
	cout << "VT:" ;
	for (it1 = VT.begin(); it1 != VT.end(); it1 ++)
	{
		cout << *it1 << " ";
	}
	
	cout << endl;
	
	for (it1 = P.begin(); it1 != P.end(); it1 ++)
	{
		cout << *it1 << endl;
	}
	cout << endl;
}
 
void output_stack(int i)
//打印栈中的内容
{
	stack<int> R1;
	stack<string> R2;
	int carry1;
	string carry2;
	if (i == 0) //打印State栈
	{
		while(!State.empty())
		{
			carry1 = State.top();
			State.pop();
			R1.push(carry1);
		}
		while(!R1.empty())
		{
			carry1 = R1.top();
			cout << carry1 << " "; 
			R1.pop();
			State.push(carry1);
		} 
	} 
	else	//打印Symble栈 
	{
		while(!Symble.empty())
		{
			carry2 = Symble.top();
			Symble.pop();
			R2.push(carry2);
			
		}
		while(!R2.empty())
		{
			carry2 = R2.top();
			cout << carry2 << " "; 
			R2.pop();
			Symble.push(carry2);
		} 
	}
}

void LR_analysis()
{
	string input;
	cout << "输入符号串：" << endl;
	cin >> input;
	cout << endl;
	input += "$";
	State.push(0);	//将状态0压入State栈中 
	int i = 0;
	int j;
	int S;
	string temp,carry;
	VT.push_back("$");

	cout << "分析过程为：" << endl;
	while(1)
	{
		S = State.top();	//S为当前State栈栈顶元素 
		j = i;

		while (finds(VT,temp) < 0)	//取得剩余输入串的第一个符号 
		{
			temp += input[j++];
		}
		
		cout << "State：" ;
		output_stack(0);
		cout << endl; 
		cout << "Symble：" ;
		output_stack(1);
		cout << '\t' << "输入：" << input.substr(i) << '\t' << "分析动作：";
		
		if (Action[S][finds(VT,temp)][0] == 'S')
		//移进操作 
		{
			State.push(Action[S][finds(VT,temp)][1] - 48);	//将状态号转为int型 
			Symble.push(temp);

			cout << "Shift " << Action[S][finds(VT,temp)][1] << endl << endl;
			temp.clear();
			i = j;	//指向剩余输入串下一个符号 
		}
		else if (Action[S][finds(VT,temp)][0] == 'R')
		//归约操作 
		{
			int r = Action[S][finds(VT,temp)][1] - 48;
			int cnt = 0;
			
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
				State.pop();
				//Symble.pop();
			}
			
			//_S为栈顶当前状态
			int _S = State.top();
			//将A压入Symble栈中	 
			string A = P[r-1].substr(0,1);
			Symble.push(A);		
			//将goto[_S,A]压入State栈中	 
			int vn = findVN(VN,A[0]);
			State.push(Goto[_S][vn]);	 
			//输出A->b 
			cout << "reduce by ：" << P[r-1] << endl << endl ;	
		}
		else if (Action[S][finds(VT,temp)][0] == 'A')
		//接收 
		{
			cout << "ACC" << endl;
			break;
		}
		else if (Action[S][finds(VT,temp)][0] == 'e')
		//错误处理 
		{
			cout << "error! ";
			//break;

			if (Action[S][finds(VT,temp)][1] == '1')
			//状态0 2 4 6 7 8 9 期待(或者 num，但输入为+、-、*、/或$时	->缺少运算对象  
			{
				Symble.push("num");
				State.push(5);
				cout << "缺少运算对象！将num压入栈" << endl; 
			}
			else if (Action[S][finds(VT,temp)][1] == '2')
			//状态0 1 2 4 6 7 8 9 期待运算对象首字符或运算符号，但输入为)	->括号不匹配
			{
				i ++;
				cout << "括号不匹配！删掉“）”" << endl;
			}
			else if (Action[S][finds(VT,temp)][1] == '3')
			//状态1 2 10 11 12期待运算符号或)，但输入是(或num	->缺少运算符
			{
				Symble.push("+");
				State.push(6);
				cout << "缺少运算符号！将+压入栈" << endl;
			}
			else if (Action[S][finds(VT,temp)][1] == '4')
			//状态10 期待运算符号或者)，但输入是$	->缺少右括号
			{
				Symble.push(")");
				State.push(15);
				cout << "缺少右括号！将)压入栈" << endl;
			}
			else if (Action[S][finds(VT,temp)][1] == '5')
			//状态1 10 期待+或-号，但输入是*或/ 
			{
				Symble.push("+");
				State.push(6);
				cout << "缺少+/-符号！将+压入栈" << endl;
			}
			else
			//归约时输入为(或者num 
			{
				if (temp == "(")
				{
					i ++;
				}
				else
				{
					i = i + 3;
				}
				cout << "缺少运算符号！跳过" << endl;
			}
		}
		temp.clear();
	}
	cout << endl;
	cout << "finished!" << endl;
}

int main()
{
	getP();
	LR_analysis();
	system("pause");
	return 0;
} 

