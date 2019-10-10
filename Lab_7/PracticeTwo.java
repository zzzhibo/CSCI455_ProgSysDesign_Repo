//lab7 string1UpToN

public class PracticeTwo {
    public static void main(String[] args) {

        System.out.println(string1UpToN(3));
        // string1UpToN(3);
        System.out.println(string1UpToN(1));
        // string1UpToN(1);
        System.out.println(string1UpToN(5));

    }

    public static String string1UpToN(int n) {
        return RAppendUpTo("", 1, n);
    }

    public static String RAppendUpTo(String str, int i, int n) {
        if (n == 1) {
            return (str = "" + n);
        } else {
            return str = (RAppendUpTo(str, i, n - 1) + " " + n);
        }
    }
}