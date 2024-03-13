package src;

public class Teacher extends User {

	private UserStrategy Strategy;

	public Teacher(String name) {
		super(name);
		this.Strategy = new TeacherStrategy();
	}

	public UserStrategy getUserStrategy() {
		return Strategy;
	}

}