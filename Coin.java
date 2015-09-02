/**
   A coin with a monetary value.
*/
public class Coin 
{
   private double value;
   private String name;

   /**
      Constructs a coin.
      @param aValue the monetary value of the coin
      @param aName the name of the coin
   */
   public Coin(double aValue, String aName) 
   { 
      value = aValue; 
      name = aName;
   }

   /**
      Gets the coin value.
      @return the value
   */
   public double getMeasure() 
   {
      return value;
   }

   /**
      Gets the coin name.
      @return the name
   */
   public String getName() 
   {
      return name;
   }
   public static void main(String[] args) {
      Coin c = new Coin(.01,"penny");
      System.out.println(c.getMeasure());
   }
}
