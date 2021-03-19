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
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ChargerUneClasseInconnuAvantExecution extends JFrame implements ActionListener {
    JTextArea text = new JTextArea();
    JScrollBar scrollBar = new JScrollBar();
    JButton btnOpenClass = new JButton("Open Class");
    JButton increaseSize = new JButton("Increase Size");
    int i;
    int j;
    String s;
    File classfile;
    Class className = null;
    Method meth = null;
    Object objectName = null;

    public ChargerUneClasseInconnuAvantExecution() {
	this.setLayout(null);
	this.setSize(400, 400);
	this.setLocation(30, 400);

	btnOpenClass.setSize(150, 30);
	btnOpenClass.setLocation(20, 20);

	increaseSize.setSize(150, 30);
	increaseSize.setLocation(20, 120);

	scrollBar.add(text);
	scrollBar.setSize(200, 400);
	scrollBar.setLocation(20, 60);
	add(text);
	add(btnOpenClass);
	add(increaseSize);
	this.setVisible(true);

	increaseSize.addActionListener(this);

	
	btnOpenClass.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		ClassOpener co = new ClassOpener();
		classfile = co.file;
		String classFileName = classfile.getName().substring(0, classfile.getName().length() - 5);
		try {
		    className = Class.forName(classFileName);
		    objectName = className.newInstance();
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		} catch (InstantiationException e) {
		    e.printStackTrace();
		} catch (IllegalAccessException e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    public static void main(String arg[]) {
	// Class c=ChargerUneClasseInconnuAvantExecution.class;

	new ChargerUneClasseInconnuAvantExecution();

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
	Object source = ae.getSource();
	try {

	    if (source == increaseSize) {
		System.out.println("increaseSize");
		Class<?>[] paramTypes = { String.class, int.class };
		meth = className.getMethod("increaseSize", paramTypes);
		Object resultat = meth.invoke(objectName, "hello", 50);
	    }

	    text.setText("h " + objectName.toString());

	    Class[] interfaces = className.getInterfaces();
	    Class[] classes = className.getClasses();
	    Method[] methods = className.getMethods();
	    Field[] fields = className.getFields();
	    Field[] delaredFields = className.getDeclaredFields();
	    Class superClasses = className.getSuperclass();
	    for (int i = 0; i < delaredFields.length; i++)
		System.out.println(delaredFields[i]);
	} catch (InvocationTargetException e) {
	    e.getCause().printStackTrace();
	} catch (Exception e) {
	    text.setText("Exception");
	    e.printStackTrace();
	}

    }
}
