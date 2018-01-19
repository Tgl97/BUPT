package homework7;

public class _14211168_÷‹“¢∆Â_7_CompositeBestForCustomer extends _14211168_÷‹“¢∆Â_7_CompositeStrategy
{
	_14211168_÷‹“¢∆Â_7_CompositeBestForCustomer(String no, String name, int type, int book)
	{
		super(no,name,type,book);
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
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < strategies.size(); i ++)
		{
			_14211168_÷‹“¢∆Â_7_IPricingStrategy tmp = (_14211168_÷‹“¢∆Â_7_IPricingStrategy) strategies.get(i);
			out.append(tmp.getNo());
			if (i != strategies.size() - 1)
			{
				out.append('+');
			}
		}
		return out.toString();
	}
	
	public double getSubTotal(_14211168_÷‹“¢∆Â_7_SaleLineItem item)
	{
		double min = Double.MAX_VALUE;
		for (int i = 0; i < strategies.size(); i ++)
		{
			_14211168_÷‹“¢∆Â_7_IPricingStrategy tmp = (_14211168_÷‹“¢∆Â_7_IPricingStrategy) strategies.get(i);
			if (tmp.getSubTotal(item) < min)
			{
				min = tmp.getSubTotal(item);
				System.out.println(Double.toString(min));
			}
		}
		return min;
	}
}
