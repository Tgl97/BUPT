package homework3;

public class HourlyEmployee extends _14211168_��Ң��_3_Employee
{
	double wage;	//ÿСʱ��Ǯ
	double hours;	//�¹���Сʱ��
	
	public HourlyEmployee(int num)
	{
		super.firstName = "c" + Integer.toString(num);
		super.lastName = "d" + Integer.toString(num);
		super.socialSecurityNumber = String.format("%06d",num);
		//%�����0��ʾǰ�油0,6��ʾ�ܳ���6λ��d��ʾΪ����
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
