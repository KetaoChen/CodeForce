package Unsolved;

import java.io.*;
import java.util.*;
import java.lang.*;


public class Cow_and_Fields_1307D implements Runnable
{
    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);
        int n = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        int m = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[k];
        for (int i = 0; i < k; i++) {
            arr[i] = in.nextInt();
        }

        List<Integer>[] graph = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int i = 0; i < m; i++) {
            int first = in.nextInt();
            int second = in.nextInt();
            graph[first].add(second);
            graph[second].add(first);
        }

        getRes(w, graph, arr);
        w.flush();
        w.close();
    }

    private static void getRes(PrintWriter w, List<Integer>[] graph, int[] arr) {
//        int l = graph.length;
//        int[] disFrom1 = bfs(graph, 1);
//        int[] disFromN = bfs(graph, l - 1);
//
//
//        List<Integer> list = new ArrayList<>();
//        for (int v : arr) {
//            list.add(dis[v]);
//        }
//        Collections.sort(list);
//        int min = l;
//        for (int i = 1; i < list.size(); i++) {
//            min = Math.min(min, list.get(i) - list.get(i - 1));
//        }
//        w.println(dis[1] - Math.max(0, min - 1));
//    }
//
//    private static int[] bfs(List<Integer>[] graph, int start) {
//        int l = graph.length;
//        Queue<Integer> q = new LinkedList<>();
//        boolean[] visited = new boolean[l];
//        int[] dis = new int[l];
//        visited[start] = true;
//
//        q.offer(start);
//        int d = 0;
//        while (!q.isEmpty()) {
//            int s = q.size();
//            for (int i = 0; i < s; i++) {
//                int cur= q.poll();
//                dis[cur] = d;
//                for (int next : graph[cur]) {
//                    if (!visited[next]) {
//                        q.offer(next);
//                        visited[next] = true;
//                    }
//                }
//            }
//            d++;
//        }
//        return dis;
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
        new Thread(null, new Cow_and_Fields_1307D(),"Main",1<<27).start();
    }

}
