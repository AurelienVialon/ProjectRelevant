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
    public String s;
    public Object o;
    
    public Message ( String s )
    {
        this.s = s;
        this.o = null;
    }
    
    public Message ( String s, Object o )
    {
        this.s = s;//Le contenu écrit du message.
        this.o = o;//Si l'on veut faire passer un objet en même temps.
    }
    
    public String code() 
    {
        return s;
    }
    
    public Object objet() 
    {
        return o;
    }
}
