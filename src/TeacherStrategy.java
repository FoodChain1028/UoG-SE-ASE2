package src;

import java.io.*;

import static src.SchoolSystem.getUserInputString;

/**
 * Strategy for Teacher actions.
 */

public class TeacherStrategy implements UserStrategy {

	@Override
	public void addRequirement() throws IOException {
		
	}

	@Override
	public void viewRequirement() throws IOException {
		String teachingPath = SystemConfiguration.getInstance().getteachingRequirementFilePath();
		FileReader file = new FileReader(new File(teachingPath).getAbsolutePath());
		BufferedReader reader = new BufferedReader(file);
		String content;
		while ((content = reader.readLine()) != null) {
			System.out.println(content);
		}
		reader.close();
		System.out.println("===The End of All Requirements===");
		
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
