package homework5;

public class _14211168_周尧棋_5_CommisionEmployee extends _14211168_周尧棋_5_Employee
{
	double grossSales;	//销售额
	double commissionRate;	//提成比率
	
	public _14211168_周尧棋_5_CommisionEmployee()
	{
		grossSales = 0;
		commissionRate = 0;
	}
	
	public _14211168_周尧棋_5_CommisionEmployee(String first, String last, double sales, double rate, String num)
	{
		super.firstName = first;
		super.lastName = last;
		super.socialSecurityNumber = num;
		this.grossSales = sales;
		this.commissionRate = rate;
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
	
	public String toString()
	{
		return super.toString() + "Earning: " + Double.toString(earning()) + "\n";
	}
	
	public double earning()
	{
		return grossSales*commissionRate;
	}
}
