package Div2_660;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;


public class C_Graph_Counting_Div2_660 implements Runnable
{
    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        w = new PrintWriter(System.out);
        t = in.nextInt();
        while (t-- > 0) {
            n = in.nextInt();
            m = in.nextInt();
            count = new int[n + 1];
            p = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                p[i] = in.nextInt();
            }
            h = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                h[i] = in.nextInt();
            }
            g = new List[n + 1];
            for (int i = 1; i <= n; i++) {
                g[i] = new ArrayList<>();
            }
            for (int i = 1; i <= n - 1; i++) {
                int x = in.nextInt(), y = in.nextInt();
                g[x].add(y);
                g[y].add(x);
            }
            // int[] res = getRes(1, 0);
            // System.out.println(Arrays.toString(res));
            w.println(getRes() ? "YES" : "NO");
        }

        w.flush();
        w.close();
    }

    static PrintWriter w;
    static int t, n, m;
    static int[] p, h, count;
    static List<Integer>[] g;

    // res[0] angry, res[1] good, res[2] = 1, yes, 0, no
    private static boolean getRes() {
        int all = postHelper(1, 0);
        // System.out.println(Arrays.toString(count));
        if (all != m) return false;
        return preHelper(1, 0, m, 0);

//        int[] res = new int[3];
//        for (int next : g[cur]) {
//            if (next == prev) continue;
//            int[] ne = getRes(next, cur);
//            if (ne[2] == 0) return new int[]{0, 0, 0};
//            res[0] += ne[0];
//            res[1] += ne[1];
//        }
//        if (((res[1] + res[0] + p[cur]) ^ h[cur] & 1) == 1) {
//            return new int[]{0, 0, 0};
//        }
//        int sum = res[1] + res[0] + p[cur] ;
//        if (sum - h[cur] < 0 || sum + h[cur] < 0) return new int[]{0, 0, 0};
//        return new int[]{(sum - h[cur]) / 2, (sum + h[cur])/ 2, 1};
    }

    private static boolean preHelper(int cur, int prev, int happy, int angry) {
        if (((count[cur] ^ h[cur]) & 1) == 1) {
            return false;
        }
        int curH = (count[cur] + h[cur]) / 2;
        int curA = (count[cur] - h[cur]) / 2;
        if (happy < curH) return false;
        // System.out.println(cur + " " + count[cur] + " " + h[cur]);
        if (curH < 0 || curA < 0) return false;


        for (int next : g[cur]) {
            if (next == prev) continue;
            int np = count[next];
            int nh = h[next];
            if ((np ^ nh) == 1) return false;
            int nHappy = (np + nh) / 2;
            int nAngry = (np - nh) / 2;
            if (nHappy < 0 || nAngry < 0) return false;
            curH -= nHappy;
            curA -= nAngry;
            if (!preHelper(next, cur, nHappy, nAngry)) {
                return false;
            }
        }
        if (curA > p[cur]) return false;
        return true;
    }

    private static int postHelper(int cur, int prev) {
        int res = p[cur];
        for (int next : g[cur]) {
            if (next == prev) continue;
            res += postHelper(next, cur);
        }
        count[cur] = res;
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
        new Thread(null, new C_Graph_Counting_Div2_660(),"Main",1<<27).start();
    }

}
