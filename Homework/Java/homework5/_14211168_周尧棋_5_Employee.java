package homework5;

public abstract class _14211168_÷‹“¢∆Â_5_Employee 
{
	String firstName;
	String lastName;
	String socialSecurityNumber;
	
	public _14211168_÷‹“¢∆Â_5_Employee()
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
		String number = "socialSecurityNumber: ";
		String first = "First name: ";
		String last = "Last name: ";
		return number + getsocialSecurityNumber() + "\n"
				+ first + getfirstName() + "\n" 
				+ last + getlastName() + "\n";	 
	}
	
	public abstract double earning();
	
}

