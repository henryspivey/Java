public class EqualNumbers
{
	public static void main(String[] args)
	{
		double original = 2;
		double root = Math.sqrt(original);
		double rootSquared = root * root;

		if(rootSquared == original)
		{
			System.out.println("They are the same");

		}
		else
		{
			System.out.println("rootSquared is" + rootSquared);

		}


	}


}