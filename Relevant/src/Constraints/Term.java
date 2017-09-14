/**
 * 
 */
package Constraints;

import java.util.ArrayList;

import Parameters.Parameter;

/**
 * @author aurel
 *
 */
public class Term<T>
{
	protected Parameter<T> left;
	protected Parameter<T> right;	
	String operator;
	/**
	 * 
	 */
	public Term(Parameter<T> arg0, Parameter<T> arg1, String arg2) 
	{
		this.left = arg0;
		this.right = arg1;
		this.operator = arg2;
	}
	public boolean contains (Parameter<?> v)
	{
		if(this.left == v || this.right == v)
			return true;
		return false;
	}
	
	public ArrayList<Object> getVariables ()
	{
		ArrayList<Object> ret = new ArrayList<Object>();		
			ret.add(this.left);
			ret.add(this.right);
		return ret;
	}
	
	public boolean test()
	{	
		return this.operate(this.left.getValue(), this.right.getValue());
	}
	
	public boolean operate(T v1, T v2)
	{
		boolean ret =  false;
		if(this.operator.matches("<") && (int)v1 < (int)v2 )
		{
			ret = true;
		}
		else if(this.operator.matches(">") && (int)v1 > (int)v2 )
		{
			ret = true;
		}
		else if(this.operator.matches("=") && (int)v1 == (int)v2 )
		{
			ret = true;
		}
		return ret;
	}
}
