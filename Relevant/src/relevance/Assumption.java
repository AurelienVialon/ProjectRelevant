package relevance;

import java.util.TreeSet;

import exampleWorkingAgents.ParameterName;
import relevance.constraint.Term;

public class Assumption extends Term
{
	TreeSet<ParameterName> linkedParameters = new TreeSet<ParameterName>();
	
	public Assumption(ParameterName ...arg0)
	{
		for(ParameterName n : arg0)
		{
			this.linkedParameters.add(n);
		}
	}
	public TreeSet<ParameterName> getParametersNames()
	{
		return this.linkedParameters;
	}
}
