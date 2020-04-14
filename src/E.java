import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;


public class E implements Runnable
{
    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            int n = in.nextInt();
            Integer[] arr = new Integer[n];
            for (int j = 0; j < n; j++) {
                arr[j] = in.nextInt();
            }
            getRes(arr, w);
        }

        w.flush();
        w.close();
    }

    static final int mod = (int) 1e9;

    private static void getRes(Integer[] arr, PrintWriter w) {
        int l = arr.length, res = 0;
        int[][] count = new int[l + 1][201];
        List<Integer>[] lists = new List[201];
        for (int i = 1; i <= 200; i++) {
            lists[i] = new ArrayList<>();
        }

        for (int i = 1; i <= l; i++) {
            for (int j = 1; j <= 200; j++) {
                count[i][j] = count[i - 1][j];
            }
            count[i][arr[i - 1]]++;
            lists[arr[i - 1]].add(i);
        }



        for (int val : count[l]) res = Math.max(res, val);

        // we need to find the next position of i & j, to make the palindrome part larger. else we prefer to stay the same


        for (int k = 1; k <= 200; k++) {
            List<Integer> list = lists[k];
            int max = 0, s = list.size(), i = 0, j = s - 1;

            while (i < j) {
                max++;
                int left = list.get(i), right = list.get(j) - 1, diff = 0;

                for (int d = 1; d <= 200; d++) {
                    diff = Math.max(diff, count[right][d] - count[left][d]);
                }
                res = Math.max(res, max * 2 + diff);
                i++;
                j--;
            }
        }

        w.println(res);
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
        new Thread(null, new E(),"Main",1<<27).start();
    }

}
