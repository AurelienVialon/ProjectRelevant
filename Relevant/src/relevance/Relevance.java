package relevance;

import java.util.HashMap;
import java.util.Map;

import MVC.ModeleThreaded;

public class Relevance
{
	int value;
	HashMap<String, Argument> related_arguments;
	
	public Relevance()
	{
		this.related_arguments = new HashMap<>();
	}
	public Relevance(HashMap<String, Argument> arg0) 
	{
		this.related_arguments = arg0;
	}
	
	public int calculate()
	{
		int totale_relevance_impact = 0;
      	for (Map.Entry<String,Argument> entry : this.related_arguments.entrySet()) 
    		{
    			totale_relevance_impact += entry.getValue().getRelevanceImpact();
    		}

		return this.value = totale_relevance_impact / this.related_arguments.size();
	}
	
	public void addArgument(Argument arg0)
	{
		this.related_arguments.put(arg0.getName(),arg0);
	}
}
