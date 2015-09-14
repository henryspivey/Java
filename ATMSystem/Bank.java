package atmsystem;
import java.util.*;

/**
	The purpose of this class is to implement Bank objects for the ATM network
*/

public class Bank {
    
	private ArrayList<ATM> atms = new ArrayList<ATM>();
        private ArrayList<Integer> cardNumbers = new ArrayList<Integer>();
        private ArrayList<BankAccount> accounts = new ArrayList<>();
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

        public void addCard(int c)
	{
            cardNumbers.add(c);
	}
        
        public void addAccount(BankAccount account)
	{
            accounts.add(account);
	}
        
        public ArrayList retrieveAccounts() {
            return this.accounts;
        }
        
        public boolean verifyPassword(CashCard c, String password) {
            if ((c.getPassword().equals(password))) {
                return true;
            }
            else {
                return false;
            }
        }
}