package homework2;

import java.util.Scanner;

public class StudentListTest 
{
	public static void print()
	{
		System.out.println("�˵����£������� 1~8������Ҫִ�еĲ�����\r\n");
		System.out.println("1.����һ��ѧ��\r\n"
						 + "2.����ѧ��ɾ��ѧ��\r\n"
						 + "3.����λ��ɾ��ѧ��\r\n"
						 + "4.�ж��Ƿ�Ϊ��\r\n"
						 + "5.����λ�÷���ѧ��\r\n"
						 + "6.����ѧ�ŷ���ѧ��\r\n"
						 + "7.���ȫ��ѧ����Ϣ\r\n"
						 + "8.�˳�����\r\n");
	}
	
	public static void main(String arg[])
	{
		print();
		_14211168_��Ң��_1_StudentList L = new _14211168_��Ң��_1_StudentList();
		int choice = 0;
		boolean flag = true;
		Scanner sc = new Scanner(System.in);
		while(flag)
		{
			choice = sc.nextInt();
			switch(choice)
			{
				case 1:
					System.out.println("������ѧ����Ϣ��\r\n");
					System.out.println("ѧ�ţ�");
					String number = sc.next();
					System.out.println("������");
					String name = sc.next();
					System.out.println("��ѧ�ɼ���");
					int math = sc.nextInt();
					System.out.println("Ӣ��ɼ���");
					int eng = sc.nextInt();
					System.out.println("��ѧ�ɼ���");
					int sci = sc.nextInt();
					_14211168_��Ң��_2_Student Stu = new _14211168_��Ң��_2_Student(number,name);
					Stu.enterMarks(math, eng, sci);
					if(L.add(Stu))
					{
						System.out.println("��ӳɹ���");
					}
					else
					{
						System.out.println("���ʧ�ܣ�");
					}
					break;
					
				case 2:
					System.out.println("������Ҫɾ��ѧ����ѧ�ţ�");
					String num1 = sc.next();
					if(L.remove(num1))
					{
						System.out.println("ɾ���ɹ���");
					}
					else
					{
						System.out.println("ɾ��ʧ�ܣ�");
					}
					break;
					
				case 3:
					System.out.println("������Ҫɾ��ѧ����λ�ã�");
					int n1 = sc.nextInt();
					if(L.remove(n1))
					{
						System.out.println("ɾ���ɹ���");
					}
					else
					{
						System.out.println("ɾ��ʧ�ܣ�");
					}
					break;
					
				case 4:
					if(L.isEmpty())
					{
						System.out.println("ѧ����Ϊ�գ�");
					}
					else
					{
						System.out.println("ѧ����Ϊ�գ�");
					}
					break;
					
				case 5:
					System.out.println("������ѧ����λ�ã�");
					int n2 = sc.nextInt();
					if(L.getItem(n2) == null)
					{
						System.out.println("�Բ���û�ж�Ӧ��ѧ����");
					}
					else
					{
						System.out.println(L.getItem(n2).toString());
					}
					break;
					
				case 6:
					System.out.println("������ѧ����ѧ�ţ�");
					String num2 = sc.next();
					if(L.getItem(num2) == null)
					{
						System.out.println("�Բ���û�ж�Ӧ��ѧ����");
					}
					else
					{
						System.out.println(L.getItem(num2).toString());
					}
					break;
					
				case 7:
					System.out.println("---Ŀǰ��" + L.getTotal() + "��ѧ������ϢΪ---��\r\n\n");
					for(int i=0; i<L.getTotal(); i++)
					{
						System.out.println(L.list[i].toString());
						System.out.println("\r\n");
					}
					break;
					
				case 8:
					flag = false;
					break;
			}
			
			if(flag)
				print();
			else
				sc.close();
		}
						
	}
}
