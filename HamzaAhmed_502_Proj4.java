package hamzaAhmedIndProj4;

import java.util.Scanner;
import java.util.Random;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

//main class
public class HamzaAhmed_502_Proj4 {

	//main method
	public static void main(String[] args) {

		//scanner for user input
		Scanner input = new Scanner(System.in);

		//instantiate string data types and variables
		String mainMenuChoice, studentMenuChoice, courseMenuChoice, firstName, lastName, 
		studentGL, job, jobType, courseId, fullName;
		
		//instantiate integer data types and variables
		int numStudents, studentID, deactivateID, id, idTest, searchID, studentCount = 0;

		//convert string value into an integer and assign to numStudents
		numStudents = Integer.parseInt(welcomeMenu(input));

		//create students array and create object
		Student[] students = new Student[numStudents];

		//create student employees array and create object
		Employees[] studentJobs = new Employees[numStudents];

		//main menu will always display unless exited by user
		do {

			//call mainMenu method capture input and assign to mainMenuChoice
			mainMenuChoice = mainMenu(input);	

			//switch cases are more efficient than if else statements in this scenario
			switch (mainMenuChoice) {
			
			//student management system
			case "1":

				//program continues to run while menuChoice != 0
				do {

					//call studentMenu method capture input and assign to studentMenuChoice
					studentMenuChoice = studentMenu(input);

					//switch statements is more efficient than if else statements
					switch (studentMenuChoice) {
					case "1":

						//student count must be less than the number of students set at the beginning of the program
						if (studentCount < numStudents) {

							System.out.print("\nStudent first name: ");
							firstName = input.next();

							System.out.print("Student last name: ");
							lastName = input.next();

							System.out.print("Student grade level: ");
							studentGL = input.next();

							//call generateRandomID method and assign integer value to randomID
							studentID = generateRandomID();

							//add student to the students array
							students[studentCount] = new Student(studentID, firstName, lastName, studentGL, true);

							studentJobs[studentCount] = new Employees(studentID, firstName, lastName, studentGL, true, null, null);

							//confirmation that student has been added successfully
							students[studentCount].addStudent();

							//once student has been added, increase student count by 1
							studentCount++;


						} else {

							//if student count reaches maximum amount, then print this
							System.out.println("\nMaximum number of students reached.");
						}
						break;

					case "2":

						System.out.print("\nEnter ID to deactivate student: ");

						//input has to have an integer value
						while (!input.hasNextInt()) {
							System.out.println("Invalid Entry. Please try again. \n");
							System.out.print("\nEnter ID to deactivate student: ");
							input.next();
						}

						//stores which id the user would like to deactivate
						deactivateID = input.nextInt();

						//boolean value must be declared inside of case 
						boolean found = false;

						//searches through array to find the correct id
						for(int x = 0; x < numStudents; x++) {
							//once the correct id is found the if statement will work
							if(students[x].ID == deactivateID) {
								students[x].isActive = false;

								found = true;

								System.out.println("\n" + students[x].firstName + " " 
								+ students[x].lastName + " has been deactivated");
								//stops searching through array and goes back to menu
								break;

							} 

						}
						
						//if correct ID not found, display message 
						if (!found) {
							
							System.out.println("\nStudent not found.");
						}

						break;

					case "3":

						//boolean value must be declared inside of case
						boolean foundAll = false;
						
						//used a for loop to find gather students in system
						for(int x = 0; x < numStudents; x++) {

							//nested loop to compare names
							for(int y = x; y < numStudents; y++) {

								if(students[x] == null) {

									foundAll = true;
									break;
								}

								else if (students[y] == null) {

									break;
								}

								//compare students and rearrange alphabetically 
								else {

									if(students[y].firstName.compareTo(students[x].firstName) < 0) {

										//order names alphabetically using temporary object
										Student temp = students[x];
										students[x] = students[y];
										students[y] = temp;
									}
								}
							}

							//loop ends once all students found
							if(foundAll) {

								break;
							}

							//display all students alphabetically
							students[x].displayStudents();

						}
						break;

					case "4":

						System.out.print("\nEnter the student's ID: ");
						
						//input has to have an integer value
						while (!input.hasNextInt()) {
							System.out.println("Invalid Entry. Please try again. \n");
							System.out.print("\nEnter the student's ID: ");
							input.next();
						}
						
						//integer value assigned to searchID
						searchID = input.nextInt();

						boolean foundID = false;
						
						//for loop searches for student in array using the ID
						for(int x = 0; x < numStudents; x++) {

							if(students[x].ID == searchID) {

								foundID = true;
								
								//once student found, display result
								students[x].displayStudents();

								break;

							}

						}
						
						//student not found 
						if (!foundID) {
							System.out.println("\nStudent not found.");
						}

						break;

					case "5":

						System.out.print("\nEnter ID to assign a job: ");

						//input has to have an integer value
						while (!input.hasNextInt()) {
							System.out.println("Invalid Entry. Please try again. \n");
							System.out.print("\nEnter ID to assign a job: ");
							input.next();
						}

						idTest = input.nextInt();

						//boolean value must be declared inside of the case
						boolean findStu = false;
						
						//loop searches for correct student
						for(int x = 0; x < numStudents; x++) {

							//once correct student is found, proceed with next steps
							if(studentJobs[x].ID == idTest) {

								//student found
								findStu = true;
								
								input.nextLine();
								
								System.out.print("Enter job: ");
								//next line to allow for special characters and spaces
								job = input.nextLine();

								System.out.print("Enter job type: ");
								//next line to allow for special characters and spaces
								jobType = input.nextLine();	
								
								//assign job and job type to the desired student
								studentJobs[x].job = job;
								studentJobs[x].jobType = jobType;
								
								//confirmation message
								System.out.println("\n" + studentJobs[x].firstName + " " + studentJobs[x].lastName + " " + 
								"has been assigned a " + jobType + " " + job + " job");
								break;

							}
						}
						
						//student not found 
						if (!findStu) {
							System.out.println("\nStudent not found.");
						}

						break;

					case "6":

						//loop searches for all students with assigned jobs
						for(int x = 0; x < numStudents; x++) {

							//exit loop if user hasn't added any student yet
							if(studentJobs[x] == null) {

								break;
							}

							//students must have a job to be displayed 
							else if(studentJobs[x].job != null) {

								studentJobs[x].displayJob();
							}

						}

						break;

					case "0":

						//SMS Exit message 
						System.out.println("\nYou have exited the Student Management System");
						break;

					default:

						//choice does not match test cases
						System.out.println("\nInvalid entry. Please try again.");
						break;

					}

					//program ends when studentMenuChoice == 0 
				} while (!studentMenuChoice.equals("0"));

				break;

			case "2":

				do {

					//display courseMenu and assign input to courseMenuChoice
					courseMenuChoice = courseMenu(input);
					
					//create course object to use the course class methods
					Courses courseObject = new Courses();

					//switch cases are more efficient than if else statements in this scenario
					switch (courseMenuChoice) {
					
					//add a course 
					case "1":

						try {
							
							//call the courseAdd method using the Courses object
							courseObject.addCourse();
						}
						
						//catch any exception
						catch (Exception exception) {
							
							//prints exception in file 
							exception.printStackTrace();
						}

						break;

					case "2":
						
						//Get user input for Student and Course ID
						System.out.print("Enter student ID: ");
						
						//input has to have an integer value
						while (!input.hasNextInt()) {
							System.out.println("Invalid Entry. Please try again. \n");
							System.out.print("\nEnter student ID: ");
							input.next();
						}
						
						id = input.nextInt();
						
						input.nextLine();
						
						System.out.print("Enter course ID: ");
						
						//input has to have an integer value
						while (!input.hasNextInt()) {
							System.out.println("Invalid Entry. Please try again. \n");
							System.out.print("\nEnter course ID: ");
							input.next();
						}
						
						courseId = input.nextLine();	
						
						fullName = null;
						
						//used a loop to search for the student
						for(int x = 0; x < numStudents; x++) {
							
							if(students[x] == null) {
								
								break;
							}
							
							//student found
							else if(students[x].ID == id) {
								
								//combine first and last name and assign to fullName
								fullName = (students[x].firstName + " " + students[x].lastName);
								
								break;
							}	
						}
						
						//use try catch block for any errors
						try {
							
							//assign user inputs
							courseObject.assignCourse(courseId, fullName, id);
						} 
						
						//catch and print exceptions to file
						catch (Exception exception) {
							
							exception.printStackTrace();
						}
						
						break;

					case "3":

						try {
							
							//call displayCourse method to display all students that have been assigned a course
							courseObject.displayCourse();
						} 
						
						//catch and print exceptions to file
						catch (Exception exception) {
							
							exception.printStackTrace();
						}

						break;

					case "0":

						//CMS Exit
						System.out.println("\nYou have exited the Course Management System");

						break;

					default:

						//courseMenuChoice does not match test cases 
						System.out.println("\nInvalid entry. Please try again.");

						break;
					}

					//end courseMenu loop if choice == 0
				} while (!courseMenuChoice.equals("0"));

				break;

			case "0":

				System.out.println("\nYou have quit the program\n"
									+ "Good Bye!");

				//close scanner to resolve resource leak
				input.close();
				break;

			default:
				
				//mainMenuChoice does not match test cases 
				System.out.println("\nInvalid entry. Please try again.");
				break;

			}

			//end mainMenu loop if choice == 0 
		} while (!mainMenuChoice.equals("0"));

	}

