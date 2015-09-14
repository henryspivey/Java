package atmsystem;
import java.util.*;
	
/**
	The purpose of this class is to implement ATM objects for the ATM network
*/

public class ATM
{
	
	private String name;
	private String id;
        private final int PREDEFINED_LIMIT = 50;
	/**
		Constructor
	*/
	public ATM(String name, String id)
	{
		this.name = name;
		this.id = id;
	}

	public String getATMID() {
		return this.id;
	}

	public String getATMName() {
		return this.name;
	}
        
        public Bank getBank(String atmId) {
            Bank newBank = new Bank(atmId);
            return newBank;
           
        }
        
        public int getLimit() {
            return this.PREDEFINED_LIMIT;
        }



}	