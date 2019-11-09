// Name: Zhibo Wang
// USC NetID: zhibowan 
// CS 455 PA3
// Fall 2019

/**
 * VisibleField class This is the data that's being displayed at any one point
 * in the game (i.e., visible field, because it's what the user can see about
 * the minefield), Client can call getStatus(row, col) for any square. It
 * actually has data about the whole current state of the game, including the
 * underlying mine field (getMineField()). Other accessors related to game
 * status: numMinesLeft(), isGameOver(). It also has mutators related to actions
 * the player could do (resetGameDisplay(), cycleGuess(), uncover()), and
 * changes the game state accordingly.
 * 
 * It, along with the MineField (accessible in mineField instance variable),
 * forms the Model for the game application, whereas GameBoardPanel is the View
 * and Controller, in the MVC design pattern. It contains the MineField that
 * it's partially displaying. That MineField can be accessed (or modified) from
 * outside this class via the getMineField accessor.
 */
public class VisibleField {
   // ----------------------------------------------------------
   // The following public constants (plus numbers mentioned in comments below) are
   // the possible states of one
   // location (a "square") in the visible field (all are values that can be
   // returned by public method
   // getStatus(row, col)).

   // Covered states (all negative values):
   public static final int COVERED = -1; // initial value of all squares
   public static final int MINE_GUESS = -2;
   public static final int QUESTION = -3;

   // Uncovered states (all non-negative values):

   // values in the range [0,8] corresponds to number of mines adjacent to this
   // square

   public static final int MINE = 9; // this loc is a mine that hasn't been guessed already (end of losing game)
   public static final int INCORRECT_GUESS = 10; // is displayed a specific way at the end of losing game
   public static final int EXPLODED_MINE = 11; // the one you uncovered by mistake (that caused you to lose)
   // ----------------------------------------------------------

   // <put instance variables here>
   private MineField mineField; // what visibleField covers (a mineField)
   private int[][] visibleField; // visibleField for client to explore

   // self-explaining instance variables for VisibleField class
   private int numRows;
   private int numCols;
   private int numMines;

   /**
    * Create a visible field that has the given underlying mineField. The initial
    * state will have all the mines covered up, no mines guessed, and the game not
    * over.
    * 
    * @param mineField the minefield to use for for this VisibleField
    */
   public VisibleField(MineField mineField) {
      this.mineField = mineField;
      this.numRows = mineField.numRows();
      this.numCols = mineField.numCols();
      this.numMines = mineField.numMines();

      visibleField = new int[numRows][numCols]; // visibleField has the identical dimension

      for (int i = 0; i < numRows; i++) {
         for (int j = 0; j < numCols; j++) {
            visibleField[i][j] = COVERED; // initial state will be all covered up
         }
      }

   }

   /**
    * Reset the object to its initial state (see constructor comments), using the
    * same underlying MineField.
    */
   public void resetGameDisplay() {
      for (int i = 0; i < numRows; i++) {
         for (int j = 0; j < numCols; j++) {
            visibleField[i][j] = COVERED; // reset visibleField back to covered
         }
      }

   }

   /**
    * Returns a reference to the mineField that this VisibleField "covers"
    * 
    * @return the minefield
    */
   public MineField getMineField() {
      return mineField;
   }

   /**
    * Returns the visible status of the square indicated.
    * 
    * @param row row of the square
    * @param col col of the square
    * @return the status of the square at location (row, col). See the public
    *         constants at the beginning of the class for the possible values that
    *         may be returned, and their meanings. PRE: getMineField().inRange(row,
    *         col)
    */
   public int getStatus(int row, int col) {
      return visibleField[row][col];
   }

   /**
    * Returns the the number of mines left to guess. This has nothing to do with
    * whether the mines guessed are correct or not. Just gives the user an
    * indication of how many more mines the user might want to guess. This value
    * can be negative, if they have guessed more than the number of mines in the
    * minefield.
    * 
    * @return the number of mines left to guess.
    */
   public int numMinesLeft() {
      int numMinesLeft = numMines;

      for (int i = 0; i < numRows; i++) {
         for (int j = 0; j < numCols; j++) {
            if (visibleField[i][j] == MINE_GUESS) {
               numMinesLeft--;
            }
         }
      }

      return numMinesLeft;

   }

   /**
    * Cycles through covered states for a square, updating number of guesses as
    * necessary. Call on a COVERED square changes its status to MINE_GUESS; call on
    * a MINE_GUESS square changes it to QUESTION; call on a QUESTION square changes
    * it to COVERED again; call on an uncovered square has no effect.
    * 
    * @param row row of the square
    * @param col col of the square PRE: getMineField().inRange(row, col)
    */
   public void cycleGuess(int row, int col) {
      if (getStatus(row, col) == COVERED) {
         visibleField[row][col] = MINE_GUESS;
      } else if (getStatus(row, col) == MINE_GUESS) {
         visibleField[row][col] = QUESTION;
      } else if (getStatus(row, col) == QUESTION) {
         visibleField[row][col] = COVERED;
      } else {
         return;// no effect
      }
   }

