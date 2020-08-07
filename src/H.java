import org.omg.PortableInterceptor.INACTIVE;

import java.io.*;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;


public class H implements Runnable
{
    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        w = new PrintWriter(System.out);
        String[] s = in.nextLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextLine();
        }
        getRes();
        w.flush();
        w.close();
    }

    static PrintWriter w;
    static int m, n;
    static String[] arr;

    private static void getRes() {
        // up, down, left, right
        int[][][] dp = new int[n][m][4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0) {
                    dp[i][j][0] = 1;
                }
                else {
                    dp[i][j][0] = arr[i].charAt(j) == arr[i - 1].charAt(j) ? dp[i - 1][j][0] + 1 : 1;
                }
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                if (i == n - 1) {
                    dp[i][j][1] = 1;
                }
                else {
                    dp[i][j][1] = arr[i].charAt(j) == arr[i + 1].charAt(j) ? dp[i + 1][j][1] + 1 : 1;
                }
            }
        }

        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                if (j == 0) {
                    dp[i][j][2] = 1;
                }
                else {
                    dp[i][j][2] = arr[i].charAt(j) == arr[i].charAt(j - 1) ? dp[i][j - 1][2] + 1 : 1;
                }
            }
        }

        for (int j = m - 1; j >= 0; j--) {
            for (int i = 0; i < n; i++) {
                if (j == m - 1) {
                    dp[i][j][3] = 1;
                }
                else {
                    dp[i][j][3] = arr[i].charAt(j) == arr[i].charAt(j + 1) ? dp[i][j + 1][3] + 1 : 1;
                }
            }
        }

        int[][] max = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                max[i][j] = Math.min(dp[i][j][0], Math.min(dp[i][j][1], Math.min(dp[i][j][2], dp[i][j][3])));
            }
        }

        int[][] res = new int[n][m];
        int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};

        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                q.offer(new int[]{i, j});
            }
        }

        int round = 0;
        long sum = 0;
        boolean[][] v = new boolean[n][m];
        while (!q.isEmpty()) {
            int s = q.size();
            for (int k = 0; k < s; k++) {
                int[] cur = q.poll();
                int i = cur[0], j = cur[1];
                res[i][j] = round + 1;
                sum++;
                boolean add = true;
                for (int d = 0; d < 4; d++) {
                    int ni = i + dx[d], nj = j + dy[d];
                    if (ni < 0 || ni >= n || nj < 0 || nj >= m || res[ni][nj] < round || arr[ni].charAt(nj) != arr[i].charAt(j)) {
                        add = false;
                        break;
                    }
                }
                if (add) {
                    q.offer(cur);
                }
            }
            round++;
        }


        w.println(sum);
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
        new Thread(null, new H(),"Main",1<<27).start();
    }

}
