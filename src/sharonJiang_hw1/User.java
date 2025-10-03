package sharonJiang_hw1;
import java.io.Serializable;

public class User implements Serializable {
	protected String username;
	protected String password;
	protected String fn;
	protected String ln;
	
	//setters
	public void setUsername(String u) {
		this.username=u;
	}
	
	public void setPassword(String p) {
		this.password=p;
	}
	
	public void setFn(String fn) {
		String newFn=fn.substring(0,1).toUpperCase()+fn.substring(1);
		this.fn=newFn.trim();
	}
	
	public void setLn(String ln) {
		String newLn=ln.substring(0,1).toUpperCase()+ln.substring(1);
		this.ln=newLn.trim();
	}
	
	//constructor with only username and password for admin
	public User(String u, String p) {
		this.username=u;
		this.password=p;
		this.fn="";
		this.ln="";
		
				
	}
	//no ags 
	public User() {
		
	}
	//constructors with all info
	public User(String u, String p, String f, String l) {
		setUsername(u);
		setPassword(p);
		setFn(f);
		setLn(l);
	}
}
