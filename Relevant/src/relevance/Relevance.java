package relevance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import MVC.ModeleThreaded;
import constraints.Term;
import exampleWorkingAgents.ArgumentName;

public class Relevance
{
	private HashMap<Term, SetOfArguments> library = new HashMap<Term, SetOfArguments>();//Library built with the various version of the constraint.
	
	private int totalRelevanceImpact = 0;
	private int value = 0;
	
	private SetOfArguments CurrentArguments = null;

	public int getValueRelevance()
	{
		return this.value;
	}
	public int getValueRelevanceImpact()
	{
		return this.totalRelevanceImpact;
	}
	
	public Term maximise()
	{
		int best = 0, current = 0;
		Term akeep = null;
		boolean flag = true;
		
		for(Map.Entry<Term, SetOfArguments> entry : this.library.entrySet())
		{
			current = entry.getValue().maximise();
			
			if(current > best || flag)
			{
				flag = false;
				best = current;
				this.CurrentArguments = entry.getValue();
				akeep = entry.getKey();
			}
		}
		this.totalRelevanceImpact = best;
		this.value = this.totalRelevanceImpact / this.CurrentArguments.size();
		return akeep;
	}
	public Term maximiseDetailed()
	{
		int best = 0, current = 0;
		Term akeep = null;
		boolean flag = true;
		
		for(Map.Entry<Term, SetOfArguments> entry : this.library.entrySet())
		{
			current = entry.getValue().maximiseDetailed();
			
			if(current > best || flag)
			{
				flag = false;
				best = current;
				this.CurrentArguments = entry.getValue();
				akeep = entry.getKey();
			}
		}
		this.totalRelevanceImpact = best;
		this.value = this.totalRelevanceImpact / this.CurrentArguments.size();
		return akeep;
	}
	
	public void addArgument(Argument arg0)
	{
		for(Term t : arg0.getSupportedRules())
		{
			if(this.library.containsKey(t))
			{
				this.library.get(t).add(arg0);
			}
			else
			{
				this.library.put(t, new SetOfArguments(arg0));
			}
		}
	}
}
