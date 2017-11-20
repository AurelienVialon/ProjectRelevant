package relevance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import exampleWorkingAgents.ParameterName;
import model.Engine;
import relevance.constraint.Term;

public class Relevance
{
	private HashMap<Term, SetOfArguments> library = new HashMap<Term, SetOfArguments>();//Library built with the various versions of the constraint.
	
	private int value = 0;
	
	private SetOfArguments CurrentArguments = null;
	private TreeSet<ParameterName> Triggers = new TreeSet<ParameterName>();

	public int getValueRelevance()
	{
		return this.value;
	}
	
	public Term maximise()
	{
		int best = 0, current = 0;
		Term akeep = null;
		boolean flag = true;
		
		for(Map.Entry<Term, SetOfArguments> entry : this.library.entrySet())
		{
			current = entry.getValue().maximise() / entry.getValue().size();
			
			if(current > best || flag)
			{
				flag = false;
				best = current;
				this.CurrentArguments = entry.getValue();
				akeep = entry.getKey();
			}
		}
		this.value = best;
		return akeep;
	}
	public Term maximiseDetailed(Engine e)
	{
		int best = 0, current = 0;
		Term akeep = null;
		boolean flag = true;
		
		for(Map.Entry<Term, SetOfArguments> entry : this.library.entrySet())
		{
			e.notifie("\n Exploration of rule definition " + entry.getKey().getName());
			current = entry.getValue().maximiseDetailed(e) / entry.getValue().size();
			
			e.notifie("\n\t\t Expected Relevance for this rule definition : " + current);
			
			if(current > best || flag)
			{
				flag = false;
				best = current;
				this.CurrentArguments = entry.getValue();
				akeep = entry.getKey();
			}
		}
		this.value = best;
		e.notifie("\n\t\t Rule definition chose: " + akeep.getName());
		return akeep;
	}
	
	public void addArgument(Argument arg0)
	{
		this.Triggers.addAll(arg0.getAssumptionParametersNames());
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
	public TreeSet<ParameterName> getTriggers()
	{
		return this.Triggers;
	}
	public ArrayList<Term> getRules()
	{
		ArrayList<Term> ret = new ArrayList<Term>();
		for(Map.Entry<Term, SetOfArguments> entry : this.library.entrySet())
		{
			ret.add(entry.getKey());
		}
		return ret;
	}
}
