/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.Observable;

import MVC.Modele;
import constraints.Constraint;
import constraints.ConstraintsManager;
import exampleWorkingAgents.ConstraintName;
import exampleWorkingAgents.ParameterName;
import parameters.Parameter;
import parameters.Parameters;
import relevance.Argument;

/**
 * @author Aurélien Vialon
 *
 */
public abstract class Engine extends Modele
{
	protected Parameters param = new Parameters();
	protected ConstraintsManager qcs;
	protected ArrayList<Constraint> violated_qcs = new ArrayList<>();

	/**
	 * 
	 */

	public Engine(String arg0)
	{
		super(arg0);
		this.qcs = new ConstraintsManager();
	}
	
	public void setQualityConstraintsManager(ConstraintsManager arg0)
	{
		this.qcs = arg0;
	}
	
	public boolean checkQCs()
	{
		this.violated_qcs.clear();
		
		this.violated_qcs = this.qcs.check();
		
		return !this.violated_qcs.isEmpty();
	}
	
		
	public synchronized Parameter<?> getParameter(ParameterName arg0)
	{
		return this.param.get(arg0);
	}
	public synchronized <T> void modifParameter(ParameterName arg0, T arg1)
	{
		Parameter<T> p = (Parameter<T>)this.param.get(arg0);
		if(p!= null)
		{
			synchronized(p)
			{
				p.setValue(arg1);
			}
		}	
	}
	
	public Constraint MakeConstraint(ConstraintName arg0, Argument arg1)
	{
		return new Constraint(arg0, arg1);
	}
	public Constraint MakeConstraint(ConstraintName arg0, ArrayList<Argument> arg1)
	{
		return new Constraint(arg0, arg1);
	}
	
	
	public void buildConstraints()
	{
	 	this.qcs.build();

	}
	public Action decide()
	{
		return null;
	}
	
	
	@Override
	public void update(Observable arg0, Object arg1) 
	{
		if(arg0 instanceof Parameter<?>)
		{
			Parameter<?> p = (Parameter<?>)arg0;
			  System.out.println(" Modification of the Parameter " + p.getName() + " detected.");
		}
	}
}
