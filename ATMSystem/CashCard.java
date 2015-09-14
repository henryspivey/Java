import java.util.*;
/**
	The purpose of this class is to implement CashCard object for the ATM network
*/
public class CashCard
{
	private String cardNumber;
	private Date expirationDate;
	private int bankId;
	private String password;
	public static final int LOWER_LIMIT = 100000;
	public static final int UPPER_LIMIT = 1000000;
	
	/**
		Constructor
	*/
	public CashCard(ATM atm) {
		makeRandomCardNumber();
		setCardId(atm.getATMID());
	}

	public void makeRandomCardNumber() {
		// make  random number here
		Random random = new Random();
		int randomNumber = random.nextInt((UPPER_LIMIT-LOWER_LIMIT)+1)+min;
		this.cardNumber = randomNumber;
	}

	public void makeExpirationDate() {
		this.expirationDate = new Date();
	}

	public int getCardNumber() {
		return this.cardNumber;
	}

	public void createPassword(String password) {
		this.password = password;
	}

	public void setCardId(String id) {
		this.bankId = id;
	}

	

	

}