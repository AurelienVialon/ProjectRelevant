/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;

/**
 *
 * @author Aurélien Vialon
 * 
 * Cette classe abstraite représente le contrôleur du modèle MVC.
 * Elle doit être implémentée pour pouvoir fonctionner selon le cas souhaité.
 * En effet, les action à gérer sont différentes selon les cas.
 */
public abstract class Controleur implements ActionListener 
{
    protected Modele m;
    protected Thread t;
    
    protected ArrayList<Vue> v = new ArrayList<Vue>();
    
    public Controleur()
    {
        super();
    }

    public abstract void actionPerformed(ActionEvent e);
 
   
     //Méthodes d'initialisation.
     public abstract void Init ();//cette méthode doit être définie en fonction de ce qui doit être fait.
    
     protected void Init (Vue v)
     {
         this.addVue(v);
         this.Init_Ecoutes(v);
     } 
     protected void Init ( Modele m )
     {
         this.m = m;
     }
     protected void Init ( Modele m , Vue v)
     {
         this.m = m;
         this.addVue(v);
         this.Init_Ecoutes(v);
     }
        
    //Méthode d'obtention.
    public Modele donne_modele()
    {
        return this.m;
    }
    
    public ArrayList<Vue> donne_vues()
    {
        return this.v;
    }
    public final boolean addVue (Vue v)
    {
    	boolean ret = false;
 
    	//We check that the "Vue" is not already contains in the "Vues" Library of the "Controleur".    	
    		if(!this.v.contains(v))
    		{
    			this.v.add(v);
    			ret = true;
    		}
    		
    	return ret;
    }
    public final boolean addVue (ArrayList<Vue> v)
    {
    	boolean ret = false;
    	
    	//We check that the "Vues" are not already contain in the "Vues" Library of the "Controleur". 
    	//Return "false" in the case of at least one vue given in parameter is alreqdy contains in the Controleur Library View.     	
    	for(Vue vue : v)
    	{
        	if(!this.v.contains(v))
        	{
    			this.v.add(vue);
        		ret = true;
        	}
        	else
        	{
        		ret = false;
        	}
    	}
    	return ret;
    }
    
    //Connect all Modeles and Vues of the MVC.
    public void Lien_Vue_Modele ()
    {
        for(Vue v : this.v)
        {
        	this.m.ajouter_lien(v);
        }
    }
    //Connect the Specified Vue.
    public void Lien_Vue_Modele ( Vue v )
    {
        this.m.ajouter_lien(v);
    }
    //Connect the Specified Vue.
    public void Lien_Vue_Modele ( ArrayList<Vue> v )
    {
    	for(Vue vue : v)
    	{
            this.m.ajouter_lien(vue);
    	}
    }
    
    public void Init_Ecoutes(Vue v)
    {
    	for(AbstractButton ct : v.get_aEcouter())
    	{
    		ct.addActionListener(this);
    	}
    }
    
    public void Lancement ()
    {
        this.t = new Thread(m); 
        t.start();
    }
    public void RepriseModele ()
    {
        this.m.retour();
    }
}
