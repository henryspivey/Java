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
		Constructor
	*/
	public CashCard(ATM atm) {
            makeExpirationDate();
            this.bankId= atm.getATMID();
	}
        
	public void makeCardNumber(int number) {
            this.cardNumber = number;
	}

	public void makeExpirationDate() {
		this.expirationDate = new Date();
	}
        
        public Date getExpirationDate() {
		return this.expirationDate;
	}
	public int getCardNumber() {
            return this.cardNumber;
	}
        
        public void setCardBankAccountNumber(int number) {
            this.BankAccountNumber = number; 
	}

	public void createPassword(String password) {
            this.password = password;    
	}

	public void setCardId(String id) {
            this.bankId = id;
	}
        
        public String getId() {
            return this.bankId;
        }
        
        public int getBankAccountNumber() {
            return BankAccountNumber;
        }
        
        public String getPassword() {
            return this.password;
        }
        
	

	

}