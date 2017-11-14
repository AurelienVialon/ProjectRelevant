/**
 * 
 */
package model;

import java.util.ArrayList;

import Constraints.Constraint;
import Constraints.ConstraintsManager;
import MVC.Modele;
import Parameters.Parameter;
import Parameters.Parameters;

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
	
		
	public synchronized Parameter<?> getParameter(String arg0)
	{
		return this.param.get(arg0);
	}
	public synchronized <T> void modifParameter(String arg0, T arg1)
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
	public Action decide()
	{
		return null;
	}
}
