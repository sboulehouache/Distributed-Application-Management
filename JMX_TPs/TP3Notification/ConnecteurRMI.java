


/* Annick Fron
 * Utilisation d'un connecteur RMI pour l'administration de Hello2
 * dans la console JMX
 */

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Random;

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



public class ConnecteurRMI implements NotificationListener{
	MBeanServer mbs=null;
	JMXConnectorServer cs=null;
	private Acquisition local;
	private Acquisition1 local1;
	Sensor1 sensorBean=null;
	Sensor1 sensorBean1=null;
	ConnecteurRMI(){
		local = this.new Acquisition();
		local1 = this.new Acquisition1();
		Runtime r =Runtime.getRuntime();
		try {
			r.exec("C:/Program Files/Java/jdk1.8.0_112/bin/rmiregistry.exe");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			mbs =  MBeanServerFactory.createMBeanServer("SimpleAgent");
			sensorBean = new Sensor1(1000);
			sensorBean1 = new Sensor1(20000);
			//SensorThread sensorBean = new SensorThread(1000);
			//Thread sensorBean = new Thread(new SensorThread(20)); sensorBean.start();
			ObjectName sensorName = null;
			ObjectName sensorName1 = null;
			sensorName = new ObjectName("SimpleAgent:name=sensor");
			sensorName1 = new ObjectName("SimpleAgent:name=sensor1");
			mbs.registerMBean(sensorBean, sensorName);
			mbs.registerMBean(sensorBean1, sensorName1);
			
			JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:1099/server");
			
			cs = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mbs);
			
			cs.start();
			System.out.println("hi");
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
		new ConnecteurRMI();
	}
	@Override
	public void handleNotification(Notification notification, Object handback) {
		System.out.println("notification to the server :"+notification.getMessage());
		// TODO Auto-generated method stub
		
	}
	private class Acquisition extends Thread implements java.io.Serializable{
		private Random random;
		public Acquisition(){
			random= new Random(); start();
		}
		public void run(){			
				while(!isInterrupted()){					
						try {Thread.sleep(300);						
						sensorBean.reset((int)(Math.random()*100), "message sensor: "+(int)Math.random());
						} 
						catch (InterruptedException e) {e.printStackTrace();}		
				} // cf page suivante
		}
	}
	private class Acquisition1 extends Thread implements java.io.Serializable{
		private Random random;
		public Acquisition1(){
			random= new Random(); start();
		}
		public void run(){
				while(!isInterrupted()){
					
						try {Thread.sleep(1500);
						sensorBean1.reset((int)(Math.random()*100), "message sensor 1: "+(int)Math.random());
						} 
						catch (InterruptedException e) {e.printStackTrace();}		
				} // cf page suivante
		}
	}

}

