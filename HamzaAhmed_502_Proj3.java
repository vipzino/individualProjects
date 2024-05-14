package hamzaAhmedIndProj3;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class HamzaAhmed_502_Proj3 {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		//string data types and variables
		String menuChoice, firstName, lastName, studentGL = "";
		//integer data types and variables
		int numStudents, studentID, deactivateID, searchID, studentCount = 0;

		//parse string value into an integer
		numStudents = Integer.parseInt(welcomeMenu(input));

		//create students array 
		Student[] students = new Student[numStudents];

		//do while continues to run unless the user quits 
		do {

			//call method and assign input to menuChoice
			menuChoice = displayMenu(input);

			//test cases
			switch (menuChoice) {
			case "1":

				//total students must be less than max students allowed in the program
				if (studentCount < numStudents) {
					System.out.print("\nStudent first name: ");
					firstName = input.next();

					System.out.print("Student last name: ");
					lastName = input.next();

					System.out.print("Student grade level: ");
					studentGL = input.next();

					//call generateRandomID method and assign to studentID
					studentID = generateRandomID();

					//add student to the students array
					students[studentCount] = new Student(studentID, firstName, lastName, studentGL, true);

					//increase student count by one
					studentCount++;

					//student successfully added
					System.out.println("\n" + firstName + " " + lastName + " has been added as a " 
					+ studentGL + " with ID " + studentID);
					
				} else {

					//cannot exceed maximum amount of students
					System.out.println("\nMaximum number of students reached.");
				}
				break;

			case "2":

				System.out.print("\nEnter ID to deactivate student: ");

				//loop runs if input does not equal integer
				while (!input.hasNextInt()) {
					System.out.println("Invalid Entry. Please try again. \n");
					System.out.print("Enter ID to deactivate student: ");
					input.next();
				}	

				deactivateID = input.nextInt();

				//Must declare boolean value inside the case
				boolean found = false;

				//loop runs to find student with matching studentID
				for (int i = 0; i < studentCount; i++) {
					if (students[i].getID() == deactivateID) {
						students[i].setActive(false);
						
						//loop ends when found
						found = true;
						
						//student successfully deactivated
						System.out.println("\n" + students[i].getFirstName() + " " + students[i].getLastName() 
								+ " has been deactivated");
						break;
					}
				}

				//cannot find matching student
				if (!found) {
					System.out.println("Student not found.");
				}

				break;

			case "3":

				//order students by first name
				Arrays.sort(students, 0, studentCount, (s1, s2) -> s1.getFirstName().compareTo(s2.getFirstName()));
				for (int i = 0; i < studentCount; i++) {
					
					//display only active students
					if (students[i].isActive()) {
						System.out.println("\n" + students[i].getFirstName());
						System.out.println("ID: " + students[i].getID());
						System.out.println("Level: " + students[i].getLevel());
						System.out.println("Status: Active");
					}
				}
				break;

			case "4":
				
				System.out.print("\nEnter the students ID: ");

				//loop runs if input does not equal integer 
				while (!input.hasNextInt()) {
					System.out.println("Invalid Entry. Please try again. \n");
					System.out.print("Enter the student ID: ");
					input.next();
				}	

				searchID = input.nextInt();
				
				//Must declare boolean value inside case
				found = false;
				
				//loop checks all students in the array
                for (int i = 0; i < studentCount; i++) {
                	
                    if (students[i].getID() == searchID && students[i].isActive()) {
                        System.out.println("\n" + students[i].getFirstName());
                        System.out.println("ID: " + students[i].getID());
                        System.out.println("Level: " + students[i].getLevel());
                        System.out.println("Status: Active");
                        
                        //loop ends when found
                        found = true;
                        break;
                    }
                }

                //if not found 
                if (!found) {
                    System.out.println("\nStudent not found or is inactive.");
                }
				break;

			case "0":
				
				System.out.println("\nYou have quit the program");

				//close scanner to resolve resource leak
				input.close();
				break;

			default:
				System.out.println("\nInvalid entry. Please try again.");
				break;

			}

		} while (!menuChoice.equals("0"));

	}

	//welcome menu returns string via input
	public static String welcomeMenu(Scanner input) 

	{

		System.out.print("Welcome to Student Management System!\n\n"
				+ "This system will allow you to manage students.\n"
				+ "Let’s start with the number of students this system will have: ");

		//loop runs if input is not integer
		while (!input.hasNextInt()) {
			System.out.println("\nInvalid entry. Please try again.");
			System.out.print("Selection: ");
			input.next();
		}
		return input.next();

	}

	//display menu returns string via input
	public static String displayMenu(Scanner input) 

	{

		System.out.print("\nWelcome to Student Management System\n\n"
				+ "Enter ‘1’ to add a student\n"
				+ "Enter ‘2’ to deactivate a student\n"
				+ "Enter ‘3’ to display all students\n"
				+ "Enter ‘4’ to search for a student by ID\n"
				+ "Enter ‘0’ to exit the system\n\n"
				+ ""
				+ "Selection: ");

		//loop runs if input is not integer
		while (!input.hasNextInt()) {
			System.out.println("\nInvalid entry. Please try again.");
			System.out.print("Selection: ");
			input.next();
		}
		return input.next();

	}

	//Generate random number between 0 and 99
	private static int generateRandomID() 

	{
		Random random = new Random();

		return random.nextInt(100);

	}

}

//Separate class for student
class Student {

	//string data types and variables accessible only in this class
	private String firstName, lastName, gradeLevel;
	//integer data types and variables accessible only in this class
	private int ID;
	//boolean data type only accessible in this class 
	private boolean isActive;

	public Student(int ID, String firstName, String lastName, String gradeLevel, boolean isActive) {
		
		this.ID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gradeLevel = gradeLevel;
		this.isActive = isActive;
	}

	//returns student ID
	public int getID() {
		
		return ID;
	}

	//returns first name 
	public String getFirstName() {
		
		return firstName;
	}

	//returns last name
	public String getLastName() {
		
		return lastName;
	}

	//returns grade level
	public String getLevel() {
		
		return gradeLevel;
	}

	//returns boolean value for status 
	public boolean isActive() {
		
		return isActive;
	}

	public void setActive(boolean active) {
		
		isActive = active;
	}

}
