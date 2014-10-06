
/**
 * Write a description of class Car here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Car
{
    // instance variables - replace the example below with your own
    private double milesDriven;
    private double gasInTank;
    //private Picture pic;
    
    public void drive (double distance){
        milesDriven = milesDriven + distance;
        int pixelsPerMile = 10;
        double milesPerGallon = 50;
        double gasConsumed = distance /milesPerGallon;
        gasInTank = gasInTank - gasConsumed;
        //pic.translate(pixelsPerMile,0);
        
    }
    
    public void addGas(double amount)
    {
        gasInTank = gasInTank + amount;
        
    }
    
    public double getGasInTank()
    {
        return gasInTank;    
    }
    public double getMilesDriven()
    {
        return milesDriven;
    }
    
}
