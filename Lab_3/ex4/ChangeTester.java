// A test driver for the Change class

public class ChangeTester {

   public static void main(String[] args) {
      Change change = new Change();
      System.out.println("Total value[exp 0] = " + change.totalValue());
      System.out.println(change);  // Java automatically calls toString()

      change = new Change(3,0,1,7,9);
      System.out.println("Total value[exp 354] = " + change.totalValue());
      System.out.println(change);
       
   }

}
