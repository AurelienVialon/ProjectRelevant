/**
 * 
 */
package Argument;

import Parameters.Parameter;

/**
 * @author aurel
 *
 */
public class FactToCheck<T>
{
	/**
	 * 
	 */
	
	Parameter<T> linked;
	T valueToCheck;
	
	public FactToCheck(Parameter arg0, T arg1) 
	{
		this.linked = arg0;
		this.valueToCheck = arg1;
	}

	public boolean check()
	{
		return this.linked.getValue() == this.valueToCheck;
	}
}
