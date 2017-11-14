package logic;

public class Term <T>
{
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
