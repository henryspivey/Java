import java.util.*;


public class CurrencyConverter {
	private static final double EUROS_PER_DOLLAR = 0.89;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean done = false;
		while(!done) {
			System.out.println("Enter a dollar ($) value to convert to euro (€) or enter Q to quit: ");
			String dollar = sc.next();
			if (dollar.equalsIgnoreCase("Q")) {
				done = true;
			}else {
				double input = Double.parseDouble(dollar);
				double euro = input * EUROS_PER_DOLLAR;
				System.out.format("The value of $%.2f in euros is: €%.2f%n", input, euro);
				
			}
			

		}
	}
}