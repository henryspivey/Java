import java.util.*;
public class DataSetRunner {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean done = false;
		DataSet d = new DataSet();
		while(!done) {
			System.out.println("Enter a number (n) or Q to quit:");
			String input = in.next();
			if (input.equalsIgnoreCase("Q")) {
				done = true;
				
			} else {
				double n = Double.parseDouble(input);
				d._sum(n);
				d.sum_of_squares(n);
			}
		}
		System.out.printf("The sum is %f: \n", d.getSum());
		System.out.printf("The average is %f: \n", d.getAverage());
		System.out.printf("The std. deviation is %f: ", d.getSt_dev());

	}

}