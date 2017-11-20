package relevance;

import java.util.HashMap;
import java.util.Map;

import exampleWorkingAgents.ArgumentName;
import model.Engine;

public class SetOfArguments
{
	private HashMap<ArgumentName, ArgumentVersions> memory = new HashMap<>();	
	
	public SetOfArguments(Argument arg0)
	{
		this.memory.put(arg0.getName(), new ArgumentVersions(arg0));
	}
	
	public void add(Argument arg0)
	{
		if(this.memory.containsKey(arg0.getName()))
		{
			this.memory.get(arg0.getName()).add(arg0);
		}
		else
		{
			this.memory.put(arg0.getName(), new ArgumentVersions(arg0));
		}
	}
	
	public int maximise()
	{
		int result = 0;
		
		for(Map.Entry<ArgumentName, ArgumentVersions> entry : this.memory.entrySet())
		{
			result += entry.getValue().maximise();
		}
		
		return result;
	}
	public int maximiseDetailed(Engine e)
	{
		int result = 0;
		String s1 = "";
		
		for(Map.Entry<ArgumentName, ArgumentVersions> entry : this.memory.entrySet())
		{
			e.notifie("\n\t Starting task of the maximisation of the set of argument's versions " + entry.getKey());
			result += entry.getValue().maximiseDetailed(e);
		}
		return result;
	}
	public int size()
	{
		return this.memory.size();
	}
}
