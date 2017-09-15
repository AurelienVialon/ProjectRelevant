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

	private int ExpectedSuccessRate = 100;
	private int SuccessRate = 0;
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
	
	public void setExpectedSuccessRate(int arg0)
	{
		this.ExpectedSuccessRate = arg0;
		
		if(this.ExpectedSuccessRate < 0)
			this.ExpectedSuccessRate = 0;
		else if(this.ExpectedSuccessRate > 100)
			this.ExpectedSuccessRate = 100;
	}
	public int getExpectedSuccessRate()
	{
		return this.ExpectedSuccessRate;
	}
	
	public void setSuccessRate(int arg0)
	{
		this.SuccessRate = arg0;
	}
	public int getSuccessRate()
	{
		return this.SuccessRate;
	}
	
	public void checkRelevance()
	{
		this.r.calculation();
	}
	public int getRelevance()
	{
		return this.r.getValue();
	}
	
	public boolean Justify(Argument<?> arg0)
	{
		if(this.r == null)
				this.r = new Relevance();
		
		return this.AttacheArgument(arg0);
	}
	public boolean Justify(ArrayList<Argument<?>> arg0)
	{
		if(this.r == null)
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
