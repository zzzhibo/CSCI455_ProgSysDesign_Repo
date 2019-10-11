import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

// Name: Zhibo Wang
// USC NetID: zhibowan
// CSCI455 PA2
// Fall 2019

/*
  class SolitaireBoard
  The board for Bulgarian Solitaire.  You can change what the total 
  number of cards is for the game by changing NUM_FINAL_PILES, below.  
  Don't change CARD_TOTAL directly, because there are only some values
  for CARD_TOTAL that result in a game that terminates.  (See comments 
  below next to named constant declarations for more details on this.)
*/

public class SolitaireBoard {

   public static final int NUM_FINAL_PILES = 9;
   // number of piles in a final configuration
   // (note: if NUM_FINAL_PILES is 9, then CARD_TOTAL below will be 45)

   public static final int CARD_TOTAL = NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2;
   // bulgarian solitaire only terminates if CARD_TOTAL is a triangular number.
   // see: http://en.wikipedia.org/wiki/Bulgarian_solitaire for more details
   // the above formula is the closed form for 1 + 2 + 3 + . . . + NUM_FINAL_PILES

   // Note to students: you may not use an ArrayList -- see assgt
   // description for details.

   /**
    * Representation invariant: NUM_FINAL_PILES is the number of piles. CARD_TOTAL
    * equals to the NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2. Cards in each pile
    * are integers between the range of 0 and CARD_TOTAL. Sum of every pile equals
    * to NUM_FINAL_PILES
    */

   // <add instance variables here>
   private Random rand; // initialize a rand
   private int[] boardPiles = new int[CARD_TOTAL]; // initialize a partially filled array for piles
   private int arrIndex = 0;

   /**
    * Creates a solitaire board with the configuration specified in piles. piles
    * has the number of cards in the first pile, then the number of cards in the
    * second pile, etc. PRE: piles contains a sequence of positive numbers that sum
    * to SolitaireBoard.CARD_TOTAL
    */
   public SolitaireBoard(ArrayList<Integer> piles) {

      // sample assert statement (you will be adding more of these calls)
      // this statement stays at the end of the constructor.
      for (int i = 0; i < (arrIndex = piles.size()); i++) {
         boardPiles[i] = piles.get(i);

      }

      assert isValidSolitaireBoard(); // Precondition: piles contains a sequence of positive numbers that sum to
                                      // SolitaireBoard.CARD_TOTAL
   }

   /**
    * Creates a solitaire board with a random initial configuration.
    */
   public SolitaireBoard() {
      rand = new Random();
      int counter = 0;
      int numTemp = 0;

      while (counter != CARD_TOTAL) {
         numTemp = rand.nextInt((CARD_TOTAL + 1 - counter)); // due to the nature of nextInt method, +1 needs to be
                                                             // added (this is not a magic number)
         if (numTemp != 0) {
            boardPiles[arrIndex] = numTemp;
            arrIndex++;
            counter += numTemp;

         }

      }

      assert isValidSolitaireBoard();

   }

   /**
    * Plays one round of Bulgarian solitaire. Updates the configuration according
    * to the rules of Bulgarian solitaire: Takes one card from each pile, and puts
    * them all together in a new pile. The old piles that are left will be in the
    * same relative order as before, and the new pile will be at the end.
    */
   public void playRound() {
      // int locIndex = 0;
      int pileSum = 0;

      for (int i = 0; i < arrIndex; i++) {
         boardPiles[i] = boardPiles[i] - 1;
         pileSum++;
      }
      boardPiles[arrIndex] = pileSum;
      arrIndex++;
      boardPiles = removeZeros(boardPiles, arrIndex);

      assert isValidSolitaireBoard();
   }

   /**
    * Returns true iff the current board is at the end of the game. That is, there
    * are NUM_FINAL_PILES piles that are of sizes 1, 2, 3, . . . , NUM_FINAL_PILES,
    * in any order.
    */

   public boolean isDone() {
      int[] finArr = new int[CARD_TOTAL];
      int[] tempArr = boardPiles.clone();
      bubbleSort(tempArr, arrIndex);

      for (int i = 0; i < NUM_FINAL_PILES; i++) {
         finArr[i] = (i + 1);
      }

      if (Arrays.equals(tempArr, finArr)) {
         return true;
      }

      assert isValidSolitaireBoard();
      return false;

   }

   /**
    * Returns current board configuration as a string with the format of a
    * space-separated list of numbers with no leading or trailing spaces. The
    * numbers represent the number of cards in each non-empty pile.
    */
   public String configString() {

      String boardPileString = "";

      for (int i = 0; i < arrIndex; i++) {
         if (i < (arrIndex - 1)) {
            boardPileString += (boardPiles[i] + " ");
         } else {
            boardPileString += boardPiles[i]; // leave no leading or trailing spaces
         }

      }
      assert isValidSolitaireBoard();
      return boardPileString;
   }

   /**
    * Returns true iff the solitaire board data is in a valid state (See
    * representation invariant comment for more details.)
    */
   private boolean isValidSolitaireBoard() {

      int sum = 0; // record the total num of piles

      for (int i = 0; i < arrIndex; i++) {

         if (boardPiles[i] < 1 || boardPiles[i] > CARD_TOTAL) { // cards can't be smaller than 1 or larger than card
                                                                // total
            return false;
         }
         sum += boardPiles[i];
      }

      if (sum != CARD_TOTAL) {
         return false;
      }

      return true; // all passed then return true

   }

   // <add any additional private methods here>

   /**
    * remove zeros method
    * 
    * @param boardPiles has to satisfy the same representation invariants as others
    * @param locIndex   is just arrIndex
    * @return int[] with all zeros removed in the original array
    */

   private int[] removeZeros(int[] boardPiles, int locIndex) {

      int[] tempArr = new int[CARD_TOTAL];
      int tempLocIndex = 0;

      for (int i = 0; i < locIndex; i++) {
         if (boardPiles[i] != 0) {
            tempArr[tempLocIndex] = boardPiles[i];
            tempLocIndex++;
         } else {
            continue;
         }
      }
      arrIndex = tempLocIndex;
      return tempArr;
   }

   /**
    * sorting method by using bubble sort algorithm
    * 
    * @param tempArr  for doing the sorting
    * @param locIndex is just arrIndex
    */
   private void bubbleSort(int[] tempArr, int locIndex) {

      for (int i = 0; i < locIndex - 1; i++) {
         for (int j = 0; j < locIndex - i - 1; j++) {
            if (tempArr[j] > tempArr[j + 1]) {
               int tempNum = tempArr[j];
               tempArr[j] = tempArr[j + 1];
               tempArr[j + 1] = tempNum;

            }
         }
      }

   }

}
