import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Mezo_Playing_Zoma_1285A {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        in.nextLine();
        String s = in.nextLine();

        System.out.println(findRange(s));
    }

    private static int findRange(String s) {
        int[] range = new int[2];
        for (char c : s.toCharArray()) {
            if (c == 'L') {
                range[0]--;
            }
            else {
                range[1]++;
            }
        }
        return range[1] - range[0] + 1;
    }
}
