package homework3;

import java.util.Random;

public class EmployeeTest 
{
	public static void main(String arg[])
	{
		_14211168_周尧棋_3_Employee[]data = new _14211168_周尧棋_3_Employee[10];
		Random random = new Random();
		for (int i = 0; i < 10; i ++)
		{
			int a = random.nextInt(4);	//a是0-4的随机数
			switch(a)
			{
				case 0:
					//System.out.println(a+"\n");
					SalaridEmployee S_Employee = new SalaridEmployee(i);
					data[i] = (_14211168_周尧棋_3_Employee)S_Employee;
					break;
				case 1:
					//System.out.println(a+"\n");
					HourlyEmployee H_Employee = new HourlyEmployee(i);
					data[i] = (_14211168_周尧棋_3_Employee)H_Employee;
					break;
				case 2:
					//System.out.println(a+"\n");
					CommisionEmployee C_Employee = new CommisionEmployee(i);
					data[i] = (_14211168_周尧棋_3_Employee)C_Employee;
					break;
				case 3:
					//System.out.println(a+"\n");
					BasePlusCommisionEmployee B_Employee = new BasePlusCommisionEmployee(i);
					data[i] = (_14211168_周尧棋_3_Employee)B_Employee;
					break;
			}
			
		}
		
		for (int j = 0; j < 10; j ++)
		{
			System.out.println(data[j].toString());
			System.out.println("Salary: " + data[j].earning() + "\n");
		}
	}
	
}
