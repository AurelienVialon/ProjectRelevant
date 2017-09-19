package MeetingScheduler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Constraints.QC;
import Constraints.Term;
import MVC.Commande;
import MVC.Message;
import MVC.Modele;
import MeetingScheduler.parameters.Participants;
import MeetingScheduler.parameters.ParticipantsRatio;
import MeetingScheduler.parameters.Participants_scheduled;

public class meeting_scheduler extends Modele
{
	//Parameters' declaration
	private Participants p = new Participants();
	private Participants_scheduled ps = new Participants_scheduled();
	private ParticipantsRatio pr;
	
	//Quality Constraints' declaration
	private Map<String, QC> qcs = new HashMap<>();
		
	public meeting_scheduler() 
	{
		this.pr = new ParticipantsRatio(ps, p);
		
		this.p.setValue(8);
		this.ps.setValue(7);
		
		this.qcs.put("check_ratio", new QC("check_ratio",
											new Term<Integer>(this.pr, 90, ">")));
	}

	@Override
	public void Reinit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void Maj() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected <T> void executerCommande(Commande<T> c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Commande<?> messageCommande(Message m) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Map<String, QC> getQCs()
	{
		return this.qcs;
	}
	public ArrayList<QC> getListQcs()
	{
		ArrayList<QC> ret = new ArrayList<QC>();
		
		for(QC qc : this.qcs.values())
		{
			ret.add(qc);
		}
		
		return ret;
	}
}
