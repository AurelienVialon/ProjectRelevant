package parameters;

import java.util.HashMap;
import java.util.Map;

import exampleWorkingAgents.ParameterName;

public class Parameters 
{
	Map<ParameterName, Parameter<?>> parameters = new HashMap<>();
	
	public Parameters() 
	{
		// TODO Auto-generated constructor stub
	}

	
	public void addParameter(Parameter<?> arg0)
	{
		this.parameters.put(arg0.getName(), arg0);
	}
	
	public Parameter<?> get(ParameterName arg0)
	{
		return this.parameters.get(arg0);
	}
	public Map<ParameterName, Parameter<?>> get()
	{
		return this.parameters;
	}
	public boolean contains(Object arg0)
	{
		return this.parameters.containsKey(arg0);
	}
}
