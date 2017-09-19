package MeetingScheduler.parameters;

import Parameters.Parameter;

public class ParticipantsRatio extends Parameter<Integer>
{
	Participants p;
	Participants_scheduled ps;
	
	public ParticipantsRatio( Participants_scheduled arg0, Participants arg1) 
	{
		this.p = arg1;
		this.ps = arg0;
	}

	@Override
	protected Integer Treatement() 
	{
		float f = (float)this.ps.getValue()/(float)this.p.getValue();
		this.value = (int) ((f) * 100);
		return this.value;
	}

	@Override
	public void setValue(Integer arg0) {
		// TODO Auto-generated method stub
		
	}
}
