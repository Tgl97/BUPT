package homework2;

public class StudentNumberException extends Exception
{
	public StudentNumberException(String ms)
	{
		System.out.println("error: " + ms);
	}
}