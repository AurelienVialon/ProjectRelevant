/**
 * 
 */
package logic;

import java.util.HashMap;
import java.util.Map;

import Parameters.Parameter;

/**
 * @author aurel
 *
 */
public class Term<T>
{
	protected T value_left;
	protected T value_right;	
	
	protected Parameter<T> linked_parameter_left;
	protected Parameter<T> linked_parameter_right;	

	String operator = "";
	/**
	 * 
	 */
	public Term(Parameter<T> arg0, Parameter<T> arg1) 
	{
		this.linked_parameter_left = arg0;
		this.linked_parameter_right = arg1;
	}
	public Term(Parameter<T> arg0, Parameter<T> arg1, String arg2) 
	{
		this.linked_parameter_left = arg0;
		this.linked_parameter_right = arg1;
		this.operator = arg2;
	}

	public Term(T arg0, Parameter<T> arg1) 
	{
		this.value_left = arg0;
		this.linked_parameter_right = arg1;
	}
	public Term(Parameter<T> arg0, T arg1) 
	{
		this.linked_parameter_left = arg0;
		this.value_right = arg1;
	}
	
	public Term(T arg0, Parameter<T> arg1, String arg2) 
	{
		this.value_left = arg0;
		this.linked_parameter_right = arg1;
		this.operator = arg2;
	}
	public Term(Parameter<T> arg0, T arg1, String arg2) 
	{
		this.linked_parameter_left = arg0;
		this.value_right = arg1;
		this.operator = arg2;
	}
	
	public boolean contains (Parameter<?> v)
	{
		if(this.linked_parameter_left == v || this.linked_parameter_right == v)
			return true;
		return false;
	}
	
	public  Map<String, Parameter<T>> getParameters ()
	{
		Map<String, Parameter<T>> ret = new HashMap<>();		
		ret.put("LEFT", this.linked_parameter_left);
		ret.put("RIGHT", this.linked_parameter_right);
	return ret;
	}
	public Map<String, T> getCurrentValues ()
	{
		Map<String, T> ret = new HashMap<>();		
			ret.put("LEFT", this.value_left);
			ret.put("RIGHT", this.value_right);
		return ret;
	}
	
	public void update()
	{
		if(this.linked_parameter_left != null)
			this.value_left = this.linked_parameter_left.getValue();
		if(this.linked_parameter_right !=  null)
			this.value_right = this.linked_parameter_right.getValue();
	}
	
	public boolean test()
	{	
		this.update();	
		return this.operate();
	}
	
	private boolean operate()
	{
		boolean ret =  false;
		
		if(!this.operator.matches(""))
		{
			if(this.operator.matches("<") && (int)this.value_left < (int)this.value_right )
			{
				ret = true;
			}
			else if(this.operator.matches(">") && (int)this.value_left > (int)this.value_right )
			{
				ret = true;
			}
			else if(this.operator.matches("=") && (int)this.value_left == (int)this.value_right )
			{
				ret = true;
			}
		}	
		return ret;
	}
}
