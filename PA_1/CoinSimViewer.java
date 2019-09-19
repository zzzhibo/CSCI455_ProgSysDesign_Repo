// Name: Zhibo Wang
// USC NetID: 7476147450
// CS 455 PA1
// Fall 2019

/**
 * class CoinSimViewer
 * 
 * Contains the main method. Prompts for the number of trials. 
 * 
 * javac @CoinSimViewer.list to run the program
 * error checking codeblock to make sure user gives valid input value
 */

import javax.swing.JFrame;
import java.util.Scanner;

public class CoinSimViewer {
    /**
     * main program to execute CoinSimViewer
     * 
     * @param args
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setSize(1200, 800);
        frame.setTitle("CoinSim");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // CoinTossSimulator cointoss = new CoinTossSimulator();
        Scanner sc = new Scanner(System.in); // grab the input and check if it's valid.
        System.out.print("Enter number of trials: ");
        int tossnum = sc.nextInt();
        while (tossnum < 1) {
            System.out.println("ERROR: Number entered must be greater than 0");
            System.out.print("Enter number of trials: ");
            tossnum = sc.nextInt();
        }

        CoinSimComponent component = new CoinSimComponent(tossnum);

        frame.add(component);
        frame.setVisible(true);
    }
}