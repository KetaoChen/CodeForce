package Practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Garland_1286A {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        int[] arr = new int[t];
        for (int i = 0; i < t; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(getMinComplex(arr));
    }

    private static int getMinComplex(int[] arr) {
        //since this the complexity only depends on the parity
        int l = arr.length;
        boolean[] check = new boolean[l + 1];
        for (int num : arr) {
            if (num != 0) {
                check[num] = true;
            }
        }

        int[] count = new int[2];
        for (int i = 1; i <= l; i++) {
            if (!check[i]) {
                count[i % 2]++;
            }
        }

        //when this pos add an odd number, and how many odd left is a state.
        int[][][] memo = new int[l][count[1] + 1][2];
        for (int i = 0; i < l; i++) {
            for (int j = 0; j <= count[1]; j++) {
                for (int k = 0; k < 2; k++) {
                    memo[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        //System.out.println(count[0] + " " + count[1]);
        return findMin(arr, 0, count[1], count[0], 0, memo);
    }

    private static int findMin(int[] arr, int index, int leftOdd, int leftEven, int k, int[][][] memo) {
        int l = arr.length;
        if (index == l) {
            return 0;
        }

        if (memo[index][leftOdd][k] != Integer.MAX_VALUE) {
            return memo[index][leftOdd][k];
        }

        int res = Integer.MAX_VALUE;
        if (arr[index] != 0) {
            if (index == 0 || ((arr[index] & 1) == k)) {
                res = Math.min(res, findMin(arr, index + 1, leftOdd, leftEven, arr[index] & 1, memo));
            }
            else {
                res = Math.min(res, findMin(arr, index + 1, leftOdd, leftEven, arr[index] & 1, memo) + 1);
            }
            memo[index][leftOdd][k] = res;
            return res;
        }

        if (leftOdd != 0) {
            int next = findMin(arr, index + 1, leftOdd - 1, leftEven, 1, memo);
            if (index != 0 && k == 0) {
                next++;
            }
            res = Math.min(res, next);
        }

        if (leftEven != 0) {
            int next = findMin(arr, index + 1, leftOdd, leftEven - 1, 0, memo);
            if (index != 0 && k == 1) {
                next++;
            }
            res = Math.min(res, next);
        }

        memo[index][leftOdd][k] = res;
        return res;
    }
}
