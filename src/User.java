package src;

public abstract class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public UserStrategy getUserStrategy() {
		return null;
    	
    }
}

class Administrator extends User {
	private UserStrategy Strategy;
    public Administrator(String name) {
        super(name);
        this.Strategy = new AdministratorStrategy();
    }
    public UserStrategy getUserStrategy() {
    	return Strategy;
    }
}

class ClassDirector extends User {
	private UserStrategy Strategy;
    public ClassDirector(String name) {
        super(name);
        this.Strategy = new ClassDirectorStrategy();
    }
    public UserStrategy getUserStrategy() {
    	return Strategy;
    }
}


class Teacher extends User {
	private UserStrategy Strategy;
	public Teacher(String name) {
		super(name);
		this.Strategy = new TeacherStrategy();
	}
	 public UserStrategy getUserStrategy() {
	    	return Strategy;
	}
}
