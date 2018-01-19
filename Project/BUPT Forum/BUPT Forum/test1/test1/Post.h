#ifndef _POST_H
#define _POST_H

#include"main.h"
#include<iterator>


//������
class comment
{
	private:

		string content;	//��������
		string Time;	//����ʱ��

	public:
		//���캯��
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
		//��������
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

		void set_time()	//���õ�ǰʱ��
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
		//�� << ������
		friend ostream & operator << (ostream& output, comment& C)
		{
			output << C.get_content() << '\t' << C.get_Time();
			return output;
		}

};

//������
class post
{
	private:

		int id;	//����ID
		string forum;	//�������ڵİ��
		string title;	//���ӵı���
		string content;	//���ӵ�����
		string Time;	//���ӷ���ʱ��
		string postman;	//������

	public:

		vector<comment> p_com;	//���ӵ�����
		//���캯��
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
		//�������캯��
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
		//��������
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
		//��ӡ���ӵ���Ļ
		void print_post()
		{
			cout << "--------------Post " << id << "--------------" << endl;
			cout << "Title:" << title << endl;
			cout << "Content:" << content << endl;
			cout << "Postman:" << postman << endl;
			cout << "Time:" << Time << endl;
			cout << "----------------------------------" << endl;
		}
		//��ӡ���ӵ����۵���Ļ
		void print_com()
		{
			cout << "-------------Comments-------------" << endl;
			vector<comment>::iterator iter;
			int count = 1;
			for (iter = p_com.begin(); iter != p_com.end(); iter++)
			{
				cout << count << "��" << (*iter) << endl;
				count++;
			}
			cout << "----------------------------------" << endl;
			cout << endl;
		}
		//��ӡ���ӵ����۵��ļ�
		void comment_file(ofstream &out)
		{
			vector<comment>::iterator iter;
			for (iter = p_com.begin(); iter != p_com.end(); iter++)
			{
				out << (*iter) << endl;
			}
		}
		//���÷����ĵ�ǰʱ��
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
		//�� << ������
		friend ostream & operator << (ostream& output, post& P)
		{
			output << "Post:\n" << P.get_title() << endl << P.get_content() << endl
				<< P.get_postman() << endl << P.get_Time();
			return output;
		}
		//�� = ������
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

//�����
class forum
{
	private:

		string name;	//�����

	public:

		vector<post> f_post;	//����е�����
		//���캯��
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
		//��ӡ������鵽��Ļ

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
		//��ӡ��鵽�ļ�
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

