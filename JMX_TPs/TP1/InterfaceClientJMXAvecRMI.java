
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;

public class InterfaceClientJMXAvecRMI extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    // private JTextField textField_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {

		    InterfaceClientJMXAvecRMI frame = new InterfaceClientJMXAvecRMI();
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the frame.
     */

    public InterfaceClientJMXAvecRMI() {

	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(400, 250, 700, 400);
	contentPane = new JPanel();
	setContentPane(contentPane);
	contentPane.setLayout(null);

	textField = new JTextField();
	textField.setBounds(224, 72, 289, 25);
	contentPane.add(textField);
	textField.setColumns(10);

	JLabel lblNewLabel = new JLabel("Enter Your Message:");
	lblNewLabel.setForeground(new Color(0, 0, 255));
	lblNewLabel.setBackground(Color.GRAY);
	lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 16));
	lblNewLabel.setBounds(10, 72, 230, 25);
	contentPane.add(lblNewLabel);

	JButton btnEnvoyer = new JButton("Send");
	btnEnvoyer.setFont(new Font("Calibri", Font.BOLD, 14));
	btnEnvoyer.setBounds(524, 72, 107, 25);
	contentPane.add(btnEnvoyer);

	JButton btnq = new JButton("Quitter");
	btnq.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		System.exit(0);
	    }
	});
	btnq.setFont(new Font("Calibri", Font.BOLD, 14));
	btnq.setBounds(567, 321, 107, 29);
	contentPane.add(btnq);

	JLabel lblNewLabel_1 = new JLabel("Send Messages To a Static MBean");
	lblNewLabel_1.setForeground(new Color(220, 20, 60));
	lblNewLabel_1.setBackground(new Color(128, 0, 0));
	lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 32));
	lblNewLabel_1.setBounds(100, 11, 500, 41);
	contentPane.add(lblNewLabel_1);

	JLabel lblConsole = new JLabel("Console");
	lblConsole.setFont(new Font("Calibri", Font.BOLD, 16));
	lblConsole.setBounds(287, 316, 122, 21);
	contentPane.add(lblConsole);

	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(31, 135, 619, 170);
	contentPane.add(scrollPane);

	JTextArea textArea = new JTextArea();
	scrollPane.setViewportView(textArea);
	// --------------------------------------------------
	try {
	    MBeanServerConnection mbsc = null;
	    JMXConnector connecteur = null;
	    ObjectName name = null;
	    name = new ObjectName("Agent:name=Hello World");
	    JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:1099/server");
	    connecteur = JMXConnectorFactory.connect(url, null);
	    mbsc = connecteur.getMBeanServerConnection();
	    HelloMBean mbean = (HelloMBean) MBeanServerInvocationHandler.newProxyInstance(mbsc, name, HelloMBean.class, false);
	    btnEnvoyer.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
		    if (textField.getText().replaceAll(" ", "").equals("")) {
			String message = mbean.getMessage();
			textArea.setText(textArea.getText() + "The conetent of the message is: " + message + "\n");
			System.out.println("The conetent of the message is: " + message);
		    } else {
			mbean.setMessage(textField.getText().toString());
			String msg = mbean.getMessage();
			textArea.setText(textArea.getText() + "The conetent of the message is: " + msg + "\n");
			System.out.println("The conetent of the message is: " + msg);
		    }

		}
	    });

	} catch (Exception e) {
	    // TODO: handle exception
	}
	// -----------------------------------------------------------------

    }

}
