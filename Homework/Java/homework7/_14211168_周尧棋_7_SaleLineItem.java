package homework7;

public class _14211168_÷‹“¢∆Â_7_SaleLineItem 
{
	int copies;
	_14211168_÷‹“¢∆Â_7_BookSpecification bookSpec;
	_14211168_÷‹“¢∆Â_7_IPricingStrategy strategy;
	
	_14211168_÷‹“¢∆Â_7_SaleLineItem(_14211168_÷‹“¢∆Â_7_BookSpecification aBook, int numbers, _14211168_÷‹“¢∆Â_7_IPricingStrategy strategy)
	{
		this.copies = numbers;
		this.bookSpec = aBook;
		this.strategy = strategy;
	}
	
	public double getSubTotal()
	{
		return strategy.getSubTotal(this);
	}
}
