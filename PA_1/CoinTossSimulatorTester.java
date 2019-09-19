// Name: Zhibo Wang
// USC NetID: 7476147450
// CS 455 PA1
// Fall 2019

/**
 * CoinTossSimulatorTester
 * 
 */
import java.util.Scanner;

public class CoinTossSimulatorTester {

    /**
     * main program to execute tester
     * 
     * @param args
     */
    public static void main(String[] args) {

        CoinTossSimulator cointoss = new CoinTossSimulator();

        // Scanner sc = new Scanner(System.in); input test, non-essential
        // System.out.printf("Number of Trials: ");
        // int tossnum = sc.nextInt();

        // cointoss.run(0); // trial run 0
        System.out.println("");
        System.out.println("After constructor:");
        System.out.println("Number of trials [exp:0]:" + cointoss.getNumTrials());
        System.out.println("Two-head tosses: " + cointoss.getTwoHeads());
        System.out.println("Two-tail tosses: " + cointoss.getTwoTails());
        System.out.println("One-head one-tail tosses: " + cointoss.getHeadTails());
        System.out.print("Tosses add up correctly? ");
        if (cointoss.getTwoHeads() + cointoss.getTwoTails() + cointoss.getHeadTails() == 0) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        cointoss.run(1); // trial run 1
        System.out.println("");
        System.out.println("After run(1):");
        System.out.println("Number of trials [exp:1]:" + cointoss.getNumTrials());
        System.out.println("Two-head tosses: " + cointoss.getTwoHeads());
        System.out.println("Two-tail tosses: " + cointoss.getTwoTails());
        System.out.println("One-head one-tail tosses: " + cointoss.getHeadTails());
        System.out.print("Tosses add up correctly? ");
        if (cointoss.getTwoHeads() + cointoss.getTwoTails() + cointoss.getHeadTails() == 1) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        cointoss.run(10); // trial run 10
        System.out.println("");
        System.out.println("After run(10):");
        System.out.println("Number of trials [exp:11]:" + cointoss.getNumTrials());
        System.out.println("Two-head tosses: " + cointoss.getTwoHeads());
        System.out.println("Two-tail tosses: " + cointoss.getTwoTails());
        System.out.println("One-head one-tail tosses: " + cointoss.getHeadTails());
        System.out.print("Tosses add up correctly? ");
        if (cointoss.getTwoHeads() + cointoss.getTwoTails() + cointoss.getHeadTails() == 11) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        cointoss.run(100); // trial run 100
        System.out.println("");
        System.out.println("After run(100):");
        System.out.println("Number of trials [exp:111]:" + cointoss.getNumTrials());
        System.out.println("Two-head tosses: " + cointoss.getTwoHeads());
        System.out.println("Two-tail tosses: " + cointoss.getTwoTails());
        System.out.println("One-head one-tail tosses: " + cointoss.getHeadTails());
        System.out.print("Tosses add up correctly? ");
        if (cointoss.getTwoHeads() + cointoss.getTwoTails() + cointoss.getHeadTails() == 111) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        cointoss.reset(); // reset
        System.out.println("");
        System.out.println("After reset:");
        System.out.println("Number of trials [exp:0]:" + cointoss.getNumTrials());
        System.out.println("Two-head tosses: " + cointoss.getTwoHeads());
        System.out.println("Two-tail tosses: " + cointoss.getTwoTails());
        System.out.println("One-head one-tail tosses: " + cointoss.getHeadTails());
        System.out.print("Tosses add up correctly? ");
        if (cointoss.getTwoHeads() + cointoss.getTwoTails() + cointoss.getHeadTails() == 0) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        cointoss.run(1000); // trial run 1000
        System.out.println("");
        System.out.println("After run(1000):");
        System.out.println("Number of trials [exp:1000]:" + cointoss.getNumTrials());
        System.out.println("Two-head tosses: " + cointoss.getTwoHeads());
        System.out.println("Two-tail tosses: " + cointoss.getTwoTails());
        System.out.println("One-head one-tail tosses: " + cointoss.getHeadTails());
        System.out.print("Tosses add up correctly? ");
        if (cointoss.getTwoHeads() + cointoss.getTwoTails() + cointoss.getHeadTails() == 1000) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

    }
}
