package homework4;

public class _14211168_周尧棋_4_BasePlusCommisionEmployee extends _14211168_周尧棋_4_CommisionEmployee
{
	double baseSalary;	//月基本工资
	
	public _14211168_周尧棋_4_BasePlusCommisionEmployee()
	{
		baseSalary = 0;
	}
	
	public _14211168_周尧棋_4_BasePlusCommisionEmployee(int num)
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
	
	public String toString()
	{
		String base = "baseSalary: ";
		return super.toString() + base + Double.toString(getbaseSalary()) + "\n";	 
	}
	
	public double earning()
	{
		return grossSales*commissionRate+baseSalary;
	}

}
