package exampleWorkingAgents;

import java.util.ArrayList;
import java.util.Map;

import Constraints.Constraint;
import MVC.Commande;
import MVC.Message;
import Parameters.Parameter;
import logic.Term;
import model.Action;
import model.Engine;

public class WorkingAgent extends Engine 
{
	public WorkingAgent(String arg0) 
	{
		super(arg0);
		this.InitParameters();
		this.InitConstraints();
			
	}
	public WorkingAgent(String arg0, Parameter<?> arg1) 
	{
		super(arg0);
		this.param.addParameter(arg1);
		this.InitParameters();
		this.InitConstraints();
	}
	public WorkingAgent(String arg0, ArrayList <Parameter<?>> arg1) 
	{
		super(arg0);	
		for(Parameter<?> p : arg1)
			this.param.addParameter(p);
		this.InitParameters();
		this.InitConstraints();
	}
	
	private void InitParameters()
	{
		this.param.addParameter(new Parameter<Integer>("WorkNeeds", 35, "hours"));
		this.param.addParameter(new Parameter<Integer>("HoursWorkedWeek", 0, "hours"));
		//this.param.put("WorkDidWeek", new Parameter<Integer>("WorkDidWeek", 0, "hours/week"));
		//this.param.put("WorkDidDay", new Parameter<Integer>("WorkDidDay", 0, "hour"));
		
		this.param.addParameter(new Parameter<Integer>("RestNeeds", 0, "hour"));
		//this.param.put("RestDid", new Parameter<Integer>("RestDid", 0, "hour"));
		this.param.addParameter(new Parameter<Boolean>("Sick", false));	
	}
	private void InitConstraints()
	{
		//Initialisation of Constraint NormalTime
		//The Constraint
		this.qcs.builds("NormalTime", new Term() 
		{
			@Override public boolean expression() 
			{ 
				return (int)param.get("HoursWorkedWeek").getValue() <= (int)param.get("NormalWorkingTime").getValue();
			}
		});
		//The Related Arguments :
		//Argument NormalTime is enough to finish the job
		
	}
	@Override
	public void Reinit() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void Maj() 
	{
		// TODO Auto-generated method stub		
	}
	@Override
	protected <T> void executerCommande(Commande<T> c) 
	{
		if(c.donneType().matches("QC_CHECK"))
			{
				ArrayList<Constraint> cl = this.qcs.check();
				
				if(!cl.isEmpty())
				{
					String ret = "For " + this.Name;
					for(Constraint ct : cl)
						ret+="\n The constraint " + ct.getName() +" is violated.";
					System.out.println(ret);
				}
				else
				{
					System.out.println("All constraints of " + this.Name +" are satisfied");
				}
			}
		else if(c.donneType().matches("DISPLAY_PARAMETERS_VALUES"))
		{
	     	for (Map.Entry<String, Parameter<?>> entry : this.param.get().entrySet()) 
	    		{
	     			System.out.println("For " + this.Name +", value of  " + entry.getKey() + " is " + entry.getValue().getValue() +" " + entry.getValue().getUnit());
	    		}
		}
		else if(c.donneType().matches("DECIDE"))
		{
	     	apply(this.decide());
		}
	}
	
	public void apply(Action arg0)
	{
		
	}
}
