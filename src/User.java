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

