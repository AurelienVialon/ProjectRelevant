package relevance;

import java.util.ArrayList;

public class ArgumentVersions 
{
	Argument active = null;
	ArrayList<Argument> memory = new ArrayList<>();
	
	public ArgumentVersions(Argument arg0) 
	{	
		super();
		this.memory.add(arg0);
	}
	
	public void add (Argument arg0)
	{
		this.memory.add(arg0);
	}	
	
	//Method maximise() returns argument which maximise the relevance.
	public int maximise()
	{
        this.active = null;
        
        int max = 0, best = 0;
		boolean flag;
        
		max = 0;
		best = 0;
		flag = false;
		Argument akeep = null;
		
		for (Argument a : this.memory) 
    	{	
			max = a.calculateRelevanceImpact();
			if(max > best || !flag)
			{
				flag = true;
				best = max;
				akeep = a;
			}
			if(akeep!= null) {this.active = akeep;}
    	}
		return best;
	}
	public int maximiseDetailed()
	{
        String s = "", relevanceImpacts = "Relevance Impacts :";
        int max = 0, best = 0;
		boolean flag;
        
		max = 0;
		best = 0;
		flag = false;
		Argument akeep = null;
		
		for (Argument a : this.memory) 
    	{	
			max = a.calculateRelevanceImpact();
			if(max > best || !flag)
			{
				flag = true;
				best = max;
				akeep = a;
			}
			relevanceImpacts+= " Version" + a.getVersion() + "=" + max;
    	}
		if(akeep!= null) 
		{
			if(this.active != akeep)
			{
				if(this.active == null)
				{
					s+= "\n\t\t Set of a new active argument : " + akeep.getName();
				}
				else
				{
					s+= "\n\t\t After the maximisation of the set of Arguments it appears that " + this.active.getName() +" Version" + this.active.getVersion() + " should be changed to Version" + akeep.getVersion();
				}
				
				this.active = akeep;
			}
			else
			{
				s+="\n\t\t System decided to keep " + this.active.getName() + " Version" + this.active.getVersion();
			}
		}
		System.out.println(s);
		System.out.println("because : " + relevanceImpacts);
		return best;
	}
}
