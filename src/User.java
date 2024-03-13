package src;

public abstract class User implements Observer{
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
	@Override
	public void update(Object notification) {
		// TODO Auto-generated method stub
	}
	public void setNotification(String notification) {
	}

	public String getNotification() {
		return null;
	}
}

class Administrator extends User {
	private UserStrategy Strategy;
	private String notification;
    public Administrator(String name) {
        super(name);
        this.Strategy = new AdministratorStrategy();
    }
    public UserStrategy getUserStrategy() {
    	return Strategy;
    }
	@Override
	public void update(Object notification) {
		// TODO Auto-generated method stub
		this.setNotification((String) notification);
		System.out.println(notification);
	}
	public void setNotification(String notification) {
		this.notification = notification;
	}

	public String getNotification() {
		return notification;
	}
}

class ClassDirector extends User {
	private UserStrategy Strategy;
	private String notification;
    public ClassDirector(String name) {
        super(name);
        this.Strategy = new ClassDirectorStrategy();
    }
    public UserStrategy getUserStrategy() {
    	return Strategy;
    }
	@Override
	public void update(Object notification) {
		// TODO Auto-generated method stub
		this.setNotification((String) notification);
		System.out.println(notification);
	}
	public void setNotification(String notification) {
		this.notification = notification;
	}

	public String getNotification() {
		return notification;
	}
}


class Teacher extends User {
	private UserStrategy Strategy;
	private String notification;
	public Teacher(String name) {
		super(name);
		this.Strategy = new TeacherStrategy();
	}
	 public UserStrategy getUserStrategy() {
	    	return Strategy;
	}
	@Override
	public void update(Object notification) {
		// TODO Auto-generated method stub
		this.setNotification((String) notification);
		System.out.println(notification);
	}
	public void setNotification(String notification) {
		this.notification = notification;
	}

	public String getNotification() {
		return notification;
	}
}
