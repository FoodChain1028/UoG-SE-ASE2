package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * This is the class for Part-time Teacher Management System implementation
 * Before the start of each term or semester, 
 * the class directors produce a list of teaching requirements which we must try and fill. 
 * Our administrator will then attempt to find suitable staff and organise training for them.
 */

public class SchoolSystem {

	private User currentUser;
	private boolean Exit = true;
	private String authFilePath = "src/database/auth.txt";
	private String teachingRequirementFilePath = "src/database/teachingRequirement.txt";
	private String trainingSessionFilePath = "src/database/trainList.txt";

	public SchoolSystem() {
		currentUser = null; // No user logged in initially
	}

	public void run() throws IOException { 
		int choice;
		do {
			displayMenu();
			choice = getUserInput();

			if (currentUser == null) {
				handleLogin(choice);
			} 
			else if (currentUser instanceof Administrator || currentUser instanceof ClassDirector){
				switch (choice) {
				case 1:
					addRequirement(); // Feature available only to Administrator and ClassDirector
					break;
				case 2:
					viewRequirement();
					break;
				case 3:
					addSession(); // Feature available only to Administrator and ClassDirector
					break;
				case 4:
					viewSession();
					break;
				case 5:
					System.out.println("You have been logged out.");
					currentUser = null;
					break;
				case 6:
					System.out.println("Exiting application...");
					Exit = false;
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
				}
			}
			else if (currentUser instanceof User){
				switch (choice) {
				case 1:
					viewRequirement();
					break;
				case 2:
					viewSession();
					break;
				case 3:
					System.out.println("You have been logged out.");
					currentUser = null;
					break;
				case 4:
					System.out.println("Exiting application...");
					Exit = false;
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
				}
			}	
		}while (Exit);
	}

	private void displayMenu() { 
		
		// For no user logged in
		if (currentUser == null) { 
			System.out.println("Part-Time Teacher Management System - Login");
			System.out.println("1. Login");
			System.out.println("2. Exit");
		} 
		// System menu for Admin and ClassDirector
		else if (currentUser instanceof Administrator || currentUser instanceof ClassDirector){ 
			System.out.println("\nPart-Time Teacher Management System - Welcome " + currentUser.getName());
			System.out.println("1. Create Teaching Requirement");
			System.out.println("2. View All Requirements");
			System.out.println("3. Organise Training session");
			System.out.println("4. View All Training Sessions");
			System.out.println("5. Logout");
			System.out.println("6. Exit");
		}
		// System menu for Teacher
		else if (currentUser instanceof User) { 
			System.out.println("\nPart-Time Teacher Management System - Welcome " + currentUser.getName());
			System.out.println("1. View All Requirements");
			System.out.println("2. View All Training Sessions");
			System.out.println("3. Logout");
			System.out.println("4. Exit");
		}
		System.out.print("Enter your choice: ");
	}

