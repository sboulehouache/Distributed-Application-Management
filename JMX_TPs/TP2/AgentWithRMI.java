




import java.io.IOException;
import java.net.MalformedURLException;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

public class AgentWithRMI implements NotificationListener{
	MBeanServer mbs=null;
	JMXConnectorServer cs=null;
	AgentWithRMI(){
		Runtime r =Runtime.getRuntime();
		try {
		    //r.getRuntime().
			r.exec("C:/Program Files/Java/jdk1.8.0_112/bin/rmiregistry.exe");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			mbs =  MBeanServerFactory.createMBeanServer("Agent");
			Sensor1 sensorBean = new Sensor1(1000);
			ObjectName sensorName = null;
			sensorName = new ObjectName("Agent:name=AgentOfSensors");
			mbs.registerMBean(sensorBean, sensorName);
			JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:1099/server");
			cs = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mbs);
			cs.start();
			sensorBean.addNotificationListener(this, null, null);

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
	new AgentWithRMI();
	}
@Override
public void handleNotification(Notification notification, Object handback) {
	System.out.println("notification"+notification.getMessage());
		// TODO Auto-generated method stub		
	}

}

