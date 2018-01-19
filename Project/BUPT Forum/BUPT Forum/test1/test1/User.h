#ifndef _USER_H
#define _USER_H

#include"Post.h"

static void choose()
{
	cout << "选择版块：" << endl;
	cout << "1、Basketball" << endl;
	cout << "2、Football" << endl;
	cout << "3、XiaoFenRui" << endl;
}

//用户类——基类
class User
{
	private:

		string id;			//用户ID
		string UserName;	//用户名
		string password;	//用户的密码
		string kind;		//用户的头衔
		string sec;			//所属的板块

	public:

		class A
		{
			public:
				post find;
				int num;
				A(post NewPost, int cnt)
				{
					find = NewPost;
					num = cnt;
				}
		};

		void SetUser(string name, string pw, string ID, string type, string s)
		{
			id = ID;
			UserName = name;
			password = pw;
			kind = type;
			sec = s;
		}

		string get_id()
		{
			return id;
		}

		string get_name()
		{
			return UserName;
		}

		string get_password()
		{
			return password;
		}

		string get_kind()
		{
			return kind;
		}

		string get_sec()
		{
			return sec;
		}

		void set_attribute(string name, string p, string ID, string Kind, string f)
		{
			UserName = name;
			password = p;
			id = ID;
			kind = Kind;
			sec = f;
		}
		//对 << 的重载
		friend ostream & operator << (ostream& output, User *user)
		{
			output << (*user).get_name() << '\t' << (*user).get_password() << '\t' << (*user).get_id()
				<< '\t' << (*user).get_kind();
			if(!(*user).get_sec().empty())
				output << '\t' << (*user).get_sec() << '\n';
			return output;
		}
		//对 >> 的重载
		friend istream & operator >> (istream& input, User *user)
		{
			input >> (*user).get_name() >> (*user).get_password() >> (*user).get_id()
				>> (*user).get_kind() >> (*user).get_sec();
			return input;
		}
		//查看用户个人信息
		void ShowInfo()
		{
			string ID, name, kind;
			ID = get_id();
			name = get_name();
			kind = get_kind();
			cout << "用户ID：" << ID << endl;
			cout << "用户名：" << name << endl;
			cout << "头衔：" << kind << endl;
			cout << "板块：" << sec << endl;
		}
		//查看帖子
		void CheckPost(vector<forum> Forums)
		{
			choose();
			int choice;
			cin >> choice;
			Forums[choice - 1].print_screen();
		}

		static bool cmp(A &a1, A &a2)
		{
			return a1.num > a2.num;
		}

		//搜索功能
		void CheckPost(vector<forum> Forums, string search)
		{
			vector<forum>::iterator iter1;
			vector<post>::iterator iter2;
			vector<A>::iterator iter3;
			vector<A> find;
			int found = 0;
			int **c = NULL;
			int i, j, m, n;
			for (iter1 = Forums.begin(); iter1 != Forums.end(); iter1++)
			{
				for (iter2 = (*iter1).f_post.begin(); iter2 != (*iter1).f_post.end(); iter2++)
				{
					string title = (*iter2).get_title();
					//LCS求两个字符串最大公共子序列
					m = title.length();
					n = search.length();
					c = new int*[m];
					for (i = 0; i < m; i++)
					{
						c[i] = new int[n];
					}
					for (i = 0; i < m; i++)
					{
						for (j = 0; j < n; j++)
						{
							c[i][j] = 0;
						}
					}
					for (i = 0; i < m; i++)
					{
						for (j = 0; j < n; j++)
						{
							if (i == 0 || j == 0)
							{
								if (title[i] == search[j])
								{
									c[i][j] = 1;
								}
								else
								{
									if (i > 0)
									{
										c[i][j] = c[i - 1][j];
									}
									if (j > 0)
									{
										c[i][j] = c[i][j - 1];
									}
								}
							}
							else if (title[i] == search[j])
							{
								c[i][j] = c[i - 1][j - 1] + 1;
							}
							else if (c[i - 1][j] > c[i][j - 1])
							{
								c[i][j] = c[i - 1][j];
							}
							else
							{
								c[i][j] = c[i][j - 1];
							}
						}
					}
					int count = 0;
					count = c[m - 1][n - 1];
					if (count > 0)
					{
						A add((*iter2), count);
						found = 1;
						find.push_back(add);
					}
				}
			}
			for (i = 0; i < m; i++)
			{
				delete[]c[i];
				c[i] = NULL;
			}
			delete[]c;
			c = NULL;
			if (found == 1)
			{
				sort(find.begin(), find.end(), cmp);
				cout << "搜索到的帖子有：" << endl;
				for (iter3 = find.begin(); iter3 != find.end(); iter3++)
				{
					cout << (*iter3).find << endl;
					cout << endl;
				}
			}
			else
			{
				cout << "找不到相关帖子！" << endl;
			}
		}

