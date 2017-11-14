/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractButton;

import Constraints.Constraint;
import exampleWorkingAgents.WorkingAgent;

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
    //protected Modele m;
	Map<String, ModeleThreaded> modeles = new HashMap<>();
	
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
    	 this.modeles.put(m.Name, new ModeleThreaded(m));
     }
     protected void Init ( Modele m , Vue v)
     {
         this.modeles.put(m.Name, new ModeleThreaded(m));
         this.addVue(v);
         this.Init_Ecoutes(v);
     }
        
     public void ajtModele(Modele m)
     {
    	 this.modeles.put(m.Name, new ModeleThreaded(m));
     }
    //Méthode d'obtention.
    public Modele donne_modele(String arg0)
    {
        return this.modeles.get(arg0).getModele();
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
        	for (Map.Entry<String, ModeleThreaded> entry : this.modeles.entrySet()) 
    		{
    			entry.getValue().getModele().ajouter_lien(v);
    		}
        }
    }
    //Connect the Specified Vue.
    public void Lien_Vue_Modele ( Vue arg0, String arg1)
    {
        this.modeles.get(arg1).getModele().ajouter_lien(arg0);
    }
    //Connect the Specified Vue.
    public void Lien_Vue_Modele ( ArrayList<Vue> arg0, String arg1 )
    {
    	for(Vue vue : arg0)
    	{
            this.modeles.get(arg1).getModele().ajouter_lien(vue);
    	}
    }
    
    public void Init_Ecoutes(Vue arg0)
    {
    	for(AbstractButton ct : arg0.get_aEcouter())
    	{
    		ct.addActionListener(this);
    	}
    }
    
    public void Lancement ()
    {
    	for (Map.Entry<String, ModeleThreaded> entry : this.modeles.entrySet()) 
		{
    		Thread t = new Thread(entry.getValue().getModele());
			entry.getValue().setThread(t);
			t.start();
		}
    }
    
    public void RepriseModele ()
    {
    	for (Map.Entry<String, ModeleThreaded> entry : this.modeles.entrySet()) 
		{
			entry.getValue().getModele().retour();
		}
    }
    public void RepriseModele (String arg0)
    {
        this.modeles.get(arg0).getModele().retour();
    }
  
    public void envoyer(Message arg0)
    {
      	for (Map.Entry<String, ModeleThreaded> entry : this.modeles.entrySet()) 
    		{
    			entry.getValue().getModele().deposerMessage(arg0);
    		}
    }
    public void envoyer(Message arg0, String arg1)
    {
    	this.modeles.get(arg1).getModele().deposerMessage(arg0);
    }
}
