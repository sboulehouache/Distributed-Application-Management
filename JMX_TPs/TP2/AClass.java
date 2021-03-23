

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

public class AClass extends JFrame {
    JButton b = new JButton();

    public AClass() {

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
	this.initialize(2,4);
    }
    public void example() {
	this.initialize(2,4);
    }
    public void getAge(int age) {
	this.initialize(2,4);
    }

    public void increaseSize(String s, int x) {
	System.out.println("Error " + "x= " + x + "     y= " + x);
	this.setSize(this.getWidth() + x, this.getHeight() + x);
	this.setTitle(s);
	this.setVisible(true);
    }
    public static void main(String arg[]) {
	// Class c=ChargerUneClasseInconnuAvantExecution.class;

	new AClass();

    }

}