package src;

public abstract class User implements Observer{
    private String name;
    private UserStrategy Strategy;
	private String notification;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public UserStrategy getUserStrategy() {
    	return Strategy;
    }
    public UserStrategy setUserStrategy(UserStrategy Strategy) {
    	return this.Strategy = Strategy;
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

