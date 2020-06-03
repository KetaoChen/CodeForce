package Div2_646;

import java.io.*;
import java.util.*;


public class E_Graph_PostOrder implements Runnable
{
    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);
        N = in.nextInt();
        nodes = new Node[N + 1];
        visited = new boolean[N + 1];
        int diff = 0;
        for (int i = 1; i <= N; i++) {
            nodes[i] = new Node(in.nextInt(), in.nextInt(), in.nextInt());
            diff += nodes[i].b - nodes[i].c;
        }
        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i < N; i++) {
            int a = in.nextInt(), b = in.nextInt();
            graph[a].add(b);
            graph[b].add(a);
        }

        if (diff != 0) w.println(-1);
        else {
            long[] res = getRes(1, nodes[1].a);
            w.println(res[0]);
        }

        w.flush();
        w.close();
    }

    static int N;
    static Node[] nodes;
    static List<Integer>[] graph;
    static boolean[] visited;

    // long[] 0: cost, 1: 0 needed, 2: 1 needed.
    private static long[] getRes(int root, int cost) {
        long[] res = new long[3];
        visited[root] = true;
        if (nodes[root].b != nodes[root].c) {
            res[nodes[root].c + 1]++;
        }

        for (int next : graph[root]) {
            if (visited[next]) continue;
            long[] n = getRes(next, Math.min(cost, nodes[next].a));
            res[0] += n[0];
            res[1] += n[1];
            res[2] += n[2];
        }

        res[0] += Math.min(res[1], res[2]) * 2 * cost;
        long min = Math.min(res[1], res[2]);
        res[1] -= min;
        res[2] -= min;
        // System.out.println(root + " " + res[0] + " " + res[1] + " " + res[2]);
        return res;
    }

    static class Node
    {
        int a, b, c;
        public Node(int i, int j, int k) {
            a = i;
            b = j;
            c = k;
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
        new Thread(null, new E_Graph_PostOrder(),"Main",1<<27).start();
    }

}
