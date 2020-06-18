package Global_Round_8;

import java.io.*;
import java.util.*;


public class E_Unsolved_Graph implements Runnable
{
    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);
        t = in.nextInt();
        for (int i = 0; i < t; i++) {
            n = in.nextInt();
            m = in.nextInt();
            g = new List[n + 1];
            degree = new int[n + 1];
            st = new int[n + 1][3];
            res = new List[2];
            res[0] = new ArrayList<>();
            res[1] = new ArrayList<>();
            q = new LinkedList<>();
            for (int j = 1; j <= n; j++) {
                g[j] = new ArrayList<>();
            }
            for (int j = 0; j < m; j++) {
                int start = in.nextInt(), end = in.nextInt();
                g[start].add(end);
                p[end].add(start);
                degree[end]++;
            }
            getRes(w);
        }
        w.flush();
        w.close();
    }

    static int t, n, m;
    static List<Integer>[] g, p;
    static int[] degree;
    static int[][] st;
    static List<Integer>[] res;
    static Queue<Integer> q;

    private static void getRes(PrintWriter w) {
        // st[i][0]
        // st: 2, there is no input. its next can also be 0.
        // st: 0, its next must be delete.
        // st: 1, this one has been deleted.

        // st[i][1]: the number of nodes to be delete.
        boolean[] visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if (degree[i] == 0) {
                q.offer(i);
                visited[i] = true;
//                st[i][0] = 0;
//                st[i][1] = 1;
//                st[i][2] = 0;
            }
        }

        int l = 0;
        while (!q.isEmpty()) {
            int s = q.size();
            for (int i = 0; i < s; i++) {
                int cur = q.poll();
                for (int next : g[cur]) {
                    degree[next]--;
                    if (degree[next] == 0) {
                        q.offer(next);
                        visited[next] = true;
                    }
                }
            }
        }

        w.println();
    }

    private static void dfs(int cur) {

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
        new Thread(null, new E_Unsolved_Graph(),"Main",1<<27).start();
    }

}
