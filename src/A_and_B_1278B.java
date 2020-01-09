import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;


public class A_and_B_1278B {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int l = in.nextInt();

        for (int i = 0; i < l; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(calStep(Math.abs(a - b)));
        }
    }

    private static int calStep(int target) {
        if (target == 0) {
            return 0;
        }
        //get the smallest n
        int left = 1;
        int right = target;

        while (left < right) {
            long mid = (right - left) / 2 + left;

            if ((1 + mid) * mid / 2 < target) {
                left = (int) mid + 1;
            }
            else {
                right = (int) mid;
            }
        }

        int furthest = left * (left + 1) / 2;
        int diff = furthest - target;
        return diff % 2 == 0 ? left : left % 2 == 0 ? left + 1 : left + 2;
    }
}
