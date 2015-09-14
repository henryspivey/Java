import java.util.*;

/**
	The purpose of this class is to implement Bank objects for the ATM network
*/

public class Bank {

	private String[] atmNames = {"ATM_A1", "ATM_A2", "ATM_B1", "ATM_B2"};
	private ArrayList<ATM> atms = new ArrayList<ATM>();
	private String id;

	/**
		Constructor
	*/
	public Bank(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}
	public void addAtm(ATM atm)
	{
		this.atms.add(atm);
	}

}