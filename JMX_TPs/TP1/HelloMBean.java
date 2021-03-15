public interface HelloMBean {
//-----------
// operations
//-----------

public void sayHello();
public int add(int x, int y);

//-----------
// attributes
//-----------

// a read-only attribute called Name of type String
public String getName();

// a read-write attributes called CacheSize and Message of type int and String respectively
public int getCacheSize();
public void setCacheSize(int size);

public String getMessage(); 
public void setMessage(String message);
}