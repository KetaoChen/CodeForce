package Div3_636;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;


public class Palin_Sum_BinarySearch implements Runnable
{
    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            int n = in.nextInt();
            int k = in.nextInt();
            int[] arr = new int[n];
            for (int j = 0; j < n; j++) {
                arr[j] = in.nextInt();
            }
            getRes(arr, k, w);
        }

        w.flush();
        w.close();
    }

    private static void getRes(int[] arr, int k, PrintWriter w) {
        int l = arr.length;
        // have some observation: if we have a pair (a,b), and we want to change that to sum.
        // if sum - a > k. or sum - b > k || a > sum || b > sum, we need to change twice.

        // loop the target sum, use binary search to find the time of change, by find out same sum and times of changing twice.
        int[][] pair = new int[l / 2][2];
        for (int i = 0; i < l / 2; i++) {
            pair[i][0] = Math.min(arr[i], arr[l - i - 1]);
            pair[i][1] = Math.max(arr[i], arr[l - i - 1]);
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < l / 2; i++) {
            int sum = pair[i][0] + pair[i][1];
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }


        int[][] count = new int[2 * k + 1][2];
        Arrays.sort(pair, (a, b) -> a[0] - b[0]);
        for (int t = 2; t <= 2 * k; t++) {
            count[t][0] = bsh(pair, t, k);
        }

        Arrays.sort(pair, (a, b) -> a[1] - b[1]);
        for (int t = 2; t <= 2 * k; t++) {
            count[t][1] = bss(pair, t, k);
        }

        int res = l;
        for (int t = 2; t <= 2 * k; t++) {
            res = Math.min(res, l / 2 - map.getOrDefault(t, 0) + count[t][0] + count[t][1]);
        }

        w.println(res);
    }

    // find the number of pairs that the larger val + k < t.
    private static int bss(int[][] arr, int t, int k) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = (right - left + 1) / 2 + left;
            if (arr[mid][1] + k < t) {
                left = mid;
            }
            else {
                right = mid - 1;
            }
        }
        return arr[left][1] + k < t ? left + 1: 0;
    }

    // find the number of pairs that the smaller one is >= t
    private static int bsh(int[][] arr, int t, int k) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (arr[mid][0] >= t) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
        return arr[left][0] >= t ? arr.length - left : 0;
    }

    static class InputReader
    {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private SpaceCharFilter filter;
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        public InputReader(InputStream stream)
        {
            this.stream = stream;
        }

        public int read()
        {
            if (numChars==-1)
                throw new InputMismatchException();

            if (curChar >= numChars)
            {
                curChar = 0;
                try
                {
                    numChars = stream.read(buf);
                }
                catch (IOException e)
                {
                    throw new InputMismatchException();
                }

                if(numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public String nextLine()
        {
            String str = "";
            try
            {
                str = br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return str;
        }
        public int nextInt()
        {
            int c = read();

            while(isSpaceChar(c))
                c = read();

            int sgn = 1;

            if (c == '-')
            {
                sgn = -1;
                c = read();
            }

            int res = 0;
            do
            {
                if(c<'0'||c>'9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            while (!isSpaceChar(c));

            return res * sgn;
        }

        public long nextLong()
        {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-')
            {
                sgn = -1;
                c = read();
            }
            long res = 0;

            do
            {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            while (!isSpaceChar(c));
            return res * sgn;
        }

        public double nextDouble()
        {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-')
            {
                sgn = -1;
                c = read();
            }
            double res = 0;
            while (!isSpaceChar(c) && c != '.')
            {
                if (c == 'e' || c == 'E')
                    return res * Math.pow(10, nextInt());
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            if (c == '.')
            {
                c = read();
                double m = 1;
                while (!isSpaceChar(c))
                {
                    if (c == 'e' || c == 'E')
                        return res * Math.pow(10, nextInt());
                    if (c < '0' || c > '9')
                        throw new InputMismatchException();
                    m /= 10;
                    res += (c - '0') * m;
                    c = read();
                }
            }
            return res * sgn;
        }

        public String readString()
        {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do
            {
                res.appendCodePoint(c);
                c = read();
            }
            while (!isSpaceChar(c));

            return res.toString();
        }

        public boolean isSpaceChar(int c)
        {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public String next()
        {
            return readString();
        }

        public interface SpaceCharFilter
        {
            public boolean isSpaceChar(int ch);
        }
    }

    public static void main(String args[]) throws Exception
    {
        new Thread(null, new Palin_Sum_BinarySearch(),"Main",1<<27).start();
    }

}
