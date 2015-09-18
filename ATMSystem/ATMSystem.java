/**
 * The ATM System implements a ATM network simulation
 * @author Henry Spivey
 * @version 1.0
 */
package atmsystem;
import java.io.*;
import java.util.*;


public class ATMSystem {
   
	public static void main(String[] args) throws FileNotFoundException {
        
            Bank a = new Bank("A");
            Bank b = new Bank("B");
            ATM a1 = new ATM("ATM_A1", a.getId());
            ATM a2 = new ATM("ATM_A2", a.getId());
            ATM b1 = new ATM("ATM_B1", b.getId());
            ATM b2 = new ATM("ATM_B2", b.getId());
            Scanner sc = new Scanner(System.in);
            
            /* 
            Create Customer a1 
            */
            BankAccount account_a1 = new BankAccount(a1, 40);
            CashCard card_a1 = new CashCard(a1);
            Customer customer_a1 = new Customer(a1, card_a1, account_a1);
            customer_a1.makeCardNumber(card_a1,sc);
            customer_a1.createCardPassword(card_a1, sc);
            customer_a1.loginToAccount(sc, card_a1);
            
            /* 
            Create Customer a2 
            */
            BankAccount account_a2 = new BankAccount(a2, 40);
            CashCard card_a2 = new CashCard(a2);
            Customer customer_a2 = new Customer(b2, card_a2, account_a2);
            customer_a2.makeCardNumber(card_a2,sc); // sets the card number of the customer card
            customer_a2.createCardPassword(card_a2, sc); // sets the card password of the customer card
            customer_a2.loginToAccount(sc, card_a2);

            /* 
            Create Customer b1 
            */
            BankAccount account_b1 = new BankAccount(b1, 40);
            CashCard card_b1 = new CashCard(b1);
            Customer customer_b1 = new Customer(b1, card_a1, account_b1);
            //customer_b1.withdraw(sc);
            customer_b1.makeCardNumber(card_b1,sc);
            customer_b1.createCardPassword(card_b1, sc);
            customer_b1.loginToAccount(sc, card_b1);
           
            /* 
            Create Customer b2 
            */
            BankAccount account_b2 = new BankAccount(b2, 40);
            CashCard card_b2 = new CashCard(b2);
            Customer customer_b2 = new Customer(b2, card_b2, account_b2);
            customer_b2.makeCardNumber(card_b2,sc);
            customer_b2.createCardPassword(card_b2, sc);
            customer_b2.loginToAccount(sc, card_b2);
            //customer_b2.loginToAccount(sc, card_b2);
            customer_b2.withdraw(sc);
            
            /* 
            Create Customer b1 
            */
            BankAccount account_b3 = new BankAccount(b1, 40);
            CashCard card_b3 = new CashCard(b1);
            Customer customer_b3 = new Customer(b1, card_b3, account_b3);
            //customer_b3.withdraw(sc);
            
            System.out.println("You have decided to stop entering commands. Program terminated!");
	}

} 