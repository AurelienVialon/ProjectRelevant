package exampleWorkingAgents;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import MVC.Commande;
import MVC.Controleur;
import MVC.Message;
import Parameters.Parameter;
import Parameters.Parameters;

public class WorkingAgentsExample extends Controleur
{
	Parameters parameters = new Parameters();
	
	public WorkingAgentsExample() 
	{
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
		this.parameters.addParameter( new Parameter<Integer>("NormalWorkingTime", 35, "hours"));
		
		this.parameters.addParameter(new Parameter<Integer>("WorkerNumber", 2, "agents"));	
		this.parameters.addParameter(new Parameter<Integer>("HoursToWork", 70, "hours"));
	}
	public void InitAgents()
	{
		ArrayList<Parameter<?>> parametersList = new ArrayList<>();
		parametersList.add(this.parameters.get("WorkerNumber"));
		parametersList.add(this.parameters.get("HoursToWork"));
		parametersList.add(this.parameters.get("NormalWorkingTime"));
		
		this.ajtModele(new WorkingAgent("Agent1", parametersList));
		this.ajtModele(new WorkingAgent("Agent2", parametersList));
		//this.ajtModele(new WorkingAgent("Agent3", parametersList));
	}
	public WorkingAgent getAgent(String arg0)
	{
		return (WorkingAgent)this.donne_modele(arg0);
	}
}
