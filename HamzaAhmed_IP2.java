package hamzaAhmedIndProj2;

import java.util.Scanner;

public class HamzaAhmed_IP2 {
	
	final static double PI = 3.14159;
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Please select a shape. \n\n"
				+ "Enter S or s for Square: \n"
				+ "Enter R or r for Rectangle: \n"
				+ "Enter C or c for Cirlce: \n\n"
				+ "Selection: ");
		
		while (!input.hasNext("[SsRrCcXx]")) {
			System.out.println("Invalid entry. Please try again. \n");
			System.out.print("Selection: ");
			input.next();
		}
		String shape = input.next();
		
		switch (shape) {
			case "S":
			case "s":
				System.out.print("Please enter the length of the square: ");
				
				while (!input.hasNextDouble()) {
					System.out.println("Invalid entry. Please try again. \n");
					System.out.print("Please enter the length of the square: ");
					input.next();
				}
				double lengthSqr = input.nextDouble();
				
				double areaSqr = lengthSqr * lengthSqr;
				System.out.println("Area of the square is: "+areaSqr);
				break;
				
			case "R":
			case "r":
				System.out.print("Please enetr the length of the rectangle: ");
				
				while (!input.hasNextDouble()) {
					System.out.println("Invalid Entry. Please try again. \n");
					System.out.print("Please enetr the length of the rectangle: ");
					input.next();
				}
				double lengthRct = input.nextDouble();
				
				System.out.print("Please enter the width of the rectangle: ");
				
				while (!input.hasNextDouble()) {
					System.out.println("Invalid entry. Please try again. \n");
					System.out.print("Please enter the width of the rectangle: ");
					input.next();
				}
				double widthRct = input.nextDouble();
				
				double areaRct = lengthRct * widthRct;
				System.out.println("The area of the rectangle is: "+areaRct);
				break;
				
			case "C":
			case "c":
				System.out.print("Please enter the radius of the circle: ");
				
				while (!input.hasNextDouble()) {
					System.out.println("Invalid entry. Please try again. \n");
					System.out.print("Please enetr the radius of the circle: ");
					input.next();
				}
				double radius = input.nextDouble();
				
				double areaCrc = PI * radius * radius;
				System.out.println("The area of the circle is: "+areaCrc);
				break;
				
			case "X":
			case "x":
				System.out.println("You have quit the program 🍌");
				break;
		}
		input.close();	
	}
}