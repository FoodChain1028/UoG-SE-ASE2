package src;

public class Administrator extends User {
	
	private UserStrategy Strategy;
	
	public Administrator(String name) {
		super(name);
		this.Strategy = new AdministratorStrategy();
	}
	public UserStrategy getUserStrategy() {
	    	return Strategy;	
	}

}
