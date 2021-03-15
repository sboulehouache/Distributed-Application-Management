/**
 * 
 */


import java.io.Serializable;
import java.util.Random;

import javax.management.AttributeChangeNotification;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

/**
 * @author soufiane
 *
 */
public class Sensor extends NotificationBroadcasterSupport implements Sensor1MBean, Serializable{
	private final int PERIOD;
	private double value=20.0;
	private String message=null;
	private int numeroSequence;
	private double currentValue=0.0;
	private double oldValue=0.0;
	NotificationBroadcasterSupport nBS=new NotificationBroadcasterSupport();
	// Acquisition est une classe interne
	// une simulation du capteur.
	private Acquisition local;
	public Sensor(int period){
	this.PERIOD = period;
	local = this.new Acquisition();
	}
	public synchronized double getValue(){
	return value;
	}
	public synchronized void setValue(double value){
	this.value = value;
	}
	public void reset(int va, String nouveauMessage){		
		local.interrupt();
		try{local.join();}
		catch(InterruptedException ie){}
		System.out.println("reset ...."+nouveauMessage+": "+va+"  value:  "+value);
		local = this.new Acquisition();
		oldValue=currentValue;
		this.currentValue=va;		
		Notification n=new AttributeChangeNotification(this,
				numeroSequence++,
				System.currentTimeMillis(),
				"message modifie",
				"pppcurrentValue", //nom attribut
				"int", // type attribut
				10,
				currentValue);		
		sendNotification(n);
				
	}
		
	private class Acquisition extends Thread implements java.io.Serializable{
		private Random random;
		public Acquisition(){
			random= new Random(); start();
		}
		public void run(){
			try{
				while(!isInterrupted()){
					Thread.sleep(PERIOD/2);
					value = random.nextInt(100);
					//reset(value, this.getName());
					Thread.sleep(PERIOD/2);
					
					System.out.println("." + value);
				} // cf page suivante
			}
			catch(InterruptedException ie){}
		}
	}

}
