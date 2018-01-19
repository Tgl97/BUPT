#ifndef _POST_H
#define _POST_H

#include"main.h"
#include<iterator>


//评论类
class comment
{
	private:

		string content;	//评论内容
		string Time;	//评论时间

	public:
		//构造函数
		comment()
		{
			content = "";
			Time = "";
		}

		comment(string con, string T)
		{
			content = con;
			Time = T;
		}
		//析构函数
		~comment()
		{}

		string get_content()
		{
			return content;
		}

		string get_Time()
		{
			return Time;
		}

		void set_content(string Content)	
		{
			content = Content;
		}

		void set_comment(comment NewCom)
		{
			content = NewCom.get_content();
			Time = NewCom.get_Time();
		}

		void set_time(string T)
		{
			Time = T;
		}

		void set_time()	//设置当前时间
		{
			time_t rawtime;
			time(&rawtime);
			char stTmp[100];
			struct tm tmTmp;
			localtime_s(&tmTmp, &rawtime);
			asctime_s(stTmp, &tmTmp);
			stTmp[strlen(stTmp) - 1] = '\0';
			Time = stTmp;
		}
		//对 << 的重载
		friend ostream & operator << (ostream& output, comment& C)
		{
			output << C.get_content() << '\t' << C.get_Time();
			return output;
		}

};

//帖子类
class post
{
	private:

		int id;	//帖子ID
		string forum;	//帖子属于的板块
		string title;	//帖子的标题
		string content;	//帖子的内容
		string Time;	//帖子发布时间
		string postman;	//发帖人

	public:

		vector<comment> p_com;	//帖子的评论
		//构造函数
		post()
		{
			id = 0;
			forum = "";
			title = "";
			content = "";
			Time = "";
			postman = "";
		}

		post(int ID, string f, string t, string c, string pm)
		{
			id = ID;
			forum = f;
			title = t;
			content = c;
			postman = pm;
		}

		post(int ID, string f, string t, string c, string pm, string tm)
		{
			id = ID;
			forum = f;
			title = t;
			content = c;
			postman = pm;
			Time = tm;
		}
		//拷贝构造函数
		post(const post &P)
		{
			id = P.id;
			forum = P.forum;
			title = P.title;
			content = P.content;
			postman = P.postman;
			Time = P.Time;
			p_com.assign(P.p_com.begin(), P.p_com.end());
		}
		//析构函数
		~post()
		{}

		string get_forum()
		{
			return forum;
		}

		string get_title()
		{
			return title;
		}

		string get_content()
		{
			return content;
		}

		int get_id()
		{
			return id;
		}

		string get_postman()
		{
			return postman;
		}

		string get_Time()
		{
			return Time;
		}
		//打印帖子到屏幕
		void print_post()
		{
			cout << "--------------Post " << id << "--------------" << endl;
			cout << "Title:" << title << endl;
			cout << "Content:" << content << endl;
			cout << "Postman:" << postman << endl;
			cout << "Time:" << Time << endl;
			cout << "----------------------------------" << endl;
		}
		//打印帖子的评论到屏幕
		void print_com()
		{
			cout << "-------------Comments-------------" << endl;
			vector<comment>::iterator iter;
			int count = 1;
			for (iter = p_com.begin(); iter != p_com.end(); iter++)
			{
				cout << count << "：" << (*iter) << endl;
				count++;
			}
			cout << "----------------------------------" << endl;
			cout << endl;
		}
		//打印帖子的评论到文件
		void comment_file(ofstream &out)
		{
			vector<comment>::iterator iter;
			for (iter = p_com.begin(); iter != p_com.end(); iter++)
			{
				out << (*iter) << endl;
			}
		}
		//设置发帖的当前时间
		void set_time()
		{
			time_t rawtime;
			time(&rawtime);
			char stTmp[100];
			struct tm tmTmp;
			localtime_s(&tmTmp, &rawtime);
			asctime_s(stTmp, &tmTmp);
			Time = stTmp;
			stTmp[strlen(stTmp) - 1] = '\0';
			Time = stTmp;
		}
		//对 << 的重载
		friend ostream & operator << (ostream& output, post& P)
		{
			output << "Post:\n" << P.get_title() << endl << P.get_content() << endl
				<< P.get_postman() << endl << P.get_Time();
			return output;
		}
		//对 = 的重载
		post & operator = (const post &P)
		{
			id = P.id;
			forum = P.forum;
			title = P.title;
			content = P.content;
			postman = P.postman;
			Time = P.Time;
			p_com.assign(P.p_com.begin(), P.p_com.end());
			return (*this);
		}

};

//板块类
class forum
{
	private:

		string name;	//板块名

	public:

		vector<post> f_post;	//板块中的帖子
		//构造函数
		forum()
		{
			name = "";
		}

		void set_name(string Name)
		{
			name = Name;
		}

		string get_name()
		{
			return name;
		}
		//打印整个板块到屏幕

		void print_screen()
		{
			vector<post>::iterator iter;
			cout << "Forum:" << get_name() << endl;
			for (iter = f_post.begin(); iter != f_post.end(); iter++)
			{
				(*iter).print_post();
				(*iter).print_com();
			}
		}
		//打印板块到文件
		void print_file(ofstream &out)
		{
			vector<post>::iterator iter;
			for (iter = f_post.begin(); iter != f_post.end(); iter++)
			{
				out << (*iter) << endl << "Comment:" << endl;
				(*iter).comment_file(out);
				out << endl;
			}
		}	
};

#endif // !_POST_H

