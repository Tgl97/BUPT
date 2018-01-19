package homework3;

public class HourlyEmployee extends _14211168_周尧棋_3_Employee
{
	double wage;	//每小时工钱
	double hours;	//月工作小时数
	
	public HourlyEmployee(int num)
	{
		super.firstName = "c" + Integer.toString(num);
		super.lastName = "d" + Integer.toString(num);
		super.socialSecurityNumber = String.format("%06d",num);
		//%后面的0表示前面补0,6表示总长度6位，d表示为正数
		wage = (num+1)*50.0;
		hours = 20*(num+6);
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
	
	public double earning()
	{
		return wage*hours;
	}
}
