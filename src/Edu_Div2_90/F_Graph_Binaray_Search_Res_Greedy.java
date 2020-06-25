package Edu_Div2_90;

import java.io.*;
import java.util.InputMismatchException;


public class F_Graph_Binaray_Search_Res_Greedy implements Runnable
{
    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        w = new PrintWriter(System.out);
        t = in.nextInt();
        for (int i = 0; i < t; i++) {
            n = in.nextInt();
            cost = new Integer[n];
            add = new Integer[n];
            long s1 = 0, s2 = 0;
            for (int j = 0; j < n; j++) {
                int num = in.nextInt();
                cost[j] = num;
                s1 += cost[j];
            }
            for (int j = 0; j < n; j++) {
                int num = in.nextInt();
                add[j] = num;
                s2 += add[j];
            }
            if (s1 > s2) {
                w.println("NO");
                continue;
            }
            getRes();
        }

        w.flush();
        w.close();
    }

    static PrintWriter w;
    static int t, n;
    static Integer[] cost, add;

    private static void getRes() {
        int left = 0, right = add[0];
        while (left <= right) {
            int mid = left + right >> 1;
            int val = check(mid);
            if (val == 1) {
                w.println("YES");
                return;
            }
            if (val == 0) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        w.println("NO");
    }

    // 1: can, -1: mid cannot, 0: first can not
    private static int check(int first) {
        int left = cost[0] - first;
        first = add[0] - first;
        for (int i = 1; i < n; i++) {
            first = Math.min(add[i], add[i] + first - cost[i]);
            if (first < 0) return -1;
        }
        return first >= left ? 1 : 0;
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
        new Thread(null, new F_Graph_Binaray_Search_Res_Greedy(),"Main",1<<27).start();
    }

}
