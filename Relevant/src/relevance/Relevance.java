package relevance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import exampleWorkingAgents.ArgumentName;

public class Relevance
{
	private int value;
	private ArrayList<Argument> activeArguments = new ArrayList<>();
	private HashMap<ArgumentName, ArrayList<Argument>> related_arguments;
	
	public Relevance()
	{
		this.related_arguments = new HashMap<>();
		this.activeArguments = new ArrayList<Argument>();
	}
	public Relevance(HashMap<ArgumentName, ArrayList<Argument>> arg0) 
	{
		this.related_arguments = arg0;
		this.activeArguments = new ArrayList<Argument>();
	}
	
	public int calculate()
	{
		int totale_relevance_impact = 0;
		
		this.maximise();
      	for (Argument a : this.activeArguments) 
    	{
    		totale_relevance_impact += a.calculateRelevanceImpact();
    	}

		return this.value = totale_relevance_impact / this.related_arguments.size();
	}
	public ArrayList<Object> calculateDetailed()
	{
		int totale_relevance_impact = 0, current_relevance_impact = 0;
		String details = "";
		
		this.maximise();
      	for (Argument a : this.activeArguments) 
    	{
      		current_relevance_impact = a.calculateRelevanceImpact();
      		details+= "\n Relevance impact of " + a.getName() + " is " + current_relevance_impact;
    		totale_relevance_impact += current_relevance_impact;
    	}
      	int relevance = this.value = totale_relevance_impact / this.related_arguments.size();
		
      	ArrayList<Object> ret = new ArrayList<>();
      		ret.add(relevance);
      		ret.add(totale_relevance_impact);
      		ret.add(this.activeArguments.size());
      		ret.add(details);
        return ret;
	}
	//Method maximise() returns an ArrayList of all chosen arguments which maximise the relevance.
	public ArrayList<Argument> maximise()
	{
        this.activeArguments.clear();
        
        int max = 0, oldMax = 0;
		boolean flag;
        
		for (Map.Entry<ArgumentName,ArrayList<Argument>> entry : this.related_arguments.entrySet()) 
    	{	
			max = 0;
			oldMax = 0;
			flag = false;
			Argument akeep = null;
			
			for( Argument a : entry.getValue())
			{
				max = a.calculateRelevanceImpact();
				if(max > oldMax || !flag)
				{
					flag = true;
					oldMax = max;
					akeep = a;
				}
			}
			if(akeep!= null) {this.activeArguments.add(akeep);}
    	}
		return this.activeArguments;
	}
	
	public void addArgument(Argument arg0)
	{
		ArrayList<Argument> list = this.related_arguments.get(arg0.getName());
		if(list != null)
			list.add(arg0);
		else
		{
			list = new ArrayList<>();
			list.add(arg0);
			this.related_arguments.put(arg0.getName(),list);
		}
	}
}
