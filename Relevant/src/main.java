
import MVC.Commande;
import MVC.Message;
import constraints.Term;
import exampleWorkingAgents.ParameterName;
import exampleWorkingAgents.WorkingAgentsExample;
import javafx.scene.paint.Color;
import model.Engine;
import parameters.Parameter;
import relevance.Argument;

public class main {

	public static void main(String[] args) throws InterruptedException 
	{
		System.out.println("Start of the WorkingAgentsExample Scenario");
		WorkingAgentsExample ex = new WorkingAgentsExample();
		
		//Commande<?> m = new Commande<>("QC_CHECK");
		//Commande<?> m2 = new Commande<>("RELEVANCECALCULATIONDETAILED");
		//Commande<?> m2 = new Commande<>("RELEVANCECALCULATION");
		//Commande<?> m2 = new Commande<>("DISPLAY_PARAMETERS_VALUES");
		//ex.envoyer(m);
		ex.Lancement();
		Thread.sleep(100);
		
		System.out.println("\n Scenario : Modification of the Parameter Worked Hours for Agent1 to 36 hours.");
		ex.getAgent("Agent1").modifParameter(ParameterName.HoursWorkedWeek, 36);
	
		Thread.sleep(100);
		
		System.out.println("\n Scenario : Modification of the Parameter RestNeeds for Agent1 to 25.");
		ex.getAgent("Agent1").modifParameter(ParameterName.RestNeeds, 25);
		Thread.sleep(100);
	
		System.out.println("\n Scenario : Modification of the Parameter Urgent to True.");
		ex.modifParameter(ParameterName.UrgentToFinish, true);
		Thread.sleep(100);
		
		System.out.println("\n Scenario : Modification of the Parameter RestNeeds for Agent1 to 0.");
		ex.getAgent("Agent1").modifParameter(ParameterName.RestNeeds,0);
		Thread.sleep(100);

		System.out.println("\n Scenario : Modification of the Parameter WorkNeed to 40.");
		ex.getAgent("Agent1").modifParameter(ParameterName.WorkNeeds,40);//ex.modifParameter(ParameterName.WorkNeeds, 40);
		Thread.sleep(100);
	}
}
