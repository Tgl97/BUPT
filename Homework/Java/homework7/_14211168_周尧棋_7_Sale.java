package homework7;

import java.util.*;

public class _14211168_÷‹“¢∆Â_7_Sale implements _14211168_÷‹“¢∆Â_7_Subject
{
	ArrayList<_14211168_÷‹“¢∆Â_7_SaleLineItem> items = new ArrayList<_14211168_÷‹“¢∆Â_7_SaleLineItem>();
	ArrayList<_14211168_÷‹“¢∆Â_7_Observer> observers = new ArrayList<_14211168_÷‹“¢∆Â_7_Observer>();
	
	public void setItems(_14211168_÷‹“¢∆Â_7_SaleLineItem item)
	{
		this.items.add(item);
	}
	
	public double getTotal()
	{
		double total = 0;
		for (int i = 0; i < items.size(); i ++)
		{
			total += items.get(i).getSubTotal();
		}
		return total;
	}
	
	public void removeObserver(_14211168_÷‹“¢∆Â_7_Observer ob)
	{
		Iterator<_14211168_÷‹“¢∆Â_7_Observer> iter = observers.iterator();
		while (iter.hasNext())
		{
			if (iter.next().equals(ob))
			{
				iter.remove();
			}
		}
	}
	
	public void registerObserver(_14211168_÷‹“¢∆Â_7_Observer ob)
	{
		observers.add(ob);
	}
	
	public void notifyObservers()
	{
		for (int i = 0; i < observers.size(); i ++)
		{
			_14211168_÷‹“¢∆Â_7_Observer ob = observers.get(i);
			ob.update(this);
		}
	}
}
