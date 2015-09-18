package atmsystem;
import java.util.*;
/**
	The purpose of this class is to implement CashCard object for the ATM network
*/
public class CashCard
{
	private int cardNumber;
	private Date expirationDate;
	private String bankId;
	private String password;
	public static final int LOWER_LIMIT = 100000;
	public static final int UPPER_LIMIT = 1000000;
        private int BankAccountNumber;
        private Bank b;
	
	/**
         * The constructor for the CashCard object
         * @param atm The ATM for the CashCard
         */
	public CashCard(ATM atm) {
            makeExpirationDate();
            this.bankId= atm.getATMID();
	}
        
        /**
         * This method gives a number to the CashCard object
         * @param number The number to give to the CashCard
         */
	public void makeCardNumber(int number) {
            this.cardNumber = number;
	}
        
        /**
         * This method creates an expiration data 5 years into the future
         */
	public void makeExpirationDate() {
            Calendar rightNow = Calendar.getInstance();
            int year = rightNow.get(Calendar.YEAR);
            int month = rightNow.get(Calendar.MONTH);
            int day = rightNow.get(Calendar.DAY_OF_MONTH);
            rightNow.set(year+5,month,day);
            Date future  = rightNow.getTime();
            this.expirationDate = future;
	}
        /**
         * This method returns the expiration date of the CashCard
         * @return The expiration date of the CashCard
         */
        public Date getExpirationDate() {
		return this.expirationDate;
	}
        /**
         * This method returns the card number 
         * @return The card number of the CashCard
         */
	public int getCardNumber() {
            return this.cardNumber;
	}
        /**
         * This method sets the BankAccount number for the CashCard
         * @param number The number to make the BankAccount number
         */
        public void setCardBankAccountNumber(int number) {
            this.BankAccountNumber = number; 
	}
        /**
         * This method creates a password for the CashCard
         * @param password The password for the CashCard object
         */
	public void createPassword(String password) {
            this.password = password;    
	}
        
        /**
         * This method sets the Bank ID for the CashCard.  It provides needed data to verify against customer input
         * @param id The ID to set for the bankID property
         */
	public void setCardId(String id) {
            this.bankId = id;
	}
        
        /**
         * The method returns the bankId for the CashCard property
         * @return The value for the bankId property
         */
        public String getId() {
            return this.bankId;
        }
        
        /**
         * This method returns the BankAccountNumber
         * @return The BankAccountNumber for the CashCard object
         */
        public int getBankAccountNumber() {
            return BankAccountNumber;
        }
        /**
         * This method returns the CashCard's current password
         * @return The password for the CashCard
         */
        public String getPassword() {
            return this.password;
        }
        
	

}