package src;

import java.io.IOException;

/**
 * Interface defining actions that users can perform.
 */

public interface UserStrategy {
	public void addRequirement() throws IOException;
	public void viewRequirement();
	public void addTrainingSession();
	public void viewTrainingSession();
}
