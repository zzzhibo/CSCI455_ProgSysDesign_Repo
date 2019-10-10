//lab7 fastExpon

public class PracticeThree {
    public static void main(String[] args) {
        System.out.println(fastExpon(3, 4));
        System.out.println(fastExpon(2, 3));
        System.out.println(fastExpon(2, 12));
    }

    public static int fastExpon(int x, int n) {
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return x;
        }

        if (n % 2 == 0) {
            return (int) Math.pow(Math.pow(x, n / 2), 2);
        } else {
            return fastExpon(x, n - 1) * x;
        }

    }

}