import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.event.SwingPropertyChangeSupport;
import javax.swing.plaf.synth.SynthSeparatorUI;

// Name: Zhibo Wang
// USC NetID: zhibowan
// CS 455 PA4
// Fall 2019

public class WordFinder {
    private static String inputRack; // letter input from client

    /**
     * This contains the main method. This class will have a main that's responsible
     * for processing the command-line argument, and handling any error processing.
     * It will probably also have the main command loop. Most of the other
     * functionality will be delegated to other object(s) created in main and their
     * methods.
     * 
     * @param args
     */
    public static void main(String[] args) {
        // if not specificed by client, use default dictionary
        // otherwise use client's dictionary or throw an exception
        try {
            String inputDict;

            if (args.length == 0) {
                inputDict = "sowpods.txt";
            } else { // load client specificed dict if exist
                inputDict = args[0];
            }

            AnagramDictionary dict = new AnagramDictionary(inputDict);
            // System.out.println("The dictionary is " + inputDict);
            play(dict);

        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * If the input dictoinary exists and is valid. The play begins. This method
     * prompts client for inputs and give hints for ending the program properly. The
     * method will invoke possibleWords for a list of possible anagram words.
     * 
     * @param dict
     */
    private static void play(AnagramDictionary dict) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Type . to quit.");

        while (true) {
            System.out.print("Rack? ");
            inputRack = sc.nextLine();

            // if client input is . game ends
            if (inputRack.equals(".")) {
                break;
            } else {
                // Arraydict.getAnagramsOf(inputRack);
                possibleWords(inputRack, dict);
            }

        }

        sc.close();

    }

    /**
     * the possibleWords method first checks if the inputRack is valid. It removes
     * illegal characters and put valid characters into a new string. Later it
     * starts going through the arraylist of anagrams
     *
     * @param inputRack
     * @param dict
     */
    private static void possibleWords(String inputRack, AnagramDictionary dict) {
        String validInputRack = "";
        ArrayList<String> wordList; // words from dictionary of all valid anagrams
        TreeMap<String, Integer> wordScoreList = new TreeMap<String, Integer>(); // using treemap because every key is
                                                                                 // sorted internally (red-black tree)
        // use handy isLetter Java library to remove illegal input characters
        for (int i = 0; i < inputRack.length(); i++) {
            char letter = inputRack.charAt(i);

            if (Character.isLetter(letter)) {
                validInputRack = validInputRack + letter;
            }
        }
        Rack rack = new Rack(validInputRack);
        ArrayList<String> rackCombo = rack.subSets(); // returns a list of letter combinations

        for (int i = 0; i < rackCombo.size(); i++) {
            wordList = dict.getAnagramsOf(rackCombo.get(i));
            // ArrayList could be null for some anagrams and single letters (not in
            // the given dictionary)
            if (wordList != null) {
                for (int j = 0; j < wordList.size(); j++) {
                    String word = wordList.get(j);
                    Integer score = scoreCalc(word);
                    wordScoreList.put(word, score);
                }
            }

        }

        printOut(wordScoreList); // final print out step
    }

    // code check for wordScoreList
    // Iterator<Map.Entry<String, Integer>> iter =
    // wordScoreList.entrySet().iterator();
    // while (iter.hasNext()) {
    // Map.Entry<String, Integer> entry = iter.next();
    // System.out.println(entry.getKey() + " " + entry.getValue());
    // }

    /**
     * the method calculates the score of corresponding word and return it back as
     * an integer. The score definition is retrieved from score table class.
     * 
     * @param word
     * @return Integer
     */
    private static Integer scoreCalc(String word) {
        ScoreTable st = new ScoreTable();
        HashMap<Character, Integer> scoreTable = st.getScoreTable();

        Integer score = 0;
        // add score character by character
        for (int i = 0; i < word.length(); i++) {
            score += scoreTable.get(word.charAt(i));
        }

        return score;
    }

    /**
     * final step of the program which prints out the sorted result on the terinal.
     * If list is empty, move back for another prompt
     * 
     * @param wordScoreList
     */
    private static void printOut(TreeMap<String, Integer> wordScoreList) {

        ArrayList<Map.Entry<String, Integer>> sortedList = new ArrayList<Map.Entry<String, Integer>>(
                wordScoreList.entrySet());

        Collections.sort(sortedList, new ScoreSort());

        System.out.println("We can make " + sortedList.size() + " words from \"" + inputRack + "\"");
        // only execute the next step if there are words in the list
        if (sortedList.size() != 0) {
            System.out.println("All of the words with their scores (sorted by score):");
            for (int i = 0; i < sortedList.size(); i++) {
                System.out.println(sortedList.get(i).getValue() + ": " + sortedList.get(i).getKey());
            }

        }

    }

    /**
     * implement Java Comparator interface to sort the list by the descending value
     * of score. If scores are the same, the order is kept (alphabetically)
     */

    static class ScoreSort implements Comparator<Map.Entry<String, Integer>> {

        public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
            return b.getValue() - a.getValue();
        }

    }

}
