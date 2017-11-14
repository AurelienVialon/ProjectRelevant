package exampleWorkingAgents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import MVC.Commande;
import MVC.Message;
import constraints.Constraint;
import constraints.Term;
import model.Action;
import model.Engine;
import parameters.Parameter;
import relevance.Argument;

public class WorkingAgent extends Engine 
{
	public WorkingAgent(String arg0) 
	{
		super(arg0);
		this.InitParameters();
		this.InitConstraints();
		this.buildConstraints();	
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
		this.param.addParameter(new Parameter<Integer>(ParameterName.WorkNeeds, 35, "hours"));
		this.param.addParameter(new Parameter<Integer>(ParameterName.HoursWorkedWeek, 0, "hours"));
		//this.param.put("WorkDidWeek", new Parameter<Integer>("WorkDidWeek", 0, "hours/week"));
		//this.param.put("WorkDidDay", new Parameter<Integer>("WorkDidDay", 0, "hour"));
		
		this.param.addParameter(new Parameter<Integer>(ParameterName.RestNeeds, 0, "hour"));
		//this.param.put("RestDid", new Parameter<Integer>("RestDid", 0, "hour"));
		this.param.addParameter(new Parameter<Boolean>(ParameterName.Sick, false));	
		this.param.addParameter(new Parameter<Boolean>(ParameterName.NothingElseToDo, false));	
	}
	private void InitConstraints()
	{
		//Initialisation of Constraint NormalTime
	
		Term<?> rule = new Term() 
		{
			@Override public boolean expression() 
			{ 
				return (int)param.get(ParameterName.HoursWorkedWeek).getValue() <= (int)param.get(ParameterName.NormalWorkingTime).getValue();
			}
		};
		Term<?> assumption = new Term()
		{
			//Assumption saying that the normal working hours are enough for the work.
			@Override public boolean expression() 
			{ 
				return (int)param.get(ParameterName.NormalWorkingTime).getValue() <= (int)param.get(ParameterName.WorkNeeds).getValue() ;
			}
		};
		ArrayList<Argument> argList = new ArrayList<>();
		

		argList.add(new Argument(ArgumentName.enoughToFinish, assumption, rule ) 
		{
			@Override
			public int calculateRelevanceImpact()
			{
				return this.MappedRelevance(100);
			}
		});
		
		//Argument NothingElseToDo
		assumption = new Term()
		{
			//Assumption saying that the normal working hours are enough for the work.
			@Override public boolean expression() 
			{ 
				return !(boolean)param.get(ParameterName.NothingElseToDo).getValue() ;
			}
		};
		argList.add(new Argument(ArgumentName.NothingElseToDo, assumption, rule ) 
		{
			@Override
			public int calculateRelevanceImpact()
			{
				return this.MappedRelevance(100);
			}
		});
		
		//Arguments NotTired
		//First Version
		assumption = new Term()
		{
			@Override public boolean expression() 
			{ 
				return (int)param.get(ParameterName.RestNeeds).getValue() >= 30 ;
			}
		};
		argList.add(new Argument(ArgumentName.Tired, assumption, rule ) 
		{
			@Override
			public int calculateRelevanceImpact()
			{
				return this.MappedRelevance(3 * (int)param.get(ParameterName.RestNeeds).getValue());
			}
		});		
		//Second Version
		assumption = new Term()
		{
			@Override public boolean expression() 
			{ 
				return (int)param.get(ParameterName.RestNeeds).getValue() >= 20 && (int)param.get(ParameterName.RestNeeds).getValue() < 30;
			}
		};
		argList.add(new Argument(ArgumentName.Tired, assumption, rule ) 
		{
			@Override
			public int calculateRelevanceImpact()
			{
				return this.MappedRelevance(2 * (int)param.get(ParameterName.RestNeeds).getValue());
			}
		});
		//Third Version
		assumption = new Term()
		{
			@Override public boolean expression() 
			{ 
				return (int)param.get(ParameterName.RestNeeds).getValue() >= 10 && (int)param.get(ParameterName.RestNeeds).getValue() < 20;
			}
		};
		argList.add(new Argument(ArgumentName.Tired, assumption, rule ) 
		{
			@Override
			public int calculateRelevanceImpact()
			{
				return this.MappedRelevance((int)param.get(ParameterName.RestNeeds).getValue());
			}
		});
		//Fourth Version
				assumption = new Term()
				{
					@Override public boolean expression() 
					{ 
						return (int)param.get(ParameterName.RestNeeds).getValue() >= 0 && (int)param.get(ParameterName.RestNeeds).getValue() < 10;
					}
				};
				argList.add(new Argument(ArgumentName.Tired, assumption, rule ) 
				{
					@Override
					public int calculateRelevanceImpact()
					{
						return this.MappedRelevance(0);
					}
				});
				
		
		//No Absents Argument
		assumption = new Term()
		{
			@Override public boolean expression() 
			{ 
				return (int)param.get(ParameterName.NumberOfWorkers).getValue() == (int)param.get(ParameterName.NormalNumberofWorkers).getValue();
			}
		};
		argList.add(new Argument(ArgumentName.Absents, assumption, rule ) 
		{
			@Override
			public int calculateRelevanceImpact()
			{
				return this.MappedRelevance(100);
			}
		});
		
		//The Constraint is created.
		this.qcs.add(this.MakeConstraint(ConstraintName.EnoughForWork, argList));
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
	     	this.checkQCs();
	     	
	     	if(this.violated_qcs.size() == 0)
	     	{
	     		System.out.println(" All quality constraints are satisfied");
	     	}
	     	else
	     		{
	     			for(Constraint cons : this.violated_qcs)
	    	     	{
	    	     		System.out.println(cons.getName() + " is violated !");
	    	     	}
	     		}
		}
		else if(c.donneType().matches("DISPLAY_PARAMETERS_VALUES"))
		{
	     	for (Map.Entry<ParameterName, Parameter<?>> entry : this.param.get().entrySet()) 
	    		{
	     			System.out.println("For " + this.Name +", value of  " + entry.getKey() + " is " + entry.getValue().getValue() +" " + entry.getValue().getUnit());
	    		}
		}
		else if(c.donneType().matches("DECIDE"))
		{
	     	apply(this.decide());
		}
		else if(c.donneType().matches("RELEVANCECALCULATION"))
		{
			this.calculateRelevance();
		}
		else if(c.donneType().matches("RELEVANCECALCULATIONDETAILED"))
		{
			this.calculateRelevanceDetailed();
		}
	}
	
	public void calculateRelevance()
	{
		Constraint c;
		
		HashMap<ConstraintName, Constraint> hm = this.qcs.getConstraints();
		for(Map.Entry<ConstraintName, Constraint> entry : hm.entrySet())
		{
			c = ((Constraint)entry.getValue());
			System.out.println("\n For " + c.getName() + " of " + this.Name + ", the value of the relevance is: " + c.RelevanceCalculation());
		}
	}
	public void calculateRelevanceDetailed()
	{
		Constraint c;
		ArrayList<Object> ret;
		HashMap<ConstraintName, Constraint> hm = this.qcs.getConstraints();
		for(Map.Entry<ConstraintName, Constraint> entry : hm.entrySet())
		{
			c = ((Constraint)entry.getValue());
			ret = c.RelevanceCalculationDetailed();
			System.out.println("\n For " + c.getName() + " of " + this.Name + ", the value of the relevance is: " + ret.get(0));
			System.out.println("\n Total Relevance Impact was " + ret.get(1) + " with a number of active arguments of " + ret.get(2));
			System.out.println("\n Details :\n" + ret.get(3));
		}
	}
	public void apply(Action arg0)
	{
		
	}
}
