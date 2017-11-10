import Argument.Argument;
import Argument.RelevanceImpact;
import MeetingScheduler.meeting_scheduler;
import Parameters.Parameter;
import model.Engine;

public class main {

	public static void main(String[] args) 
	{
		meeting_scheduler ms = new meeting_scheduler();
	
		
		Engine e = new Engine();
		e.addQualityConstraints(ms.getListQcs());
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
	}
}
