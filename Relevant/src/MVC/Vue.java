/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC;


import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


import javafx.application.Application;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 *
 * @author Aurélien Vialon
 * 
 * Cett classe abstraite est la vue du modèle MVC.
 * Elle représentera ce que l'utilisateur verra et aura besoin du modèle pour fonctionner.
 */

public abstract class Vue extends Application implements Observer
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
    
	
	public String getName(){
		return this.name;
	}
}
