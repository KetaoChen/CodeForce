import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;


public class MEX_maximizing_1294D {
    //To optimize the input and output method. Get some template
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int n = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        int m = in.nextInt();
        getRes(in, n, m);
    }

    private static void getRes(Scanner in, int q, int x) {
        int[] count = new int[x];
        int start = 0;

        PrintWriter w = new PrintWriter(System.out);
        for (int i = 0; i < q; i++) {
            count[in.nextInt() % x]++;
            while (count[start % x] > start / x) {
                start++;
            }
            w.println(start);
        }
        w.flush();
        w.close();
    }
}
