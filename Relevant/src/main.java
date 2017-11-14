import MVC.Commande;
import MVC.Message;
import Parameters.Parameter;
import exampleWorkingAgents.WorkingAgentsExample;
import model.Engine;
import relevance.Argument;
import relevance.RelevanceImpact;
import logic.Term;

public class main {

	public static void main(String[] args) throws InterruptedException 
	{
		/*meeting_scheduler ms = new meeting_scheduler();
	
		
		Engine e = new Engine();
		e.setQualityConstraintsManager(ms.getQCs());
		e.checkQCs();
		System.out.println(e.explain_violation());
		
		Parameter<Integer> p1 = new Parameter<>(5);
		Parameter<Integer> p2 = new Parameter<>(90);
		
		Argument a = new Argument("test1", null, null);
		a.setRelevanceImpact(new RelevanceImpact() {
			@Override
			public int calculate()
			{
				return p1.getValue() + p2.getValue();
			}
		});
		
		System.out.println(a.getRelevanceImpact());
		
		p1.setValue(78);
		System.out.println(a.getRelevanceImpact());
		
		Argument b = new Argument("test2", null, null, 34);
		System.out.println(b.getRelevanceImpact());
		
		
		
		//Test de la nouvelle version du Term :
		Term<Integer> t  = new Term<Integer>() 
		{
			@Override
			public boolean expression()
			{
				return true;
			}
		};
		
		System.out.println(t.get_Checking());*/
		
		System.out.println("Start of the WorkingAgentsExample");
		WorkingAgentsExample ex = new WorkingAgentsExample();
		
		Commande<?> m = new Commande<>("QC_CHECK");
		//Commande<?> m2 = new Commande<>("DISPLAY_PARAMETERS_VALUES");
		ex.envoyer(m);
		ex.Lancement();
		Thread.sleep(100);
		
		System.out.println("Modification of the Parameter Worked Hours for Agent1 to 34 hours");
		ex.getAgent("Agent1").modifParameter("HoursWorkedWeek", 34);
		ex.envoyer(m);
		ex.RepriseModele();

		System.out.println("Modification of the Parameter Worked Hours for Agent2 to 36 hours");
		ex.getAgent("Agent2").modifParameter("HoursWorkedWeek", 36);
		ex.envoyer(m);
		ex.RepriseModele();
	}
}
