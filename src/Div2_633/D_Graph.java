package Div2_633;

import java.io.*;
import java.util.*;


public class D_Graph implements Runnable
{
    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);
        int N = in.nextInt();
        List<Integer>[] arr = new List[N + 1];
        int[] d = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i = 1; i < N; i++) {
            int f = in.nextInt(), s = in.nextInt();
            arr[f].add(s);
            arr[s].add(f);
            d[f]++;
            d[s]++;
        }
        getRes(arr, d, N, w);
        w.flush();
        w.close();
    }


    private static void getRes(List<Integer>[] graph, int[] d, int l, PrintWriter w) {
        int[] leaf = new int[l + 1];
        for (int i = 1; i <= l; i++) {
            for (int next : graph[i]) {
                if (d[next] == 1) leaf[i]++;
            }
        }
        int max = l - 1;
        for (int i = 1; i <= l; i++) {
            max -= Math.max(0, leaf[i] - 1);
        }

        int[] path = new int[l + 1];
        Queue<Integer> q = new LinkedList<>();
        int start = 0;
        for (int i = 1; i <= l; i++) {
            if (d[i] == 1) {
                start = i;
                break;
            }
        }
        int dis = 0;

        q.offer(start);
        while (!q.isEmpty()) {
            int s = q.size();
            for (int i = 0; i < s; i++) {
                int cur = q.poll();
                path[cur] = dis;
                for (int next : graph[cur]) {
                    if (next == start || path[next] != 0) continue;
                    q.offer(next);
                }
            }
            dis++;
        }

        int min = 1;
        for (int i = 1; i <= l; i++) {
            // System.out.println(path[i] + " ");
            if (d[i] == 1 && path[i] % 2 == 1) {
                min = 3;
                break;
            }
        }
        w.println(min + " " + max);
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
        new Thread(null, new D_Graph(),"Main",1<<27).start();
    }

}
