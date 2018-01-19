package homework4;

public class _14211168_周尧棋_4_CommisionEmployee extends _14211168_周尧棋_4_Employee
{
	double grossSales;	//销售额
	double commissionRate;	//提成比率
	
	public _14211168_周尧棋_4_CommisionEmployee()
	{
		grossSales = 0;
		commissionRate = 0;
	}
	
	public _14211168_周尧棋_4_CommisionEmployee(int num)
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
	
	public String toString()
	{
		String sales = "grossSales: ";
		String rate = "commissionRate: ";
		String salary = "salary: ";
		return super.toString() + sales + Double.toString(getgrossSales()) + "\n" 
				+ rate + Double.toString(getcommissionRate()) + "\n"
				+ salary + Double.toString(earning()) + "\n";	 
	}
	
	public double earning()
	{
		return grossSales*commissionRate;
	}
}
