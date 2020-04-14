package Div3_634;

import java.io.*;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Set;


public class D_Sudoku_Construct implements Runnable
{
    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);
        int N = Integer.parseInt(in.nextLine());
        for (int i = 0; i < N; i++) {
            String[] sudo = new String[9];
            for (int j = 0; j < 9; j++) {
                sudo[j] = in.nextLine();
            }
            getRes(sudo, w);
        }

        w.flush();
        w.close();
    }

    private static void getRes(String[] arr, PrintWriter w) {
        // we change (0, 0), (3, 1), (6, 2)
        //           (1, 3), (4, 4), (7, 5)
        //           (2, 6), (5, 7), (8, 8)
        int[] change = {0, 3, 6, 1, 4, 7, 2, 5, 8};
        for (int i = 0; i < 9; i++) {
            StringBuilder sb = new StringBuilder(arr[i]);
            sb.setCharAt(change[i], find(arr, i, change[i]));
            w.println(sb.toString());
        }

    }

    private static char find(String[] arr, int row, int col) {
        Set<Character> set = new HashSet<>();
        String s = arr[row];
        for (int i = 0; i < 9; i++) {
            if (i / 3 == col / 3) continue;
            set.add(s.charAt(i));
        }
        for (int i = 0; i < 9; i++) {
            if (i / 3 == row / 3) continue;
            if (set.contains(arr[i].charAt(col))) return arr[i].charAt(col);
        }
        return ' ';
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
        new Thread(null, new D_Sudoku_Construct(),"Main",1<<27).start();
    }

}
