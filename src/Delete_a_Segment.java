import sun.reflect.generics.tree.Tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Delete_a_Segment {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.

        for (int i = 0; i < t; i++) {
            int k = in.nextInt();
            System.out.println(findMinSeg(in, k));
        }
    }

    private static int findMinSeg(Scanner in, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        Map<Integer, Integer> range = new HashMap<>();
        Map<Integer, Integer> point = new HashMap<>();

        for (int i = 0; i < k; i++) {
            int first = in.nextInt();
            int second = in.nextInt();
            if (first == second) {
                point.put(first, point.getOrDefault(first, 0) + 1);
            }
            range.put(first, Math.max(map.getOrDefault(first, 0), second));
            map.put(first, map.getOrDefault(first, 0) + 1);
            map.put(second, map.getOrDefault(second, 0) - 1);
        }

        int d = 0;
        int sum = 0;
        Map<Integer, Integer> degree = new HashMap<>();
        int count = 0;
        Map<Integer, Integer> countOne = new HashMap<>();
        for (int key : map.keySet()) {
            d += map.get(key);
            degree.put(key, d);
            if (d == 0) {
                sum++;
            }
            if (d == 1) {
                count++;
            }
            countOne.put(key, count);
        }

        //System.out.println("sum is: " + sum);
        int change = 0;
        int max = -1;
        for (int key : range.keySet()) {
            int end = range.get(key);
            int endDegree = degree.get(end);
            if (key == end) {
                if (point.get(key) > 1) {
                    max = Math.max(max, 0);
                }
            }
            change = countOne.get(end) - countOne.get(key) + (endDegree == 1 ? -1 : 0);
            if (degree.get(key) == 1 && point.containsKey(key)) {
                change++;
            }

            max = Math.max(change, max);
        }

        return sum + max;
        //a interval have several situations
        //1. itself is a interval, if we delete, lose one.  map.get(left) == 1
        //2. it crosses with one, with left, or with right, does not change.
        //3. it crosses with two, if delete, get one more.

        //* a very long interval, include a lot of discrete intervals. add a lot of segment.
    }
}
