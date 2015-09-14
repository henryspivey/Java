package atmsystem;
import java.util.*;

/**
	The purpose of this class is to create Customer objects that hold info for use in the ATM network
*/

public class Customer {

	private ArrayList<ATM> atms = new ArrayList<>();
	private ArrayList<BankAccount> accounts = new ArrayList<>();
        private ArrayList<Bank> banks = new ArrayList<>();
        private final int INITIAL_BALANCE = 40;
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
        
        // this will be used to verify card numbers against what is entered
        public boolean verifyCashCard(int number) {
            /**
             * Check if the cash card number is associated with this user
             * if c.getCardNumber() is a card number of an account of this user return true
             * else return false
             */
            int cardNumber = number;
            int i = 0;
            boolean verified = false;
            for(BankAccount account: this.accounts) {
                ArrayList<CashCard> cards = account.getCards();
                while(i < cards.size()){
                    CashCard current = cards.get(i);
                    if(current.getCardNumber() == cardNumber) {
                        if(current.getId().equals(account.getBankID(current))) {
                            System.out.println("Welcome to Bank " + account.getBankID(current));
                            verified = true;
                        }
                    }
                    else {
                        System.out.println("Cashcard not recognized.");
                        verified = false;
                    }
                    i++;
                }
            }
            return verified;
	}
        
        public void makeCardNumber(CashCard c) {
            Scanner sc = new Scanner(System.in);
            boolean entered = false;
            do {
                System.out.println("Your desired card number: ");
                int input = sc.nextInt();
                c.makeCardNumber(input);
                entered = true;
                System.out.println("Number set!");
                
            } while (entered = false);
            
	}
	public void addAcct(ATM a) {
		BankAccount account = new BankAccount(a,INITIAL_BALANCE);
		accounts.add(account); // adds account data to customer object
                banks.add(a.getBank(a.getATMID()));
                // get card from account and set its password
         
                ArrayList cards = account.getCards();
                
                // check if bank account is in cards
                int i = 0;
                while (i < cards.size()) {
                    CashCard current = (CashCard) cards.get(i);
                    // set the cash card number to entered number
                    makeCardNumber(current);
                    current.createPassword(createPassword());
                    // checks to see if the particular account in question is in the cards object
                    /*if(current.getCardNumber() == account.getAccountNumber()) {
                        // prompt for password here
                        
                        System.out.println("Your cashcard number  " + current.getCardNumber());
                        
                    }*/
                    i++;
                }
                
	}
        
        public void listAccountData() {
            int number_of_accounts = this.accounts.size();
            
            for(int i = 0; i < number_of_accounts; i++)
            {
                BankAccount currentAccount = this.accounts.get(i);
                ArrayList cards = currentAccount.getCards();
                CashCard currentCashCard = (CashCard) cards.get(cards.size()-1);
                System.out.println(currentCashCard.getCardNumber());
                System.out.println(currentCashCard.getId());
                
               
            }
        }
        
        public boolean enterCardNumber(int number) {
            return verifyCashCard(number);
        }
        public Bank getBanks() {
            for (Bank n : this.banks) {
                return n;
            }
        }
        
        public String createPassword() {
            String password;
            Scanner sc = new Scanner(System.in);
            boolean entered  = false;
            do {
                System.out.println("Enter a password to protect your cashcard: ");
                String input = sc.next();
                password = input;
                entered = true;
                System.out.println("Password set!");
                return password;
            } while(entered = false);
            
        }
        
        public String getPassword() {
            boolean entered = false;
            do {
                System.out.println("Please enter your password for your cash card:  ");
                Scanner sc = new Scanner(System.in);
                String password = sc.next();
                return password;
            }while (entered = false);
            
        }
        
        public boolean loginToAccount(ATM a) {
            /*
            Take in atm
            check if atm exists with verify atm info
            if that is true then we ask for a card number
            get the card number and verify with verifyCashCard
            if that is true then we ask for the password
            if password matches then return true
            
            */
            boolean verified = false;
            if(verifyATMInfo(a)) {
                Scanner sc = new Scanner(System.in);
                boolean entered = false;
                int cardNumber;
                do {
                    System.out.println("Your card number: ");
                    cardNumber = sc.nextInt();
                    entered = true;
                } while (entered = false);
                if(verifyCashCard(cardNumber)) {
                    Bank retrieved = a.getBank(a.getATMID());
                    int i = 0;
                    for(BankAccount account: this.accounts) {
                        ArrayList<CashCard> cards = account.getCards();
                        while (i < cards.size()) {
                            CashCard current = cards.get(i);
                            String password = getPassword();
                            if (retrieved.verifyPassword(current, password)) {
                                verified = true;
                                System.out.println("Successfully logged in. ");
                            }
                            while (!verified) {
                                System.out.println("Password incorrect. Please enter it again: ");
                                password = getPassword();
                                if(retrieved.verifyPassword(current,password)) {
                                    verified = true;
                                    System.out.println("Successfully logged in. ");
                                }
                            } 
                            i++;
                        }
                    }
                } 
            }
            return verified;
        }
        /**
         * 
         * @param a The ATM that is being withdrawn from
         * Precondition: The amount must be greater than 0 and less 
         * than or equal to the predefined limit for the ATM
         */
        public void withdraw(ATM a) {
            if (loginToAccount(a)) // if they have logged in successfully then proceed
            {
                Bank retrieved = new Bank(a.getATMID());
                ArrayList bankAccounts = retrieved.retrieveAccounts();
                int accountSize = bankAccounts.size();
                int i = 0;
                Scanner sc = new Scanner(System.in);
                System.out.print("Please enter an amount to withdraw: $");
                int amount = sc.nextInt();
                boolean verified = false;
                if(amount < 0)
                {
                    System.out.println("Please enter an amount greater than $0.00");
                    verified = false;
                }
                else if (amount >= a.getLimit()) {
                    verified = false;
                    while (!verified) {
                        System.out.println("Amount entered is greater than the limit for this ATM. \n"
                                + "Please enter a different amount: ");
                        if (amount > 0 && amount <= a.getLimit()) {
                            verified = true;

                            while(i < accountSize) {
                                BankAccount current = (BankAccount)bankAccounts.get(i);
                                current.withdraw(amount);
                                i++;
                            }
                        }
                    }
                }
            }
        }
}