package homework3;

public class CommisionEmployee extends _14211168_��Ң��_3_Employee
{
	double grossSales;	//���۶�
	double commissionRate;	//��ɱ���
	
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
