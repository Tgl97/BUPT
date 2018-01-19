package homework2;

import java.util.Scanner;

public class StudentListTest 
{
	public static void print()
	{
		System.out.println("菜单如下，请输入 1~8代表您要执行的操作：\r\n");
		System.out.println("1.增加一个学生\r\n"
						 + "2.根据学号删除学生\r\n"
						 + "3.根据位置删除学生\r\n"
						 + "4.判断是否为空\r\n"
						 + "5.根据位置返回学生\r\n"
						 + "6.根据学号返回学生\r\n"
						 + "7.输出全部学生信息\r\n"
						 + "8.退出程序\r\n");
	}
	
	public static void main(String arg[])
	{
		print();
		_14211168_周尧棋_1_StudentList L = new _14211168_周尧棋_1_StudentList();
		int choice = 0;
		boolean flag = true;
		Scanner sc = new Scanner(System.in);
		while(flag)
		{
			choice = sc.nextInt();
			switch(choice)
			{
				case 1:
					System.out.println("请输入学生信息：\r\n");
					System.out.println("学号：");
					String number = sc.next();
					System.out.println("姓名：");
					String name = sc.next();
					System.out.println("数学成绩：");
					int math = sc.nextInt();
					System.out.println("英语成绩：");
					int eng = sc.nextInt();
					System.out.println("科学成绩：");
					int sci = sc.nextInt();
					_14211168_周尧棋_2_Student Stu = new _14211168_周尧棋_2_Student(number,name);
					Stu.enterMarks(math, eng, sci);
					if(L.add(Stu))
					{
						System.out.println("添加成功！");
					}
					else
					{
						System.out.println("添加失败！");
					}
					break;
					
				case 2:
					System.out.println("请输入要删除学生的学号：");
					String num1 = sc.next();
					if(L.remove(num1))
					{
						System.out.println("删除成功！");
					}
					else
					{
						System.out.println("删除失败！");
					}
					break;
					
				case 3:
					System.out.println("请输入要删除学生的位置：");
					int n1 = sc.nextInt();
					if(L.remove(n1))
					{
						System.out.println("删除成功！");
					}
					else
					{
						System.out.println("删除失败！");
					}
					break;
					
				case 4:
					if(L.isEmpty())
					{
						System.out.println("学生表为空！");
					}
					else
					{
						System.out.println("学生表不为空！");
					}
					break;
					
				case 5:
					System.out.println("请输入学生的位置：");
					int n2 = sc.nextInt();
					if(L.getItem(n2) == null)
					{
						System.out.println("对不起，没有对应的学生！");
					}
					else
					{
						System.out.println(L.getItem(n2).toString());
					}
					break;
					
				case 6:
					System.out.println("请输入学生的学号：");
					String num2 = sc.next();
					if(L.getItem(num2) == null)
					{
						System.out.println("对不起，没有对应的学生！");
					}
					else
					{
						System.out.println(L.getItem(num2).toString());
					}
					break;
					
				case 7:
					System.out.println("---目前有" + L.getTotal() + "个学生，信息为---：\r\n\n");
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
