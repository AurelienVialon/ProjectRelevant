package parameters;

import java.util.HashMap;
import java.util.Map;

import exampleWorkingAgents.ParameterName;

public class Parameters 
{
	Map<ParameterName, Parameter<?>> parameters = new HashMap<>();
	Parameters external;
	
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
		Parameter<?> ret = null;
		if(this.parameters.containsKey(arg0))
		{
			ret = this.parameters.get(arg0);
		}
		else if(this.external != null &&
				this.external.contains(arg0))
		{
			ret = this.external.get(arg0);
		}
		return ret;
	}
	public Map<ParameterName, Parameter<?>> get()
	{
		return this.parameters;
	}
	public boolean contains(Object arg0)
	{
		return this.parameters.containsKey(arg0);
	}
	public void addExternalParameters(Parameters arg0)
	{
		this.external = arg0;
	}
}
