import java.util.*;
	
/**
	The purpose of this class is to implement ATM objects for the ATM network
*/

public class ATM
{
	
	private String name;
	private String id;
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



}	