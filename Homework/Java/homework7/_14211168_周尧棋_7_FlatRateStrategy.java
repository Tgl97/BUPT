package homework7;

import java.math.BigDecimal;

public class _14211168_÷‹“¢∆Â_7_FlatRateStrategy implements _14211168_÷‹“¢∆Â_7_IPricingStrategy
{
	String no;
	String name;
	int type = 0;
	int book = 0;
	double discountPerBook;
	
	_14211168_÷‹“¢∆Â_7_FlatRateStrategy(String no, String name, int type, int book, String discountPerBook)
	{
		this.no = no;
		this.name = name;
		this.type = type;
		this.book = book;
		this.discountPerBook = Double.parseDouble(discountPerBook);
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
		return "$" + Double.toString(discountPerBook) + "/book";
	}
	
	public double getSubTotal(_14211168_÷‹“¢∆Â_7_SaleLineItem item)
	{
		BigDecimal a = new BigDecimal(Double.toString(item.bookSpec.price));
		BigDecimal b = new BigDecimal(Double.toString(discountPerBook));
		return a.subtract(b).doubleValue() * item.copies;
	}
	
	public String toString()
	{
		return "Strategy Number: " + no + "\nStrategy Name: " + name
				+ "\nStrategy Type: " + Integer.toString(type) 
				+"\nApplied Book Type: " + Integer.toString(book)
				+ "\nDiscount: $" + Double.toString(discountPerBook) + "/book\n";
	}
}
