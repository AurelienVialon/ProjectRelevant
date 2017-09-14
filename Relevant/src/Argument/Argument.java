package Argument;

public class Argument<T> 
{
	FactToCheck<T> fact;
	Rule rule;
	int relevanceImpact;

	public Argument(FactToCheck<T> arg0, Rule arg1, int arg2) 
	{
		this.fact = arg0;
		this.rule = arg1;
		this.relevanceImpact = arg2;
	}

	public boolean valid()
	{
		return this.fact.check();
	}
	
	public int getRelevanceImpact()
	{
		return this.relevanceImpact;
	}
}
