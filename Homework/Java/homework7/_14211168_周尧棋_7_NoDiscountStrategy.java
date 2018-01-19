package homework7;

public class _14211168_÷‹“¢∆Â_7_NoDiscountStrategy implements _14211168_÷‹“¢∆Â_7_IPricingStrategy
{
	String no;
	String name;
	int type = 0;
	int book = 0;
	
	_14211168_÷‹“¢∆Â_7_NoDiscountStrategy(String no, String name, int type, int book)
	{
		this.no = no;
		this.name = name;
		this.type = type;
		this.book = book;
	}
	
	public String getNo()
	{
		return no;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getType()
	{
		return Integer.toString(type);
	}
	
	public String getBook()
	{
		return Integer.toString(book);
	}
	
	public String getDiscount()
	{
		return "No discount!";
	}
	
	public double getSubTotal(_14211168_÷‹“¢∆Â_7_SaleLineItem item)
	{
		return item.bookSpec.price * item.copies;
	}
	
	public String toString()
	{
		return "Strategy Number: " + no + "\nStrategy Name: " + name
				+ "\nStrategy Type: " + Integer.toString(type) 
				+"\nApplied Book Type: " + Integer.toString(book)
				+ "\nDiscount: No discount!\n";
	}
}
