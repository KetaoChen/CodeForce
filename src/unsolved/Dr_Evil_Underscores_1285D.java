import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

class Dr_Evil_Underscores {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        int[] arr = new int[t];
        for (int i = 0; i < t; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(findMin(arr));
    }

    private static int findMin(int[] arr) {
        return (2 ^ 5);
    }
}
