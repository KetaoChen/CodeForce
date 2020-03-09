package Div3_615;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Product_of_Three_Numbers_1294C {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int n = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.

        for (int i = 0; i < n; i++) {
            getRes(in.nextInt());
        }
    }

    private static void getRes(int num) {
        int a = -1, b = -1;

        int temp = num;
        for (int i = 2; i * i <= temp; i++) {
            if (temp % i == 0) {
                a = i;
                temp /= i;
                break;
            }
        }
        if (temp == num) {
            System.out.println("NO");
            return;
        }

        int temp2 = temp;
        for (int i = 2; i * i <= temp2; i++) {
            if (temp2 % i == 0 && i > a) {
                b = i;
                temp2 /= i;
                break;
            }
        }
        if (temp2 == temp || temp2 <= b) {
            System.out.println("NO");
            return;
        }

        System.out.println("YES");
        System.out.println(a + " " + b + " " + temp2);
    }
}
