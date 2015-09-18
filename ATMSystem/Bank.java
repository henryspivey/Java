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
         * The constructor for the Bank object
         * @param id The ID for the Bank
         */
	public Bank(String id) {
            this.id = id;
	}
        /**
         * This method returns the ID of the current Bank
         * @return The ID of the current Bank
         */
	public String getId() {
            return this.id;
	}
        /**
         * This method adds an ATM to the Bank's ATM arraylist
         * @param atm The ATM to add
         */
	public void addAtm(ATM atm)
	{
            this.atms.add(atm);
	}
        /**
         * This method adds a CashCard number to the Bank's card numbers arraylist
         * @param c The number to add
         */
        public void addCard(int c)
	{
            cardNumbers.add(c);
	}
        /**
         * This method adds an account to the Bank's accounts arraylist
         * @param account The account to add
         */
        public void addAccount(BankAccount account)
	{
            accounts.add(account);
	}
        /**
         * This method retrieves the accounts from the current Bank
         * @return The Bank's accounts 
         */
        public ArrayList retrieveAccounts() {
            return this.accounts;
        }
        /**
         * This method compares a CashCard's password with a password supplied by the user
         * @param c The CashCard 
         * @param password The password
         * @return True if the data entered is valid
         */
        public boolean verifyPassword(CashCard c, String password) {
            if ((c.getPassword().equals(password))) {
                return true;
            }
            else {
                return false;
            }
        }
}