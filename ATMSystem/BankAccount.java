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
		Constructor
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
            addAccount();
	}

	// separate method for make number which is random 6-8 numbers
	public void makeRandomAccountNumber () {
		// make  random number here
		Random random = new Random();
		int randomNumber = random.nextInt((UPPER_LIMIT-LOWER_LIMIT)+1)+LOWER_LIMIT;
		this.accountNumber = randomNumber;
	}
        
        public void addAccount() {
            b.addAccount(this);
        }

	public int getAccountNumber() {
		return this.accountNumber;
	}

	public void setCashCardNumber(CashCard c) {
            c.setCardBankAccountNumber(this.accountNumber);
	}
        
        public String getBankID(CashCard c) {
            return c.getId(); 
       }
        
        public ArrayList getCards() {
            return this.cards;
        }
        
        public int getBalance() {
            return this.balance;
        }
        
        public boolean acceptOrReject(CashCard c, String password) {
            if(c.getPassword().equals(password)) {
                return true;
            }
            else {
                return false;
            }
        }
        
        public void withdraw(int amount) {
            this.balance -= amount;
        }



}