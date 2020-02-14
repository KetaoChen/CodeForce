import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Fadi_and_LCM_1285C {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        long t = in.nextLong(); // Scanner has functions to read ints, longs, strings, chars, etc.

        findMinPair(t);
    }

    private static void findMinPair(long x) {
        long max = x;
        long mid = binarySearch(x);
        for (long i = mid; i > 1; i--) {
            if (x % i == 0 && lca(i, x / i) == 1) {
                max = Math.min(max, x / i);
            }
        }
        System.out.println(max);
        System.out.println(x / max);
    }

    private static long lca(long a, long b) {
        if (b == 0) {
            return a;
        }
        return lca(b, a % b);
    }

    private static long binarySearch(long x) {
        long left = 1;
        long right = 1000000;

        while (left < right) {
            long mid = (right - left + 1) / 2 + left;
            if (mid * mid == x) {
                return mid;
            }
            if (mid * mid < x) {
                left = mid;
            }
            else {
                right = mid - 1;
            }
        }
        return left;
    }
}
