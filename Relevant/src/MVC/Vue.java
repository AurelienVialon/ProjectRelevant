/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC;


import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

/**
 *
 * @author Aurélien Vialon
 * 
 * Cett classe abstraite est la vue du modèle MVC.
 * Elle représentera ce que l'utilisateur verra et aura besoin du modèle pour fonctionner.
 */

public abstract class Vue extends JFrame implements Observer
{
    protected Controleur c;
    
    protected String name = "";
    
    public Vue ( )
    {
        super();
        this.c = null;
    }
    public Vue ( Controleur c )
    {
        super();
        this.c = c;
    }
    
    
    @Override
    public abstract void update(Observable o, Object ob); 
    
    public abstract void createNewView(Modele arg0);
    
	public String getName(){
		return this.name;
	}
}
