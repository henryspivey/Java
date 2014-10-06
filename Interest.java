public class Interest
{
	public static void main(String[] args) {
		double balance = 100;
		int year= 0;
		final double INTEREST = 0.1;

		while (balance < 1000000)
		{
			double interest  = balance * 0.1;
			balance += interest;
			year ++;

		}
		System.out.println("It will take "+ year+ " years with an interest rate of "+INTEREST+ " %.");
		
	}


}