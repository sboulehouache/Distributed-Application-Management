package classestobecharged;
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

import javax.swing.JButton;
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

    public void setNom(String nom) {
	this.nom = nom;
    }
}

public class Personne extends JFrame {
    JButton b = new JButton();

    public Personne() {

    }

    public void initialize(int x, int y) {
	this.setTitle("Personne");
	this.setSize(400, 400);
	
	b.setSize(400, 400);
	b.setLocation(400, 400);
	this.add(b);
	this.setVisible(true);
    }

    public void start() {
	// Personne personne=new Personne();
	this.initialize(2,4);
    }
    public void example() {
	// Personne personne=new Personne();
	this.initialize(2,4);
    }
    public void getAge(int age) {
	// Personne personne=new Personne();
	this.initialize(2,4);
    }

    public void increaseSize(String s, int x) {
	System.out.println("Error " + "x= " + x + "y= " + x);
	this.setSize(this.getWidth() + x, this.getHeight() + x);
	this.setTitle(s);
	this.setVisible(true);
    }

    public static void main(String args[]) throws Exception {

	try {
	    Personne1 p = new Personne1("Boulehouache", "soufiane", 32);
	    System.out.print("C1");
	    Class c1 = p.getClass();
	    System.out.print("C2");
	    Field f = c1.getField("nom");
	    System.out.print("C3");
	    Method m = c1.getMethod("setNom", new Class[] { int.class });
	    Object v = f.get(p);
	    System.out.print(m);
	    System.out.print(v); // affiche "Chombier"
	} catch (Exception e) {
	    System.out.print("erreur");
	}
    }
}