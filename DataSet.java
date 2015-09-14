public class DataSet {
	
	private double sum;
	private double st_dev;
	private double average;
	private int count;
	private double sum_of_squares;

	public DataSet()
	{
		this.sum = 0.0;
		this.st_dev = 0.0;
		this.average = 0.0;
		this.count = 0;
		this.sum_of_squares= 0.0;
	}

	public void _sum(double number)
	{
		this.sum += number;
		this.count ++;
	}

	public void average(){
		this.average = this.sum/this.count;
	}

	public void sum_of_squares(double number) {
		this.sum_of_squares += (number*number);
	}

	public void st_dev(){
		double numerator = this.sum_of_squares - ((1.0/this.count)*(this.sum*this.sum));
		double denominator = this.count - 1;
		double quotient = numerator/denominator;
		this.st_dev = Math.sqrt(quotient);
	}

	public double getSum()
	{
		return this.sum;
	}

	public double getAverage()
	{
		this.average();
		return this.average;
	}

	public double getSt_dev()
	{ 
		this.st_dev();
		return this.st_dev;
	}

}