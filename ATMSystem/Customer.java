import java.util.*;

/**
	The purpose of this class is to create Customer objects that hold info for use in the ATM network
*/

public class Customer {

	private ArrayList<ATM> atms = new ArrayList<ATM>();
	private ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
	public Customer() {

	}

	public void addAtm(ATM atm) {
		if (verifyATMInfo(atm) == true) {
			System.out.println("You already have a bank account with this bank.");
		} else {
			this.atms.add(atm);
		}
	}
	
	public boolean verifyATMInfo(ATM b) {
		/**
			Must check if customer already has account in a certain bank
			if ATM bank id already in atms, return false
		*/
		boolean found = false;
		if (this.atms.size() == 0) {
			return false;
		} else {
			for (ATM a: this.atms) {
				if (a.getATMID().equals(b.getATMID())) {
					found = true;
				}
			}
		}
		return found;
	}

	public void addAcct(ATM a) {
		BankAccount account = new BankAccount(a);
		accounts.add(account);
	}

}