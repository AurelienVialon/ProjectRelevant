package MeetingScheduler.parameters;

import Parameters.Parameter;

public class Participants extends Parameter<Integer>
{
	public Participants() 
	{
		
	}
	public synchronized void setValue(int arg0) 
	{
		this.value = arg0;
	}
}
