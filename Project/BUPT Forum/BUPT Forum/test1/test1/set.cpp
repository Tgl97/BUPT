#include"Post.h"

//从文件中读取信息到容器Forums中
int initial(vector<forum> &Forums)
{
	ifstream PostFile;
	int error = -1;
	PostFile.open("posts.txt");
	//1.Basketball
	//2.Football
	//3.XiaoFenRui
	try
	{
		if (!PostFile)
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
	int No = 0, temp = 0;
	string name;
	string title;
	bool flag = true;
	while (!PostFile.eof())
	{
		getline(PostFile, name, '\n');
		if (!name.empty())
		{
			if (name == "Basketball")
			{
				No = 0;
				flag = true;
			}
			else if (name == "Football")
			{
				No = 1;
				flag = true;
			}
			else if (name == "XiaoFenRui")
			{
				No = 2;
				flag = true;
			}
			else if (name == "Post:")
			{
				No = temp;
				flag = false;
			}
			if (flag)
			{
				Forums[No].set_name(name);
				getline(PostFile, title, '\n');
			}
			else 
			{
				title = name;
			}
			if (title == "Post:")
			{
				getline(PostFile, title, '\n');
				string con, man, comments, T1, T2;
				getline(PostFile, con, '\n');
				getline(PostFile, man, '\n');
				int num = Forums[No].f_post.size();
				string forum = Forums[No].get_name();
				getline(PostFile, T1, '\n');
				post NewPost(num, forum, title, con, man, T1);
				getline(PostFile, comments, '\n');
				if (comments == "Comment:")
				{
					getline(PostFile, comments, '\n');
					comment NewCom;
					while (!comments.empty())
					{
						string first = comments.substr(0, comments.find('\t', 0));
						string second = comments.substr(first.length() + 1);
						NewCom.set_content(first);
						NewCom.set_time(second);
						NewPost.p_com.push_back(NewCom);
						if (PostFile.eof())
						{
							break;
						}
						getline(PostFile, comments, '\n');
					}
				}
				
				Forums[No].f_post.push_back(NewPost);
			}
			temp = No;
		}	
	}
	PostFile.close();
	return 1;
}

//将所有板块内容读入文件
void generate(vector<forum> &Forums)
{
	int error = -1;
	ofstream output("posts.txt", ios::trunc);
	try
	{
		if (!output)
		{
			error = 0;
			throw error;
		}
	}
	catch (int)
	{
		cout << "Can't open the file!" << endl;
		return;
	}
	vector<forum>::iterator iter1;
	for (iter1 = Forums.begin(); iter1 != Forums.end(); iter1++)
	{
		output << (*iter1).get_name() << endl;
		(*iter1).print_file(output);
	}
}