	private static int getUserInput() throws IOException { // The Exception for catching invalid number
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			return Integer.parseInt(reader.readLine());
		} catch (NumberFormatException e) {
			System.out.println("Please enter a valid number.");
			return getUserInput();
		}
	}

	private void handleLogin(int choice) throws IOException { // This method handles login authentication check
		if (choice == 1) {
			System.out.print("Username: ");
			String username = getUserInputString();
			System.out.print("Password: ");
			String password = getUserInputString();
			boolean loginSuccess = false;
			FileReader filereader = new FileReader(new File(authFilePath).getAbsolutePath());
			try (BufferedReader reader = new BufferedReader(filereader)) {
		        String line;
		        String user = "";
		        while ((line = reader.readLine()) != null) {
		            line = line.trim();
		            // The logic for user check (e.g. Admin, ClassDirector and Teacher
		            if(line.startsWith("Name:") && line.split(":\\s*")[1].equalsIgnoreCase("Administrator")) {
		            	String userLine = reader.readLine();
		            	if(userLine.split(":\\s*")[1].equals(username)) {
		            		String passwordLine = reader.readLine();
		            		if(passwordLine.split(":\\s*")[1].equals(password)) {
		            			loginSuccess = true;
		            			user ="Administrator";
		            			break;
		            		}
		            	}
		            }
		            if(line.startsWith("Name:") && line.split(":\\s*")[1].equalsIgnoreCase("Class Director")) {
		            	String userLine = reader.readLine();
		            	if(userLine.split(":\\s*")[1].equals(username)) {
		            		String passwordLine = reader.readLine();
		            		if(passwordLine.split(":\\s*")[1].equals(password)) {
		            			loginSuccess = true;
		            			user ="Class Director";
		            			break;
		            		}
		            	}
		            }
		            if(line.startsWith("Name:") && line.split(":\\s*")[1].equalsIgnoreCase("Teacher")) {
		            	String userLine = reader.readLine();
		            	if(userLine.split(":\\s*")[1].equals(username)) {
		            		String passwordLine = reader.readLine();
		            		if(passwordLine.split(":\\s*")[1].equals(password)) {
		            			loginSuccess = true;
		            			user ="Teacher";
		            			break;
		            		}
		            	}
		            }
		            /*if (!line.isEmpty() && line.startsWith("Username:") 
		            		&& line.split(":\\s*")[1].equalsIgnoreCase(username)) {
		                String passwordLine = reader.readLine();
		                if (passwordLine.split(":\\s*")[1].equals(password)) {
		                    loginSuccess = true;
		                    break;
		                }
		            }*/
		        }
		     
		    	if(loginSuccess) {
		    		if(user.equals("Administrator")) {
		    			currentUser = new Administrator("Administrator");
						System.out.println("Login successful!");
		    		}
		    		if(user.equals("Class Director")) {
		    			currentUser = new ClassDirector("ClassDirector");
						System.out.println("Login successful!");
		    		}
		    		if(user.equals("Teacher")) {
		    			currentUser = new User("Teahcer");
						System.out.println("Login successful!");
		    		}
					/*currentUser = new Administrator("Admin");
					System.out.println("Login successful!");*/
				}
				else {
					System.out.println("Invalid username or password.");
				}
			 }
		
			catch (IOException e) {
				System.out.println("Error reading file: " + e.getMessage());
			}
		}
		else if(choice==2){
			System.out.println("Exiting application...");
			Exit = false;
		}
		else{
			System.out.println("Invalid choice. Please try again.");
		}
	}
	
	private void addRequirement() throws IOException { // add method for requirement
		FileWriter writer = new FileWriter (new File(teachingRequirementFilePath).getAbsolutePath(),true);
		System.out.print("Enter teaching requirement: ");
        String requirement = getUserInputString();
        writer.write(requirement + "\n");
        writer.close();
        System.out.println("Requirement added successfully!");
	}
	
	private void viewRequirement() throws IOException {// view method for requirement
        FileReader file = new FileReader(new File(teachingRequirementFilePath).getAbsolutePath());
        BufferedReader reader = new BufferedReader(file);
        String content;
        while ((content = reader.readLine()) != null) {
            System.out.println(content);
        }
        reader.close();
        System.out.println("===The End of All Requirements===");
	}
	private void addSession() throws IOException { // add method for training session
		FileWriter writer = new FileWriter (new File(trainingSessionFilePath).getAbsolutePath(),true);
		System.out.print("Enter training session: ");
        String requirement = getUserInputString();
        writer.write(requirement + "\n");
        writer.close();
        System.out.println("Requirement added successfully!");
	}
	
	private void viewSession() throws IOException { // view method for training session
        FileReader file = new FileReader(new File(trainingSessionFilePath).getAbsolutePath());
        BufferedReader reader = new BufferedReader(file);
        String content;
        while ((content = reader.readLine()) != null) {
            System.out.println(content);
        }
        reader.close();
        System.out.println("===The End of All Training sessions===");
	}

	private static String getUserInputString() throws IOException {// The method is for getting input from user
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		return reader.readLine();
	}
}