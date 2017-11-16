/**
 * 
 */
package constraints;

import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import exampleWorkingAgents.ConstraintName;
import relevance.Argument;
import relevance.Relevance;

/**
 * @author aurel
 *
 */
public class Constraint
{
	private Term expression = new Term();
	private ConstraintName name;
	private boolean active = true;
	
	private Relevance relevance = new Relevance(); 
	
	private int Relaxed = 0;
	private int SuccessRate = 0;
	
	/**
	 * 
	 */
	
	//Constructors
	public Constraint(ConstraintName arg0){this.name = arg0;}
	//public Constraint(String arg0, Term<?> arg1){this.name = arg0; this.expression.add(arg1);}
	//public Constraint(String arg0, Term<?> arg1, boolean arg2){this.name = arg0; this.expression.add(arg1);this.active = arg2;}
	
	//public Constraint(Term<?> arg1){this.expression.add(arg1);}
	//public Constraint(Term<?> arg1, boolean arg2){this.expression.add(arg1);this.active = arg2;}
	
	public Constraint(ConstraintName arg0, Argument arg1)
	{
		this.name = arg0;
		this.addArgument(arg1);
		this.build();
	}
	public Constraint(ConstraintName arg0, ArrayList<Argument> arg1)
	{
		this.name = arg0;
		this.addArguments(arg1);
		this.build();
	}
	
	public boolean  getActive()
	{
		return this.active;
	}
	public Term test()
	{
		Term ret = null;
		
		if(this.expression.get_Checking())
		{
			ret = this.expression;
		}
		return ret;
	}
	public void build()
	{
		this.expression =  this.relevance.maximise();
		System.out.println(" The definition of " + this.name + " will  be: " + this.expression.getName() + "\n");

	}
	public void reassess()
	{
		this.expression = this.RelevanceMaximise();
	}
	public void reassessDetailed()
	{
		this.expression = this.RelevanceMaximiseDetailed();
	}
	
	private Term RelevanceMaximise()
	{
		return this.relevance.maximise();
	}
	private Term RelevanceMaximiseDetailed()
	{
		return this.relevance.maximiseDetailed();
	}
	
	public ConstraintName getName()
	{
		return this.name;
	}
	public void addArgument(Argument arg0)
	{
		this.relevance.addArgument(arg0);
	}
	public void addArguments(ArrayList<Argument> arg0)
	{
		for(Argument arg : arg0)
			this.relevance.addArgument(arg);
	}
	public int getRelevance()
	{
		this.reassess();
		return this.relevance.getValueRelevance();
	}
	public int getRelevanceDetailed()
	{
		this.reassessDetailed();
		return this.relevance.getValueRelevance();
	}
}
