package Edu_Div2_80;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Yet_Another_Meme_Problem_1288B {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int n = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.

        for (int i = 0; i < n; i++) {
            System.out.println(getRes(in));
        }
    }

    private static long getRes(Scanner in) {
        int a = in.nextInt();
        int b = in.nextInt();

        //a + b + a * b = a * (len(b)) + b;
        //a + a * b = a * (10^lb)
        //a * (b + 1)
        //b + 1 = (10 ^ len(b)): b = 9, b = 99, 999, 9999

        String valB = Integer.toString(b);

        for (char c : valB.toCharArray()) {
            if (c != '9') {
                return a * 1L * (valB.length() - 1);
            }
        }

        return a * 1L * valB.length();
    }
}
