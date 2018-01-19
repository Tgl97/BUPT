package homework7;

import java.util.HashMap;

public class _14211168_周尧棋_7_StrategyCatalog 
{
	//Key为策略的编号，Value为策略
	protected HashMap<String,_14211168_周尧棋_7_IPricingStrategy> strategies = new HashMap<String,_14211168_周尧棋_7_IPricingStrategy>();
	
	public HashMap<String,_14211168_周尧棋_7_IPricingStrategy> getHashMap()
	{
		return this.strategies;
	}
}
