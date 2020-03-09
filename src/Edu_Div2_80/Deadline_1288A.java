package Edu_Div2_80;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Deadline_1288A {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int n = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.

        for (int i = 0; i < n; i++) {
            if (getRes(in)) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }
    }

    private static boolean getRes(Scanner in) {
        int n = in.nextInt();
        int d = in.nextInt();
        if (d <= n) {
            return true;
        }

        for (double i = 1.0; i * i <= d; i++) {
            double after = Math.ceil(d / (i + 1)) + i;
            if (after <= n) {
                return true;
            }
        }

        return false;
    }
}