		void login()
		{
			cout << "登录成功！" << endl;
		}
		//发帖功能
		void PutPost(vector<forum> &Forums)
		{
			choose();
			int f;
			cin >> f;
			string title, content;
			cout << "输入帖子的标题：" << endl;
			cin.ignore();
			getline(cin, title);
			cout << "输入帖子的内容：" << endl;
			getline(cin, content);
			post NewPost(Forums[f - 1].f_post.size(), Forums[f - 1].get_name(), title, content, get_name());
			NewPost.set_time();
			Forums[f - 1].f_post.push_back(NewPost);
		}
		//评论功能
		void PutComment(vector<forum> &Forums)
		{
			choose();
			int f;
			cin >> f;
			Forums[f - 1].print_screen();
			cout << "请输入要评论的帖子ID：" << endl;
			int id;
			cin >> id;
			cout << "Input your comments:" << endl;
			string content;
			cin.ignore();
			getline(cin, content);
			comment NewCom;
			NewCom.set_content(content);
			NewCom.set_time();
			Forums[f - 1].f_post[id].p_com.push_back(NewCom);
		}

		virtual void SetModerator() = 0;				//任命版主
		virtual void CanModerator() = 0;				//撤销版主
		virtual void Delete(vector<forum> &Forums) = 0;	//删帖
		virtual void Stick(vector<forum> &Forums) = 0;	//帖子置顶
};

//普通用户类
class OUser : public User
{
	public:

		virtual void SetModerator()
		{
			cout << "无该操作的权限！" << endl;
		}

		virtual void CanModerator()
		{
			cout << "无该操作的权限！" << endl;
		}

		virtual void Stick(vector<forum> &Forums)
		{
			cout << "无该操作的权限！" << endl;
		}
		//删除自己发的帖子
		virtual void Delete(vector<forum> &Forums)
		{
			choose();
			int f, id;
			cin >> f;
			Forums[f - 1].print_screen();
			cout << "选择要删除的帖子ID：" << endl;
			cin >> id;
			while (Forums[f - 1].f_post[id].get_postman() != get_name())
			{
				cout << "不能删除别人发布的帖子！" << endl;
				cout << "重新输入要删除的帖子的ID号：" << endl;
				cout << "退出操作输入-1" << endl;
				cin >> id;
				if (id == -1)
					return;
			}
			Forums[f - 1].f_post.erase(Forums[f - 1].f_post.begin() + id);
		}
		//对 << 的重载
		friend ostream & operator << (ostream& output, OUser user)
		{
			output << user.get_name() << '\t' << user.get_password() << '\t' << user.get_id()
				<< '\t' << user.get_kind();
			if(!user.get_sec().empty())
				output << '\t' << user.get_sec();
			return output;
		}
};

