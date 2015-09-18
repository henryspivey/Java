package atmsystem;
import java.util.*;
/**
	The purpose of this class is to implement BankAccount objects for the ATM network
*/

public class BankAccount {

	private String atmName;
	public static final int LOWER_LIMIT = 100000;
	public static final int UPPER_LIMIT = 1000000;
	private int accountNumber;
        private ArrayList<CashCard> cards = new ArrayList<>();
        Bank b;
        
        private int balance;

	/**
         * The constructor for the BankAccount object
         * @param a The ATM to add to the BankAccount
         * @param balance The initial balance for the BankAccount
         */
	public BankAccount(ATM a, int balance)
	{	
            CashCard card = new CashCard(a);
            this.atmName = a.getATMName();
            makeRandomAccountNumber();
            String id = a.getATMID();
            card.setCardId(id);
            cards.add(card); // bank account has a set of cashcards 
            this.balance = balance;   
	}

	/**
         * This method creates a random account number for the BankAccount object to make it a more realistic experience
         */
	public void makeRandomAccountNumber () {
		// make  random number here
		Random random = new Random();
		int randomNumber = random.nextInt((UPPER_LIMIT-LOWER_LIMIT)+1)+LOWER_LIMIT;
		this.accountNumber = randomNumber;
	}
        /**
         * This method adds an account to the BankAccount object
         * @param a The BankAccount to add
         */
        public void addAccount(BankAccount a) {
            b.addAccount(a);
        }
        /**
         * This method returns the BankAccount's accountNumber
         * @return The account number for the BankAccount
         */
	public int getAccountNumber() {
		return this.accountNumber;
	}
        
        /**
         * This method sets the CashCard's BankAccount number
         * @param c The CashCard to add a number to
         */
	public void setCashCardNumber(CashCard c) {
            c.setCardBankAccountNumber(this.accountNumber);
	}
        /**
         * This method gets the BankId for a given CashCard object. It is useful for verifying BankAccount data
         * @param c The CashCard to get the ID from
         * @return The ID of the CashCard
         */
        public String getBankID(CashCard c) {
            return c.getId(); 
        }
        
        /**
         * This method returns the Customer's BankAccount cards arraylist
         * @return The cards arraylist
         */
        public ArrayList getCards() {
            return this.cards;
        }
        /**
         * This method returns the balance for the BankAccount 
         * @return The balance for the BankAccount
         */
        public int getBalance() {
            return this.balance;
        }
        
        
}