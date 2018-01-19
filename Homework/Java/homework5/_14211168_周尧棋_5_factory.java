package homework5;

import java.util.*;

public class _14211168_÷‹“¢∆Â_5_factory 
{
	private HashMap <String,_14211168_÷‹“¢∆Â_5_Employee> employees = new HashMap <String,_14211168_÷‹“¢∆Â_5_Employee> ();
	public int  number = 0;
	
	public _14211168_÷‹“¢∆Â_5_Employee getEmployee(String empSecNum)
	{
		if (employees.get(empSecNum) != null)
		{
			return employees.get(empSecNum);
		}
		else
		{
			return null;
		}
	}
	
	public _14211168_÷‹“¢∆Â_5_Employee addEmployee(_14211168_÷‹“¢∆Â_5_Employee emp)
	{
		if (employees.get(emp) == null)
		{
			employees.put(emp.getsocialSecurityNumber(),emp);
			return emp;
		}
		else
		{
			return null;
		}
	}
	
	public _14211168_÷‹“¢∆Â_5_Employee deleteEmployee(String empSecNum)
	{
		if (employees.get(empSecNum)!= null)
		{
			_14211168_÷‹“¢∆Â_5_Employee tmp = employees.get(empSecNum);
			employees.remove(empSecNum);
			return tmp;
		}
		else
		{
			return null;
		}
	}
	
	public _14211168_÷‹“¢∆Â_5_Employee updateEmployee(String empSecNum, Scanner sc)
	{
		if (employees.get(empSecNum)!= null)
		{
			_14211168_÷‹“¢∆Â_5_Employee tmp = employees.get(empSecNum);
			String first = null, last = null;
			
			System.out.println("Please input the last name of the Employee: ");
			last = sc.next();
			tmp.setlastName(last);
			System.out.println("Please input the first name of the Employee: ");
			first = sc.next();
			tmp.setfirstName(first);
		
			employees.put(empSecNum, tmp);
			return tmp;
		}
		else
		{
			return null;
		}
	}
	
	public void initEmployees(Scanner sc)
	{
		Random random = new Random();
		System.out.println("Initialization\n");
		for (int i = 0; i < 10; i ++)
		{
			String first, last, number;
			double sales, rate;
			
			System.out.println("Please input the socialSecurityNumber of the Employee: ");
			number = sc.next();
			if (number.equals("0000"))
			{
				System.out.println("\n");
				break;
			}
			System.out.println("Please input the last name of the Employee: ");
			last = sc.next();
			System.out.println("Please input the first name of the Employee: ");
			first = sc.next();
			
			int a = random.nextInt(4);	//a «0-4µƒÀÊª˙ ˝
			switch(a)
			{
				case 0:
					double salary;
					System.out.println("Please input the weekly salary of the SalaridEmployee: ");
					salary = sc.nextDouble();
					_14211168_÷‹“¢∆Â_5_SalaridEmployee S_Employee = new _14211168_÷‹“¢∆Â_5_SalaridEmployee(first,last,salary,number);
					addEmployee((_14211168_÷‹“¢∆Â_5_Employee)S_Employee);
					System.out.println("Information input is finished!\n");
					break;
					
				case 1:
					double wage, hours;
					System.out.println("Please input the wage of the HourlyEmployee: ");
					wage = sc.nextDouble();
					System.out.println("Please input the hours of the HourlyEmployee: ");
					hours = sc.nextDouble();
					_14211168_÷‹“¢∆Â_5_HourlyEmployee H_Employee = new _14211168_÷‹“¢∆Â_5_HourlyEmployee(first,last,wage,hours,number);
					addEmployee((_14211168_÷‹“¢∆Â_5_Employee)H_Employee);
					System.out.println("Information input is finished!\n");
					break;
					
				case 2:
					System.out.println("Please input the gross sales of the CommisionEmployee: ");
					sales = sc.nextDouble();
					System.out.println("Please input the commission rate of the CommisionEmployee: ");
					rate = sc.nextDouble();
					_14211168_÷‹“¢∆Â_5_CommisionEmployee C_Employee = new _14211168_÷‹“¢∆Â_5_CommisionEmployee(first,last,sales,rate,number);
					addEmployee((_14211168_÷‹“¢∆Â_5_Employee)C_Employee);
					System.out.println("Information input is finished!\n");
					break;
					
				case 3:
					double base;
					System.out.println("Please input the gross sales of the BasePlusCommisionEmployee: ");
					sales = sc.nextDouble();
					System.out.println("Please input the commission rate of the BasePlusCommisionEmployee: ");
					rate = sc.nextDouble();
					System.out.println("Please input the base salary of the BasePlusCommisionEmployee: ");
					base = sc.nextDouble();
					_14211168_÷‹“¢∆Â_5_BasePlusCommisionEmployee B_Employee = new _14211168_÷‹“¢∆Â_5_BasePlusCommisionEmployee(first,last,sales,rate,base,number);
					addEmployee((_14211168_÷‹“¢∆Â_5_Employee)B_Employee);
					System.out.println("Information input is finished!\n");
					break;
			}
			
		}
	}
	
	public void printEmployees()
	{
		for (String key : employees.keySet())
		{
			System.out.println(employees.get(key).toString());
		}
	}
	
}
