package homework7;

import java.math.BigDecimal;

public class _14211168_÷‹“¢∆Â_7_PercentageStrategy implements _14211168_÷‹“¢∆Â_7_IPricingStrategy
{
	String no;
	String name;
	int type = 0;
	int book = 0;
	double discountPercentage;
	
	_14211168_÷‹“¢∆Â_7_PercentageStrategy(String no, String name, int type, int book, String discountPercentage)
	{
		this.no = no;
		this.name = name;
		this.type = type;
		this.book = book;
		this.discountPercentage = Double.parseDouble(discountPercentage);
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
		return Double.toString(discountPercentage) + "%";
	}
	
	public double getSubTotal(_14211168_÷‹“¢∆Â_7_SaleLineItem item)
	{
		BigDecimal a = new BigDecimal(Double.toString(discountPercentage));
		BigDecimal b = new BigDecimal(Double.toString(0.01));
		BigDecimal c = new BigDecimal(item.bookSpec.price);
		BigDecimal d = a.multiply(b);
		BigDecimal e = new BigDecimal(1);
		BigDecimal f = new BigDecimal(item.copies);
		
		return f.multiply(c.multiply(e.subtract(d))).doubleValue();
	}
	
	public String toString()
	{
		return "Strategy Number: " + no + "\nStrategy Name: " + name
				+ "\nStrategy Type: " + Integer.toString(type) 
				+"\nApplied Book Type: " + Integer.toString(book)
				+ "\nDiscount: " + Double.toString(discountPercentage) + "%\n";
	}
	
}
