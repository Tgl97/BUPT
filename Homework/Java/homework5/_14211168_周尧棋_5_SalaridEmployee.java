package homework5;

public class _14211168_��Ң��_5_SalaridEmployee extends _14211168_��Ң��_5_Employee
{
	double weeklySalary;	//��н
	
	public _14211168_��Ң��_5_SalaridEmployee(String first, String last, double salary, String num)
	{
		super.firstName = first;
		super.lastName = last;
		super.socialSecurityNumber = num;	
		weeklySalary = salary;
	}
	
	public double getweeklySalary()
	{
		return weeklySalary;
	}
	
	public void setweeklySalary(double money)
	{
		weeklySalary = money;
	}
	
	public String toString()
	{
		return super.toString() + "Earning: " + Double.toString(earning()) + "\n";
	}
	
	public double earning()
	{
		return weeklySalary*4.0;
	}
}
