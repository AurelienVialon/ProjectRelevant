import MeetingScheduler.meeting_scheduler;
import model.Engine;

public class main {

	public static void main(String[] args) 
	{
		meeting_scheduler ms = new meeting_scheduler();
	
		
		Engine e = new Engine();
		e.addQualityConstraints(ms.getListQcs());
		e.checkQCs();
		System.out.println(e.explain_violation());
	}
}
