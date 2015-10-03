import java.util.*;


public class FibonacciGenerator {

	public void nextNumber(int n)
	{
		int f1 = 0;
		int f2 = 1;
		int count = 1;
		while(n >= count) {
			int fnew = f1 + f2;
			int temp = f2;
			f1 = f2;
			f2 = fnew;
			count ++;
			System.out.println(fnew);

		}
		
	}

}