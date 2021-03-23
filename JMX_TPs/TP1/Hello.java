import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

//my class
public class Hello implements HelloMBean {
private String message=null;
private final String name = "Reginald";
private int cacheSize = 2100;
public Hello() { }
public Hello(String message) { this.message=message; }

public void sayHello() {
    System.out.println("hello, world");
    }
 
public int add(int x, int y) {
    return x + y;
    }

public String getName() {
    return this.name;
    }

public int getCacheSize() {
    return this.cacheSize;
    }

public synchronized void setCacheSize(int size) {
    int oldSize = this.cacheSize;
    this.cacheSize = size;
    System.out.println("Cache size now " + this.cacheSize);
}
public String getMessage() { return message; }
public void setMessage(String nouveauMessage) { 
	String ancienMessage=message;
	this.message=nouveauMessage;
	System.out.println("message " + this.message);
}
}