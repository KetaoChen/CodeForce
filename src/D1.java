import java.io.*;
import java.util.*;
import java.lang.*;


public class D1 implements Runnable
{
    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);
        int m = in.nextInt();
        int n = in.nextInt();
        int k = in.nextInt();


        getRes(w, m, n, k);

        w.flush();
        w.close();
    }

    private static void getRes(PrintWriter w, int m, int n, int k) {
        int max = 4 * m * n - 2 * m - 2 * n - 2 * (Math.min(m, n) - 2) * (Math.max(m, n) - 1);
        if (k > max) {
            w.println("NO");
            return;
        }

        int i = 0, j = 0;
        m--;
        n--;
        if (m >= n) {
            // m rows, n cols
            while (k > 0) {

                int step = Math.min(n, k);
                w.println(step + " R");
                k -= step;

                if (i > 0 && k > 0) {
                    k--;
                    w.println(1 + " U");
                    if (k > 0) {
                        k--;
                        w.println(1 + " D");
                    }
                }

                if (k == 0) {
                    return;
                }
                step = Math.min(n, k);
                w.println(step + " L");
                k -= step;

                if (i != m && k > 0) {
                    i++;
                    k--;
                    w.println(1 + " D");
                }
                else if (i == m && k > 0) {
                    w.println(k + " U");
                    return;
                }
            }
        }
        else {
            while (k > 0) {
                int step = Math.min(m, k);
                w.println(step + " D");
                k -= step;

                if (j > 0 && k > 0) {
                    k--;
                    w.println(1 + " L");
                    if (k > 0) {
                        k--;
                        w.println(1 + " R");
                    }
                }

                if (k == 0) {
                    return;
                }
                step = Math.min(m, k);
                w.println(step + " U");
                k -= step;

                if (j != n && k > 0) {
                    j++;
                    k--;
                    w.println(1 + " R");
                }
                else if (j == n && k > 0) {
                    w.println(k + " L");
                    return;
                }
            }
        }
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
        new Thread(null, new D1(),"Main",1<<27).start();
    }

}
