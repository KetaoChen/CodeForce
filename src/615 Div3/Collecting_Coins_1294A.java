import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Collecting_Coins_1294A {

    //ATTENTION: the initial coins everyone has can not exceed the average!
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int n = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc
        for (int i = 0; i < n; i++) {
            getRes(in);
        }

    }

    private static void getRes(Scanner in) {
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        int n = in.nextInt();
        int sum = a + b + c + n;
        if (sum % 3 != 0) {
            System.out.println("NO");
            return;
        }
        if (a > sum / 3 || b > sum / 3 || c > sum / 3) {
            System.out.println("NO");
            return;
        }
        System.out.println("YES");
    }
}
