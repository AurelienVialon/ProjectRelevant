/**
 * 
 */
package Constraints;

import java.util.ArrayList;

import Argument.Argument;
import relevance.Relevance;

/**
 * @author aurel
 *
 */
public class QualityConstraint extends Constraint
{
	private Relevance r;

	//For later// ArrayList<SoftGoal> linked_SG;
	/**
	 * 
	 */
	/*public QualityConstraint() 
	{
		super();
		this.linked_SG = new ArrayList<SoftGoal>();
	}*/
	public QualityConstraint(String arg0, Term<?> arg1)
	{
		super(arg0, arg1);
	}
	public QualityConstraint(String arg0, Term<?> arg1, boolean arg2)
	{
		super(arg0, arg1, arg2);
	}
	
	public boolean Justify(Argument<?> arg0)
	{
		this.r = new Relevance();
		
		return this.AttacheArgument(arg0);
	}
	public boolean Justify(ArrayList<Argument<?>> arg0)
	{
		this.r = new Relevance();
		boolean ret = true;
		
		for (Argument<?> a : arg0)
			ret = this.AttacheArgument(a);
		
		return ret;
	}
	
	private boolean AttacheArgument(Argument<?> arg0)
	{
		boolean ret = true;
		
		if(this.expression.contains(arg0))
			this.r.addArgument(arg0);
		
		return ret;
	}
	
	
}
