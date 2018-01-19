package homework2;

import java.util.Scanner;

public class StudentTest 
{
	public static void main(String arg[])
	{
		System.out.println("请输入学生学号：");
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
				//条件为：学号为10位
				{
					flag = false;
					throw new StudentNumberException("学号格式不正确，请重新输入!");
				}
				//System.out.println("test");
				first = str1.charAt(0);          //取字符串第一位
				second = str1.charAt(1);		 //取字符串第二位
				isNum = str1.matches("[0-9]+");	 //利用正则表达式判断字符串是否为数字串
				if (first != '2' || second != '0' || isNum == false)
				//第1位为2，第1位为0，其余位为数字0~9
				{
					flag = false;
					throw new StudentNumberException("学号格式不正确，请重新输入!");
				}
				System.out.println("学号输入正确!");
				flag = true;
			}
			catch(StudentNumberException E)
			{
				//E.printStackTrace();
				System.out.println("请输入学生学号：");
				str1 = s1.nextLine();
			}
		}
		
		System.out.println("请输入学生姓名：");
		String str2 = s1.nextLine();
		_14211168_周尧棋_2_Student Stu = new _14211168_周尧棋_2_Student(str1,str2);
		
		flag = false;
		System.out.println("请输入学生三门课成绩（数学，英语，科学）：");
		
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
					//当输入的学生成绩不在[0,100]区间时，抛出该异常
					{
						throw new ScoreException("成绩数据有误，请重新输入!");
					}
				}
				flag = true;
				System.out.println("成绩输入正确!");
			}
			catch(ScoreException e)
			{
				//e.printStackTrace();
				System.out.println("请输入学生三门课成绩（数学，英语，科学）：");
			}
			catch(Exception e)
			{
				//e.printStackTrace();
				System.out.println("请输入学生三门课成绩（数学，英语，科学）：");
			}
		}
		
		Stu.enterMarks(a[0], a[1], a[2]);
		String Output = Stu.toString();
		System.out.println(Output);
		s1.close();
	}
}
