// Name: Zhibo Wang
// USC NetID: zhibowan
// CS 455 PA4
// Fall 2019

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.print.MultiDocPrintJob;

/**
 * A Rack of Scrabble tiles
 */

public class Rack {
   private String validInputRack; // valid letters
   private int k; // see allSubsets doc for detailed description
   private int multIndex; // see allSubsets doc for detailed description
   private String unique; // see allSubsets doc for detailed description

   /**
    * constructor of Rack class. Take input and initialize other essential
    * variables for the subset method
    * 
    * @param validInputRack
    * @return
    */
   public Rack(String validInputRack) {
      this.validInputRack = validInputRack;
      k = 0; // k = 0 means start from the beginning
      multIndex = 0;
      unique = "";

   }

   /**
    * the method returns a subset of combinations of letters by invoking provided
    * allSubsets method. Used HashMap for data structure because the order of input
    * characters does not matter to allSubSet method. Repeated character is allowed
    * e.g. aab -> b, a, ab, aa, aab | abc -> c, b, a, bc, ac, ab, abc
    * 
    * @param validInputRack
    * @return ArrayList<String>
    */
   public ArrayList<String> subSets() {
      Map<Character, Integer> letterNum = new HashMap<Character, Integer>(); // map of letters and their corresponding
                                                                             // num

      for (int i = 0; i < validInputRack.length(); i++) {
         Character letter = validInputRack.charAt(i);
         Integer num = letterNum.get(letter);
         if (num == null) {
            letterNum.put(letter, 1);
         } else {
            letterNum.put(letter, letterNum.get(letter) + 1);
         }
      }

      int[] mult = new int[letterNum.size()];
      Iterator<Map.Entry<Character, Integer>> iter = letterNum.entrySet().iterator();
      while (iter.hasNext()) {
         Map.Entry<Character, Integer> entry = iter.next();
         unique = unique + entry.getKey();
         mult[multIndex] = entry.getValue();
         multIndex++;
      }

      return allSubsets(unique, mult, k);
   }

   /**
    * Finds all subsets of the multiset starting at position k in unique and mult.
    * unique and mult describe a multiset such that mult[i] is the multiplicity of
    * the char unique.charAt(i). PRE: mult.length must be at least as big as
    * unique.length() 0 <= k <= unique.length()
    * 
    * @param unique a string of unique letters
    * @param mult   the multiplicity of each letter from unique.
    * @param k      the smallest index of unique and mult to consider.
    * @return all subsets of the indicated multiset. Unlike the multiset in the
    *         parameters, each subset is represented as a String that can have
    *         repeated characters in it.
    * @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();

      if (k == unique.length()) { // multiset is empty
         allCombos.add("");
         return allCombos;
      }

      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k + 1);

      // prepend all possible numbers of the first char (i.e., the one at position k)
      // to the front of each string in restCombos. Suppose that char is 'a'...

      String firstPart = ""; // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {
         for (int i = 0; i < restCombos.size(); i++) { // for each of the subsets
                                                       // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));
         }
         firstPart += unique.charAt(k); // append another instance of 'a' to the first part
      }

      return allCombos;
   }

}
