package Argument;

import java.util.ArrayList;

import logic.Term;

public class Argument 
{
	private String name = "";
	private Term assumption;
	private Rule rule;
	private RelevanceImpact ri = null;
	
	public Argument(String arg0, Term<?> arg1, Rule arg2) 
	{
		this.name = arg0;
		this.assumption = arg1;
	}
	public Argument(String arg0, Term<?> arg1, Rule arg2, int arg3) 
	{
		this.name = arg0;
		this.assumption = arg1;
		this.ri = new RelevanceImpact() {
			@Override
			public int calculate()
			{
				return arg3;
			}
		};
	}

	public int check()
	{
		return this.relevanceImpactCalculation();
	}
	private boolean valid()
	{
		return this.assumption.test();
	}
	
	private int relevanceImpactCalculation()
	{
		return this.ri.calculate();
	}
	
	
	public int getRelevanceImpact()
	{
		return this.relevanceImpactCalculation();
	}
	public void setRelevanceImpact(int arg0)
	{
		this.ri = new RelevanceImpact() {
			@Override
			public int calculate()
			{
				return arg0;
			}
		};
	}
	public void setRelevanceImpact(RelevanceImpact arg0)
	{
		this.ri = arg0;
	}
}
