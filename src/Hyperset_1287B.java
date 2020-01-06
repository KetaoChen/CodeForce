import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Hyperset_1287B {


    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int l = in.nextInt();
        System.out.println(l);
        int f = in.nextInt();
        System.out.println(f);
        String s = in.nextLine();
        //System.out.println("this is s: " + s);
        String[] input = new String[l];


        for (int i = 0; i < l; i++) {
            input[i] = in.nextLine();
            System.out.println(i + " " + input[i]);
        }

        //String[] test = {"SETT", "TEST", "EEET", "ESTE", "STES"};
        System.out.println(findPair(input));
    }

    private static int findPair(String[] s) {
        int[] map = new int[26];
        map[4] = 0; //E = 0
        map[18] = 1; //S = 1
        map[19] = 2; //T = 2

        Set<Long> set = new HashSet<>();
        int res = 0;
        int l = s.length;
        int m = s[0].length();

        //bitmask all the string in (3)
        for (int i = 0; i < l; i++) {
            long val = 0;
            for (char c : s[i].toCharArray()) {
                val = val * 3 + map[c - 'A'];
            }
            set.add(val);
        }

        for (int i = 0; i < l; i++) {
            for (int j = i + 1; j < l; j++) {
                long target = 0;
                for (int k = 0; k < m; k++) {
                    if (s[i].charAt(k) == s[j].charAt(k)) {
                        target = target * 3 + map[s[i].charAt(k) - 'A'];
                    } else {
                        target = target * 3 + 3 - map[s[i].charAt(k) - 'A'] - map[s[j].charAt(k) - 'A'];
                    }
                }
                if (set.contains(target)) {
                    res++;
                }
            }
        }
        return res / 3;
    }
}

