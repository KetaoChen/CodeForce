import java.io.*;
import java.util.*;
import java.lang.*;


public class PizzaSmallData implements Runnable
{
    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);
        long n = in.nextLong(); // Scanner has functions to read ints, longs, strings, chars, etc.
        long m = in.nextLong();
        long[] arr = new long[(int)m];
        for (int i = 0; i < m; i++) {
            arr[i] = in.nextLong();
        }
        List<Integer> res = getRes2(n, arr);
        w.println(res.size());
        for (int i = res.size() - 1; i >= 0; i--) {
            w.print(res.get(i) + " ");
        }
        w.flush();
        w.close();
    }

    private static List<Integer> getRes2(long target, long[] arr) {
        List<Integer> res = new ArrayList<>();

        int index = arr.length - 1;
        while (index >= 0) {
            while (index >= 0 && target >= arr[index]) {
                res.add(index);
                target -= arr[index--];
            }
            index--;
        }
        return res;
    }

    private static List<Integer> getRes(int target, int[] arr) {
        List<Integer> res = new ArrayList<>();
        int l = arr.length;
        boolean[][] dp = new boolean[l + 1][target + 1];
        dp[0][0] = true;

        for (int i = 1; i <= l; i++) {
            int val = arr[i - 1];
            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= val) {
                    dp[i][j] |= dp[i - 1][j - val];
                }
            }
        }

        int i = l, j = target;
        while (j >= 0 && i > 0) {

            if (dp[i][j]) {
                //System.out.println(i + " " + j);
                if (j >= arr[i - 1] && dp[i - 1][j - arr[i - 1]]) {
                    i--;
                    res.add(i);
                    j-=arr[i];
                }
                else {
                    i--;
                }
            }
            else {
                j--;
            }
        }
        return res;
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
        new Thread(null, new PizzaSmallData(),"Main",1<<27).start();
    }

}
