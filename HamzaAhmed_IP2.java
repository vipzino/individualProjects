package hamzaAhmedIndProj2;

import java.util.Scanner;

public class HamzaAhmed_IP2 {

	//PI remains constant
	final static double PI = 3.14;

	public static void main(String[] args) {

		//import scanner to get user input
		Scanner input = new Scanner(System.in);

		//instantiate data type and variable
		double lengthS, lengthR, widthR, radius;
		String shape;

		//program continues to run while shape != X or x
		do { 
			//call displayMenu method and capture input from scanner and assign to shape
			shape = displayMenu(input);

			switch (shape) {
			case "S":
			case "s":
				System.out.print("\nEnter the length of the square: ");

				//input has to have a double value
				while (!input.hasNextDouble()) {
					System.out.println("Invalid entry. Please try again. \n");
					System.out.print("Enter the length of the square: ");
					input.next();
				}
				//double value assigned to lengthS
				lengthS = input.nextDouble();

				//Call areaS method and pass lengthS
				System.out.println("\nArea of the square is: " + areaS(lengthS) + "\n");
				break;

			case "R":
			case "r":
				System.out.print("Enter the length of the rectangle: ");

				//input has to have a double value
				while (!input.hasNextDouble()) {
					System.out.println("Invalid Entry. Please try again. \n");
					System.out.print("Enter the length of the rectangle: ");
					input.next();
				}
				//double value assigned to lengthR
				lengthR = input.nextDouble();

				System.out.print("Enter the width of the rectangle: ");

				//input has to have a double value
				while (!input.hasNextDouble()) {
					System.out.println("Invalid entry. Please try again. \n");
					System.out.print("Enter the width of the rectangle: ");
					input.next();
				}
				//double value assigned to widthR
				widthR = input.nextDouble();

				//call areaR method and pass lengthR and widthR
				System.out.println("\nThe area of the rectangle is: " + areaR(lengthR, widthR) + "\n");
				break;

			case "C":
			case "c":
				System.out.print("Enter the radius of the circle: ");

				//input has to have a double value
				while (!input.hasNextDouble()) {
					System.out.println("Invalid entry. Please try again. \n");
					System.out.print("Enter the radius of the circle: ");
					input.next();
				}
				//double value assigned to radius
				radius = input.nextDouble();

				//call areaC method and pass radius
				System.out.printf("\nThe area of the circle is: %.2f\n\n", areaC(radius));
				break;

			case "X":
			case "x":
				System.out.println("You have quit the program 🍌");
				input.close();
				break;
			}

			// program ends if shape == X or x
		} while (!shape.equalsIgnoreCase("X"));

	}

	//create displayMenu method which returns String values == [SsRrCcXx]
	public static String displayMenu(Scanner input) 

	{
		System.out.print("\nArea Calculator\n\n"
				+ "S  --  Square\n"
				+ "R  --  Rectangle\n"
				+ "C  --  Circle\n"
				+ "X  --  Quit\n\n"
				+ "Selection: ");

		//input has to equal [SsRrCcXx]
		while (!input.hasNext("[SsRrCcXx]")) {
			System.out.println("Invalid entry. Please try again. \n");
			System.out.print("Selection: ");
			input.next();
		}
		return input.next();
	}

	//Area Square method which returns a double value
	public static double areaS(double lengthS)
	{
		//calculate areaS
		return lengthS * lengthS;	
	}

	//Area Rectangle Method which returns a double value
	public static double areaR(double lengthR, double widthR)
	{
		//calculate areaR
		return lengthR * widthR;	
	}

	//Area Circle method which returns a double value
	public static double areaC(double radius)
	{
		//calculate areaC
		return PI * radius * radius;	
	}
}