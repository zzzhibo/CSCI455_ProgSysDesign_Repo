// Name: Zhibo Wang
// USC NetID: 7476147450
// CSCI 455 Fall 2019
// Lab 5

import java.util.ArrayList;
import java.util.Scanner;

public class ReadTester {
    public static void main(String[] args) {

        String usrinput = new String();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a space separated list of numbers: ");
        while (sc.hasNextLine()) {

            usrinput = sc.nextLine().trim().replaceAll("\\s+", " "); // remove all white spaces via trim method and Java
                                                                     // regular expression. ( +) in replaceAll method
                                                                     // means to remove all spaces
                                                                     // with one

            if (usrinput.isEmpty()) {
                System.out.println("usrinput " + "[]");
                System.out.print("Enter a space separated list of numbers: ");
                continue;
            } else {
                // System.out.println(usrinput);]
                // String[] usrinput2 = usrinput.trim().replaceAll(" +", " ").split(" ");
                String[] usrinput2 = usrinput.split(" ");
                // List<Integer> list = new ArrayList<Integer>();
                ArrayList<Integer> list = new ArrayList<Integer>();
                for (int i = 0; i < usrinput2.length; i++) {
                    list.add(Integer.parseInt(usrinput2[i]));
                }
                System.out.println("usrinput " + list);
                System.out.print("Enter a space separated list of numbers: ");

            }

        }

    }
}