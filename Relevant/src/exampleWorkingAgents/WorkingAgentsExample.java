package exampleWorkingAgents;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import MVC.Commande;
import MVC.Controleur;
import MVC.Message;
import parameters.Parameter;
import parameters.Parameters;

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
		this.parameters.addParameter( new Parameter<Integer>(ParameterName.NormalWorkingTime, 35, "hours"));
		
		this.parameters.addParameter(new Parameter<Integer>(ParameterName.NumberOfWorkers, 2, "agents"));	
		this.parameters.addParameter(new Parameter<Integer>(ParameterName.NormalNumberofWorkers, 2, "agents"));
		
		this.parameters.addParameter(new Parameter<Integer>(ParameterName.WorkNeeds, 35, "hours"));
	}
	public void InitAgents()
	{
		ArrayList<Parameter<?>> parametersList = new ArrayList<>();
		parametersList.add(this.parameters.get(ParameterName.NumberOfWorkers));
		parametersList.add(this.parameters.get(ParameterName.NormalNumberofWorkers));
		
		parametersList.add(this.parameters.get(ParameterName.WorkNeeds));
		parametersList.add(this.parameters.get(ParameterName.NormalWorkingTime));
		
		this.ajtModele(new WorkingAgent("Agent1", parametersList));
		//this.ajtModele(new WorkingAgent("Agent2", parametersList));
		//this.ajtModele(new WorkingAgent("Agent3", parametersList));
	}
	public WorkingAgent getAgent(String arg0)
	{
		return (WorkingAgent)this.donne_modele(arg0);
	}
}
