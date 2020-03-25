package Edu_Div2_80;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Two_Arrays_1288C {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        System.out.println(getRes(in));
    }

    final static  int mod = (int) (1e9 + 7);

    private static long getRes(Scanner in) {
        int n = in.nextInt();
        int m = in.nextInt();

        // after we combine the number of two arrays, it is still a non-descending array
        // our problem becomes that we need to get 2m numbers from n numbers, which can have repetition.
        // res = Div2_622.Edu_Div2_84.C(n + 2m - 1, 2m)
        long[] inv = getInvArray(2 * m);
        long res = 1;
        for (int i = 1; i <= 2 * m; i++) {
            res = (res * (n + 2 * m - i) % mod) * ((inv[i] + mod) % mod) % mod;
        }
        return (res + mod) % mod;
    }

    private static long[] getInvArray(int n) {
        long[] res = new long[n + 1];
        res[1] = 1;
        for (int i = 2; i <= n; i++) {
            res[i] = (mod - mod / i) * res[mod % i] % mod;
        }
        return res;
    }
}
