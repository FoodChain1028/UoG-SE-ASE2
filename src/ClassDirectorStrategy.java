package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Strategy for ClassDirector actions.
 */

public class ClassDirectorStrategy implements UserStrategy {

	@Override
	public void addRequirement() throws IOException {
		// TODO Auto-generated method stub
		String teachingPath = SystemConfiguration.getInstance().getteachingRequirementFilePath();
		FileWriter writer = new FileWriter(new File(teachingPath).getAbsolutePath(),true);
		System.out.print("Enter teaching requirement: ");
		String requirement = SchoolSystem.getUserInputString();
		writer.write(requirement + "\n");
		writer.close();
		//System.out.println("Administrator Requirement added successfully!");
		
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
		//System.out.println("===The End of All Requirements===");
		
	}

	@Override
	public void addTrainingSession() throws IOException {
		String trainingPath = SystemConfiguration.getInstance().getTrainingSessionFilePath();
		FileWriter writer = new FileWriter (new File(trainingPath).getAbsolutePath(),true);
		System.out.print("Enter training session: ");
		String requirement = SchoolSystem.getUserInputString();
		writer.write(requirement + "\n");
		writer.close();
		//System.out.println("Requirement added successfully!");
		
	}

	@Override
	public void viewTrainingSession() throws IOException {
		String trainingPath = SystemConfiguration.getInstance().getTrainingSessionFilePath();
		FileReader file = new FileReader(new File(trainingPath).getAbsolutePath());
		BufferedReader reader = new BufferedReader(file);
		String content;
		while ((content = reader.readLine()) != null) {
			System.out.println(content);
		}
		reader.close();
		//System.out.println("===The End of All Training sessions===");
		
	}
}
