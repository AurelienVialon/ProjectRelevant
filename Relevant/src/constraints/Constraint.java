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
public class Constraint implements Observer
{
	private ArrayList<Term<?>> expression = new ArrayList<Term<?>>();
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
	
	@Override 
	public void update(Observable arg0, Object arg1) 
	{
		
	}
	public Term<?> test()
	{
		Term<?> ret = null;
		
		for(Term<?> t : this.expression)
		{
			if(!t.get_Checking())
			{
				ret = t;
				break;
			}
		}	
		return ret;
	}
	public void build()
	{
		for( Argument a : this.relevance.maximise())
		{
			this.expression.add(a.getRule());
		}
	}
	public int RelevanceCalculation()
	{
		return this.relevance.calculate();
	}
	public ArrayList<Object> RelevanceCalculationDetailed()
	{
		return this.relevance.calculateDetailed();
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
}
