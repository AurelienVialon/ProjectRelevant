/**
 * 
 */
package Constraints;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * @author aurel
 *
 */
public class Constraint implements Observer
{
	ArrayList<Term<?>> expression = new ArrayList<Term<?>>();
	/**
	 * 
	 */
	public Constraint(){}
	public Constraint(Term<?> arg0){this.expression.add(arg0);}

	@Override
	public void update(Observable arg0, Object arg1) 
	{
		
	}
	public Term<?> test()
	{
		Term<?> ret = null;
		
		for(Term<?> t : this.expression)
		{
			if(!t.test())
			{
				ret = t;
				break;
			}
		}	
		return ret;
	}
}
