package homework5;

public class _14211168_��Ң��_5_BasePlusCommisionEmployee extends _14211168_��Ң��_5_CommisionEmployee
{
	double baseSalary;	//�»�������
	
	public _14211168_��Ң��_5_BasePlusCommisionEmployee(String first, String last, double sales, double rate, double base, String num)
	{
		super.firstName = first;
		super.lastName = last;
		super.socialSecurityNumber = num;
		baseSalary = base;
		super.grossSales = sales;
		super.commissionRate = rate;
		this.baseSalary = base;
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
		return super.toString() + "\n";
	}
	
	public double earning()
	{
		return grossSales*commissionRate+baseSalary;
	}
}
