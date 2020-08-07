package Div2_660;

import java.io.*;
import java.util.*;


public class D_Topological_Sorting_Div2_660 implements Runnable
{
    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        w = new PrintWriter(System.out);
        n = in.nextInt();
        a = new long[n + 1];
        b = new int[n + 1];
        // g = new List[n + 1];
        v = new boolean[n + 1];
        level = new int[n + 1];
//        for (int i = 1; i <= n; i++) {
//            g[i] = new ArrayList<>();
//        }
        for (int i = 1; i <= n; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 1; i <= n; i++) {
            b[i] = in.nextInt();
            if (b[i] != -1) level[b[i]]++;
        }
        getRes();
        w.flush();
        w.close();
    }

    static PrintWriter w;
    static int t, k, n;
    static long[] a;
    static int[] level, b;
    // static List<Integer>[] g;
    static boolean[] v;


    private static void getRes() {
        long res = 0;
        Queue<Integer> q = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i <= n; i++) {
            if (level[i] == 0) q.offer(i);
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            res += a[cur];
            if (a[cur] < 0) stack.push(cur);
            else list.add(cur);
            if (b[cur] == -1) continue;
            level[b[cur]]--;
            if (a[cur] >= 0) {
                a[b[cur]] += a[cur];
            }
            if (level[b[cur]] == 0) {
                q.offer(b[cur]);
            }
        }

        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
        w.println(res);
        for (int num : list) {
            w.print(num + " ");
        }
        w.println();
    }

    // find the max value we can get from this subtree
//    private static long helper(int cur, List<Integer> order) {
//        if (v[cur]) return a[cur];
//        long val = a[cur];
//        for (int next : g[cur]) {
//            long nVal = helper(next, order);
//
//            val += Math.max(0, );
//        }
//        a[cur] = val;
//        return a[cur];
//    }

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
        new Thread(null, new D_Topological_Sorting_Div2_660(),"Main",1<<27).start();
    }

}
