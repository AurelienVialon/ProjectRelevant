/**
 * 
 */
package MVC;
/**
 * @author aurel
 * @param <T>
 *
 */
public class Commande<T>
{
	String type;
	T attache; 
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
	public T donneObjet()
	{
		return this.attache;
	}
	public void setAttache(T arg0)
	{
		this.attache = arg0;
	}
}
