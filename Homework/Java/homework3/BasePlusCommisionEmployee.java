package homework3;

public class BasePlusCommisionEmployee extends CommisionEmployee
{
	double baseSalary;	//月基本工资
	
	public BasePlusCommisionEmployee(int num)
	{
		super.firstName = "g" + Integer.toString(num);
		super.lastName = "h" + Integer.toString(num);
		super.socialSecurityNumber = String.format("%06d",num);
		baseSalary = 2000.0;
		super.grossSales = (num+1)*3000.0;
		super.commissionRate = 0.2;
	}
	public double getbaseSalary()
	{
		return baseSalary;
	}
	
	public void setbaseSalary(double base)
	{
		baseSalary = base;
	}
	
	public double earning()
	{
		return grossSales*commissionRate+baseSalary;
	}
}
