package homework5;

public class _14211168_��Ң��_5_HourlyEmployee extends _14211168_��Ң��_5_Employee
{
	double wage;	//ÿСʱ��Ǯ
	double hours;	//�¹���Сʱ��
	
	public _14211168_��Ң��_5_HourlyEmployee(String first, String last, double wage, double hours, String num)
	{
		super.firstName = first;
		super.lastName = last;
		super.socialSecurityNumber = num;
		this.wage = wage;
		this.hours = hours;
	}
	
	public double getWage()
	{
		return wage;
	}
	
	public double getHours()
	{
		return hours;
	}
	
	public void setWage(double w)
	{
		wage = w;
	}
	
	public void setHours(double h)
	{
		hours = h;
	}
	
	public String toString()
	{
		return super.toString() + "Earning: " + Double.toString(earning()) + "\n";
	}
	
	public double earning()
	{
		return wage*hours;
	}
}
