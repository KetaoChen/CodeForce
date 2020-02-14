import java.io.*;
import java.util.*;
import java.lang.*;


public class D implements Runnable
{
    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);
        long q = in.nextLong(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 0; i < q; i++) {
            long n = in.nextLong();
            long m = in.nextLong();

            //System.out.println(n + " " + m);
            long[] arr = new long[(int)m];
            for (int k = 0; k < m; k++) {
                arr[k] = in.nextLong();
            }


            w.println(getRes(n, arr));
        }
        w.flush();
        w.close();
    }

    private static int getRes(long n, long[] arr) {
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        if (sum < n) {
            return -1;
        }

        long temp = n;
        sum = 0;
        for (long num : arr) {
            for (int i = 0; i < 60; i++) {
                if (((temp >> i) & 1) == 1 && ((sum >> i) & 1) == 1) {
                    temp = (temp ^ (1 << i));
                    sum = (sum ^ (1 << i));
                }
            }
            if ((temp & num) == 1) {
                temp = (temp ^ num);
            }
            else {
                sum += num;
            }
        }
        //System.out.println(temp + " " + sum);
        for (int i = 0; i < 60; i++) {
            //System.out.println(i + " " + ((temp >> i) & 1));
            //System.out.println(i + " " + ((sum >> i) & 1));
            if (((temp >> i) & 1) == 1 && ((sum >> i) & 1) == 1) {
                temp = (temp ^ (1 << i));
                sum = (sum ^ (1 << i));
            }
        }

        //System.out.println(temp + " " + sum);
        int res = 0, index = 0;
        Queue<Integer> q = new LinkedList<>();
        boolean[] used = new boolean[60];
        while (sum > 0) {
            if ((sum & 1) == 1) {
                q.offer(index);
                used[index] = true;
            }
            index++;
            sum >>= 1;
        }

        while (!q.isEmpty()) {
            int s = q.size();
            for (int i = 0; i < s; i++) {
                int cur = q.poll();
                //System.out.println(res + " " + cur);
                if (((temp >> cur) & 1) == 1) {
                    temp = (temp ^ (1 << cur));
                }
                if (cur > 0 && !used[cur - 1]) {
                    q.offer(cur - 1);
                    used[cur - 1] = true;
                }
            }
            if (temp == 0) {
                return res;
            }
            res++;
        }
        //System.out.println(temp);
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
        new Thread(null, new D(),"Main",1<<27).start();
    }

}
