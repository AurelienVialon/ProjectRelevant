
import MVC.Commande;
import MVC.Message;
import constraints.Term;
import exampleWorkingAgents.ParameterName;
import exampleWorkingAgents.WorkingAgentsExample;
import model.Engine;
import parameters.Parameter;
import relevance.Argument;

public class main {

	public static void main(String[] args) throws InterruptedException 
	{
		System.out.println("Start of the WorkingAgentsExample");
		WorkingAgentsExample ex = new WorkingAgentsExample();
		
		Commande<?> m = new Commande<>("QC_CHECK");
		Commande<?> m2 = new Commande<>("RELEVANCECALCULATIONDETAILED");
		//Commande<?> m2 = new Commande<>("DISPLAY_PARAMETERS_VALUES");
		ex.envoyer(m);
		ex.Lancement();
		Thread.sleep(100);
		
		System.out.println("Modification of the Parameter Worked Hours for Agent1 to 36 hours");
		ex.getAgent("Agent1").modifParameter(ParameterName.HoursWorkedWeek, 36);
		ex.envoyer(m);
		ex.envoyer(m2);
		ex.RepriseModele();
		Thread.sleep(100);
		
		System.out.println("Modification of the Parameter RestNeeds for Agent1 to 25");
		ex.getAgent("Agent1").modifParameter(ParameterName.RestNeeds, 25);
		ex.envoyer(m);
		ex.envoyer(m2);
		ex.RepriseModele();

		//System.out.println("Modification of the Parameter Worked Hours for Agent2 to 36 hours");
		//ex.getAgent("Agent2").modifParameter(ParameterName.HoursWorkedWeek, 36);
		//ex.envoyer(m);
		//ex.RepriseModele();
	}
}
