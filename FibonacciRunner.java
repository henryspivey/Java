import java.util.*;

public class FibonacciRunner {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean done = false;
		Scanner sc = new Scanner(System.in);
		while(!done) {
			System.out.println("Enter a number (n) or Q to quit:");
			String input = sc.next();
			if (input.equalsIgnoreCase("Q")) {
				done = true;
			} else {
				FibonacciGenerator fg = new FibonacciGenerator();
				int n = Integer.parseInt(input);
				fg.nextNumber(n);
			}
		}
	}
}