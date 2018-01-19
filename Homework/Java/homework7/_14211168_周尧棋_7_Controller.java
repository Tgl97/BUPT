package homework7;

import java.util.HashMap;
import java.util.Iterator;

public class _14211168_÷‹“¢∆Â_7_Controller 
{
	_14211168_÷‹“¢∆Â_7_BookCatalog bookCatalog = new _14211168_÷‹“¢∆Â_7_BookCatalog();
	_14211168_÷‹“¢∆Â_7_StrategyCatalog strategyCatalog = new _14211168_÷‹“¢∆Â_7_StrategyCatalog();
	_14211168_÷‹“¢∆Â_7_Sale aSale = new _14211168_÷‹“¢∆Â_7_Sale();
	
	public void addBook(String isbn, String title, double price, int type)
	{
		_14211168_÷‹“¢∆Â_7_BookSpecification abook = new _14211168_÷‹“¢∆Â_7_BookSpecification(isbn,title,price,type);
		bookCatalog.addBookSpecification(abook);
	}
	
	public void addCompositeStrategy(String no, String name, int type, int book, String[] strategies)
	{
		_14211168_÷‹“¢∆Â_7_CompositeStrategy str = new _14211168_÷‹“¢∆Â_7_CompositeBestForCustomer(no,name,type,book);
		HashMap<String,_14211168_÷‹“¢∆Â_7_IPricingStrategy> map;
		map = _14211168_÷‹“¢∆Â_7_PricingStrategyFactory.getInstance().getStrategyCatalog().getHashMap();
		
		for (int i = 0; i < strategies.length; i ++)
		{
			_14211168_÷‹“¢∆Â_7_IPricingStrategy tmp = map.get(strategies[i]);
			str.add(tmp);
		}
		
		map.put(no, str);
	}
	
	public void addSimpleStrategy(String no, String name, int type, int book, String discount)
	{
		_14211168_÷‹“¢∆Â_7_IPricingStrategy str = null;
		
		switch(type)
		{
			case 1:
				str = new _14211168_÷‹“¢∆Â_7_PercentageStrategy(no,name,type,book,discount);
				break;
			
			case 2:
				str = new _14211168_÷‹“¢∆Â_7_FlatRateStrategy(no,name,type,book,discount);
				break;
				
			case 3:
				str = new _14211168_÷‹“¢∆Â_7_NoDiscountStrategy(no,name,type,book);
				break;
			
			default:
				break;
		}
		//System.out.println("111");
		strategyCatalog.strategies.put(no, str);
		_14211168_÷‹“¢∆Â_7_PricingStrategyFactory.getInstance().addStrategy(no, str);
		Iterator<String> iter = _14211168_÷‹“¢∆Â_7_PricingStrategyFactory.getInstance().getStrategyCatalog().getHashMap().keySet().iterator();
		while(iter.hasNext())
		{
			String key = (String)iter.next();
			System.out.println(_14211168_÷‹“¢∆Â_7_PricingStrategyFactory.getInstance().getStrategyCatalog().getHashMap().get(key).toString());
		}
	}
	
	public void deleteStrategy(String no)
	{
		_14211168_÷‹“¢∆Â_7_PricingStrategyFactory.getInstance().getStrategyCatalog().getHashMap().remove(no);
		System.out.println(_14211168_÷‹“¢∆Â_7_PricingStrategyFactory.getInstance().getStrategyCatalog().getHashMap().size());
	}
	
	public void updateStrategy(String no, String name, int type, int book, String discount)
	{
		addSimpleStrategy(no, name, type, book, discount);
	}
	
	public void buyBook(String isbn, int numbers)
	{
		_14211168_÷‹“¢∆Â_7_BookSpecification aBook = bookCatalog.getBookSpecification(isbn);
		
		if (aBook.equals(null))
		{
			System.out.println("error!\n");
		}
		else
		{
			int type = aBook.getType();
			_14211168_÷‹“¢∆Â_7_IPricingStrategy strategy = null;
			for (_14211168_÷‹“¢∆Â_7_IPricingStrategy value : _14211168_÷‹“¢∆Â_7_PricingStrategyFactory.getInstance().getStrategyCatalog().getHashMap().values())
			{
				if (value.getBook().equals(Integer.toString(type)))
				{
					strategy = value;
				}
			}
			System.out.println(strategy.getClass().getName());
			_14211168_÷‹“¢∆Â_7_SaleLineItem saleItem = new _14211168_÷‹“¢∆Â_7_SaleLineItem(aBook,numbers,strategy);
			aSale.setItems(saleItem);
			System.out.println(aSale.items.size());
			//aSale.notifyObservers();
		}
	}
	
	
	public _14211168_÷‹“¢∆Â_7_Sale getSale()
	{
		return aSale;
	}
	
	public _14211168_÷‹“¢∆Â_7_StrategyCatalog getStrategies()
	{
		return strategyCatalog;
	}
	
	public _14211168_÷‹“¢∆Â_7_BookCatalog getBookCatalog()
	{
		return bookCatalog;
	}
}

