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
 * Design Patterns: 
 * 1. Factory Pattern for User Creation
 * 2. Strategy Pattern for User Behaviors
 * 3. Singleton Pattern for System Resources
 * 4. Observer Pattern for System Updates and Notifications
 */

public class SchoolSystem implements Observer{

	private User currentUser;
	private boolean Exit = true;
	private String notification;

	public SchoolSystem() {
		currentUser = null; // No user logged in initially
	}
	// Strategy pattern implementation
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
					currentUser.getUserStrategy().addRequirement();// Feature available only to Administrator and ClassDirector
					notification = "Teaching Requirement added successfully!";
					this.update(notification);
					break;
				case 2:
					currentUser.getUserStrategy().viewRequirement();
					notification = "===The End of All Requirements===";
					this.update(notification);
					break;
				case 3:
					currentUser.getUserStrategy().addTrainingSession(); // Feature available only to Administrator and ClassDirector
					notification = "Training added successfully!";
					break;
				case 4:
					currentUser.getUserStrategy().viewTrainingSession();
					notification ="===The End of All Training sessions===";
					this.update(notification);
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
					currentUser.getUserStrategy().viewRequirement();
					notification = "===The End of All Requirements===";
					this.update(notification);
					break;
				case 2:
					currentUser.getUserStrategy().viewTrainingSession();
					notification ="===The End of All Training sessions===";
					this.update(notification);
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
			System.out.println("\nPart-Time Teacher Management System - Login");
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

	private static int getUserInput() throws IOException { // The exception for catching invalid input
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			return Integer.parseInt(reader.readLine());
		} catch (NumberFormatException e) {
			System.out.println("Please enter a valid number.");
			return getUserInput();
		}
	}
	/** This method handles login authentication check
	 * 
	 * @param choice
	 * @throws IOException
	 */
	
	private void handleLogin(int choice) throws IOException {
		if (choice == 1) {
			System.out.print("Username: ");
			String username = getUserInputString();
			System.out.print("Password: ");
			String password = getUserInputString();
			boolean loginSuccess = false;
			// Singleton pattern for getting file path
			FileReader filereader = new FileReader(new File(SystemConfiguration.getInstance().getAuthFilePath()).getAbsolutePath());
			try (BufferedReader reader = new BufferedReader(filereader)) {
		        String line;
		        String user = "";
		        while ((line = reader.readLine()) != null) {
		            line = line.trim();
		            // The logic for user check (e.g. Administrator, ClassDirector and Teacher)
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
		        }
		        // Factory pattern implementation
		    	if(loginSuccess) {
		    		if(user.equals("Administrator")) {
		    			currentUser = UserFactory.createUser(user, user);
						System.out.println("Login successful!");
		    		}
		    		if(user.equals("Class Director")) {
		    			currentUser = UserFactory.createUser(user, user);
						System.out.println("Login successful!");
		    		}
		    		if(user.equals("Teacher")) {
		    			currentUser = UserFactory.createUser(user, user);
						System.out.println("Login successful!");
		    		}
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
	
	public static String getUserInputString() throws IOException { // The method is for getting input from user
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		return reader.readLine();
	}
	
	/**
	 * Observer pattern implementation
	 * @param o
	 */
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