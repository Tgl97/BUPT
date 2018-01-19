package homework3;

public class SalaridEmployee extends _14211168_周尧棋_3_Employee
{
	double weeklySalary;	//周薪
	
	public SalaridEmployee(int num)
	{
		super.firstName = "a" + Integer.toString(num);
		super.lastName = "b" + Integer.toString(num);
		super.socialSecurityNumber = String.format("%06d",num);	
		//%后面的0表示前面补0,6表示总长度6位，d表示为正数
		weeklySalary = (num+1)*1000.0;
		//不同的人周薪不同
	}
	
	public double getweeklySalary()
	{
		return weeklySalary;
	}
	
	public void setweeklySalary(double money)
	{
		weeklySalary = money;
	}
	
	public double earning()
	{
		return weeklySalary*4.0;
	}
}
