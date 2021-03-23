

/**
 *
 * @author souf
 */
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ChargerUneClasseInconnuAvantExecution extends JFrame implements ActionListener {
    JTextArea text = new JTextArea();
    JScrollPane scrollPane = new JScrollPane();
    JButton btnOpenClass = new JButton("Open Class .java");
    JButton increaseSize = new JButton("Show Meta Data");
    JTextArea textArea=new JTextArea();
    JTextArea fileName= new JTextArea();
    int i;
    int j;
    String s;
    File classfile = null;
    Class className = null;
    Method meth = null;
    Object objectName = null;

    public ChargerUneClasseInconnuAvantExecution() {
	this.setLayout(null);
	this.setSize(850, 600);
	this.setLocation(100, 20);

	btnOpenClass.setSize(800, 30);
	btnOpenClass.setLocation(20, 0);
	
	fileName.setSize(800, 30);
	fileName.setLocation(20, 35);
	
	increaseSize.setSize(800, 30);
	increaseSize.setLocation(20, 70);

	
	scrollPane.setSize(800, 550);
	scrollPane.setLocation(20, 105);
	scrollPane.setViewportView(text);
	add(fileName);
	add(scrollPane);
	add(btnOpenClass);
	add(increaseSize);
	this.setVisible(true);

	increaseSize.addActionListener(this);
	increaseSize.setEnabled(false);
	increaseSize.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		try {
		    System.out.println("increaseSize");
		    //Class<?>[] paramTypes = { String.class, int.class };
		    //meth = className.getMethod("increaseSize", paramTypes);
		    //Object resultat = meth.invoke(objectName, "hello", 50);

		    

		    Class[] interfaces = className.getInterfaces();
		    Class[] classes = className.getClasses();
		    Method[] methods = className.getMethods();
		    Field[] fields = className.getFields();
		    Field[] delaredFields = className.getDeclaredFields();
		    Class superClasses = className.getSuperclass();
		    text.append("---- super Classes ----\n");
		    text.append(superClasses.getName()+"\n");
		    text.append("---- Interfaces ---- \n");
		    for (int i = 0; i < interfaces.length; i++)
			text.append("- "+interfaces[i]+"\n");
		    text.append("---- Classes ----\n");
		    for (int i = 0; i < classes.length; i++)
			text.append("- "+classes[i]+"\n");
		    text.append("---- Methods ----\n");
		    for (int i = 0; i < methods.length; i++)
			text.append("- "+methods[i]+"\n");
		    text.append("---- Declared Fields ----\n");
		    for (int i = 0; i < delaredFields.length; i++)
			text.append("- "+delaredFields[i]+"\n");
		    
		} catch (Exception e) {
		    text.setText("Exception");
		    e.printStackTrace();
		}

	    }
	});

	btnOpenClass.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		ClassOpener co = new ClassOpener();
		System.out.println("bbb");
		classfile = co.getFile();
		try {
		    if (classfile != null) {
			String classFileName = classfile.getName().substring(0, classfile.getName().length() - 5);
			System.out.println("classFileName= "+classFileName);
			className = Class.forName(classFileName);
			objectName = className.newInstance();
			fileName.setText("class Name is: "+className+" ");
			
			fileName.append("and object Name is: " + objectName.toString());
			increaseSize.setEnabled(true);
		    }
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

    }
}
