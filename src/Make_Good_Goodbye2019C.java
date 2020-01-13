import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Make_Good {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int n = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.

        for (int i = 0; i < n; i++) {
            int m = in.nextInt();
            int[] arr = new int[m];
            for (int j = 0; j < m; j++) {
                arr[j] = in.nextInt();
            }
            long[] res = getRes2(arr);
            System.out.println(res.length);
            for (long r : res) {
                System.out.print(r + " ");
            }
            System.out.println();
        }
    }

    private static long[] getRes2(int[] arr) {
        long sum = 0;
        long xor = 0;
        for (int num : arr) {
            sum += num;
            xor ^= num;
        }

        //if we only use two number: "sum = A, xor = B"
        //A + B + (A + B)
        //B ^ B ^ (A + B) = B + A

        long[] res = new long[2];
        res[0] = xor;
        res[1] = sum + xor;
        return res;
    }

    private static long[] getRes3(int[] arr) {
        long sum = 0;
        long xor = 0;
        for (int num : arr) {
            sum += num;
            xor ^= num;
        }

        //make sure xor is larger than sum
        long first = (long) (1 << 29) * (1 << 29);
        //System.out.println("This is first: " + first);
        sum += first;

         //if sum is odd, sum++.
        if ((sum & 1) == 1) {
            first++;
            sum++;
        }
        xor ^= first;

        //since sum = xor * 2;
        //1. make sum + first = xor * first
        //2. second == third = ((xor * first) * 2 - (sum + first)) / 2
        long[] res = new long[3];
        res[0] = first;
        res[1] = res[2] = (2 * xor - sum) / 2;
        return res;
    }
}
