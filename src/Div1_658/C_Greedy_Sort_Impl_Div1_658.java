package Div1_658;

import java.io.*;
import java.util.*;


public class C_Greedy_Sort_Impl_Div1_658 implements Runnable
{
    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        w = new PrintWriter(System.out);
        t = in.nextInt();
        for (int i = 0; i < t; i++) {
            n = in.nextInt();
            x = in.nextInt();
            y = in.nextInt();
            arr = new int[n];
            for (int j = 0; j < n; j++) {
                arr[j] = in.nextInt();
            }
            getRes();
        }

        w.flush();
        w.close();
    }

    static PrintWriter w;
    static int t, x, y, n;
    static int[] arr;

    private static void getRes() {
        int[] count = new int[n + 2];
        for (int color : arr) {
            count[color]++;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        int miss = -1;
        for (int i = 1; i <= n + 1; i++) {
            if (count[i] > 0) {
                pq.offer(new int[]{i, count[i]});
            }
            else {
                miss = i;
            }
        }

        int[] same = new int[n + 2];
        int xx = x;
        while (xx > 0) {
            int[] cur = pq.poll();
            same[cur[0]]++;
            cur[1]--;
            if (cur[1] > 0) {
                pq.offer(cur);
            }
            xx--;
        }

        int pos = n - x, diff = y - x;
        int max = pq.isEmpty() ? 0 : pq.peek()[1];
        if (pos - max < (diff + 1) / 2) {
            w.println("NO");
            return;
        }

        // put them into correct positions.
        w.println("YES");
        int[] res = new int[n];
        boolean[] s = new boolean[n];
        Arrays.fill(res, -1);
        for (int i = 0; i < n; i++) {
            if (same[arr[i]] > 0) {
                res[i] = arr[i];
                same[arr[i]]--;
                s[i] = true;
            }
        }

        int yy = diff;
        for (int i = 0; i < n; i++) {
            if (res[i] != -1) continue;
            if (yy == 0) {
                res[i] = miss;
                continue;
            }

            int[] cur = pq.poll();
            if (arr[i] == cur[0]) {
                if (pq.isEmpty()) {
                    boolean find = false;
                    for (int k = 0; k < i; k++) {
                        if (!s[k] && arr[k] != cur[0] && arr[i] != res[k]) {
                            res[i] = res[k];
                            res[k] = cur[0];
                            cur[1]--;
                            pq.offer(cur);
                            yy--;
                            find = true;
                            break;
                        }
                    }
                    if (!find) {
                        res[i] = miss;
                        pq.offer(cur);
                    }

                }
                else {
                    yy--;
                    int[] next = pq.poll();
                    pq.offer(cur);
                    res[i] = next[0];
                    next[1]--;
                    if (next[1] > 0) {
                        pq.offer(next);
                    }
                }
            }
            else {
                yy--;
                res[i] = cur[0];
                cur[1]--;
                if (cur[1] > 0) {
                    pq.offer(cur);
                }
            }
        }

        for (int num : res) {
            w.print(num + " ");
        }
        w.println();
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
        new Thread(null, new C_Greedy_Sort_Impl_Div1_658(),"Main",1<<27).start();
    }

}
