package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SchoolSystem {

	private User currentUser;
	private boolean Exit = true;

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
					System.out.println("Create Teaching Requirement");
					// This case need create and edit function
					break;
				case 2:
					System.out.println("View All Requirements");
					// This case need view function
					break;
				case 3:
					System.out.println("Logout, please login again");
					currentUser = null;
					break;
				case 4:
					System.out.println("Exiting application...");
					Exit = false;
				}
			}
			else if (currentUser instanceof User){
				switch (choice) {
				case 1:
					System.out.println("View All Requirements");
					break;
				case 2:
					System.out.println("Logout, please login again");
					currentUser = null;
					break;
				case 3:
					System.out.println("Exiting application...");
					Exit = false;
				}
			}	
		}while (Exit);
	}

	private void displayMenu() {
		if (currentUser == null) {
			System.out.println("\nPart-Time Teacher Management System - Login");
			System.out.println("1. Login");
			System.out.println("2. Exit");
		} 
		else if (currentUser instanceof Administrator || currentUser instanceof ClassDirector){
			System.out.println("\nPart-Time Teacher Management System - Welcome " + currentUser.getName());
			System.out.println("1. Create Teaching Requirement");
			System.out.println("2. View All Requirements");
			System.out.println("3. Logout");
			System.out.println("4. Exit");
		}
		else if (currentUser instanceof User) {
			System.out.println("\nPart-Time Teacher Management System - Welcome " + currentUser.getName());
			System.out.println("1. View All Requirements");
			System.out.println("2. Logout");
			System.out.println("3. Exit");
		}
		System.out.print("Enter your choice: ");
	}

	private static int getUserInput() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			return Integer.parseInt(reader.readLine());
		} catch (NumberFormatException e) {
			System.out.println("Please enter a valid number.");
			return getUserInput();
		}
	}

	private void handleLogin(int choice) throws IOException {
		if (choice == 1) {
			System.out.print("Username: ");
			String username = getUserInputString();
			System.out.print("Password: ");
			String password = getUserInputString();
			boolean loginSuccess = false;
			FileReader filereader = new FileReader(new File("src/database/auth.txt").getAbsolutePath());
			try (BufferedReader reader = new BufferedReader(filereader)) {
		        String line;
		        String user = "";
		        while ((line = reader.readLine()) != null) {
		            line = line.trim();
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
		    			currentUser = new Administrator("Admin");
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


	private static String getUserInputString() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		return reader.readLine();
	}
}