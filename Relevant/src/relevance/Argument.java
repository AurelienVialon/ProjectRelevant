package relevance;

import java.util.ArrayList;

import constraints.Term;
import exampleWorkingAgents.ArgumentName;

public class Argument 
{
	private ArgumentName name;
	private int version = -1;
	private Term assumption;
	private ArrayList<Term> supportedRules = new ArrayList<Term>();
	
	public Argument(ArgumentName arg0, Term arg1, Term arg2) 
	{
		this.name = arg0;
		this.assumption = arg1;
		this.supportedRules.add(arg2);
	}
	public Argument(ArgumentName arg0, Term arg1, Term arg2, int arg3) 
	{
		this.name = arg0;
		this.assumption = arg1;
		this.supportedRules.add(arg2);
		this.version = arg3;
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
}
