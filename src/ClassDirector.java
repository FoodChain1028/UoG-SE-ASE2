package src;

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