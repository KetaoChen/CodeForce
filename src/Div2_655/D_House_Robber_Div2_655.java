package Div2_655;

import java.io.*;
import java.util.Arrays;
import java.util.InputMismatchException;


public class D_House_Robber_Div2_655 implements Runnable
{
    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        w = new PrintWriter(System.out);
        n = in.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        getRes();
        w.flush();
        w.close();
    }

    static PrintWriter w;
    static int t, k, n;
    static int[] arr;

    private static void getRes() {
        int l = arr.length;
        if (l == 1) {
            w.println(arr[0]);
            return;
        }
        long sum = 0;
        for (int num : arr) {
            sum += num;
        }
        long min = Math.min(helper(0, l - 2), helper(1, l - 1));
        w.println(sum - min);
    }

    private static long helper(int start, int end) {
        long[][] dp = new long[2][n];
        for (long[] d : dp) Arrays.fill(d, Long.MAX_VALUE);
        dp[0][start] = arr[start];
        dp[1][start] = 0;
        for (int i = start + 1; i <= end; i++) {
            if (i - 2 >= 0 && dp[0][i - 2] != Long.MAX_VALUE) {
                dp[0][i] = dp[0][i - 2] + arr[i];
            }
            if (i - 1 >= 0 && dp[0][i - 1] != Long.MAX_VALUE) {
                dp[1][i] = dp[0][i - 1] - arr[i - 1] + arr[i];
            }
            if (i - 2 >= 0 && dp[1][i - 2] != Long.MAX_VALUE) {
                dp[1][i] = Math.min(dp[1][i], dp[1][i - 2] + arr[i]);
            }
        }
        // for (long[] d : dp) System.out.println(Arrays.toString(d));
        return Math.min(dp[0][end - 1], dp[1][end]);

//        // sum[i] is the sum when we miss i
//        long[] sum1 = new long[n + 1], sum2 = new long[n + 1];
//        for (int i = start; i <= end; i++) {
//            sum1[i + 1] = sum1[i];
//            sum2[i + 1] = sum2[i];
//            if ((i - start) % 2 == 0) {
//                sum1[i + 1] = sum1[i] + arr[i];
//            }
//            else {
//                sum2[i + 1] = sum2[i] + arr[i];
//            }
//        }
//
//
//        long min = Long.MAX_VALUE;
//        for (int i = start; i <= end; i++) {
//            long left = start > 0 ? sum1[i - 1] : 0;
//            System.out.println(i + " " + left + " " + sum2[i + 1]);
//            long cur = (i - start) % 2 == 0 ? left + sum2[i + 1] : sum2[i - 1] + sum1[end] - sum1[i];
//            min = Math.min(min, cur);
//        }
//        return min;
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
        new Thread(null, new D_House_Robber_Div2_655(),"Main",1<<27).start();
    }

}
