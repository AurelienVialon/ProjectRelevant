package exampleWorkingAgents;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextArea;

import MVC.Commande;
import MVC.Controleur;
import MVC.Message;
import MVC.Vue;
import parameters.Parameter;
import parameters.Parameters;

public class WorkingAgentsExample extends Controleur
{
	Interface principal;
	Parameters parameters = new Parameters();
	
	public WorkingAgentsExample(Interface principal) 
	{
		super();
		this.principal = principal;
		
		this.Init();
	}	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Init() 
	{
		this.InitParameters();
		this.InitAgents();
	}
	
	public void InitParameters()
	{
		this.parameters.addParameter( new Parameter<Integer>(ParameterName.NormalWorkingTime, 35, "hours"));
		
		this.parameters.addParameter(new Parameter<Integer>(ParameterName.NumberOfWorkers, 2, "agents"));	
		this.parameters.addParameter(new Parameter<Integer>(ParameterName.NormalNumberofWorkers, 2, "agents"));
		
		this.parameters.addParameter(new Parameter<Integer>(ParameterName.WorkNeeds, 35, "hours"));
		this.parameters.addParameter(new Parameter<Boolean>(ParameterName.UrgentToFinish, false));
		
		for(Map.Entry<ParameterName, Parameter<?>> entry : this.parameters.get().entrySet())
		{
			this.principal.addParameterInput(entry.getValue());
		}
	}
	public void InitAgents()
	{
		ArrayList<Parameter<?>> parametersList = new ArrayList<>();
		parametersList.add(this.parameters.get(ParameterName.NumberOfWorkers));
		parametersList.add(this.parameters.get(ParameterName.NormalNumberofWorkers));
		
		parametersList.add(this.parameters.get(ParameterName.WorkNeeds));
		parametersList.add(this.parameters.get(ParameterName.NormalWorkingTime));
		parametersList.add(this.parameters.get(ParameterName.UrgentToFinish));
		
		this.ajtModele(new WorkingAgent("Agent1", parametersList),
				this.principal);	
		//this.ajtModele(new WorkingAgent("Agent2", parametersList));
		//this.ajtModele(new WorkingAgent("Agent3", parametersList));
		this.Lancement();
	}
	public WorkingAgent getAgent(String arg0)
	{
		return (WorkingAgent)this.donne_modele(arg0);
	}
	public synchronized Parameter<?> getParameter(ParameterName arg0)
	{
		return this.parameters.get(arg0);
	}
	public synchronized <T> void modifParameter(ParameterName arg0, T arg1)
	{
		Parameter<T> p = (Parameter<T>)this.parameters.get(arg0);
		if(p!= null)
		{
			synchronized(p)
			{
				p.setValue(arg1);
			}
		}	
	}
}
