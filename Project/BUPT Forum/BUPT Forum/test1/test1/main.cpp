#include"User.h"
#include"initial.h"
#include"Login.h"

void windows()
{
	cout << "--------BYR��̳--------" << endl;
	cout << "��ѡ�������" << endl;
	cout << "1����¼" << endl;
	cout << "2��ע��" << endl;
	cout << "3���˳�ϵͳ" << endl;
	cout << "-----------------------" << endl;
}

void operation()
{
	cout << "��ѡ�������" << endl
		<< "0���û�ע��" << endl
		<< "1���鿴����" << endl
		<< "2������" << endl
		<< "3��ɾ��" << endl
		<< "4����������" << endl
		<< "5�������ö�" << endl
		<< "6������" << endl
		<< "7����������" << endl
		<< "8����������" << endl
		<< "9���鿴������Ϣ" << endl;
}

int main()
{
	User *NewUser;
	vector<forum> Forums(3);
	int init = -1;
	init = initial(Forums);
	if (init == 0)
	{
		cout << "�޷����ļ���" << endl;
		return -1;
	}
	windows();
	int option;
	cin >> option;
	while (option != 3)
	{
		if (option == 1)
		{
			struct info temp = { "","","","","" };
			int kind = 0;
			kind = Login(temp);
			if (kind == 0)
				continue;
			else if (kind == 1)
				NewUser = new OUser;
			else if (kind == 2)
				NewUser = new Admin;
			else
				NewUser = new Moderator;
			NewUser->set_attribute(temp.UserName, temp.password, temp.id, temp.kind, temp.sec);
			operation();
			int choice;
			cin >> choice;
			while (choice != 0)
			{
				switch (choice)
				{
					case 1:
						NewUser->CheckPost(Forums);
						break;
					case 2:
						NewUser->PutPost(Forums);
						break;
					case 3:
						NewUser->Delete(Forums);
						break;
					case 4:
					{
						cout << "Please input the key words:" << endl;
						string key;
						cin >> key;
						NewUser->CheckPost(Forums, key);
						break;
					}
					case 5:
						NewUser->Stick(Forums);
						break;
					case 6:
						NewUser->PutComment(Forums);
						break;
					case 7:
						NewUser->SetModerator();
						break;
					case 8:
						NewUser->CanModerator();
						break;
					case 9:
						NewUser->ShowInfo();
						break;
				}
				system("pause");
				system("cls");
				operation();
				cin >> choice;
			}
		}
		else if (option == 2)
		{
			Register();
			system("cls");
		}
		system("pause");
		system("cls");
		generate(Forums);
		windows();
		cin >> option;
	}
	generate(Forums);
	system("pause");
	return 0;
}