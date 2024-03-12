package src;

public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Administrator extends User {

    public Administrator(String name) {
        super(name);
    }
}

class ClassDirector extends User {

    public ClassDirector(String name) {
        super(name);
    }
}

class Teacher extends User {
	
	public Teacher(String name) {
		super(name);
	}
}