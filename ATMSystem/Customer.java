package atmsystem;
import java.util.*;

/**
 * The purpose of this class is to create Customer objects that hold info for use in the ATM network
*/

public class Customer {

	private ArrayList<ATM> atms = new ArrayList<>();
	private ArrayList<BankAccount> accounts = new ArrayList<>();
        private ArrayList<Bank> banks = new ArrayList<>();
        private ArrayList<CashCard> cards = new ArrayList<>();
        private final int INITIAL_BALANCE = 40;
        private ATM atm;
        private CashCard card;
        private BankAccount account;
        /**
         * The constructor for the customer object
         * @param a This is an ATM and is used to verify account data along with the CashCard
         * @param c This is a CashCard and is used to verify account data along with the ATM
         * @param account a BankAccount to hold important data
         */
	public Customer(ATM a, CashCard c, BankAccount account) {
                this.account = account;
                this.atm = a;
                this.card = c;
	}
        
        /**
         * This method provides feedback on the state of the user's ATM data
         * @param atm 
         */
	public void addAtm(ATM atm) {
            if (verifyATMInfo(atm) == true) {
                System.out.println("You already have a bank account with this bank.");
            } else {
                this.atms.add(atm);
            }
	}
	/**
         * This method verifies the customer's ATM data
         * @param b The ATM the customer claims to use
         * @return found if the id of the ATM is found in the customer's ATMs variable
         */
	public boolean verifyATMInfo(ATM b) {
            boolean found = false;
            if (this.atm == null) {
                return false;
            } else if (this.atm.getATMID().equals(b.getATMID())) {
                found = true;
            } else {
                System.out.println("Account data not found for account "+ this.card.getCardNumber() +
                        " at Bank " + this.atm.getATMID());
            }
            return found;
	}
        
        /**
         * This method verifies the CashCard data input by the customer along with its expiration date
         * @param number A number entered to check against entered CashCard's number
         * @param c The CashCard of the customer to verify
         * @return verified if the entered data is correct and the card is valid
         */
        public boolean verifyCashCard(int number, CashCard c) {
 
            Date today = Calendar.getInstance().getTime(); // http://alvinalexander.com/blog/post/java/determine-today-date
            int cardNumber = number;
            boolean verified = false;
            if (this.card.getId().equals(this.account.getBankID(c))) {
                if (c.getExpirationDate().after(today)) {
                    System.out.println("Welcome to Bank " + account.getBankID(c));
                    verified = true;
                } else {
                    System.out.println(c.getExpirationDate());
                    System.out.println("Card is expired.");
                    verified = false;
                }
            } else {
                System.out.println("Cashcard has ID " + this.card.getId()+ " which is not recognized at Bank " +
                            this.account.getBankID(c));
                verified = false;
            }
            return verified;
        }

        /**
         * This method creates a card number from scanner input
         * @param c The CashCard to make a number for
         * @param sc This scanner to retrieve data
         */
        public void makeCardNumber(CashCard c, Scanner sc) {
            int number;
            System.out.println("Enter your card number: ");
            number = sc.nextInt();
            System.out.println(number);
            c.makeCardNumber(number);
	}
        /**
         * This method adds a CashCard to the users CashCards arraylist
         * @param c The CashCard to add
         */
        public void addCard(CashCard c) {
            this.cards.add(c);
        }
        /**
         * This method creates a password for the CashCard from scanner input
         * @param c The CashCard to make a password for
         * @param sc The scanner to read in data
         */
        public void createCardPassword(CashCard c, Scanner sc) { 
            System.out.println("Enter your card password: ");
            String password = sc.next();
           
            System.out.println(password);
            c.createPassword(password);
        }
        /**
         * This method adds an account to the users account arraylist
         * @param a The ATM to add to the account
         */
	public void addAcct(ATM a) {
		BankAccount account = new BankAccount(a,INITIAL_BALANCE);
		accounts.add(account); // adds account data to customer object
                banks.add(a.getBank(a.getATMID()));
 	}

        /**
         * This method retrieves a password from scanner input
         * @param sc The scanner to read data from
         * @return String The password retrieved from scanner input
         */
        public String getPassword(Scanner sc) {
            String password = sc.next();
            return password;
        }
        
        /**
         * This method's purpose is to verify customer data which is later needed to successfully withdraw 
         * @param sc The scanner to read in input
         * @param c The CashCard to verify
         * @return verified if the data entered is valid 
         */
        public boolean loginToAccount(Scanner sc, CashCard c) {
  
            Bank bank = new Bank(sc.next()); // read in a bank id
            ATM a = new ATM(sc.next(), bank.getId()); // make new ATM with bank id and ATM choice
            
            boolean verified = false;
            if(verifyATMInfo(a)) { 
                int cardNumber = c.getCardNumber();
                String password = c.getPassword();
                
                if(verifyCashCard(cardNumber,c)) { // needs to check if user has cardnumber data in instance variable
                    Bank retrieved = a.getBank(a.getATMID());
                    if (retrieved.verifyPassword(c, password)) {
                        verified = true;
                        System.out.println("Successfully logged in. ");
                    } else {
                        System.out.println("Password incorrect. Please enter it again: ");
                    }
                    while (!verified) {
                        System.out.println("Password incorrect. Please enter it again: ");
                        password = getPassword(sc);
                        if (retrieved.verifyPassword(c, password)) {
                            verified = true;
                            System.out.println("Successfully logged in. ");
                        }
                    }

                } 
            }
            return verified;
        }
        /**
         * This methods verifies entered data and withdraws amount entered from an account
         * @param sc The scanner object to read in data
         * Precondition: The amount must be greater than 0 and less 
         * than or equal to the predefined limit for the ATM. All data must be verified to successfully withdraw
         * Postcondition: The amount must be deducted from the account
         */
        public void withdraw(Scanner sc) {
            int balance = this.account.getBalance();
            String password = sc.next();
            System.out.println(password);
            while(sc.hasNextLine()) {
                if (!password.equals(this.card.getPassword())) {
                    System.out.println("Password incorrect. Please enter it again: ");
                    password = sc.next();
                    System.out.println(password);
                } else {
                    int amount = 0;
                    while(sc.hasNextInt()) {
                        amount = sc.nextInt();
                        System.out.println("Enter an amount to withdraw: $");
                        System.out.println(amount);

                    if (amount < 0)
                    {
                        System.out.println("$"+amount+" is not valid.  Please enter an amount greater than $0.00");

                    }else if (amount >= this.atm.getLimit())
                    {
                        System.out.println("Amount entered is greater than the limit for this ATM. \n"
                                                + "Please enter a different amount: ");       
                        amount = sc.nextInt();
                        if (amount > 0 && amount <= this.atm.getLimit())
                        {
                            balance -= amount;
                            System.out.println("$" + amount + " is withdrawn from  your account. The remaining "
                                    + "balance of this account is $" + balance + ".  If you have more transactions, "
                                    + "enter the amount or quit.");
                        }
                    }else 
                    { 
                        balance -= amount;
                        System.out.println("$" + amount + " is withdrawn from  your account. The remaining "
                                + "balance of this account is $" + balance + ".  If you have more transactions, "
                                + "enter the amount or quit.");
                    }
                    }
                }
            }
        }
    }
