#include"main.h"
#include"User.h"

bool flag;

//登录函数
int Login(struct info &temp)
{
	ifstream File;
	flag = false;
	int error = -1;
	while (!flag)
	{
		cout << "--------登录窗口--------" << endl;
		File.open("users.txt");
		try 
		{
			if (!File)
			{
				error = 0;
				throw error;
			}
		}
		catch (int)
		{
			cout << "Can't open the file!" << endl;
			return 0;
		}
		string name, password;
		cout << "请输入用户名：";
		cin >> name;
		cout << "请输入密码：";
		cin >> password;
		string str1, str2;
		//从文件读取信息判断是否为存在用户
		while (!File.eof())
		{
			getline(File, str1, '\t');
			getline(File, str2, '\t');
			if (str1 == name && str2 == password)
			{
				flag = true;
				string str3, str4;
				getline(File, str3, '\t');
				getline(File, str4, '\n');
				temp.UserName = str1;
				temp.password = str2;
				temp.id = str3;
				if (str4 == "OrdinaryUser")
				{
					temp.kind = str4;
					temp.sec = "";
					return 1;
				}
				else if (str4 == "Admin")
				{
					temp.kind = str4;
					temp.sec = "";
					return 2;
				}
				else
				{
					string sub = str4;
					temp.kind = sub.substr(0, 9);
					temp.sec = sub.substr(10, sub.length()-10);
					return 3;
				}
			}
			else
			{
				string skip;
				getline(File, skip, '\n');
			}
		}
		cout << "用户名或密码错误！" << endl;
		File.close();
	}
}

void Register()
{
	string name, password, read;
	cout << "创建的用户名为：" << endl;
	cin >> name;
	cout << "请输入密码：" << endl;
	cin >> password;
	int error = -1;
	fstream File;
	File.open("users.txt");
	try
	{
		if (!File)
		{
			error = 0;
			throw error;
		}
	}
	catch (int)
	{
		cout << "Can't open the file!" << endl;
		return ;
	}

	while (!File.eof())
	{
		getline(File, read, '\n');
	}
	//ID号加1操作（000000->000001）
	vector<string> stu;
	stringstream sstr(read);
	stringstream change1, change2;
	int id;
	char temp[10];
	string ID;
	string token;
	while (getline(sstr, token, '\t'))
	{
		stu.push_back(token);
	}
	change1 << stu[2];
	change1 >> id;
	id++;
	sprintf_s(temp, "%06d", id);
	ID = temp;
	OUser NewUser;
	NewUser.SetUser(name,password,ID,"OrdinaryUser","");
	//cout << NewUser ;
	File.seekg(0, ios::end);
	File << endl << NewUser;
	File.close();
}

