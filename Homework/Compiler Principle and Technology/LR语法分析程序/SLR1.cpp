#include<iostream>
#include<cstdlib>
#include<vector>
#include<sstream>
#include<string>
#include<stack>

using namespace std;

vector<char> VN;	//���ս���ż��� 
vector<string> VT;	//�ս���ż��� 
vector<string> P;	//����ʽ���� 
stack<int> State;
stack<string> Symble;

//��ʼ��LR�������е�action�� 
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

//��ʼ��LR�������е�goto�� 
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
//��char��ת��Ϊstring 
{
	string str;
	stringstream stream;
	stream << c;
	str = stream.str();
	return str;
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

void addToVT(string vt)
//����VT�� 
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
//�������ʽ 
{
	string s;
	string temp;
	string vt;
	cout << "��~�����˦ţ�" << endl;
	cout << "�������ʽ������������over��" << endl;
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
		cout << "�������ʽ������������over��" << endl;
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
//��ӡջ�е�����
{
	stack<int> R1;
	stack<string> R2;
	int carry1;
	string carry2;
	if (i == 0) //��ӡStateջ
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
	else	//��ӡSymbleջ 
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
	cout << "������Ŵ���" << endl;
	cin >> input;
	cout << endl;
	input += "$";
	State.push(0);	//��״̬0ѹ��Stateջ�� 
	int i = 0;
	int j;
	int S;
	string temp,carry;
	VT.push_back("$");

	cout << "��������Ϊ��" << endl;
	while(1)
	{
		S = State.top();	//SΪ��ǰStateջջ��Ԫ�� 
		j = i;

		while (finds(VT,temp) < 0)	//ȡ��ʣ�����봮�ĵ�һ������ 
		{
			temp += input[j++];
		}
		
		cout << "State��" ;
		output_stack(0);
		cout << endl; 
		cout << "Symble��" ;
		output_stack(1);
		cout << '\t' << "���룺" << input.substr(i) << '\t' << "����������";
		
		if (Action[S][finds(VT,temp)][0] == 'S')
		//�ƽ����� 
		{
			State.push(Action[S][finds(VT,temp)][1] - 48);	//��״̬��תΪint�� 
			Symble.push(temp);

			cout << "Shift " << Action[S][finds(VT,temp)][1] << endl << endl;
			temp.clear();
			i = j;	//ָ��ʣ�����봮��һ������ 
		}
		else if (Action[S][finds(VT,temp)][0] == 'R')
		//��Լ���� 
		{
			int r = Action[S][finds(VT,temp)][1] - 48;
			int cnt = 0;
			
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
				State.pop();
				//Symble.pop();
			}
			
			//_SΪջ����ǰ״̬
			int _S = State.top();
			//��Aѹ��Symbleջ��	 
			string A = P[r-1].substr(0,1);
			Symble.push(A);		
			//��goto[_S,A]ѹ��Stateջ��	 
			int vn = findVN(VN,A[0]);
			State.push(Goto[_S][vn]);	 
			//���A->b 
			cout << "reduce by ��" << P[r-1] << endl << endl ;	
		}
		else if (Action[S][finds(VT,temp)][0] == 'A')
		//���� 
		{
			cout << "ACC" << endl;
			break;
		}
		else if (Action[S][finds(VT,temp)][0] == 'e')
		//������ 
		{
			cout << "error! ";
			//break;

			if (Action[S][finds(VT,temp)][1] == '1')
			//״̬0 2 4 6 7 8 9 �ڴ�(���� num��������Ϊ+��-��*��/��$ʱ	->ȱ���������  
			{
				Symble.push("num");
				State.push(5);
				cout << "ȱ��������󣡽�numѹ��ջ" << endl; 
			}
			else if (Action[S][finds(VT,temp)][1] == '2')
			//״̬0 1 2 4 6 7 8 9 �ڴ�����������ַ���������ţ�������Ϊ)	->���Ų�ƥ��
			{
				i ++;
				cout << "���Ų�ƥ�䣡ɾ��������" << endl;
			}
			else if (Action[S][finds(VT,temp)][1] == '3')
			//״̬1 2 10 11 12�ڴ�������Ż�)����������(��num	->ȱ�������
			{
				Symble.push("+");
				State.push(6);
				cout << "ȱ��������ţ���+ѹ��ջ" << endl;
			}
			else if (Action[S][finds(VT,temp)][1] == '4')
			//״̬10 �ڴ�������Ż���)����������$	->ȱ��������
			{
				Symble.push(")");
				State.push(15);
				cout << "ȱ�������ţ���)ѹ��ջ" << endl;
			}
			else if (Action[S][finds(VT,temp)][1] == '5')
			//״̬1 10 �ڴ�+��-�ţ���������*��/ 
			{
				Symble.push("+");
				State.push(6);
				cout << "ȱ��+/-���ţ���+ѹ��ջ" << endl;
			}
			else
			//��Լʱ����Ϊ(����num 
			{
				if (temp == "(")
				{
					i ++;
				}
				else
				{
					i = i + 3;
				}
				cout << "ȱ��������ţ�����" << endl;
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

