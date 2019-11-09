import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// Name: Zhibo Wang
// CSCI455 PA2
// USC NetID: zhibowan
// Fall 2019

/**
 * A main program that does a Bulgarian Solitaire Simulation. Implementing a
 * program to model the game Bulgarian Solitaire. This program will run in the
 * console window and will not have a GUI. java -ea BulgarianSolitaireSimulator
 * -u java -ea BulgarianSolitaireSimulator -s java -ea
 * BulgarianSolitaireSimulator -u -s java -ea BulgarianSolitaireSimulator -u
 * Prompts for the initial configuration from the user, instead of generating a
 * random configuration. -s Stops between every round of the game. The game only
 * continues when the user hits enter (a.k.a., return).
 * 
 */

public class BulgarianSolitaireSimulator {

   public static void main(String[] args) {

      boolean singleStep = false;
      boolean userConfig = false;

      for (int i = 0; i < args.length; i++) {
         if (args[i].equals("-u")) {
            userConfig = true;
         } else if (args[i].equals("-s")) {
            singleStep = true;
         }
      }

      // <add code here>

      flagChecker(singleStep, userConfig);

   }

   // <add private static methods here>

   /**
    * readInput gives prompts to user asking for valid input get usr input and call
    * other valid-checking methods
    * 
    * @return ArrayList<Integer>
    */

   private static ArrayList<Integer> readInput(Scanner sc) {
      String usrInput = new String();

      ArrayList<Integer> piles = new ArrayList<Integer>();

      System.out.println("Please enter a space-separated list of positive integers followed by newline:");
      usrInput = sc.nextLine().trim().replaceAll("\\s+", " "); // replaces all extra spaces by one
      String[] usrInputArr = usrInput.split(" "); // convert into a space separated string list

      while (inputCheck(usrInputArr) == false) { // keep calling for a valid input
         System.out.println("ERROR: Each pile must have at least one card and the total number of cards must be "
               + SolitaireBoard.CARD_TOTAL);
         System.out.println("Please enter a space-separated list of positive integers followed by newline:");
         usrInput = sc.nextLine().trim().replaceAll("\\s+", " ");
         usrInputArr = usrInput.split(" ");
      }

      for (int i = 0; i < usrInputArr.length; i++) {
         piles.add(Integer.parseInt(usrInputArr[i]));
      }

      return piles;

   }

   /**
    * check if usr input is valid. Elements can't be negative, non-integer. Sum
    * must be equal to the card total
    * 
    * @param usrInputArr
    * @return boolean
    */
   private static boolean inputCheck(String[] usrInputArr) {
      int arraySum = 0; // record the sum of usrInputArr
      int numTemp = 0; // temporary number for parseInt

      if (usrInputArr.length == 0) { // input can't be empty
         return false;
      }

      for (int i = 0; i < usrInputArr.length; i++) {
         if (isInteger(usrInputArr[i]) == false) {
            return false;
         }
         numTemp = Integer.parseInt(usrInputArr[i]);
         if (numTemp < 1) {
            return false;
         }
         arraySum += numTemp;
      }

      if (arraySum != SolitaireBoard.CARD_TOTAL) { // check if sum equals to the number of cards
         return false;
      }
      return true;
   }

   /**
    * check if usr input is integer
    * 
    * @param inputString
    * @return boolean
    */
   private static boolean isInteger(String inputString) { // check if input is all integers, if not jump right back to
                                                          // ask for another round of input
      try {
         Integer.parseInt(inputString);
         return true;
      } catch (Exception e) {
         return false;
      }
   }

   /**
    * check the usr input flags and execute the program accordingly
    * 
    * @param singleStep
    * @param userConfig
    */
   private static void flagChecker(boolean singleStep, boolean userConfig) {
      Scanner sc = new Scanner(System.in);

      if (singleStep == true && userConfig == true) {

         userConfigRun(singleStep, sc);

      } else if (singleStep == true) {

         SolitaireBoard slb = new SolitaireBoard();
         System.out.print("Initial configuration: ");
         System.out.println(slb.configString());
         singleStepRun(slb, sc);

      } else if (userConfig == true) {

         userConfigRun(singleStep, sc);

      } else {

         int currIndex = 1;
         SolitaireBoard slb = new SolitaireBoard();
         System.out.print("Initial configuration: ");
         System.out.println(slb.configString());
         while (slb.isDone() != true) {
            slb.playRound();
            System.out.print("[" + currIndex + "] Current configuration: ");
            System.out.println(slb.configString());
            currIndex++;
         }

      }

      System.out.println("Done!");

   }

   /**
    * execute the singleStep method
    * 
    * @param slb
    * @param sc
    */
   private static void singleStepRun(SolitaireBoard slb, Scanner sc) {
      int currIndex = 1;

      slb.playRound();
      System.out.print("[" + currIndex + "] Current configuration: ");
      System.out.println(slb.configString());
      System.out.print("<Type return to continue>");
      currIndex++;

      while (slb.isDone() != true && sc.hasNextLine()) {
         slb.playRound();
         System.out.print("[" + currIndex + "] Current configuration: ");
         System.out.println(slb.configString());
         currIndex++;
         System.out.print("<Type return to continue>");
         sc.nextLine();
      }
      System.out.println(); // print an empty line to fit format standard

   }

   /**
    * execute the userConfig method
    * 
    * @param singleStep
    * @param sc
    */
   private static void userConfigRun(boolean singleStep, Scanner sc) {
      int currIndex = 1;
      System.out.println("Number of total cards is " + SolitaireBoard.CARD_TOTAL);
      System.out.println("You will be entering the initial configuration of the cards (i.e., how many in each pile).");
      ArrayList<Integer> boardPiles = readInput(sc);

      SolitaireBoard slb = new SolitaireBoard(boardPiles);

      System.out.print("Initial configuration: ");
      System.out.println(slb.configString());

      while (slb.isDone() != true && singleStep != true) {
         slb.playRound();
         System.out.print("[" + currIndex + "] Current configuration: ");
         System.out.println(slb.configString());
         currIndex++;
      }

      if (singleStep == true) {
         singleStepRun(slb, sc);
      }

   }

}
