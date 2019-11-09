// Name: Zhibo Wang
// USC NetID: 7476147450
// CS 455 PA1
// Fall 2019

/**
 * class CoinTossSimulator
 * 
 * Simulates trials of repeatedly tossing two coins and allows the user to
 * access the cumulative results.
 * 
 * NOTE: we have provided the public interface for this class. Do not change the
 * public interface. You can add private instance variables, constants, and
 * private methods to the class. You will also be completing the implementation
 * of the methods given.
 * 
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 * 
 */
import java.util.Random;

public class CoinTossSimulator {
   private int numTrials; // Trials variables
   private int get_NumTrials; // instance variables record possible outcomes
   private int get_TwoHeads;
   private int get_TwoTails;
   private int get_HeadTails;

   private boolean coin1;
   private boolean coin2;

   /**
    * Creates a coin toss simulator with no trials done yet.
    */

   public CoinTossSimulator() {

   }

   /**
    * Runs the simulation for numTrials more trials. Multiple calls to this method
    * without a reset() between them *add* these trials to the current simulation.
    * 
    * @param numTrials number of trials to for simulation; must be >= 1
    */
   public void run(int numTrials) {
      this.numTrials = numTrials;

      int toss_TwoHeads = 0; // record the toss result and reset everytime
      int toss_TwoTails = 0;
      int toss_HeadTails = 0;

      Random cointoss = new Random();

      for (int i = 0; i < numTrials; i++) {
         coin1 = cointoss.nextBoolean();
         coin2 = cointoss.nextBoolean();

         if (coin1 == true && coin2 == true) {
            toss_TwoHeads++;
         } else if (coin1 == false && coin2 == false) {
            toss_TwoTails++;
         } else {
            toss_HeadTails++;
         }

      }

      get_TwoHeads += toss_TwoHeads;
      get_TwoTails += toss_TwoTails;
      get_HeadTails += toss_HeadTails;
   }

   /**
    * Get number of trials performed since last reset.
    */
   public int getNumTrials() {
      get_NumTrials = get_TwoHeads + get_TwoTails + get_HeadTails;
      return get_NumTrials;
   }

   /**
    * Get number of trials that came up two heads since last reset.
    */
   public int getTwoHeads() {
      return get_TwoHeads;
   }

   /**
    * Get number of trials that came up two tails since last reset.
    */
   public int getTwoTails() {
      return get_TwoTails;
   }

   /**
    * Get number of trials that came up one head and one tail since last reset.
    */
   public int getHeadTails() {
      return get_HeadTails;
   }

   /**
    * Resets the simulation, so that subsequent runs start from 0 trials done.
    */
   public void reset() {
      // reset every variable within the class
      numTrials = 0;
      get_TwoHeads = 0;
      get_TwoTails = 0;
      get_HeadTails = 0;
   }

}
