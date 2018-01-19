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
char S;   			//��ʼ����S 
string FIRST[20];	//ÿ�����ս���ŵ�FIRST�� 
string FOLLOW[20];	//ÿ�����ս���ŵ�FOLLOW�� 
int num = 0;		//�ܹ�����ʽ�ĸ��� 

struct Pro			//�ṹ�壬�洢����ʽ 
{
	char left;
	string right;
}pro[100];

string charToString(char c)
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

int findVT(vector<string> F, string c)
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
			if (findVT(VT,vt) < 0)
			{
				VT.push_back(vt);
			}
		}
		else
		{
			for (int j = 0; j < vt.length(); j ++)
			{
				if (findVT(VT,charToString(vt[j])) < 0)
				{
					VT.push_back(charToString(vt[j]));
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
}

void split(string temp, vector<string> &str)
//�����ַ�����ʾ��FIRST����FOLLOW�����ո�ָ������浽vector�� 
{
	str.clear();
	int pos = 0;
	while(1)
	{
		string temp_s = "";
		pos = temp.find(" ");
		if(pos == temp.npos)
		{
			temp_s = temp.substr(0,temp.length());
			str.push_back(temp_s);
			break;
		}
		temp_s = temp.substr(0,pos);
		temp.erase(0,pos+1);
		str.push_back(temp_s);
	}
	str.pop_back();
}

string get_FIRST(int index)
//��ȡFIRST�� 
{
	char c = VN[index];
	int m = 0;
	string right_s;
	vector<string> str;
	vector<string>::iterator it;
	for (int i = 0; i < num; i ++)
	{
		if (pro[i].left == c)
		{
			if (pro[i].right[0] >= 'A' && pro[i].right[0] <= 'Z')
			//���ʽ�ұߵ�һ��Ϊ���ս���ţ�X->Y...��� 
			{
				if (findVN(VN,pro[i].right[0]) >= 0)
				//�ڷ��ս������ 
				{
					string temp = get_FIRST(findVN(VN,pro[i].right[0]));	//tempΪ�ұߵ�һ�����ս����first��
					//cout << temp << endl; 
					str.clear();
					split(temp,str);
					//��first(Y)�еķ�~Ԫ�ؼ���first(X)�� 
					for (it = str.begin(); it != str.end(); it++)
					{
						if (*it != "~" && FIRST[index].find(*it) == FIRST[index].npos)
						{
							FIRST[index] += *it;
							FIRST[index] += ' ';
						}
					}
					int count = 1;
					if (count < pro[i].right.length())
					{
						while (temp.find('~') != temp.npos && count < pro[i].right.length())
						//���first(Y)����~Ԫ�أ� 
						{
							//cout << pro[i].right[count] << endl;
							if (findVT(VT,charToString(pro[i].right[count])) >= 0)
							{
								if (FIRST[index].find(pro[i].right[count]) == FIRST[index].npos)
								{
									FIRST[index] += pro[i].right[count];
									FIRST[index] += ' ';
									break;
								}
							}
							else
							{
								temp = get_FIRST(findVN(VN,pro[i].right[count]));
								count ++;
							}
							
							str.clear();
							split(temp,str);
							for (it = str.begin(); it != str.end(); it++)
							{
								if (*it != "~" && FIRST[index].find(*it) == FIRST[index].npos)
								{
									FIRST[index] += *it;
									FIRST[index] += ' ';
								}
							}
						}
					}
				}
			}
			else
			//���ʽ�ұߵ�һ��Ϊ�ս���� 
			{
				if (findVT(VT,charToString(pro[i].right[0])) >= 0)
				//X->a...������a����VT 
				{
					if (FIRST[index].find(pro[i].right[0]) == FIRST[index].npos)
					{
						FIRST[index] += pro[i].right[0];
						FIRST[index] += ' ';
					}	
				}
				else
				//����Ϊ�ս����Ϊnum����� 
				{
					while (right_s != "num")
					{
						right_s += pro[i].right[m];
						m++;
					}
					if (FIRST[index].find("num") == FIRST[index].npos)
					{
						FIRST[index] += right_s;
						FIRST[index] += ' ';
					}	
					right_s.clear();
				}
			}	
		}
	}
	return FIRST[index];
}

void get_follow(vector<string> &record)
{
	int i, j, m;
	vector<string> str;
	for (i = 0; i < num; i ++)
	{
		for (j = 0; j < pro[i].right.length(); j ++)
		{
			//�ҵ�һ�����ս����B 
			int n = findVN(VN,pro[i].right[j]);   //VN��λ�� 
			if (n >= 0)
			{
				if (j < pro[i].right.length() - 1)
				//B��Ϊ���һ������ 
				{
					if (findVT(VT,charToString(pro[i].right[j+1])) >= 0)
					//B����ӵ����ս����A->aBb������follow(B)�� 
					{
						if (FOLLOW[n].find(pro[i].right[j+1]) == FOLLOW[n].npos)
						{
							FOLLOW[n] += pro[i].right[j+1];
							FOLLOW[n] += ' ';
						}
					}
					//B����ӵ��Ƿ��ս����A->aBC����first(C)�ķ�~���ż���follow(B)�� 
					else if (findVN(VN,pro[i].right[j+1]) >= 0)
					{
						string temp = FIRST[findVN(VN,pro[i].right[j+1])];
						int flag = 0;
						vector<string>::iterator it;
						str.clear();
						split(temp,str);
						for (it = str.begin(); it != str.end(); it ++)
						{
							if (*it == "~")
							//��first(C)����~Ԫ�أ���follow(A)����Ԫ�ؼ���follow(B)�� 
							{
								flag = 1;
							} 
							if (*it != "~" && FOLLOW[n].find(*it) == FOLLOW[n].npos) 
							{
								FOLLOW[n] += *it;
								FOLLOW[n] += ' ';
							}
						}
						
						if (flag == 1)
						{
							string r ;
							r += pro[i].left;
							r += pro[i].right[j];
							record.push_back(r);
						}
					}
				}
				else
				//����ʽΪA->aB�������ֱ�ӽ�follow(A)�е�Ԫ�ؼ���follow(B)�� 
				{
					string r ;
					r += pro[i].left;
					r += pro[i].right[j];
					record.push_back(r);
				}
			}
		}
	}
}

void get_FOLLOW()
//��ȡFOLLOW�� 
{
	cout << "��������ķ�����ʼ���ţ�" << endl;
	cin >> S;
	FOLLOW[findVN(VN,S)] += "$ ";
	vector<string> record;
	vector<string> str;
	get_follow(record);
	vector<string>::iterator it;
	vector<string>::iterator it1;
	for (it = record.begin(); it != record.end(); it ++)
	{
		string s = (*it);
		char A = s[0];
		char B = s[1];
		string temp = FOLLOW[findVN(VN,A)];
		str.clear();
		split(temp,str);
		for (it1 = str.begin(); it1 != str.end(); it1 ++)
		{
			if (FOLLOW[findVN(VN,B)].find(*it1) == FOLLOW[findVN(VN,B)].npos) 
			{
				FOLLOW[findVN(VN,B)] += *it1;
				FOLLOW[findVN(VN,B)] += ' ';
			}
		}
	}
}

void predict_table(string M[][10])
//����Ԥ�����������M[][]�� 
{
	string s;
	s += '$';
	VT.push_back(s);
	vector<string> First;
	vector<string> Follow;
	vector<string>::iterator it;
	bool flag = false;
	int i,j,a,b;
	
	for (i = 0; i < num; i ++)
	{
		flag = false;
		a = findVN(VN,pro[i].left);		//���ʽ��߷��ս����VN�е�λ��
		for (j = 0; j < pro[i].right.length(); j ++)
		{
			int vn = findVN(VN,pro[i].right[j]);
			int vt = findVT(VT,pro[i].right.substr(0,j+1));
			if (vn >= 0)
			{
				split(FIRST[vn],First);
				for (it = First.begin(); it != First.end(); it ++)
				{
					if (*it == "~")
					{
						flag = true;
					}
					b = findVT(VT,*it);
					M[a][b] += pro[i].left;
					M[a][b] += "->" + pro[i].right ;
				}
				if (!flag)
				{
					break;
				}
			}
			else if (vn < 0 && vt >= 0)
			{
				M[a][vt] += pro[i].left;
				M[a][vt] +=	"->" + pro[i].right ;
				if (VT[vt] == "~")
				{
					flag = true;
				}
				break;
			}
		}
		
		if (flag)
		{
			split(FOLLOW[a],Follow);
			for (it = Follow.begin(); it != Follow.end(); it ++)
			{
				b = findVT(VT,*it);
				M[a][b] += pro[i].left;
				M[a][b] += "->" + pro[i].right ;
			}
		}
	}
	
	for (i = 0; i < VN.size(); i ++)
	{
		split(FOLLOW[i],Follow);
		for (it = Follow.begin(); it != Follow.end(); it ++)
		{
			int j = findVT(VT,*it);
			if (M[i][j].empty())
			{
				M[i][j] = "synch";
			}
		}
	}
	
	cout << "Ԥ�������Ϊ��" << endl;
	cout << '\t' ;
	for (j = 0; j < VT.size(); j ++)
	{
		if (VT[j] != "~")
		{
			cout << VT[j] << '\t';
		}
	}
	cout << endl;
	for (i = 0; i < VN.size(); i ++)
	{
		cout << VN[i] << '\t';
		for (j = 0; j < VT.size(); j ++)
		{
			 if (VT[j] != "~")
			 {
			 	cout << M[i][j] << '\t';
			 }
		}
		cout << endl;
	}
		
}

void predict_anylsis(string M[][10])
{
	string input;
	int i = 0;
	int vt,vn;
	cout << "������Ŵ���" << endl;
	cin >> input;
	cout << endl;
	
	input += "$";
	stack<string> St;
	St.push("$");
	string in_stack;
	in_stack += S;
	St.push(in_stack);
	
	cout << "��������Ϊ��" << endl;
	cout << "ջ��" << S << " $" << '\t' << "���룺" << input << '\t' << "�����" << endl; 
	while(St.top() != "$")
	{
		vt = findVT(VT,St.top());
		vn = findVN(VN,St.top()[0]);
		string carry;
		stack<string> R;
		if (vt >= 0)
		{
			//cout << St.top() << endl;
			if (St.top() == input.substr(i,St.top().length()))
			{
				i += St.top().length();
				St.pop();
			}
			else  //ջ������Ϊ�ս���ţ��뵱ǰ������Ų�ƥ�� 
			{
				cout << "error! ջ��������������Ų�ƥ�䣬��ջ�е����ս��" << endl;
				St.pop();
			}
			//ջ�����
			cout << "ջ��"; 
			while(!St.empty())
			{
				carry = St.top();
				St.pop();
				R.push(carry);
				cout << carry << " "; 
			}
			while(!R.empty())
			{
				carry = R.top();
				R.pop();
				St.push(carry);
			}
			//���봮
			cout << '\t' << "���룺"; 
			cout << input.substr(i);
			//���
			cout << '\t' << "�����" << endl; 
			//system("pause"); 
		}
		else if (vt < 0 && vn >= 0)
		{
			string tmp;
			tmp += input[i];
			int j = findVT(VT,tmp);
			int k = i;
			while (j < 0)
			{
				tmp += input[++k];
				j = findVT(VT,tmp);
			}
			//cout << "tmp: " << tmp << endl;
			tmp.clear();
			
			string right;
			if (!M[vn][j].empty())
			{
				if (M[vn][j] != "synch")
				{
					right = M[vn][j].substr(3);
				}
				else
				{
					right = "synch";
				} 
			}
			else
			{
				right.clear();
			}
			
			if (!right.empty() && right != "synch")
			{
				St.pop();
				for (int m = 0; m < right.length(); m ++)
				{
					tmp += right[m];
					int n1 = findVT(VT,tmp);
					int n2 = findVN(VN,tmp[0]);
					if (n1 >= 0 || n2 >= 0)
					{
						if (tmp != "~")
						{
							R.push(tmp);
						} 
						tmp.clear();
					}
				}
			
				while(!R.empty())
				{
					St.push(R.top());
					R.pop();
				}
				
			}
			else if (right.empty()) 
			{
				i += VT[j].length();
				cout << "error! M[][]Ϊ�գ���ǰ�ƶ�ָ��" << endl;
			}
			else
			{
				St.pop();
				cout << "error! M[][]Ϊsynch����ջ�е������ս��" << endl;
			}
			
			//ջ�����
			cout << "ջ��"; 
			while(!St.empty())
			{
				carry = St.top();
				St.pop();
				R.push(carry);
				cout << carry << " "; 
			}
			while(!R.empty())
			{
				carry = R.top();
				R.pop();
				St.push(carry);
			}
			//���봮
			cout << '\t' << "���룺"; 
			cout << input.substr(i);
			//���
			cout << '\t' << "�����"; 
			cout << M[vn][j] << endl;
			//system("pause"); 
		}
	}
}

int main()
{
	getP();
	string M[10][10];
	vector<string>::iterator it;
	int i;
	for (it = P.begin(); it != P.end(); it ++)
	{
		pro[num].left = (*it)[0];
		pro[num].right = (*it).substr(3);
		num ++;
	}
	
	cout << "FIRST����" << endl;
	for(i = 0; i < VN.size(); i++)
	{
		cout << VN[i] << ": " << get_FIRST(i) << endl;
	}
	cout << endl;
	
	get_FOLLOW();
	cout << "FOLLOW����" << endl;
	for(i = 0; i < VN.size(); i++)
	{
		cout << VN[i] << ": " << FOLLOW[i] << endl;
	}
	cout << endl;
	
	predict_table(M);
	cout << endl;
	
	predict_anylsis(M);
	system("pause");
	return 0;
}
