package Div2_662;

import java.io.*;
import java.util.*;


public class B_Cases_Counting_Div2_662 implements Runnable
{
    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        w = new PrintWriter(System.out);
        n = Integer.parseInt(in.nextLine());
        String[] s = in.nextLine().split(" ");
        int[] count = new int[N];
        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt(s[i]);
            count[a]++;
        }
        Set<Integer>[] nums = new Set[N];
        for (int i = 0; i < N; i++) {
            nums[i] = new HashSet<>();
        }
        int all = 0;
        for (int i = 0; i < N; i++) {
            if (count[i] > 0) {
                all++;
                nums[count[i]].add(i);
            }
        }

        int q = Integer.parseInt(in.nextLine());
        for (int i = 0; i < q; i++) {
            s = in.nextLine().split(" ");
            int num = Integer.parseInt(s[1]);
            int c = count[num];
            if (s[0].charAt(0) == '-') {
                nums[c].remove(num);
                count[num]--;
                if (c != 1) {
                    nums[c - 1].add(num);
                }
                else {
                    all--;
                }
            }
            else {
                count[num]++;
                if (c == 0) {
                    nums[1].add(num);
                    all++;
                }
                else {
                    nums[c].remove(num);
                    nums[c + 1].add(num);
                }
            }

            int[] cc = new int[8];
            for (int k = 1; k < 8; k++) {
                cc[k] = cc[k - 1] + nums[k].size();
            }
            if (all - cc[7] >= 1 || all - cc[3] >= 2) {
                // w.println(1);
                w.println("YES");
                continue;
            }

            if ((nums[2].size() >= 1 || nums[3].size() >= 1) && (all - cc[5] >= 1) || (nums[2].size() + nums[3].size()) >= 2 && (all - cc[3] >= 1)) {
                // w.println(2);
                w.println("YES");
                continue;
            }
            w.println("NO");
        }

        w.flush();
        w.close();
    }

    static PrintWriter w;
    static int t, k, n;
    static int[] arr;
    static int N=100050;

    private static void getRes() {

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
        new Thread(null, new B_Cases_Counting_Div2_662(),"Main",1<<27).start();
    }

}
