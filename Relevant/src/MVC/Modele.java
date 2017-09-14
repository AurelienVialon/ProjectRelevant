/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

/**
 *
 * @author Aurélien Vialon
 * 
 * Cette classe abstraitre représente le modèle de MVC.
 * Elle doit être implémenté pour pouvoir être utilisé selon les cas.
 */

public abstract class Modele extends Observable implements Runnable
{                 
    protected boolean continuer;   // pour quitter le thread
    protected boolean en_pause;   // pour pauser / depauser
    
    protected String Name = "";
    protected Stack<Message> bol = new Stack<Message>();
    
	protected String flagMaj;
    
    public void pause ()
    {
        en_pause = true;
        
        synchronized(this)
        {
            try 
            {
                this.wait();
            } 
            catch (InterruptedException ex) 
            {
                
            }
        }
    }
    
    //Description of the Model cycle life.
	@Override
	public void run() 
	{
		boolean c;//Variable to pursue the thread.
        while (true) 
        {
        	c = true;
        	while(c)
        	{
        		synchronized(this.bol)
        		{
        			if (this.bol.size() > 0)
        			{
                    	this.executerCommande(this.messageCommande(this.ouvrirBol()));
                    	this.Maj();
        			}
        			else
        			{
        				c = false;
        			}
        		}
        	}
        	this.pause();
        }
	}
    
    public void retour ()
    {
        en_pause = false;    
        synchronized(this)
        {
            this.notify();
        }
    }
        
    //Méthode de notification de messages aux observateurs.
    protected void notifie(Object o)
    {
            this.setChanged();
            if ( this.countObservers() > 0)
            {
                this.notifyObservers(o);
            }
    }
    //Méthodes pour ajouter des liens entre le modele courant et des observateurs.
    public void ajouter_lien( Observer ob)
    {
        this.addObserver(ob);
    }
    
    public void ajouter_lien (ArrayList <Observer> obs)
    {
        for (Observer ob : obs) 
        {
            this.addObserver(ob);
        }   
    }
    
    //Méthodes pour enlever des liens entre le modele courant et ses observateurs.
    public void couper_lien (Observer ob)          
    {
        this.deleteObserver(ob);
    }
    public void couper_liens ()
    {
        this.deleteObservers();
    }
    
    public abstract void Reinit();
    
    // Pour les demandes de mise a jour de la Vue.
    protected abstract void Maj ();
    
    public void ChangeContinuer (boolean b) { continuer = b; }
    public void ChangePause (boolean b) { en_pause = b; }
    
    public boolean estEnPause () { return en_pause; }
    
    protected abstract <T> void executerCommande(Commande<T> c);
    
    public void deposerMessage(Message m){ synchronized(this.bol){this.bol.push(m);} }
    
	protected Message ouvrirBol() 
	{
		Message m = null;
		
		synchronized(this.bol)
		{
			m = this.bol.pop();
		}	
				
		return m;
	}
	
	protected abstract Commande<?> messageCommande(Message m);
}
