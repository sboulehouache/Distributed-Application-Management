/* Annick Fron
 * Utilisation d'un connecteur RMI pour l'administration de Hello
 * dans la console JMX
 */

import java.io.IOException;
import java.net.MalformedURLException;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import javax.swing.JFrame;

public class AgentRMI {
	MBeanServer mbs=null;	
	JMXConnectorServer cs=null;
	AgentRMI(){
	    	
		System.out.println("Agent Started ...");
		Runtime r =Runtime.getRuntime();
		try {
		    	// Lancement automatique du rmiregistry.exe
			r.exec("C:/Program Files/Java/jdk1.8.0_112/bin/rmiregistry.exe");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			mbs =  MBeanServerFactory.createMBeanServer("Agent");
			Hello helloBean = new Hello("Hello");
			Hello helloBean1 = new Hello("Salut");
			ObjectName helloName = new ObjectName("Agent:name=Hello World");
			ObjectName helloName1 = new ObjectName("Agent:name=Salut tout le monde");
			mbs.registerMBean(helloBean, helloName);
			mbs.registerMBean(helloBean1, helloName1);
			JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:1099/server");
			cs = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mbs);
			cs.start();			

		} catch (MalformedObjectNameException e) {	
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (InstanceAlreadyExistsException e) {	
			e.printStackTrace();
		} catch (MBeanRegistrationException e) {	
			e.printStackTrace();
		} catch (NotCompliantMBeanException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {		
			e.printStackTrace();
		} catch (IOException e) {	
			e.printStackTrace();
		}
	
	}
public JMXConnectorServer getJMXConnectorServer(){
	return cs;
}
public static void main(String[] args) {
			new AgentRMI();
}

}

