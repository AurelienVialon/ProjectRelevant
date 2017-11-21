package exampleWorkingAgents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import MVC.Commande;
import model.Action;
import model.Engine;
import parameters.Parameter;
import parameters.Parameters;
import relevance.Argument;
import relevance.Assumption;
import relevance.constraint.Constraint;
import relevance.constraint.Term;

public class WorkingAgent extends Engine 
{
	public WorkingAgent(String arg0) 
	{
		super(arg0, false, 1000);
		this.InitInternalParameters();
		this.InitConstraints();
		this.buildConstraints();	
	}
	public WorkingAgent(String arg0, Parameters arg1) 
	{
		super(arg0, false, 1000);	
		this.addExternalParameters(arg1);
		this.InitInternalParameters();
		this.InitConstraints();
	}
	
	private void InitInternalParameters()
	{
		this.addParameter(new Parameter<Integer>(ParameterName.WorkNeeds, 35, "hours"));
		this.addParameter(new Parameter<Integer>(ParameterName.HoursWorkedWeek, 0, "hours"));
		//this.param.put("WorkDidWeek", new Parameter<Integer>("WorkDidWeek", 0, "hours/week"));
		//this.param.put("WorkDidDay", new Parameter<Integer>("WorkDidDay", 0, "hour"));
		
		this.addParameter(new Parameter<Integer>(ParameterName.RestNeeds, 0, "hour"));
		//this.param.put("RestDid", new Parameter<Integer>("RestDid", 0, "hour"));
		this.addParameter(new Parameter<Boolean>(ParameterName.Sick, false));	
		this.addParameter(new Parameter<Boolean>(ParameterName.NothingElseToDo, false));	
	}
	private void InitConstraints()
	{
		this.InitConstraintNormalTime();
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
	public void apply(Action arg0)
	{
		
	}
	private void addParameter(Parameter<?> arg0)
	{
		this.param.addParameter(arg0);
		arg0.addObserver(this);
	}
	@Override
	protected void processus() 
	{
		if(this.checkQCs())
		{
			for(Constraint cons : this.violated_qcs)
	     	{
	     		System.out.println(cons.getName() + " is violated !");
	     	}
		}
		else
		{
			System.out.println(" There is no violated constraints.");
		}
	}
	private void InitConstraintRest()
	{
		Term best = new Term("RestNeed < 85") 
		{
			@Override public boolean expression() 
			{ 
				return (int)param.get(ParameterName.RestNeeds).getValue() <= 85;
			}
		};
		Term bad = new Term("HoursWorkedWeek < NormalWorkingTime") 
		{
			@Override public boolean expression() 
			{ 
				return (int)param.get(ParameterName.HoursWorkedWeek).getValue() <= (int)param.get(ParameterName.NormalWorkingTime).getValue();
			}
		};
	}
	private void InitConstraintNormalTime()
	{
		//Initialisation of Constraint NormalTime
		
		Term rule = new Term("HoursWorkedWeek < NormalWorkingTime") 
		{
			@Override public boolean expression() 
			{ 
				return (int)param.get(ParameterName.HoursWorkedWeek).getValue() <= (int)param.get(ParameterName.NormalWorkingTime).getValue();
			}
		};
		Term rule2 = new Term("HoursWorkedWeek == TimeNeeds") 
		{
			@Override public boolean expression() 
			{ 
				return (int)param.get(ParameterName.HoursWorkedWeek).getValue() == (int)param.get(ParameterName.WorkNeeds).getValue();
			}
		};
		
		
		Assumption assumption = new Assumption(ParameterName.NormalWorkingTime, ParameterName.WorkNeeds)
		{
			//Assumption saying that the normal working hours are enough for the work.
			@Override public boolean expression() 
			{ 
				return (int)param.get(ParameterName.NormalWorkingTime).getValue() >= (int)param.get(ParameterName.WorkNeeds).getValue() ;
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
		assumption = new Assumption(ParameterName.NothingElseToDo)
		{
			//Assumption saying that the normal working hours are enough for the work.
			@Override public boolean expression() 
			{ 
				return !(boolean)param.get(ParameterName.NothingElseToDo).getValue() ;
			}
		};
		argList.add(new Argument(ArgumentName.NothingElseToDo, assumption, rule, rule2 ) 
		{
			@Override
			public int calculateRelevanceImpact()
			{
				return this.MappedRelevance(100);
			}
		});
		
		//Argument Tired
		//First Version
		assumption = new Assumption(ParameterName.RestNeeds)
		{
			@Override public boolean expression() 
			{ 
				return (int)param.get(ParameterName.RestNeeds).getValue() >= 30 ;
			}
		};
		argList.add(new Argument(ArgumentName.Tired, 1, assumption, rule ) 
		{
			@Override
			public int calculateRelevanceImpact()
			{
				return this.MappedRelevance(3 * (int)param.get(ParameterName.RestNeeds).getValue());
			}
		});		
		//Second Version
		assumption = new Assumption(ParameterName.RestNeeds)
		{
			@Override public boolean expression() 
			{ 
				return (int)param.get(ParameterName.RestNeeds).getValue() >= 20 && (int)param.get(ParameterName.RestNeeds).getValue() < 30;
			}
		};
		argList.add(new Argument(ArgumentName.Tired, 2, assumption, rule ) 
		{
			@Override
			public int calculateRelevanceImpact()
			{
				return this.MappedRelevance(2 * (int)param.get(ParameterName.RestNeeds).getValue());
			}
		});
		//Third Version
		assumption = new Assumption(ParameterName.RestNeeds)
		{
			@Override public boolean expression() 
			{ 
				return (int)param.get(ParameterName.RestNeeds).getValue() >= 10 && (int)param.get(ParameterName.RestNeeds).getValue() < 20;
			}
		};
		argList.add(new Argument(ArgumentName.Tired, 3, assumption, rule, rule2 ) 
		{
			@Override
			public int calculateRelevanceImpact()
			{
				return this.MappedRelevance((int)param.get(ParameterName.RestNeeds).getValue());
			}
		});
		//Fourth Version
				assumption = new Assumption(ParameterName.RestNeeds)
				{
					@Override public boolean expression() 
					{ 
						return (int)param.get(ParameterName.RestNeeds).getValue() >= 0 && (int)param.get(ParameterName.RestNeeds).getValue() < 10;
					}
				};
				argList.add(new Argument(ArgumentName.Tired, 4, assumption, rule, rule2 ) 
				{
					@Override
					public int calculateRelevanceImpact()
					{
						return this.MappedRelevance(0);
					}
				});
				
		//Urgence Argument
		assumption = new Assumption(ParameterName.UrgentToFinish)
		{
			@Override public boolean expression() 
			{ 
				return (boolean)param.get(ParameterName.UrgentToFinish).getValue();
			}
		};
		argList.add(new Argument(ArgumentName.Urgent, assumption, rule2 ) 
		{
			@Override
			public int calculateRelevanceImpact()
			{
				return this.MappedRelevance(100);
			}
		});
				
		//No Absents Argument
		assumption = new Assumption(ParameterName.NumberOfWorkers, ParameterName.NormalNumberofWorkers)
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
		
		//Not Too Much Hours Argument
		assumption = new Assumption(ParameterName.WorkNeeds)
		{
			@Override public boolean expression() 
			{ 
				return (int)param.get(ParameterName.WorkNeeds).getValue() < 50;
			}
		};
		argList.add(new Argument(ArgumentName.NotTooMuchWorkNeed, assumption, rule, rule2 ) 
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
	
	public void addExternalParameters(Parameters arg0)
	{
		this.param.addExternalParameters(arg0);
		

		for(Map.Entry<ParameterName, Parameter<?>> entry :arg0.get().entrySet())
		{
			entry.getValue().addObserver(this);
		}
	}
}
