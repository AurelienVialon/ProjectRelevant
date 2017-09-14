/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC;


import java.awt.Component;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author Aurélien Vialon
 * 
 * Cett classe abstraite est la vue du modèle MVC.
 * Elle représentera ce que l'utilisateur verra et aura besoin du modèle pour fonctionner.
 */

public abstract class Vue implements Observer
{
    protected Controleur c;
    protected Component cp;
    protected ArrayList<AbstractButton> aEcouter = new ArrayList<AbstractButton>();
    
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
    
    //Si l'on souhaite modifier le modèle que suit cette vue par cette vue.
    protected void maj_mod(Modele m)
    {
       this.c.donne_modele().addObserver(this);
    }
	/**
	 * @return the cp
	 */
	public Component getCp() 
	{
		return cp;
	}
	
	public ArrayList<AbstractButton> get_aEcouter()
	{
		return this.aEcouter;
	}
	
	/**
	 * @param cp the cp to set
	 */
	public void setCp(JComponent cp) {
		this.cp = cp;
	}
	
	public String getName(){
		return this.name;
	}
	
	
	public void setVisibleGraphic(boolean b)
	{
		this.cp.setVisible(b);
	}
	public void disposeGraphic()
	{
		if(this.cp instanceof JFrame)
			((JFrame)this.cp).dispose();
		else
			this.cp = null;
	}
}
