package homework2;

public class ScoreException extends Exception
{
	public ScoreException(String msg)
	{
		System.out.println("error: " + msg);
	}
}

