import java.util.Random;

// Name: Zhibo Wang
// USC NetID: zhibowan 
// CS 455 PA3
// Fall 2019 

/**
 * MineField class with locations of mines for a game. This class is mutable,
 * because we sometimes need to change it once it's created. mutators:
 * populateMineField, resetEmpty includes convenience method to tell the number
 * of mines adjacent to a location.
 */
public class MineField {

   // <put instance variables here>

   // self-explaining instance variables for MineField class
   private boolean[][] mineData;
   private int numMines;
   private int numRows;
   private int numCols;

   /**
    * Create a minefield with same dimensions as the given array, and populate it
    * with the mines in the array such that if mineData[row][col] is true, then
    * hasMine(row,col) will be true and vice versa. numMines() for this minefield
    * will corresponds to the number of 'true' values in mineData.
    * 
    * @param mineData the data for the mines; must have at least one row and one
    *                 col.
    */
   public MineField(boolean[][] mineData) {
      numRows = mineData.length;
      numCols = mineData[0].length;
      this.mineData = new boolean[numRows][numCols]; // creates a new same dimension minefield

      for (int i = 0; i < numRows; i++) {
         for (int j = 0; j < numCols; j++) {
            this.mineData[i][j] = mineData[i][j];
            if (this.mineData[i][j] == true) {
               numMines++;
            }
         }
      }

   }

   /**
    * Create an empty minefield (i.e. no mines anywhere), that may later have
    * numMines mines (once populateMineField is called on this object). Until
    * populateMineField is called on such a MineField, numMines() will not
    * correspond to the number of mines currently in the MineField.
    * 
    * @param numRows  number of rows this minefield will have, must be positive
    * @param numCols  number of columns this minefield will have, must be positive
    * @param numMines number of mines this minefield will have, once we populate
    *                 it. PRE: numRows > 0 and numCols > 0 and 0 <= numMines < (1/3
    *                 of total number of field locations).
    */
   public MineField(int numRows, int numCols, int numMines) {
      this.numMines = numMines;
      this.numRows = numRows;
      this.numCols = numCols;

   }

   /**
    * Removes any current mines on the minefield, and puts numMines() mines in
    * random locations on the minefield, ensuring that no mine is placed at (row,
    * col).
    * 
    * @param row the row of the location to avoid placing a mine
    * @param col the column of the location to avoid placing a mine PRE:
    *            inRange(row, col)
    */
   public void populateMineField(int row, int col) {
      mineData = new boolean[numRows][numCols]; // creates a new empty minefield
      int mines = 0; // mines in the field
      Random rand = new Random();

      // adding mines until there are numMines in the field
      // can not add mines add repeated or restricted location
      while (mines != numMines) {

         int randRow = rand.nextInt(numRows); // random mine row position (within # rows)
         int randCol = rand.nextInt(numCols); // random mine col position (within # cols)

         if (randRow == row && randCol == col) {
            continue;
         } else {
            if (mineData[randRow][randCol] == false) {
               mineData[randRow][randCol] = true;
               mines++;
            }
         }
      }
      printOut(mineData); // prints out minefield for debug
   }

   /**
    * Reset the minefield to all empty squares. This does not affect numMines(),
    * numRows() or numCols() Thus, after this call, the actual number of mines in
    * the minefield does not match numMines(). Note: This is the state the
    * minefield is in at the beginning of a game.
    */
   public void resetEmpty() {

      mineData = new boolean[numRows][numCols];

   }

   /**
    * Returns the number of mines adjacent to the specified mine location (not
    * counting a possible mine at (row, col) itself). Diagonals are also considered
    * adjacent, so the return value will be in the range [0,8]
    * 
    * @param row row of the location to check
    * @param col column of the location to check
    * @return the number of mines adjacent to the square at (row, col) PRE:
    *         inRange(row, col)
    */
   public int numAdjacentMines(int row, int col) {
      int numAdjacentMines = 0;

      for (int i = row - 1; i <= row + 1; i++) {
         for (int j = col - 1; j <= col + 1; j++) {
            // in range & not counting a possible mine at (row, col) itself
            if (inRange(i, j) && !(i == row && j == col)) {
               if (mineData[i][j] == true) {
                  numAdjacentMines++;
               }
            }
         }
      }

      return numAdjacentMines;
   }

   /**
    * Returns true iff (row,col) is a valid field location. Row numbers and column
    * numbers start from 0.
    * 
    * @param row row of the location to consider
    * @param col column of the location to consider
    * @return whether (row, col) is a valid field location
    */
   public boolean inRange(int row, int col) {

      if ((row >= 0 && row < numRows) && (col >= 0 && col < numCols)) {
         return true;
      } else {
         return false;
      }

   }

   /**
    * Returns the number of rows in the field.
    * 
    * @return number of rows in the field
    */
   public int numRows() {
      return numRows;
   }

   /**
    * Returns the number of columns in the field.
    * 
    * @return number of columns in the field
    */
   public int numCols() {
      return numCols;
   }

   /**
    * Returns whether there is a mine in this square
    * 
    * @param row row of the location to check
    * @param col column of the location to check
    * @return whether there is a mine in this square PRE: inRange(row, col)
    */
   public boolean hasMine(int row, int col) {
      return mineData[row][col];
   }

   /**
    * Returns the number of mines you can have in this minefield. For mines created
    * with the 3-arg constructor, some of the time this value does not match the
    * actual number of mines currently on the field. See doc for that constructor,
    * resetEmpty, and populateMineField for more details.
    * 
    * @return number of mines
    */
   public int numMines() {
      return numMines;
   }

   // <put private methods here>

   /**
    * print out the current minefield status for easy debug | comment out if player
    * does not want to see the minefield layout
    * 
    * @param mineData the data for the mines
    */

   private void printOut(boolean[][] mineData) {
      System.out.println("----------------------------------------------------");
      for (int i = 0; i < numRows; i++) {
         for (int j = 0; j < numCols; j++) {
            System.out.print(mineData[i][j] + " ");
         }
         System.out.println();
      }
      System.out.println("----------------------------------------------------");
   }

}
