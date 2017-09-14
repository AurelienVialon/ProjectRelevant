package relevance;

import java.util.ArrayList;


import Argument.Argument;

public class Relevance
{
	private int value;
	private ArrayList<Argument<?>> args;
	public Relevance() {}
	
	public synchronized int calculation ()
	{
		this.value = 100;
		
		for(Argument<?> a : this.args)
		{
			if(!a.valid())
				this.value -= a.getRelevanceImpact();
		}
		return this.value;
	}
	
	public synchronized String update(Argument<?> arg0) 
	{	
		String ret = "OK";
		
		if(this.args.contains(arg0))
			this.value -= arg0.getRelevanceImpact();
		else
			ret = "null";
		return ret;
	}
}
