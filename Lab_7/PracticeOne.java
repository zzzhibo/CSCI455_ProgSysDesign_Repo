import java.sql.Array;
import java.util.Arrays;

//lab7 longestWordLen

public class PracticeOne {

    public static void main(String[] args) {
        longestWordLen("What is the longest word");
        longestWordLen("some words");
        longestWordLen("one");
    }

    public static void longestWordLen(String words) {

        // System.out.println(Arrays.toString(words.split(" ")));

        longestWordLenR(words.split(" "), 0, 0);

    }

    public static void longestWordLenR(String[] words, int i, int lengthInt) {

        if (i < words.length) {

            if (lengthInt < words[i].length()) {
                lengthInt = words[i].length();
            }
            longestWordLenR(words, i + 1, lengthInt);

        } else {
            System.out.println(Arrays.toString(words) + " -> " + lengthInt);
        }

    }
}
