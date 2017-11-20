/**
 * 
 */
package relevance.constraint;

import java.util.ArrayList;
import java.util.TreeSet;

import MVC.Message;
import MVC.SujetMessage;
import exampleWorkingAgents.ConstraintName;
import exampleWorkingAgents.ParameterName;
import model.Engine;
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
	private int MinimumSuccessRate = 100;

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
		
		System.out.println("Others definitions are :");
		
		for(Term t : this.relevance.getRules())
			if(t != this.expression){System.out.println("\t" + t.getName());}	
	}
	public void reassess()
	{
		this.expression = this.RelevanceMaximise();
	}
	public void reassessDetailed(Engine e)
	{
		e.notifie("\n\t Starting of reassessment of " + this.getName() + " constraint.");
		
		Term t = this.RelevanceMaximiseDetailed(e);
		if(t!= this.expression)
		{
			e.notifie(new Message(SujetMessage.ConstraintChange, "\n Changement of the definition of Constraint " + this.getName() + " from " + this.expression.getName() + " to " + t.getName()));
		}	
		else
		{
			e.notifie("\n No change into the constraint definition");
		}
		this.expression = t;
	}
	
	private Term RelevanceMaximise()
	{
		return this.relevance.maximise();
	}
	private Term RelevanceMaximiseDetailed(Engine e)
	{
		return this.relevance.maximiseDetailed(e);
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
		return this.relevance.getValueRelevance();
	}
	public int getRelevanceDetailed(Engine e)
	{
		this.reassessDetailed(e);
		return this.relevance.getValueRelevance();
	}
	public TreeSet<ParameterName> sensibleTo()
	{
		return this.relevance.getTriggers();
	}
	public boolean relax()
	{
		boolean ret = false;
		
		if(this.SuccessRate > this.MinimumSuccessRate && 
				this.MinimumSuccessRate > this.getRelevance())
		{
			this.MinimumSuccessRate = this.getRelevance();
			ret = true;
		}
		
		return ret;
	}
}