	//create welcomeMenu method which returns String values
	public static String welcomeMenu(Scanner input) 

	{

		System.out.print("Welcome to Student & Course Management System!\n\n"
				+ "This system will allow you to manage students and courses.\n"
				+ "Let’s start with the number of students this system will have: ");

		//loop runs if input != an Integer value
		while (!input.hasNextInt()) {
			System.out.println("\nInvalid entry. Please try again.");
			System.out.print("Selection: ");
			input.next();
		}
		return input.next();

	}

	//create mainMenu method which returns String values
	public static String mainMenu(Scanner input) 

	{

		//menu
		System.out.print("\nWelcome to the Student and Course Management System\n\n"
				+ "Enter ‘1’ for Student Managemnet System\n"
				+ "Enter ‘2’ for Course Managemnet System\n"
				+ "Enter ‘0’ to exit\n\n"
				+ ""
				+ "Selection: ");

		//loop runs if input != an Integer value
		while (!input.hasNextInt()) {
			System.out.println("\nInvalid entry. Please try again.");
			System.out.print("Selection: ");
			input.next();
		}
		return input.next();

	}

	//create studentMSMenu method which returns String values
	public static String studentMenu(Scanner input) 

	{

		//menu
		System.out.print("\nWelcome to Student Management System\n\n"
				+ "Enter ‘1’ to add a student\n"
				+ "Enter ‘2’ to deactivate a student\n"
				+ "Enter ‘3’ to display all students\n"
				+ "Enter ‘4’ to search for a student by ID\n"
				+ "Enter '5' to assign on campus job\n"
				+ "Enter '6' to display all students with on-campus job\n"
				+ "Enter ‘0’ to exit the SMS\n\n"
				+ ""
				+ "Selection: ");

		//loop runs if input != an Integer value
		while (!input.hasNextInt()) {
			System.out.println("\nInvalid entry. Please try again.");
			System.out.print("Selection: ");
			input.next();
		}
		return input.next();

	}

