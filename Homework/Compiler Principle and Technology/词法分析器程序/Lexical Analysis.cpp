#include<iostream>
#include<cstdlib>
#include<cstring> 
#include<vector>
#include<fstream>
#include<sstream>

#define KEYWORD    0  //�ؼ��� 
#define ID         1  //��ʶ�� 
#define RELOP      2  //����� 
#define NUM        3  //���� 
#define LITERAL    4  //�ַ��� 
#define PUNC       5  //��� 
#define ANNOTATION 6  //ע�� 
#define MAXBUF     82 //���������� 
#define L_END      40 //������������ֹλ 
#define R_END      81 //�������Ұ����ֹλ 
#define START      0  //��ʼָ�� 

using namespace std;

vector<string> key;   //C���ԵĹؼ��ֱ� 

class Lex 
{
	public:
		string file;		 //��Ҫ�ʷ��������ļ��� 
		ifstream in;
		char buffer[MAXBUF]; //���뻺���� 
		int l_end, r_end;    //���Ұ������յ�
		int forward;         //ǰ��ָ��
		bool flag_l,flag_r;
		vector<string> id;   //�Լ�����ı�ʶ����
		vector<string> keyword;
		vector<string> num;
		vector<string> literal;  //""�е��ַ��� 
		char C;              //���뵱ǰ�ַ�
		int linenum, wordnum, charnum;   //���������������ַ���
		string token;          //��ŵ�ǰ���ַ���
		string change_to_string(char c);  //��char->string 
		string change_to_string(int c);	  //��int->string 
		void get_char();	//�����뻺�����ж�һ���ַ�������C�У�forwardָ����һ��
		void get_nbc();		//���C���ַ��Ƿ�Ϊ�ո��������get_char()��ֱ��C����ǿո��ַ� 
		void retract();		//��ǰָ�����һλ
		void initial();		//��ʼ��
		void fillBuffer(int a);	//��仺������0��1�� 
		void function();	//��������
		void output(int type,string out);		//��<�Ǻţ�����>����ʽ���
		void error();		//�������󣬴�ӡ��������� 
		void operation(string f);	//����main()���� 
};

void init_key()
{
	key.clear();
	key.push_back("auto");		key.push_back("double");	key.push_back("int");		key.push_back("struct");
	key.push_back("break");		key.push_back("else");		key.push_back("long");		key.push_back("switch");
	key.push_back("case");		key.push_back("enum");		key.push_back("register");	key.push_back("typdef");
	key.push_back("char");		key.push_back("extern");	key.push_back("return");	key.push_back("union");
	key.push_back("const");		key.push_back("float");		key.push_back("short");		key.push_back("unsigned");
	key.push_back("continue");	key.push_back("for");		key.push_back("signed");	key.push_back("void");
	key.push_back("default");	key.push_back("goto");		key.push_back("sizeof");	key.push_back("volatile");
	key.push_back("do");		key.push_back("if");		key.push_back("static");	key.push_back("while");
}

