package homework2;

import java.util.Scanner;

public class StudentTest 
{
	public static void main(String arg[])
	{
		System.out.println("������ѧ��ѧ�ţ�");
		Scanner s1 = new Scanner(System.in);
		String str1 = s1.nextLine();
		boolean flag = false;
		while (!flag)
		{
			try
			{
				char first, second;
				boolean isNum = false;
				if (str1.length() != 10)
				//����Ϊ��ѧ��Ϊ10λ
				{
					flag = false;
					throw new StudentNumberException("ѧ�Ÿ�ʽ����ȷ������������!");
				}
				//System.out.println("test");
				first = str1.charAt(0);          //ȡ�ַ�����һλ
				second = str1.charAt(1);		 //ȡ�ַ����ڶ�λ
				isNum = str1.matches("[0-9]+");	 //����������ʽ�ж��ַ����Ƿ�Ϊ���ִ�
				if (first != '2' || second != '0' || isNum == false)
				//��1λΪ2����1λΪ0������λΪ����0~9
				{
					flag = false;
					throw new StudentNumberException("ѧ�Ÿ�ʽ����ȷ������������!");
				}
				System.out.println("ѧ��������ȷ!");
				flag = true;
			}
			catch(StudentNumberException E)
			{
				//E.printStackTrace();
				System.out.println("������ѧ��ѧ�ţ�");
				str1 = s1.nextLine();
			}
		}
		
		System.out.println("������ѧ��������");
		String str2 = s1.nextLine();
		_14211168_��Ң��_2_Student Stu = new _14211168_��Ң��_2_Student(str1,str2);
		
		flag = false;
		System.out.println("������ѧ�����ſγɼ�����ѧ��Ӣ���ѧ����");
		
		int[] a = new int[3];
		int i;
		while (!flag)
		{
			for(i = 0;i < 3; i++)
			{
				a[i] = s1.nextInt();
			}
			try
			{
				for(i = 0; i < 3; i++)
				{
					if(a[i] < 0 || a[i] > 100)
					//�������ѧ���ɼ�����[0,100]����ʱ���׳����쳣
					{
						throw new ScoreException("�ɼ�������������������!");
					}
				}
				flag = true;
				System.out.println("�ɼ�������ȷ!");
			}
			catch(ScoreException e)
			{
				//e.printStackTrace();
				System.out.println("������ѧ�����ſγɼ�����ѧ��Ӣ���ѧ����");
			}
			catch(Exception e)
			{
				//e.printStackTrace();
				System.out.println("������ѧ�����ſγɼ�����ѧ��Ӣ���ѧ����");
			}
		}
		
		Stu.enterMarks(a[0], a[1], a[2]);
		String Output = Stu.toString();
		System.out.println(Output);
		s1.close();
	}
}
