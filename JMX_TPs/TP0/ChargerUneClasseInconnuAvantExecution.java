/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author souf
 */
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ChargerUneClasseInconnuAvantExecution extends JFrame implements ActionListener {
JTextArea text= new JTextArea();
JScrollBar scrollBar=new JScrollBar();
JButton ok=new JButton("ok");
JButton increaseSize=new JButton("Increase Size");
int i;
int j;
String s;

Class c=null;
Method meth=null;
Object name=null;
public ChargerUneClasseInconnuAvantExecution(){
    this.setLayout(null);
    this.setSize(400, 400);
    this.setLocation(30,400);
    
    ok.setSize(150,30);
    ok.setLocation(20, 20);
    
    increaseSize.setSize(150,30);
    increaseSize.setLocation(20, 120);
    
    
    scrollBar.add(text);
    scrollBar.setSize(200,400);
    scrollBar.setLocation(20, 60);
    add(text);
    add(ok);
    add(increaseSize);
    this.setVisible(true);
    ok.addActionListener(new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent e) {
	    // TODO Auto-generated method stub
	    
	}});
    increaseSize.addActionListener(this);
    /*increaseSize.addActionListener(new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent e) {
	    
	    try {
		Class<?>[] paramTypes = {int.class};
		meth = c.getMethod("increaseSize", paramTypes);
		Object resultat = meth.invoke(name, 10);
	    } catch (IllegalAccessException e1) {
		e1.printStackTrace();
	    } catch (IllegalArgumentException e1) {
		e1.printStackTrace();
	    } catch (InvocationTargetException e1) {
		e1.printStackTrace();
	    } catch (NoSuchMethodException e1) {
		e1.printStackTrace();
	    } catch (SecurityException e1) {
		e1.printStackTrace();
	    }
	}
	
    });*/
}

public static void main(String arg[]){
        //Class c=ChargerUneClasseInconnuAvantExecution.class;
    
    new ChargerUneClasseInconnuAvantExecution();

}
@Override
public void actionPerformed(ActionEvent ae) {
    Object source = ae.getSource();
    try {
	    String nomClass="classestobecharged.Personne";
	    if(c==null) {
	    c=Class.forName(nomClass);
	    name=c.newInstance();
	    }    
	    if(source==ok) {
		System.out.println("ok");
	    meth = c.getMethod("start"); 
	    ok.setText(meth.getName());
	    Object resultat = meth.invoke(name);
	    }   
	    if(source==increaseSize) {
		System.out.println("increaseSize");
	    Class<?>[] paramTypes = {String.class,int.class};
	    meth = c.getMethod("increaseSize", paramTypes);
	    Object resultat = meth.invoke(name, "hello", 50);
	    }
	   	    
	    text.setText("h "+name.toString());
	    
	    Class[] interfaces = c.getInterfaces();
	    Class[] classes = c.getClasses();
	    Method[] methods= c.getMethods();
	    Field[] fields=c.getFields();
	    Field[] delaredFields=c.getDeclaredFields();
	    Class superClasses=c.getSuperclass();
	    for(int i = 0; i < delaredFields.length; i++)
	            System.out.println(delaredFields[i]);
	}
    	catch (InvocationTargetException e) {
	    e.getCause().printStackTrace();
    	}
    	catch (Exception e){text.setText("Exception");
	e.printStackTrace();
	}
    
}
}
