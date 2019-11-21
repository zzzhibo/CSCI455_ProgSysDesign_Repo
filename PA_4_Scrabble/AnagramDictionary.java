// Name: Zhibo Wang
// USC NetID: zhibowan
// CS 455 PA4
// Fall 2019

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * A dictionary of all anagram sets. Note: the processing is case-sensitive; so
 * if the dictionary has all lower case words, you will likely want any string
 * you test to have all lower case letterNum too, and likewise if the dictionary
 * words are all upper case.
 */

public class AnagramDictionary {

   private Map<String, ArrayList<String>> anagramDict;

   /**
    * Create an anagram dictionary from the list of words given in the file
    * indicated by fileName. PRE: The strings in the file are unique.
    * 
    * @param fileName the name of the file to read from
    * @throws FileNotFoundException if the file is not found
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException {

      // use HashMap (order doesn't matter) to construct the anagram dictionary
      // if file not found throw a FileNotFoundException

      // the logic behind this class is to convert each word obtained from exisiting
      // dictionary to a form of "LetterNumberLetterNumberLetterNumber..." And then
      // push this LetterN as key and its corresponding words into an arraylist

      anagramDict = new HashMap<String, ArrayList<String>>();
      File file = new File(fileName);

      try {
         // Scanner has to be in the try-catch block for error handling reason
         Scanner sc = new Scanner(file);
         while (sc.hasNext()) {
            String word = sc.next();
            String letterNum = letterN(word);

            ArrayList<String> wordList = anagramDict.get(letterNum);

            if (wordList == null) {
               wordList = new ArrayList<>();
               wordList.add(word);
            } else {
               wordList.add(word);
            }

            anagramDict.put(letterNum, wordList); // update the corresponding letter's wordlist

         }
      } catch (FileNotFoundException e) { // Scanner will throw an exception if dict file DNE
         throw e;
      }

   }

   /**
    * Get all anagrams of the given string. This method is case-sensitive. E.g.
    * "CARE" and "race" would not be recognized as anagrams.
    * 
    * @param s string to process
    * @return a list of the anagrams of s
    */
   public ArrayList<String> getAnagramsOf(String s) {

      String letterNum = letterN(s);

      return anagramDict.get(letterNum);
   }

   /**
    * Convert the parameter word into a sorted form of letter + number i.e. abc ->
    * a1b1c1, cabc -> a1b1c2. TreeMap is a great data structure in this case due to
    * its nature to sort the input into a BST. The order is in natural increasing
    * order. O(logn) for get/put/remove.
    * 
    * @param word
    * @return String
    */
   private String letterN(String word) {
      String letterN = "";
      Map<Character, Integer> letterNum = new TreeMap<Character, Integer>();

      for (int i = 0; i < word.length(); i++) {

         Character letter = word.charAt(i);
         Integer num = letterNum.get(letter);

         // in case of the letter is new to the tree map, initialize it by 1
         if (num == null) {
            letterNum.put(letter, 1);
         } else {
            letterNum.put(letter, letterNum.get(letter) + 1);
         }

      }

      Iterator<Map.Entry<Character, Integer>> iter = letterNum.entrySet().iterator();

      // form a string which has the format of letterNumletterNum...
      while (iter.hasNext()) {
         Map.Entry<Character, Integer> entry = iter.next();

         Character entryKey = entry.getKey();
         String entryVal = Integer.toString(entry.getValue());

         letterN = letterN + entryKey + entryVal;
      }

      // System.out.println(letterN);
      return letterN;
   }

}
