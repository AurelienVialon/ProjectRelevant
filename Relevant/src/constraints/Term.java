package constraints;

public class Term <T>
{
	//To be defined by the engineers at development time.
	public boolean expression()
	{
		return false;
	}
	
	private boolean check()
	{
		return this.expression();
	}
	public boolean get_Checking()
	{
		return this.check();
	}
}
