/**
 * 
 */
package model;

import java.util.ArrayList;

import Constraints.QualityConstraint;
import MVC.Commande;
import MVC.Message;
import MVC.Modele;

/**
 * @author Aurélien Vialon
 *
 */
public class Engine extends Modele
{
	private ArrayList<QualityConstraint> qc = new ArrayList<QualityConstraint>();
	/**
	 * 
	 */

	@Override
	public void Reinit()
	{
		// TODO Auto-generated method stub	
	}

	@Override
	protected void Maj()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected <T> void executerCommande(Commande<T> c)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Commande<?> messageCommande(Message m)
	{  
		return null;
	}

	private boolean readFile()
	{
		return true;
	}
}
