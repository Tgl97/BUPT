package homework3;

public class SalaridEmployee extends _14211168_��Ң��_3_Employee
{
	double weeklySalary;	//��н
	
	public SalaridEmployee(int num)
	{
		super.firstName = "a" + Integer.toString(num);
		super.lastName = "b" + Integer.toString(num);
		super.socialSecurityNumber = String.format("%06d",num);	
		//%�����0��ʾǰ�油0,6��ʾ�ܳ���6λ��d��ʾΪ����
		weeklySalary = (num+1)*1000.0;
		//��ͬ������н��ͬ
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
