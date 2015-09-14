/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atmsystem;
import java.util.*;


public class ATMSystem {

	public static void main(String[] args) {
		Bank a = new Bank("A");
		Bank b = new Bank("B");
		ATM a1 = new ATM("ATM_A1", a.getId());
		ATM a2 = new ATM("ATM_A2", a.getId());
		ATM b1 = new ATM("ATM_B1", b.getId());
		ATM b2 = new ATM("ATM_B2", b.getId());
                
		Customer c = new Customer();
                
		c.addAtm(a1);
		c.addAcct(a1);
                c.withdraw(a1);

	}
}