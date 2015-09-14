import java.util.*;
public class Die {
	
	public static void main(String[] args) {
		boolean inRun = false;
		int[] die = {1,2,3,4,5,6};
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		for (int i = 0;i<20; i++) {
			Random rand = new Random();
			int n = rand.nextInt(5);
			numbers.add(die[n]);
		}
		for (Integer i : numbers ) {
			while(i != numbers.get(numbers.size()-1)) {
				int next = numbers.get(i+1);
				System.out.print(next + " ");
			};
			if (inRun) {
				/*if (numbers.get(i)) {
					
				}*/
			} else {
				/*if (numbers.get(i) == next) {
					System.out.print('(');
				    inRun = true;
				}*/
			}

		}
		
	}

}