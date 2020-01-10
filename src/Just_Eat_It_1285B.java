import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Just_Eat_It_1285B {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 0; i < t; i++) {
            int k = in.nextInt();
            int[] arr = new int[k];
            for (int j = 0; j < k; j++) {
                arr[j] = in.nextInt();
            }
            System.out.println(isHappy(arr));
        }
    }

    private static String isHappy(int[] arr) {
        //find the maxSumOf every substring
        long max = 0;
        long res = Long.MIN_VALUE;
        long sum = 0;


        for (int j = 0; j < arr.length - 1; j++) {
            int i = arr[j];
            sum += i;
            if (max  < 0) {
                max = 0;
            }
            max += i;
            res = Math.max(max, res);
        }

        sum += arr[arr.length - 1];

        max = 0;
        for (int j = arr.length - 1; j > 0; j--) {
            max += arr[j];
            res = Math.max(res, max);
        }

        return sum > res ? "YES" : "NO";
    }
}
