package homework7;

public class _14211168_÷‹“¢∆Â_7_BookSpecification 
{
	String isbn;
	String title;
	double price;
	int type;
	
	
	_14211168_÷‹“¢∆Â_7_BookSpecification(String isbn, String title, double price, int type)
	{
		this.isbn = isbn;
		this.title = title;
		this.price = price;
		this.type = type;
	}
	
	public void setInfo(String isbn, String title, double price, int type)
	{
		this.isbn = isbn;
		this.title = title;
		this.price = price;
		this.type = type;	
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void setPrice(double price)
	{
		this.price = price;
	}
	
	public String getISBN()
	{
		return isbn;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public int getType()
	{
		return type;
	}
}
