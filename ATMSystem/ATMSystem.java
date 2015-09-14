import java.util.*;


public class ATMSystem {

	public static void main(String[] args) {
		Bank a = new Bank("A");
		Bank b = new Bank("B");
		ATM a1 = new ATM("ATM_A1", a.getId());
		ATM a2 = new ATM("ATM_A2", a.getId());
		ATM b1 = new ATM("ATM_B1", b.getId());
		ATM b2 = new ATM("ATM_B2", b.getId());

		// customer picks open an account
		// List all atm names from bank
		// prompt customer to choose an atm from the bank
		// create customer object, check if atm ref. already exists
		// if it already is in the customer object, then customer can't open another account with the same bank
		// if not add atm to customer object for later reference
		// Customer c = new Customer(atm obj); 
		Customer c = new Customer();
		c.addAtm(a1);
		c.addAcct(a1);

	}

}