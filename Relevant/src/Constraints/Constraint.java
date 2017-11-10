/**
 * 
 */
package Constraints;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import logic.Term;

/**
 * @author aurel
 *
 */
public abstract class Constraint implements Observer
{
	protected ArrayList<Term<?>> expression = new ArrayList<Term<?>>();
	protected String name;
	protected boolean active = true;
	/**
	 * 
	 */
	
	//Constructors
	public Constraint(String arg0){this.name = arg0;}
	public Constraint(String arg0, Term<?> arg1){this.name = arg0; this.expression.add(arg1);}
	public Constraint(String arg0, Term<?> arg1, boolean arg2){this.name = arg0; this.expression.add(arg1);this.active = arg2;}
	
	public Constraint(Term<?> arg1){this.expression.add(arg1);}
	public Constraint(Term<?> arg1, boolean arg2){this.expression.add(arg1);this.active = arg2;}
	
	public boolean  getActive()
	{
		return this.active;
	}
	
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
	
	public String getName()
	{
		return this.name;
	}
}
