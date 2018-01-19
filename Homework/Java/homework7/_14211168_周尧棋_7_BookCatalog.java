package homework7;

import java.util.ArrayList;

public class _14211168_÷‹“¢∆Â_7_BookCatalog 
{
	ArrayList<_14211168_÷‹“¢∆Â_7_BookSpecification> books = new ArrayList<_14211168_÷‹“¢∆Â_7_BookSpecification>();
	
	public _14211168_÷‹“¢∆Â_7_BookSpecification getBookSpecification(String isbn)
	{
		int i;
		for (i = 0; i < books.size(); i ++)
		{
			if (books.get(i).isbn.equals(isbn))
			{
				return books.get(i);
			}
		}
		return null;
	}
	
	public void addBookSpecification(_14211168_÷‹“¢∆Â_7_BookSpecification abook)
	{
		books.add(abook);
	}
}
