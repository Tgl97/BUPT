package homework5;

import java.util.Scanner;

public class _14211168_��Ң��_5_EmployeeTest 
{
	public static void main(String arg[])
	{
		_14211168_��Ң��_5_factory Factory = new _14211168_��Ң��_5_factory();
		_14211168_��Ң��_5_Employee tmp;
		Scanner s = new Scanner(System.in);
		Factory.initEmployees(s);
		System.out.println("Please input the operation number");
		System.out.println("1��Search\n2��Generate\n3��Delte\n4��Print\n5��Exit");
		
		int choice = s.nextInt();
		while (choice != 5)
		{
			switch (choice)
			{
				case 1:
					System.out.println("Please input the socialSecurityNumber of the Employee: ");
					String Num1 = s.next();
					tmp = Factory.getEmployee(Num1);
					if (tmp != null)
					{
						System.out.println(tmp.toString());
					}
					else
					{
						System.out.println("The employee is not in the factory!\n");
					}
					break;
					
				case 2:
					System.out.println("Please input the socialSecurityNumber of the Employee: ");
					String Num2 = s.next();
					tmp = Factory.updateEmployee(Num2,s);
					if (tmp != null)
					{
						System.out.println(tmp.toString());
					}
					else
					{
						System.out.println("The employee is not in the factory!\n");
					}
					break;
				
				case 3:
					System.out.println("Please input the socialSecurityNumber of the Employee: ");
					String Num3 = s.next();
					tmp = Factory.deleteEmployee(Num3);
					if (tmp != null)
					{
						System.out.println(tmp.toString());
					}
					else
					{
						System.out.println("The employee is not in the factory!\n");
					}
					break;
				
				case 4:
					Factory.printEmployees();
					break;
					
				case 5:
					break;
					
				default:
					System.out.println("�����������");
					break;
			}
			System.out.println("�����������");
			System.out.println("1����ѯ\n2������\n3��ɾ��\n4����ӡ\n5���˳�");
			choice = s.nextInt();
		}
		s.close();
	}
}
