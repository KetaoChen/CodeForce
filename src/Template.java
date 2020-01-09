import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.BufferedReader;
 
public class Template {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        int[] arr = new int[t];
        for (int i = 0; i < t; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(findMaxInc(arr, 0, t - 1));
    }
    
    private static int findMaxInc(int[] arr, int start, int end) {
        for (int i = start; i < end; i++) {
            if (arr[i] > arr[i + 1]) {
                int mid = (end + start - 1) / 2;
                return Math.max(findMaxInc(arr, start, mid), findMaxInc(arr, mid + 1, end));
            }
        }
        return end - start + 1;
    }
}
