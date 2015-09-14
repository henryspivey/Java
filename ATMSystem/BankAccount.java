import java.util.*;
/**
	The purpose of this class is to implement BankAccount objects for the ATM network
*/

public class BankAccount {

	private String atmName;
	public static final int LOWER_LIMIT = 100000;
	public static final int UPPER_LIMIT = 1000000;
	private String accountNumber;

	/**
		Constructor
	*/
	public BankAccount(ATM a)
	{	
		CashCard card = new CashCard(a);
		this.atmName = a.getATMName();
		makeRandomAccountNumber();
		card.setCardId(a.getATMID());
		card.cardNumber = makeRandomCardNumber();
		card.expirationDate = card.makeExpirationDate();
		setCashCardNumber(card);
	}

	// separate method for make number which is random 6-8 numbers
	public void makeRandomAccountNumber () {
		// make  random number here
		Random random = new Random();
		int randomNumber = random.nextInt((UPPER_LIMIT-LOWER_LIMIT)+1)+min;
		this.accountNumber = randomNumber;
	}

	public int getAccountNumber() {
		return this.accountNumber;
	}

	public void setCashCardNumber(CashCard c) {
		c.accountNumber = this.getAccountNumber();
	}



}