package homework3;

public abstract class _14211168_��Ң��_3_Employee 
{
	String firstName;
	String lastName;
	String socialSecurityNumber;
	
	public _14211168_��Ң��_3_Employee()
	{
		firstName = "";
		lastName = "";
		socialSecurityNumber = "";
	}
	
	public String getfirstName()
	{
		return firstName;
	}
	
	public String getlastName()
	{
		return lastName;
	}
	
	public String getsocialSecurityNumber()
	{
		return socialSecurityNumber;
	}
	
	public void setfirstName(String first)
	{
		firstName = first;
	}
	
	public void setlastName(String last)
	{
		lastName = last;
	}
	
	public String toString()
	{
		String first = "First name: ";
		String last = "Last name: ";
		String number = "socialSecurityNumber: ";
		return first + getfirstName() + "\n" 
				+ last + getlastName() + "\n"
				+ number + getsocialSecurityNumber();	 
	}
	
	public abstract double earning();
	
}
