import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Minimax_Problem_1288D {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int n = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        int m = in.nextInt();
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = in.nextInt();
            }
        }
        int[] res = getRes(arr);
        System.out.println((res[0] + 1) + " " + (res[1] + 1));
    }

    private static int[] getRes(int[][] arr) {
        int left = 0;
        int right = (int) 1e9;
        int[] res = new int[]{0, 0};
        while (left < right) {
            int mid = (right - left + 1) / 2 + left;
            //System.out.println(mid);
            int[] temp = canReach(arr, mid);
            if (temp.length > 0) {
                left = mid;
                res = temp;
            }
            else {
                right = mid - 1;
            }
        }
        return res;
    }

    private static int[] canReach(int[][] arr, int val) {
        int[][] check = new int[arr.length][arr[0].length];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int v = 0;
            for (int j = 0; j < arr[0].length; j++) {
                check[i][j] = arr[i][j] >= val ? 1 : 0;
                v = v * 2 + check[i][j];
            }
            map.put(v, i);
        }
        int target = (1 << arr[0].length) - 1;
        //System.out.println(target);
        for (int i : map.keySet()) {
            for (int j : map.keySet()) {
                if ((i | j) == target) {
                    return new int[]{map.get(i), map.get(j)};
                }
            }
        }
        return new int[]{};
    }
}
