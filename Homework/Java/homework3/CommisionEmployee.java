package homework3;

public class CommisionEmployee extends _14211168_周尧棋_3_Employee
{
	double grossSales;	//销售额
	double commissionRate;	//提成比率
	
	public CommisionEmployee()
	{
		grossSales = 0;
		commissionRate = 0;
	}
	
	public CommisionEmployee(int num)
	{
		super.firstName = "e" + Integer.toString(num);
		super.lastName = "f" + Integer.toString(num);
		super.socialSecurityNumber = String.format("%06d",num);
		grossSales = (num+1)*5000.0;
		commissionRate = 0.3;
	}
	
	public double getgrossSales()
	{
		return grossSales;
	}
	
	public double getcommissionRate()
	{
		return commissionRate;	
	}
	
	public void setgrossSales(double sales)
	{
		grossSales = sales;
	}
	
	public void setcommissionRate(double rate)
	{
		commissionRate = rate;
	}
	
	public double earning()
	{
		return grossSales*commissionRate;
	}
}
