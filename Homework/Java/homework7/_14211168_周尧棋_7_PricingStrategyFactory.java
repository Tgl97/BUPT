package homework7;

public class _14211168_÷‹“¢∆Â_7_PricingStrategyFactory 
{
	_14211168_÷‹“¢∆Â_7_StrategyCatalog strategyCatalog = new _14211168_÷‹“¢∆Â_7_StrategyCatalog();
	
	private static class LazyHolder
	{
		private static final _14211168_÷‹“¢∆Â_7_PricingStrategyFactory instance = new _14211168_÷‹“¢∆Â_7_PricingStrategyFactory();
	}
	
	private _14211168_÷‹“¢∆Â_7_PricingStrategyFactory()
	{
		
	}
	
	public static final _14211168_÷‹“¢∆Â_7_PricingStrategyFactory getInstance()
	{
		return LazyHolder.instance;
	}
	
	public _14211168_÷‹“¢∆Â_7_IPricingStrategy getPriceStrategy(String no)
	{
		return strategyCatalog.strategies.get(no);
	}
	
	public void addStrategy(String no, _14211168_÷‹“¢∆Â_7_IPricingStrategy str)
	{
		strategyCatalog.strategies.put(no,str);
	}
	
	public _14211168_÷‹“¢∆Â_7_StrategyCatalog getStrategyCatalog()
	{
		return this.strategyCatalog;
	}
}
