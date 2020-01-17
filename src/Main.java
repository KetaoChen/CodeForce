import java.util.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;
 
public class Main {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int n = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(getRes(arr, 0));
    }

    private static int getRes(int[] arr, int m) {
        Arrays.sort(arr);
        long sum = 0;
        int mod = 1000000007;
        int l = arr.length;
        long base = 1;

        for (int i = 0; i <= l - m; i++) {
            //System.out.println(arr[m + i - 1] + " " + arr[l - m - i] + " " + base);
            sum = (sum + base * arr[m + i - 1] - base * arr[l - m - i]) % mod;
            while (base % (i + 1) != 0) {
                base += mod;
            }
            base = (base * (m + i) / (i + 1)) % mod;
        }

        return (int) ((sum + mod) % mod);
    }
}
