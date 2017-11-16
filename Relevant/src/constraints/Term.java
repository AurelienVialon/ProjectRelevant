package constraints;

public class Term
{
	private String name;
	//To be defined by the engineers at development time.
	public Term()
	{
		
	}
	public Term(String arg0)
	{
		this.name = arg0;
	}
	
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
	
	public String getName()
	{
		return this.name;
	}
}
