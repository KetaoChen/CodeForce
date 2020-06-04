package Div2_647;

import java.io.*;
import java.util.*;


public class D_Topological_Sorting implements Runnable
{
    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);
        n = in.nextInt();
        m = in.nextInt();
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt(), b = in.nextInt();
            graph[a].add(b);
            graph[b].add(a);
        }

        level = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int l = in.nextInt();
            level[i] = l;
        }

        List<Integer> list = getRes();
        if (list.size() == 0) w.println(-1);
        else {
            for (int num : list) {
                w.print(num + " ");
            }
        }
        w.flush();
        w.close();
    }

    static int n, m;
    static List<Integer>[] graph;
    static int[] level;
    private static List<Integer> getRes() {
        if (!bipart()) return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        Set<Integer>[] sets = new HashSet[n + 1];
        for (int i = 1; i <= n; i++) {
            sets[i] = new HashSet<>();
        }
        boolean[] visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if (level[i] == 1) {
                q.offer(i);
                visited[i] = true;
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            res.add(cur);
            for (int next : graph[cur]) {
                if (level[cur] < level[next]) {
                    if (!sets[next].contains(level[cur])) {
                        sets[next].add(level[cur]);
                    }
                }
                if (!visited[next] && sets[next].size() == level[next] - 1) {
                    q.offer(next);
                    visited[next] = true;
                }
            }
        }
        return res.size() == n ? res : new ArrayList<>();
    }

    private static boolean bipart() {
        int[] visited = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (visited[i] != 0) continue;
            Queue<Integer> q = new LinkedList<>();
            q.offer(i);
            visited[i] = 1;
            while (!q.isEmpty()) {
                int cur = q.poll();
                for (int next : graph[cur]) {
                    if (level[next] == level[cur]) return false;
                    if (visited[next] == 0) {
                        q.offer(next);
                        visited[next] = 1;
                    }
                }
            }
        }
        return true;
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
        new Thread(null, new D_Topological_Sorting(),"Main",1<<27).start();
    }

}
