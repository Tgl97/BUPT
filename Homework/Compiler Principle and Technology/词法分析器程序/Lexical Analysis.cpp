#include<iostream>
#include<cstdlib>
#include<cstring> 
#include<vector>
#include<fstream>
#include<sstream>

#define KEYWORD    0  //关键字 
#define ID         1  //标识符 
#define RELOP      2  //运算符 
#define NUM        3  //常数 
#define LITERAL    4  //字符串 
#define PUNC       5  //标点 
#define ANNOTATION 6  //注释 
#define MAXBUF     82 //缓冲区长度 
#define L_END      40 //缓冲区左半边终止位 
#define R_END      81 //缓冲区右半边终止位 
#define START      0  //开始指针 

using namespace std;

vector<string> key;   //C语言的关键字表 

class Lex 
{
	public:
		string file;		 //需要词法分析的文件名 
		ifstream in;
		char buffer[MAXBUF]; //输入缓冲区 
		int l_end, r_end;    //左右半区的终点
		int forward;         //前进指针
		bool flag_l,flag_r;
		vector<string> id;   //自己定义的标识符表
		vector<string> keyword;
		vector<string> num;
		vector<string> literal;  //""中的字符串 
		char C;              //读入当前字符
		int linenum, wordnum, charnum;   //行数，单词数，字符数
		string token;          //存放当前的字符串
		string change_to_string(char c);  //将char->string 
		string change_to_string(int c);	  //将int->string 
		void get_char();	//从输入缓冲区中读一个字符，放入C中，forward指向下一个
		void get_nbc();		//检查C中字符是否为空格，是则调用get_char()，直到C进入非空格字符 
		void retract();		//向前指针后退一位
		void initial();		//初始化
		void fillBuffer(int a);	//填充缓冲区，0左1右 
		void function();	//分析过程
		void output(int type,string out);		//以<记号，属性>的形式输出
		void error();		//检查出错误，打印错误的行数 
		void operation(string f);	//调用main()函数 
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

//判断字符是否为字母 
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
//判断字符是否为数字 
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
//判断标识符是否在C语言关键字表中 
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
//初始化 
void Lex::initial()
{
	id.clear();	     //清空标识符表 
	l_end = L_END;	 //初始化缓冲区 
	r_end = R_END;
	forward = 0;
	flag_l = flag_r = false;
	buffer[l_end] = buffer[r_end] = EOF;
	fillBuffer(0);
	linenum = wordnum = charnum = 0;  //初始化行数、单词数、字符数 
}

void Lex::fillBuffer(int a)
{
	if(a == 0)    //填充缓冲区左半边
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
		flag_l = true;    //已经读取过文件，不要再读取
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
//按<记号，属性>的格式输出 
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
		cout << "文件不存在！" << endl;
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
	cout << "输入文件名：" ;
	cin >> filename;
	Lex test;
	test.operation(filename);
	system("pause");
	return 0;
} 
