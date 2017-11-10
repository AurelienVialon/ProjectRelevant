package relevance;

import java.util.ArrayList;


import Argument.Argument;
import Constraints.QC;

public class Relevance
{
	private int value;
	
	private QC qc ;
	
	private ArrayList<Argument> args;
	
	
	public Relevance() {}
	
	public void addArgument(Argument arg0)
	{	
		this.args.add(arg0);
	}
	
	public int getValue()
	{
		return this.value;
	}
	
	public synchronized int calculation ()
	{
		this.value = 100;
		
		for(Argument a : this.args)
		{
			this.value -= a.getRelevanceImpact();
		}
		return this.value;
	}
	
	public synchronized String update(Argument arg0) 
	{	
		String ret = "OK";
		
		if(this.args.contains(arg0))
			this.value -= arg0.getRelevanceImpact();
		else
			ret = "null";
		return ret;
	}
}