	//create courseMSMenu method which returns String values
	public static String courseMenu(Scanner input) 

	{

		//menu
		System.out.print("\nWelcome to Course Management System\n\n"
				+ "Enter ‘1’ to add a new course\n"
				+ "Enter ‘2’ to assign a student a course\n"
				+ "Enter ‘3’ to display students with assigned courses\n"
				+ "Enter ‘0’ to exit the CMS\n\n"
				+ ""
				+ "Selection: ");

		//loop runs if input != an Integer value
		while (!input.hasNextInt()) {
			System.out.println("\nInvalid entry. Please try again.");
			System.out.print("Selection: ");
			input.next();
		}
		return input.next();

	}

	//create generateRandomID method
	private static int generateRandomID() 

	{
		//used random to receive random integer values
		Random random = new Random();

		//Randomly assigned between 0 and 99 
		return random.nextInt(100);

	}

}

//create interface and initialize all methods
interface studentInterface {

	//adds new students to the system
	public void addStudent();
	
	//displays all active students
	public void displayStudents();
	
	//closes studentReport
	public void studentExit() throws Exception;
}

//student class
class Student implements studentInterface {

	//instantiate string data types and variables
	public String firstName, lastName, gradeLevel;
	//instantiate integer data types and variables
	public int ID;
	//instantiate boolean data type and variable
	public boolean isActive;

	public Student(int ID, String firstName, String lastName, String gradeLevel, boolean isActive) {

		this.ID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gradeLevel = gradeLevel;
		this.isActive = isActive;
	}


	//adds new students to the system
	public void addStudent() {

		System.out.println("\n" + firstName + " " + lastName + " has been added as a " + gradeLevel + " with ID " + ID);

	}

	//display all active students
	public void displayStudents() {

		//If active is true it will print "active" and if not "deactivated"
		String status;
		if(isActive) {

			status = "Active";

		}

		else {

			status = "Deactived";
		}

		//used to display each student
		if(isActive) {}

		System.out.println("Student name: " + firstName + " " + lastName
				+ "\nStudent ID: " + ID +
				"\nStudent Level: " + gradeLevel + 
				"\nStatus: " + status + "\n");

		//Create the file
		File stuFile = new File("d:\\StudentReport.txt");

		//Try writing to the file
		try {

			//Makes the writing within the file stay as the program runs and even after writer is closed
			FileWriter fw = new FileWriter(stuFile, true);
			PrintWriter stuWrite = new PrintWriter(fw);
			//prints to file
			stuWrite.println("Student name: " + firstName + " " + lastName
					+ "\nStudent ID: " + ID +
					"\nStudent Level: " + gradeLevel + 
					"\nStatus: " + status + "\n");
			//close writer
			stuWrite.close();
		} 

		//catches and prints exceptions
		catch (IOException e) {

			e.printStackTrace();
		}
	}

