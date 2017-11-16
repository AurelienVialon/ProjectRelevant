package relevance;

import java.util.ArrayList;
import java.util.TreeSet;

import constraints.Term;
import exampleWorkingAgents.ArgumentName;
import exampleWorkingAgents.ParameterName;

public class Argument 
{
	private ArgumentName name;
	private int version = -1;
	private Assumption assumption;
	private ArrayList<Term> supportedRules = new ArrayList<Term>();
	
	public Argument(ArgumentName arg0, Assumption arg1, Term ...arg2) 
	{
		this.name = arg0;
		this.assumption = arg1;
		
		for(Term t : arg2)
		{
			if(!this.supportedRules.contains(t))
			{
				this.supportedRules.add(t);
			}
		}
	}
	public Argument(ArgumentName arg0, int arg3, Assumption arg1, Term ...arg2) 
	{
		this.name = arg0;
		this.version = arg3;
		this.assumption = arg1;
		for(Term t : arg2)
		{
			if(!this.supportedRules.contains(t))
			{
				this.supportedRules.add(t);
			}
		}
	}

	public int calculateRelevanceImpact()
	{
		//To be defined by engineers at the argument definition.
		return 0;
	}
	public int MappedRelevance(int arg0)
	{
		int ret = 0;
		
		if(this.assumption.expression())
		{
			ret = arg0;
		}
		else
		{
			ret = 0;
		}
		return ret;
	}
	public int MappedRelevance(int arg0, int arg1)
	{
		int ret = 0;
		
		if(this.assumption.expression())
		{
			ret = arg1;
		}
		else
		{
			ret = arg0;
		}
		return ret;
	}
	
	public ArgumentName getName()
	{
		return this.name;
	}
	public ArrayList<Term> getSupportedRules()
	{
		return this.supportedRules;
	}
	public Term getAssumption()
	{
		return this.assumption;
	}
	public int getVersion()
	{
		return this.version;
	}
	public TreeSet<ParameterName> getAssumptionParametersNames()
	{
		return this.assumption.getParametersNames();
	}
}
