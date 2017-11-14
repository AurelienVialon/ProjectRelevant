package MVC;

public class ModeleThreaded 
{
	private Modele m;
	private Thread t;
	
	public ModeleThreaded(Modele arg0) 
	{
		this.m = arg0;
	}
	public ModeleThreaded(Modele arg0, Thread arg1) 
	{
		this.m = arg0;
		this.t = arg1;
	}
	
	public Modele getModele ()
	{
		return this.m;
	}
	public Thread getThread()
	{
		return this.t;
	}
	public void setThread(Thread arg0)
	{
		this.t = arg0;
	}
}
