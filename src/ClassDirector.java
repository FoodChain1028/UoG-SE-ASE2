package src;

public class ClassDirector extends User {
	
	private UserStrategy Strategy;

	public ClassDirector(String name) {
		super(name);
		this.Strategy = new ClassDirectorStrategy();
	}

	public UserStrategy getUserStrategy() {
		return Strategy;
	}
}