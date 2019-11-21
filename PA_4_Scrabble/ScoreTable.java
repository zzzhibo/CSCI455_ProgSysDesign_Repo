import java.util.HashMap;

// Name: Zhibo Wang
// USC NetID: zhibowan
// CS 455 PA4
// Fall 2019

/**
 * This class has information about Scrabble scores for scrabble letters and
 * words. In scrabble not every letters has the same value. Letters that occur
 * more often in the English language are worth less (e.g., 'e' and 's' are each
 * worth 1 point), and letters that occur less often are worth more (e.g., 'q'
 * and 'z' are worth 10 points each). You may use hard-coded values in its data.
 * Here are all the letters values: (1 point)-A, E, I, O, U, L, N, S, T, R (2
 * points)-D, G (3 points)-B, C, M, P (4 points)-F, H, V, W, Y (5 points)-K (8
 * points)- J, X (10 points)-Q, Z This class should work for both upper and
 * lower case versions of the letters, e.g., 'a' and 'A' will have the same
 * score. Hint: You can index an array with a char that is a lower case letters
 * by treating it as an int and subtracting 'a' from it (because the internal
 * numeric codes for letters are all sequential). E.g., If your letters is 'd',
 * ('d' - 'a') = 3 and if it's 'e', ('e' - 'a') = 4.
 */

public class ScoreTable {
    HashMap<Character, Integer> scoreTable;

    /**
     * initialize a score table of every letters in alphabet with different score
     * points (score points given in PA description) by using hashmap. Upper case
     * and lower case of the same letters are considered different.
     * 
     */
    public ScoreTable() {
        scoreTable = new HashMap<Character, Integer>();
        oneLetters();
        twoLetters();
        threeLetters();
        fourLetters();
        fiveLetters();
        eightLetters();
        tenLetters();
    }

    /**
     * return the score table hashmap to the client after score table hash map has
     * been constructed
     * 
     * @return HashMap<Character, Integer>
     */
    public HashMap<Character, Integer> getScoreTable() {
        return scoreTable;
    }

    /**
     * Everything below initialize the corresponding score to each letter and put
     * them into the score table hashmap
     * 
     */

    private void oneLetters() {
        // lowercase 1 point letters
        scoreTable.put('a', 1);
        scoreTable.put('e', 1);
        scoreTable.put('i', 1);
        scoreTable.put('o', 1);
        scoreTable.put('u', 1);
        scoreTable.put('l', 1);
        scoreTable.put('n', 1);
        scoreTable.put('s', 1);
        scoreTable.put('t', 1);
        scoreTable.put('r', 1);
        // uppercase 1 point letters
        scoreTable.put('A', 1);
        scoreTable.put('E', 1);
        scoreTable.put('I', 1);
        scoreTable.put('O', 1);
        scoreTable.put('U', 1);
        scoreTable.put('L', 1);
        scoreTable.put('N', 1);
        scoreTable.put('S', 1);
        scoreTable.put('T', 1);
        scoreTable.put('R', 1);
    }

    private void twoLetters() {
        // lower case 2 point letters
        scoreTable.put('d', 2);
        scoreTable.put('g', 2);
        // upper case 2 point letters
        scoreTable.put('D', 2);
        scoreTable.put('G', 2);

    }

    private void threeLetters() {
        // lower case 3 point letters
        scoreTable.put('b', 3);
        scoreTable.put('c', 3);
        scoreTable.put('m', 3);
        scoreTable.put('p', 3);
        // upper case 3 point letters
        scoreTable.put('B', 3);
        scoreTable.put('C', 3);
        scoreTable.put('M', 3);
        scoreTable.put('P', 3);

    }

    private void fourLetters() {
        // lower case 4 point letters
        scoreTable.put('f', 4);
        scoreTable.put('h', 4);
        scoreTable.put('v', 4);
        scoreTable.put('w', 4);
        scoreTable.put('y', 4);
        // upper case 4 point letters
        scoreTable.put('F', 4);
        scoreTable.put('H', 4);
        scoreTable.put('V', 4);
        scoreTable.put('W', 4);
        scoreTable.put('Y', 4);
    }

    private void fiveLetters() {
        // lower case 5 point letters
        scoreTable.put('k', 5);
        // upper case 5 point letters
        scoreTable.put('K', 5);
    }

    private void eightLetters() {
        // lower case 8 point letters
        scoreTable.put('j', 8);
        scoreTable.put('x', 8);
        // upper case 8 point letters
        scoreTable.put('J', 8);
        scoreTable.put('X', 8);
    }

    private void tenLetters() {
        // lower case 10 point letters
        scoreTable.put('q', 10);
        scoreTable.put('z', 10);
        // upper case 10 point letters
        scoreTable.put('Q', 10);
        scoreTable.put('Z', 10);
    }

}