   /**
    * Uncovers this square and returns false iff you uncover a mine here. If the
    * square wasn't a mine or adjacent to a mine it also uncovers all the squares
    * in the neighboring area that are also not next to any mines, possibly
    * uncovering a large region. Any mine-adjacent squares you reach will also be
    * uncovered, and form (possibly along with parts of the edge of the whole
    * field) the boundary of this region. Does not uncover, or keep searching
    * through, squares that have the status MINE_GUESS. Note: this action may cause
    * the game to end: either in a win (opened all the non-mine squares) or a loss
    * (opened a mine).
    * 
    * @param row of the square
    * @param col of the square
    * @return false iff you uncover a mine at (row, col) PRE:
    *         getMineField().inRange(row, col)
    */
   public boolean uncover(int row, int col) {

      if (mineField.hasMine(row, col) == true) {
         visibleField[row][col] = EXPLODED_MINE;
         return false; // game ends if player clicks on mine
      } else {
         mineDetector(row, col);
         return true; // explore the field via calling mineDetector method
      }

   }

   /**
    * Returns whether the game is over. (Note: This is not a mutator.)
    *
    * The user can win without it reaching zero, because the win-condition is to
    * open all the non-mine locations, not to guess any particular number of mines.
    * 
    * In other words, the user can't win the game even if all mine locations are
    * correctly marked (using printOut method to cheat through), he/she still has
    * to click to open all non-mine locations
    * 
    * @return whether game over
    */
   public boolean isGameOver() {
      int safeField = numCols * numRows - numMines; // calculate the number of safe spots
      int fieldCounter = 0; // count how many spots have been uncovered by the player
      boolean resultTick = false; // signal to gameOverset to tell whether it's a win(true) or lose(false)

      // Losing case where a mine is wrongfully clicked
      // Once game is over, update to show all mines locations
      for (int i = 0; i < numRows; i++) {
         for (int j = 0; j < numCols; j++) {
            if (visibleField[i][j] == EXPLODED_MINE) {
               gameOverSet(resultTick);
               return true; // game ends here (lose)
            } else if (isUncovered(i, j)) {
               fieldCounter++;
            }
         }
      }

      // once the # of uncovered spots equals to the # of safe spots that is a
      // winning case where mines are not triggered
      if (fieldCounter == safeField) {
         resultTick = true;
         gameOverSet(resultTick);
         return true; // game ends here (win)
      }

      return false;

   }

   /**
    * Returns whether this square has been uncovered. (i.e., is in any one of the
    * uncovered states, vs. any one of the covered states).
    * 
    * @param row of the square
    * @param col of the square
    * @return whether the square is uncovered PRE: getMineField().inRange(row, col)
    */
   public boolean isUncovered(int row, int col) {
      // Uncovered states (all non-negative values)
      if (visibleField[row][col] >= 0) {
         return true;
      }
      return false;
   }

   // <put private methods here>

   /**
    * mineDetector explores the field for mines recursively until it finds any mine
    * adjacency
    * 
    * The user can win without it reaching zero, because the win-condition is to
    * open all the non-mine locations, not to guess any particular number of mines.
    * 
    * @param row of the square
    * @param col of the square
    */
   private void mineDetector(int row, int col) {

      if (mineField.numAdjacentMines(row, col) != 0) {
         // when a particular spot has mine adjacency
         visibleField[row][col] = mineField.numAdjacentMines(row, col);
         // return;
      } else {
         // when a particular spot has no mine adjacency
         visibleField[row][col] = 0;
         for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
               // Does not uncover, or keep searching * through, squares that have the status
               // MINE_GUESS or outside the range or is already uncovered or the spot itself
               if (mineField.inRange(i, j) && (visibleField[i][j] == COVERED || visibleField[i][j] == QUESTION)
                     && !(i == row && j == col)) {
                  mineDetector(i, j);
               }
            }
         }

      }

   }

   /**
    * set the visibleField to game end display
    * 
    * @param resultTick signals the game whether ends as win or lose
    */
   private void gameOverSet(boolean resultTick) {

      for (int i = 0; i < numRows; i++) {
         for (int j = 0; j < numCols; j++) {
            if (!resultTick && mineField.hasMine(i, j) && visibleField[i][j] != MINE_GUESS
                  && visibleField[i][j] != EXPLODED_MINE) {
               // when a player loses the game, mines have not been uncovered will be marked as
               // MINE
               visibleField[i][j] = MINE;
            } else if (!resultTick && mineField.hasMine(i, j) == false && visibleField[i][j] == MINE_GUESS) {
               // when player loses the game and has wrong guesses
               visibleField[i][j] = INCORRECT_GUESS;
            } else if (resultTick && mineField.hasMine(i, j)) {
               // winning case where all mines will be marked as Mine_GUESS
               visibleField[i][j] = MINE_GUESS;
            }
         }
      }
   }

}
