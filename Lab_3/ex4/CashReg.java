/**
 * A cash register totals up sales and computes change due.
 * 
 * Version for CS 455 lab 3. Modified from version from Big Java, 6th ed.
 * 
 * Changes [made by CMB]:
 * 
 * This version of the class is called CashReg (instead of CashRegister) Added
 * getTotal() accessor function. Made constants private.
 * 
 * Ex: CashReg register = new CashReg(); register.recordPurchase(0.59); // ring
 * something up register.recordPurchase(1.99); // ring up another item
 * register.recordPurchase(5.0); // ring up a third item double tot =
 * register.getTotal(); // total of purchases so far: 7.58
 * register.receivePayment(10,0,0,0,0); // customer pays with a 10 int change =
 * register.giveChange(); // compute change owed: 2.42 // and zeroes out
 * register
 * 
 * register.recordPurchase(1.0); // now we start ringing up someone else . . .
 * 
 */
public class CashReg {
   // private static final double QUARTER_VALUE = 0.25;
   // private static final double DIME_VALUE = 0.1;
   // private static final double NICKEL_VALUE = 0.05;
   // private static final double PENNY_VALUE = 0.01;

   // private double purchase;
   // private double payment;

   private static final int DOLLAR_VALUE = 100;
   private static final int QUARTER_VALUE = 25;
   private static final int DIME_VALUE = 10;
   private static final int NICKEL_VALUE = 5;
   private static final int PENNY_VALUE = 1;

   private int purchase;
   // private int payment;
   private Change payment = new Change();

   /**
    * Constructs a cash register with no money in it.
    */
   public CashReg() {
      purchase = 0;
      // payment = 0;
   }

   /**
    * Records the purchase price of an item.
    * 
    * @param amount the price of the purchased item
    */
   public void recordPurchase(double amount) {
      amount = amount * 1000; // convert dollar to cent
      purchase = purchase + (int) (amount / 10); // 1000 for high precision, convert back

   }

   /**
    * Gets total of all purchases made.
    */
   public double getTotal() {
      return purchase / 100.0;
   };

   /**
    * Enters the payment received from the customer.
    * 
    * @param dollars  the number of dollars in the payment
    * @param quarters the number of quarters in the payment
    * @param dimes    the number of dimes in the payment
    * @param nickels  the number of nickels in the payment
    * @param pennies  the number of pennies in the payment
    */
   // public void receivePayment(int dollars, int quarters, int dimes, int nickels,
   // int pennies) {
   // payment = dollars * DOLLAR_VALUE + quarters * QUARTER_VALUE + dimes *
   // DIME_VALUE + nickels * NICKEL_VALUE
   // + pennies * PENNY_VALUE;

   // }

   /**
    * Computes the change due and resets the machine for the next customer.
    * 
    * @return the change due to the customer
    */
   // public double giveChange() {
   // double change = payment - purchase;
   // purchase = 0;
   // payment = 0;
   // return change/100.0;
   // }

   int paymentnum;

   public void receivePayment(Change money) {
      payment = money;

   }

   public Change giveChange() {

      int changetemp = payment.totalValue() - purchase;
      // int pennychange = changetemp;
      int pennychange = 0;
      int nickelchange = 0;
      int dimechange = 0;
      int quarterchange = 0;
      int dollarchange = 0;

      while (changetemp != 0) {

         if (changetemp >= 100) {
            dollarchange++;
            changetemp = changetemp - 100;
         } else {

            if (changetemp >= 25) {
               quarterchange++;
               changetemp = changetemp - 25;
            } else if (changetemp < 25 && changetemp >= 10) {
               dimechange++;
               changetemp = changetemp - 10;
            } else if (changetemp < 10 && changetemp >= 5) {
               nickelchange++;
               changetemp = changetemp - 5;
            } else {
               pennychange++;
               changetemp = changetemp - 1;
            }

         }

      }

      purchase = 0;

      return new Change(dollarchange, quarterchange, dimechange, nickelchange, pennychange);

   }
}
