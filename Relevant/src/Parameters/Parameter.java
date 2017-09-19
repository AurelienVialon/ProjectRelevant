package Parameters;
/**
 * 
 */


import java.util.Observable;

/**
 * @author Aurélien Vialon
 *
 */
public abstract class Parameter<T> extends Observable
{
	protected String name;
	
	protected int id;
	
	protected T value;
	/**
	 * 
	 */
	public Parameter(){};
	public Parameter(String arg0) {this.name = arg0;}
	public Parameter(String arg0, T arg1) {this.name = arg0;this.value = arg1;}
	public Parameter(String arg0, int arg1, T arg2) {this.name = arg0; this.id = arg1; this.value = arg2;}
	
	public Parameter(int arg0) {this.id = arg0;}
	public Parameter(int arg0, T arg1) {this.id = arg0;this.value = arg1;}
	
	public Parameter(T arg0) {this.value = arg0;}
	
	public void setName(String arg0){this.name = arg0;}
	public String getName(){return this.name;}
 	
	public void setId(int arg0){this.id = arg0;}
	public int getId(){return this.id;}
	
	public synchronized T getValue ()
	{
		return this.Treatement();
	}
	public void setValue(T arg0){};
	
	protected T Treatement()
	{
		return this.value;
	}
}
