import java.io.*;
import java.util.*;


public class D implements Runnable
{
    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);

        String s = in.nextLine();
        String[] cur = s.split(" ");
        int n = Integer.parseInt(cur[0]), k = Integer.parseInt(cur[1]);
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextLine();
        }
        getRes(arr, k, w);

        w.flush();
        w.close();
    }

    static final int mod = (int) 1e9;

    private static void getRes(String[] arr, int k, PrintWriter w) {
        int l = arr.length;
        int[][] dp = new int[l + 1][k + 1];
        // String[][] dp = new String[l + 1][k + 1];
        // dp[0][0] = "";
        // String res = helper(arr, l, k, dp);
        helper(arr, 0, k, dp);

        StringBuilder sb = new StringBuilder();
        if (dp[0][k] == -1) {
            w.println(-1);
            return;
        }
        for (int i = 0; i < l; i++) {
            sb.append(dp[i][k]);
            int cost = cal(arr[i], num[dp[i][k]]);
            k -= cost;
        }
        w.println(sb.toString());
    }

    static String[] num = {"1110111", "0010010", "1011101", "1011011", "0111010", "1101011", "1101111", "1010010", "1111111", "1111011"};

    private static int helper(String[] arr, int index, int rest, int[][] dp) {
        if (index == arr.length && rest == 0) return 1;
        if (index == arr.length || rest < 0) return -1;
        if (dp[index][rest] != 0) return dp[index][rest];
        String cur = arr[index];

        for (int next = 9; next >= 0; next--) {
            int cost = cal(cur, num[next]);
            if (cost == -1) continue;
            int can = helper(arr, index + 1, rest - cost, dp);
            if (can != -1) {
                dp[index][rest] = next;
                return next;
            }
        }
        dp[index][rest] = -1;
        return dp[index][rest];
    }

//    private static String helper(String[] arr, int index, int rest, String[][] dp) {
//        if (rest < 0 || index < 0) return null;
//        if (index == 0) {
//            return rest == 0 ? "" : null;
//        }
//        if (dp[index][rest] != null) {
//            return dp[index][rest];
//        }
//
//        String[] num = {"1110111", "0010010", "1011101", "1011011", "0111010", "1101011", "1101111", "1010010", "1111111", "1111011"};
//        String cur = arr[index - 1];
//        int[] cost = new int[10];
//        for (int i = 0; i < 10; i++) {
//            cost[i] = cal(cur, num[i]);
//            // System.out.println(i + " " + cost[i]);
//        }
//
//        Map<Integer, Integer> next = new HashMap<>();
//
//        for (int i = 0; i < 10; i++) {
//            if (cost[i] >= 0) {
//                next.put(cost[i], i);
//            }
//        }
//        String res = null;
//        for (int n : next.keySet()) {
//            String temp = helper(arr, index - 1, rest - n, dp);
//            if (temp != null) {
//                temp = temp + next.get(n);
//                if (res == null || temp.compareTo(res) > 0) res = temp;
//            }
//        }
//        dp[index][rest] = res;
//        return res;
//    }

    private static int cal(String s1, String s2) {
        int res = 0, l = s1.length();
        for (int i = 0; i < l; i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if (c1 == '1' && c2 == '0') return -1;
            if (c1 == '0' && c2 == '1') res++;
        }
        return res;
    }



    private static long lowbit(long x) {
        return x & -x;
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public  static long[] getInvArray(long n, int p){
        long[] inv = new long[(int)n + 1];
        inv[1] = 1;
        for (int i = 2; i <= n; i++) {
            inv[i] = ((p - p / i) * inv[p % i] % p + p) % p;
        }
        return inv;
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
        new Thread(null, new D(),"Main",1<<27).start();
    }

}
