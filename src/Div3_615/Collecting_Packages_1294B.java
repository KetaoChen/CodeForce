package Div3_615;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;


public class Collecting_Packages_1294B {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int n = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.


        for (int i = 0; i < n; i++) {
            int k = in.nextInt();
            int[][] arr = new int[k][2];
            for (int j = 0; j < k; j++) {
                for (int m = 0; m < 2; m++) {
                    arr[j][m] = in.nextInt();
                }
            }
            getRes(arr);
        }

    }

    private static void getRes(int[][] arr) {
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });
        int i = 0;
        int j = 0;
        StringBuilder sb = new StringBuilder();
        for (int[] point : arr) {
            if (point[0] < i || point[1] < j) {
                System.out.println("NO");
                return;
            }
            while (i < point[0]) {
                sb.append('R');
                i++;
            }
            while (j < point[1]) {
                sb.append('U');
                j++;
            }
        }
        System.out.println("YES");
        System.out.println(sb.toString());
    }

}
