package Parameters;
/**
 * 
 */


import java.util.Observable;

/**
 * @author Aurélien Vialon
 *
 */
public class Parameter<T> extends Observable
{
	protected String name;
	protected String unit;
	protected T value;
	/**
	 * 
	 */
	public Parameter(){};
	public Parameter(String arg0) {this.name = arg0;}
	public Parameter(String arg0, T arg1) {this.name = arg0;this.value = arg1;}
	public Parameter(String arg0, T arg1, String arg2) {this.name = arg0;this.value = arg1;this.unit = arg2;}
	
	public Parameter(T arg0) {this.value = arg0;}
	
	public void setName(String arg0){this.name = arg0;}
	public String getName(){return this.name;}
	
	public void setUnit(String arg0) {this.unit = arg0;}
	public String getUnit() {return this.unit;}
	
	public synchronized T getValue ()
	{
		return this.value;
	}
	public synchronized void setValue(T arg0){this.value = arg0;}

}
