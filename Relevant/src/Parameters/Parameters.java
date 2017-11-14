package Parameters;

import java.util.HashMap;
import java.util.Map;

public class Parameters 
{
	Map<String, Parameter<?>> parameters = new HashMap<>();
	
	public Parameters() 
	{
		// TODO Auto-generated constructor stub
	}

	
	public void addParameter(Parameter<?> arg0)
	{
		this.parameters.put(arg0.getName(), arg0);
	}
	
	public Parameter<?> get(String arg0)
	{
		return this.parameters.get(arg0);
	}
	public Map<String, Parameter<?>> get()
	{
		return this.parameters;
	}
}
