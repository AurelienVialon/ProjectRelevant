/**
 * 
 */
package MVC;
/**
 * @author aurel
 * @param <T>
 *
 */
public class Commande<T> extends Message
{
	private String type;
	private T attache; 
	/**
	 * 
	 */
	public Commande(String t) 
	{
		this.type = t;
	}
	public Commande(String t, T a) 
	{
		this.type = t;
		this.attache =a;
	}
	public String donneType()
	{
		return this.type;
	}
	public void setType(String arg0)
	{
		this.type = arg0;
	}
	public T donneObjet()
	{
		return this.attache;
	}
	public void setAttache(T arg0)
	{
		this.attache = arg0;
	}
}