	//closes studentReport
	public void studentExit() throws Exception {

		PrintWriter studentReport = new PrintWriter("d:\\StudentReport.txt");
		studentReport.close();
	}

}

//student employees sub class
class Employees extends Student {

	//job attributes
	public String job, jobType;

	Employees(int id, String firstName, String lastName, String gradeLevel, boolean isActive, String job,	String jobType) {

		//additional attributes added
		super(id, firstName, lastName, gradeLevel, isActive);
		this.job = job;
		this.jobType = jobType;

	}

	//display all students with a job
	public void displayJob() {

		System.out.println("\n" + firstName + " " + lastName + "\n" + "Id - " + ID + "\n" + jobType + " " + job);
	}
}

//create interface and initialize all methods
interface coursesInterface {
	
	//adds new course to the system
	public void addCourse() throws Exception;
	
	//assigns course to a student
	public void assignCourse(String x, String y, int z) throws Exception;
	
	//display all students with assigned courses
	public void displayCourse() throws Exception;
	
	//closes and erases file
	public void courseExit() throws FileNotFoundException;
}

//Courses class
class Courses implements coursesInterface {
	
	//Scanner to capture user input
	static Scanner input = new Scanner(System.in);
	
	//create new file object that will store all created courses 
	static File courseFile = new File("d:\\Courses.txt");
	
	//create new file object that will store all students assigned to courses
	static File assignFile = new File("d:\\CourseAssignment.txt");
	
	//instantiate printWriter variables
	static PrintWriter courseAssignment = null, courseWriter = null;
	
	public int count;
	
	//create new object
	static Courses object = new Courses();
	
	//count to be used in assigning method
	public void getCount(int count) {
		
		this.count = count;
	}
	
	//all courses the user adds will be stored in this file
	public void addCourse() throws Exception {
		
		//create file
		FileWriter fileWriter = new FileWriter(courseFile, true);
		
		//writer writes onto the file
		courseWriter = new PrintWriter(fileWriter);
		
		
		System.out.print("\nEnter course ID: ");
		String courseId = input.nextLine();
		
		//print courseID to file
		courseWriter.println(courseId);
		
	
		System.out.print("Enter Course name: ");
		String courseName = input.nextLine();
		
		//print courseName to file
		courseWriter.println(courseName);
		
		//once course has been added, print confirmation message
		System.out.println("\nConfirmation: " + courseName + " " + courseId + " has been added!\n");
		courseWriter.close();
		
		//increments by one every time a course is added
		object.count++;
		}
	
	//assigns a course to a student
	public void assignCourse(String x, String y, int z) throws Exception {
		
		//CourseAssignment file will store all students that have been assigned a course
		assignFile = new File("d:\\CourseAssignment.txt");
		
		//create file
		FileWriter fileWriter = new FileWriter(assignFile, true);
		
		Scanner fileReader = new Scanner(courseFile);
		
		courseAssignment = new PrintWriter(fileWriter);
		
		//loop to identify course matches
		int matchCount = 0;
		
		//match count cannot equal course count
		while(matchCount != object.count) {
			
			if(x.equalsIgnoreCase(fileReader.nextLine())) {
				
				//Assign student to the course
				courseAssignment.println(y + "\nID - " + z + "\nCourses: " + x +"\n");
				
				//print confirmation that student has been added
				System.out.println("\nConfirmation: " + y + " has been assigned course " + x);
				
				courseAssignment.close();
				
				//increase by one
				matchCount++;
				
				break;
			}
			
		}
		
		//close file reader
		fileReader.close();
	}
	
	//display all students that have been assigned a course
	public void displayCourse() throws Exception {
		
		Scanner input = new Scanner(assignFile);
		
		//loop to print every line
		while(input.hasNextLine()) {
			
			System.out.println(input.nextLine());
		}
		
		input.close();
	}
	
	//exit and clear all files
	public void courseExit() throws FileNotFoundException {
	
		courseWriter = new PrintWriter("d:\\Courses.txt");
		
		courseWriter.close();
		
		courseWriter = new PrintWriter("d:\\CourseAssignment.txt");
		
		courseWriter.close();
	}
}