//管理员类
class Admin : public User
{
	public:
		//任命版主功能
		void SetModerator()
		{
			cout << "输入该用户的用户名：" << endl;
			string name;
			cin >> name;
			fstream file;
			ofstream filetmp("temp.txt");
			string getName;
			char ch;
			file.open("users.txt");
			bool flag = false;
			while (!file.eof())
			{
				getline(file, getName, '\t');
				filetmp << getName << '\t';
				if (name == getName)
				{
					flag = true;
					getline(file, getName, '\t');
					filetmp << getName << '\t';
					getline(file, getName, '\t');
					filetmp << getName << '\t';
					getline(file, getName, '\n');

					cout << "请输入任命的板块：" << endl;
					string F;
					cin >> F;
					while (!(F == "Basketball" || F == "Football" || F == "XiaoFenRui"))
					{
						cout << "输入的板块名错误！请重新输入：" << endl;
						cin >> F;
					}
					filetmp << "Moderator" << '\t' << F;
					if (!file.eof())
					{
						filetmp << endl;
					}
					cout << "任命成功！" << endl;
				}
				else
				{
					file.get(ch);
					while (ch != '\n' && !file.eof())
					{
						filetmp << ch;
						file.get(ch);
					}
					if(!file.eof())
						filetmp << endl;
				}
			}
			if (!flag)
			{
				cout << "不存在该用户！" << endl;
			}
			file.close();
			filetmp.close();
			ifstream in("temp.txt");
			ofstream out("users.txt");
			out.seekp(0, ios::beg);
			while (in.get(ch))
			{
				out << ch;
			}
			in.close();
			out.close();
			remove("temp.txt");
		}
		//撤销版主功能
		void CanModerator()
		{
			cout << "输入该用户的用户名：" << endl;
			string name;
			cin >> name;
			fstream file;
			ofstream filetmp("temp.txt");
			string getName;
			char ch;
			file.open("users.txt");
			bool flag = false;
			while (!file.eof())
			{
				getline(file, getName, '\t');
				filetmp << getName << '\t';
				if (name == getName)
				{
					string getPass;
					string getID;
					string getkind;
					getline(file, getPass, '\t');
					filetmp << getPass << '\t';
					getline(file, getID, '\t');
					filetmp << getID << '\t';
					getline(file, getkind, '\n');
					if (getkind == "OrdinaryUser")
					{
						cout << "该用户为普通用户！不为版主！" << endl;
						cout << "请重新输入用户名：" << endl;
						cin >> name;
						filetmp.clear();
						filetmp.seekp(0, ios::beg);
						file.seekg(0, ios::beg);
						continue;
					}
					flag = true;
					filetmp << "OrdinaryUser";
					if (!file.eof())
					{
						filetmp << endl;
					}
					cout << "撤销版主成功！" << endl;
				}
				else
				{
					file.get(ch);
					while (ch != '\n' && !file.eof())
					{
						filetmp << ch;
						file.get(ch);
					}
					if (!file.eof())
						filetmp << endl;
				}
			}
			if (!flag)
			{
				cout << "不存在该用户！" << endl;
			}

			file.close();
			filetmp.close();
			ifstream in("temp.txt");
			ofstream out("users.txt");

			while (in.get(ch))
			{
				out << ch;
			}
			in.close();
			out.close();
			remove("temp.txt");
		}
		//管理员可删除所有人的帖子
		void Delete(vector<forum> &Forums)
		{
			choose();
			int f, id;
			cin >> f;

			Forums[f - 1].print_screen();
			int err = 1;
			int num = -1;
			//异常捕获、处理
			try
			{
				cout << "选择要删除的帖子ID：" << endl;
				cin >> id;
				vector<post>::iterator iter;
				for (iter = Forums[f - 1].f_post.begin(); iter != Forums[f - 1].f_post.end(); iter++)
				{
					if (id == (*iter).get_id())
					{
						num = iter - Forums[f - 1].f_post.begin();
					}
				}
				if (num == -1)
				{
					err = 0;
					throw err;
				}
			}
			catch(int)
			{
				cout << "输入错误！" << endl;
				return;
			}
			
			Forums[f - 1].f_post.erase(Forums[f - 1].f_post.begin() + num);
		}

		virtual void Stick(vector<forum> &Forums)
		{
			cout << "无该操作的权限！" << endl;
		}
};

class Moderator : public OUser
{
	public:

		virtual void SetModerator()
		{
			cout << "无该操作的权限！" << endl;
		}

		virtual void CanModerator()
		{
			cout << "无该操作的权限！" << endl;
		}
		//版主可删除自己管理板块的帖子
		void Delete(vector<forum> &Forums)
		{
			choose();
			int f, id;
			cin >> f;
			while (Forums[f - 1].get_name() != get_sec())
			{
				cout << "您无权限删除该板块帖子！" << endl;
				cout << "重新输入板块号：" << endl;
				cout << "退出操作输入-1" << endl;
				cin >> f;
				if (f == -1)
				{
					return;
				}
			}
			Forums[f - 1].print_screen();

			int err = 1;
			int num = -1;
			try
			{
				cout << "选择要删除的帖子ID：" << endl;
				cin >> id;
				vector<post>::iterator iter;
				for (iter = Forums[f - 1].f_post.begin(); iter != Forums[f - 1].f_post.end(); iter++)
				{
					if (id == (*iter).get_id())
					{
						num = iter - Forums[f - 1].f_post.begin();
						//cout << num;
					}
				}
				if (num == -1)
				{
					err = 0;
					throw err;
				}
			}
			catch (int)
			{
				cout << "输入错误！" << endl;
				return;
			}
			Forums[f - 1].f_post.erase(Forums[f - 1].f_post.begin() + num);
		}
		//帖子置顶功能
		void Stick(vector<forum> &Forums)
		{
			int f;
			if (get_sec() == "Basketball")
			{
				f = 0;
			}
			else if (get_sec() == "Football")
			{
				f = 1;
			}
			else
			{
				f = 2;
			}
			Forums[f].print_screen();
			int err = 1;
			int num = -1;
			int id;
			try
			{
				cout << "选择要置顶的帖子ID：" << endl;
				cin >> id;
				vector<post>::iterator iter;
				for (iter = Forums[f].f_post.begin(); iter != Forums[f].f_post.end(); iter++)
				{
					if (id == (*iter).get_id())
					{
						num = iter - Forums[f].f_post.begin();
						cout << num;
					}
				}
				if (num == -1)
				{
					err = 0;
					throw err;
				}
			}
			catch (int)
			{
				cout << "输入错误！" << endl;
				return;
			}
			post PostTmp(Forums[f].f_post[num]);
			//vector<post>::iterator iter;
			for (; num > 0; num--)
			{
				Forums[f].f_post[num] = Forums[f].f_post[num - 1];
			}
			Forums[f].f_post[num] = PostTmp;
		}
};

#endif