//�ж��ַ��Ƿ�Ϊ��ĸ 
bool isletter(char c)
{
	if(c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z')
	{
		return true;
	}
	else
	{
		return false;
	}
}
//�ж��ַ��Ƿ�Ϊ���� 
bool isdigit(char c)
{
	if(c >= '0' && c <= '9')
	{
		return true;
	}
	else
	{
		return false;
	}
}
//�жϱ�ʶ���Ƿ���C���Թؼ��ֱ��� 
int iskey(string token)
{
	vector<string>::iterator it;
	for(it = key.begin(); it != key.end(); it++)
	{
		if(token == (*it))
		{
			return it-key.begin(); 
		}
	}
	return -1;
}

string Lex::change_to_string(char c)
{
	string str;
	stringstream stream;
	stream << c;
	str = stream.str();
	return str;
}

string Lex::change_to_string(int c)
{
	string str;
	stringstream stream;
	stream << c;
	str = stream.str();
	return str;
}
//��ʼ�� 
void Lex::initial()
{
	id.clear();	     //��ձ�ʶ���� 
	l_end = L_END;	 //��ʼ�������� 
	r_end = R_END;
	forward = 0;
	flag_l = flag_r = false;
	buffer[l_end] = buffer[r_end] = EOF;
	fillBuffer(0);
	linenum = wordnum = charnum = 0;  //��ʼ�����������������ַ��� 
}

void Lex::fillBuffer(int a)
{
	if(a == 0)    //��仺��������
	{
		if(flag_l == false)
		{
			in.read(buffer,l_end);
			if(in.gcount() != l_end)
			{
				buffer[in.gcount()] = EOF;
			}
		}
		else
		{
			flag_l = false;
		}
	} 
	else
	{
		if(flag_r == false)
		{
			in.read(buffer+l_end+1,l_end);
			if(in.gcount() != l_end)
			{
				buffer[in.gcount()+l_end+1] = EOF;
			}
		}
		else
		{
			flag_r = false;
		}
	}
}

void Lex::get_char()
{
	C = buffer[forward];
	if(C == EOF)
	{
		return;
	}
	if(C == '\n')
	{
		linenum++;
		charnum++;
	}
	else
	{
		charnum++;
	}
	
	forward++;
	if(buffer[forward] == EOF)
	{
		if(forward == l_end)
		{
			fillBuffer(1);
			forward++;
		}
		else if(forward == r_end)
		{
			fillBuffer(0);
			forward = START;
		}
	}
}

void Lex::get_nbc()
{
	while(C == ' ' || C == '\t' || C == '\n')
	{
		get_char();
	}
} 

void Lex::retract()
{
	if(forward == 0)
	{
		flag_l = true;    //�Ѿ���ȡ���ļ�����Ҫ�ٶ�ȡ
		forward = l_end - 1; 
	}
	else
	{
		forward--;
		if(forward == l_end)
		{
			flag_r = true;
			forward--;
		}
	}
}
//��<�Ǻţ�����>�ĸ�ʽ��� 
void Lex::output(int type,string out)
{
	switch(type)
	{
		case KEYWORD:	
			cout << "<keyword," << atoi(out.c_str()) << ">" << endl;
			break;
		case ID:
			cout << "<id," << atoi(out.c_str()) << ">" << endl;
			break;
		case NUM:
			cout << "<num," << out << ">" << endl;
			break;
		case RELOP:
			cout << "<" << out << ", >" << endl;
			break;
		case LITERAL:
			cout << "<literal," << out << ">" << endl;
			break;
		case PUNC:
			cout << "<" << out << ", >" << endl;
			break;
		case ANNOTATION:
			cout << "<annotation," << out << ">" << endl;
			break;
	}
	wordnum++;
}

void Lex::error()
{
	cout << "Line: " << linenum+1 << " error!" << endl;
}

void Lex::function()
{
	int state = 0;
	do
	{
		switch(state)
		{
			case 0:
				token.clear();
				get_char();
				get_nbc();
				if(isletter(C) || C == '_')
				{
					state = 1;
				}
				else if(C == '-')
				{
					state = 2;
				}
				else if(C >= '1' && C <= '9')
				{
					state = 3;
				}
				else if(C == '0')
				{
					state = 9;
				}
				else if(C == '+')
				{
					state = 15;
				}
				else if(C == '<')
				{
					state = 16;
				}
				else if(C == '>')
				{
					state = 18;
				}
				else if(C == '*' || C == '&' || C == '%' || C == '!' || C == '=' || C == '|' || C == '^')
				{
					state = 20;
				}
				else if(C == '~' || C == '.')
				{
					output(RELOP,change_to_string(C));
					state = 0;
				}
				else if(C == '{' || C == '}' || C == '[' || C == ']' || C == '(' || C == ')' || C == ';' || C == ':' || C == ',')
				{
					output(PUNC,change_to_string(C));
					state = 0;
				}
				else if(C == '"')
				{
					state = 22;
				}
				else if(C == '\'')
				{
					state = 23;
				}
				else if(C == '/')
				{
					state = 24;
				}
				else if(C == '#')
				{
					state = 28;
				}
				else
				{
					state = 29;
				}
				break;
				
			case 1:
				token.push_back(C);
				get_char();
				if(isletter(C) || isdigit(C) || C == '_')
				{
					state = 1;
				}
				else
				{
					retract();
					state = 0;
					if(iskey(token) != -1)
					{
						output(KEYWORD,change_to_string(iskey(token)));
					}
					else
					{
						id.push_back(token);
						int local = id.size() - 1;
						output(ID,change_to_string(local));
						state = 0;
					}
				}
				break;
				
			case 2:
				token.push_back(C);
				get_char();
				if(C >= '1' && C <= '9')
				{
					state = 3;
				}
				else if(C == '0')
				{
					state = 9;
				}
				else if(C == '.')
				{
					state = 4;
				}
				else if(C == '=' || C == '-' || C == '>')
				{
					token.push_back(C);
					output(RELOP,token);
					state = 0;
				}
				else
				{
					retract();
					output(RELOP,token);
					state = 0;
				}
				break;
				
			case 3:
				token.push_back(C);
				get_char();
				if(isdigit(C))
				{
					state = 3;
				}
				else if(C == '.')
				{
					state = 4;
				}
				else if(C == 'e' || C == 'E')
				{
					state = 6;
				}
				else
				{
					retract();
					output(NUM,token);
					state = 0;
				}
				break;
			
			case 4:
				token.push_back(C);
				get_char();
				if(isdigit(C))
				{
					state = 5;
				}
				else
				{
					error();
					state = 0;
				}
				break;
				
			case 5:
				token.push_back(C);
				get_char();
				if(isdigit(C))
				{
					state = 5;
				}
				else if(C == 'e' || C == 'E')
				{
					state = 6;
				}
				else
				{
					retract();
					output(NUM,token);
					state = 0;
				}
				break;
				
			case 6:
				token.push_back(C);
				get_char();
				if(C == '+' || C == '-')
				{
					state = 7;
				}
				else if(isdigit(C))
				{
					state = 8;
				}
				else
				{
					retract();
					output(NUM,token);
					state = 0;
				}
				break;
				
			case 7:
				token.push_back(C);
				get_char();
				if(isdigit(C))
				{
					state = 8;
				}
				else
				{
					retract();
					error();
					state = 0;
				}
				break;
			
			case 8:
				token.push_back(C);
				get_char();
				if(isdigit(C))
				{
					state = 8;
				}
				else
				{
					retract();
					output(NUM,token);
					state = 0;
				}
				break;
				
			case 9:
				token.push_back(C);
				get_char();
				if(C == '.')
				{
					state = 4;
				}
				else if(C == 'X' || C == 'x')
				{
					state = 10;
				}
				else if(C == 'B' || C == 'b')
				{
					state = 12;
				}
				else if(C >= '0' && C <= '7')
				{
					state = 14; 
				}
				else
				{
					retract();
					output(NUM,token);
					state = 0;
				}
				break;
			
			case 10:
				token.push_back(C);
				get_char();
				if((C >= '0' && C <= '9') || (C >= 'A' && C <= 'F') || (C >= 'a' && C <= 'f') )
				{
					state = 11;
				}
				else
				{
					retract();
					error();
					state = 0;
				}
				break;
				
			case 11:
				token.push_back(C);
				get_char();
				if((C >= '0' && C <= '9') || (C >= 'A' && C <= 'F') || (C >= 'a' && C <= 'f') )
				{
					state = 11;
				}
				else
				{
					retract();
					output(NUM,token);
					state = 0;
				}
				break;
			
			case 12:
				token.push_back(C);
				get_char();	
				if(C == '0' || C == '1')
				{
					state = 13;
				}
				else
				{
					retract();
					error();
					state = 0;
				}	
				break;
				
			case 13:
				token.push_back(C);
				get_char();
				if(C == '0' || C == '1')
				{
					state = 13;
				}
				else
				{
					retract();
					output(NUM,token);
					state = 0;
				}	
				break;
				
			case 14:
				token.push_back(C);
				get_char();
				if(C >= '0' && C <= '7')
				{
					state = 14; 
				}
				else
				{
					retract();
					output(NUM,token);
					state = 0;
				}	
				break;
				
			case 15:
				token.push_back(C);
				get_char();
				if(C >= '1' && C <= '9')
				{
					state = 3;
				}
				else if(C == '0')
				{
					state = 9;
				}
				else if(C == '=' || C == '+')
				{
					token.push_back(C);
					output(RELOP,token);
					state = 0;
				}
				else
				{
					retract();
					output(RELOP,token);
					state = 0;
				}
				break;
			
			case 16:
				token.push_back(C);
				get_char();
				if(C == '<')
				{
					state = 17;
				}
				else if(C == '=')
				{
					token.push_back(C);
					output(RELOP,token);
					state = 0;
				}
				else
				{
					retract();
					output(RELOP,token);
					state = 0;
				}
				break;
			
			case 17:
				token.push_back(C);
				get_char();
				if(C == '=')
				{
					token.push_back(C);
					output(RELOP,token);
					state = 0;
				}
				else
				{
					retract();
					output(RELOP,token);
					state = 0;
				}
				break;
				
			case 18:
				token.push_back(C);
				get_char();
				if(C == '>')
				{
					state = 19;
				}
				else if(C == '=')
				{
					token.push_back(C);
					output(RELOP,token);
					state = 0;
				}
				else
				{
					retract();
					output(RELOP,token);
					state = 0;
				}
				break;
				
			case 19:
				token.push_back(C);
				get_char();
				if(C == '=')
				{
					token.push_back(C);
					output(RELOP,token);
					state = 0;
				}
				else
				{
					retract();
					output(RELOP,token);
					state = 0;
				}
				break;
			
			case 20:
				token.push_back(C);
				get_char();
				if(C == '=')
				{
					token.push_back(C);
					output(RELOP,token);
					state = 0;
				}
				else
				{
					retract();
					output(RELOP,token);
					state = 0;
				}
				break;
			
			case 21:
				token.push_back(C);
				get_char();
				if(C == '&')
				{
					token.push_back(C);
					output(RELOP,token);
					state = 0;
				}
				else if(C == '=')
				{
					token.push_back(C);
					output(RELOP,token);
					state = 0;
				}
				else
				{
					retract();
					output(RELOP,token);
					state = 0;
				}
				break;
				
			case 22:
				get_char();
				if(C == '"')
				{
					output(LITERAL,token);
					state = 0;
				}
				else
				{
					token.push_back(C);
					state = 22;
				}
				break;
				
			case 23:
				get_char();
				if(C == '\'')
				{
					output(LITERAL,token);
					state = 0;
				}
				else
				{
					token.push_back(C);
					state = 23;
				}
				break;
			
			case 24:
				get_char();
				if(C == '/')
				{
					state = 25;
				}
				else if(C == '*')
				{
					state = 26;
				}
				else if(C == '=')
				{
					token.push_back(C);
					output(RELOP,token);
					state = 0;
				}
				else
				{
					retract();
					error();
					state = 0;
				}
				break;
			
			case 25:
				get_char();
				if(C == '\n')
				{
					output(ANNOTATION,token);
					state = 0;
				}
				else
				{
					token.push_back(C);
					state = 25;
				}
				break;
				
			case 26:
				get_char();
				if(C == '*')
				{
					state = 27;
				}
				else
				{
					token.push_back(C);
					state = 26;
				}
				break;
				
			case 27:
				get_char();
				if(C == '*')
				{
					token.push_back('*');
					state = 27;
				}
				else if(C == '/')
				{
					output(ANNOTATION,token);
					state = 0;
				}
				else 
				{
					token.push_back('*');
					token.push_back(C);
					state = 26;
				}
				break;
				
			case 28:
				while(C != '\n')
				{
					get_char();
				}
				state = 0;
				break;
				
			case 29:
				cout << "Line: " << linenum << " error!" << endl;
			//	system("pause");
				state = 0;
				break;
		}
		
	}while(C != EOF);
}


void Lex::operation(string f)
{
	char filename[20];
	file = f;
	strcpy(filename,file.c_str());
	in.open(filename);
	if(!in)
	{
		cout << "�ļ������ڣ�" << endl;
	} 
	else
	{
		initial();
		function();
		cout << endl;
		cout << "linenum:" << linenum << endl;
		cout << "wordnum:" << wordnum << endl;
		cout << "charnum:" << charnum << endl;
	}
	
}

int main()
{
	init_key();
	string filename;
	cout << "�����ļ�����" ;
	cin >> filename;
	Lex test;
	test.operation(filename);
	system("pause");
	return 0;
} 
