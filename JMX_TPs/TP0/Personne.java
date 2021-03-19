/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author soufiane
 */
import java.lang.reflect.*;
import java.lang.reflect.Method;

import javax.swing.JFrame;

import java.lang.*;
import java.lang.reflect.*;
class Personne1 {
public String nom;
String prenom;
int age;
public Personne1(String nom1) {
this.nom = nom1;

}
public Personne1(String nom1, String prenom1, int age1) {
this.nom = nom1;
this.prenom = prenom1;
this.age = age1;
}
public void setNom(){
this.nom="nom1";    
}
}
public class Personne extends JFrame{
    public Personne() {
	this.setTitle("Personne");
	this.setVisible(true);	
    }
    public void start() {
	new Personne();
    }
public static void main(String args[])throws Exception {
    
    
    try {
        Personne1 p= new Personne1("Boulehouache", "soufiane", 32); 
        System.out.print("C1");
        Class c1 = p.getClass();
        System.out.print("C2");
        Field f=c1.getField("nom");
        System.out.print("C3");
        Method m=c1.getMethod("setNom",new Class[] {int.class});
        Object v=f.get(p);
        System.out.print(m);
        System.out.print(v); // affiche "Chombier"
    }
    catch (Exception e){System.out.print("erreur");}
}
}