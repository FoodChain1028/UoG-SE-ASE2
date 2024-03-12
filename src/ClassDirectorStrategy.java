package src;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Strategy for ClassDirector actions.
 */

public class ClassDirectorStrategy implements UserStrategy {

	@Override
	public void addRequirement() throws IOException {
		// TODO Auto-generated method stub
		String teachingPath = SystemConfiguration.getInstance().getAuthFilePath();
		FileWriter writer = new FileWriter (new File(teachingPath),true);
		System.out.print("Enter teaching requirement: ");
        String requirement = SchoolSystem.getUserInputString();
        writer.write(requirement + "\n");
        writer.close();
        System.out.println("Requirement added successfully!");
		
	}

	@Override
	public void viewRequirement() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTrainingSession() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewTrainingSession() {
		// TODO Auto-generated method stub
		
	}

}
