package homework7;

import java.util.ArrayList;

public abstract class _14211168_÷‹“¢∆Â_7_CompositeStrategy implements _14211168_÷‹“¢∆Â_7_IPricingStrategy 
{
	String no;
	String name;
	int type = 0;
	int book = 0;
	protected ArrayList<_14211168_÷‹“¢∆Â_7_IPricingStrategy> strategies = new ArrayList<_14211168_÷‹“¢∆Â_7_IPricingStrategy>();
	
	_14211168_÷‹“¢∆Â_7_CompositeStrategy(String no, String name, int type, int book)
	{
		this.no = no;
		this.name = name;
		this.type = type;
		this.book = book;
	}
	
	public void add(_14211168_÷‹“¢∆Â_7_IPricingStrategy str)
	{
		strategies.add(str);
	}
	
	public int getStrategyType()
	{
		return type;
	}
	
	public abstract double getSubTotal(_14211168_÷‹“¢∆Â_7_SaleLineItem item);
	
}
