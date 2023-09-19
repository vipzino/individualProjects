package hamzaAhmedIndProj1;

// import NumberFormat to print values in USD form
import java.text.NumberFormat;
// import Locale to get US format
import java.util.Locale;
// import scanner to allow for user input
import java.util.Scanner;

public class HamzaAhmed_IP1 {

	public static void main(String[] args) {

		// Project 1: Write a Java program that prompts the user to enter a starting salary and yearly increase rate.
		
		// Notes for professor: For annual raise, test 0.10 and 10, to test if else statement. 
		
		
		// Used to Scanner to receive user input 
		Scanner input = new Scanner(System.in);
		// Used NumberFormat to print results in with the $ sign and commas in the appropriate place
		NumberFormat usd = NumberFormat.getCurrencyInstance(Locale.US);
		
		System.out.print("Insert your starting salary: ");
		/* input startingSalary as a double to allow decimals 
		 * by initializing the data type and variable */
		double startingSalary = input.nextDouble();
				
		System.out.print("Insert the annual salary raise: ");
		/* input the annual raise amount as a double to allow decimals
		 * by initializing the data type and variable */
		double annualRaise = input.nextDouble(); 
				
		System.out.print("Calculate up to year: ");
		/* input the number of years you would like to 
		 * forecast as an integer to specify whole years
		 * by initializing the data type and variable */
		int numYear = input.nextInt();
		
		/* I used an if statement to allow whole numbers to be 
		 * converted into decimals without dividing decimal values by 100 */
		if (annualRaise>0.99) {
			
			// convert the annual raise into a decimal value
			annualRaise/=100;
			/* used a for loop to forecast the salary adjusted
			 * with the annual raise starting from year 1 up to the 
			 * specified year as inputed by the user 
			 * I also used concatenation to print the results 
			 * in an appealing manner*/
			for (int year = 1; year <= numYear; year++) {
				System.out.println("Year " + year + ": " + usd.format(startingSalary));
				startingSalary += startingSalary * annualRaise;
			}
		}
		
		/* I used an else statement to skip the division step 
		 * because the annualRaise is already a decimal value */
		else {
			for (int year = 1; year <= numYear; year++) {
				System.out.println("Year " + year + ": " + usd.format(startingSalary));
				startingSalary += startingSalary * annualRaise;
			}
			
		}
		// close scanner to resolve resource leak
		input.close();
		
	}
	
}
