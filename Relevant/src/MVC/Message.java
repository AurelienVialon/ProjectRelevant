/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC;

/**
 *
 * @author Aurélien Vialon
 * 
 * Cette classe est l'objet qui sera envoyé par le modèle à la vue.
 * C'est elle qui permettra à la vue d'être informé des modifications du modèle;
 */
public class Message 
{
    //Classe qui nous permettra de passer des messages entre les modeles et les vues.
    public Object attache;
    
    public Message ()
    {
        this.attache = null;
    }
    
    public Message ( Object o )
    {
        this.attache = o;//Si l'on veut faire passer un objet en meme temps.
    }
    
    public Object objet() 
    {
        return this.attache;
    }
}
