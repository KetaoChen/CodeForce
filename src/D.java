import java.io.*;
import java.util.*;
import java.lang.*;


public class D implements Runnable
{
    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);
        int q = Integer.parseInt(in.nextLine()); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 0; i < q; i++) {
            String s = in.nextLine();
            getRes(w, s);
        }
        w.flush();
        w.close();
    }

    private static void getRes(PrintWriter w, String s) {
        int l = Integer.parseInt(s.split(" ")[0]);
        s = s.split(" ")[1];

        int min = 1;
        int max = l;
        StringBuilder longest = new StringBuilder();
        int[] longg = new int[l];
        for (int i = l - 2; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '<') {
                longg[i + 1] = max--;
            }
            else {
                longg[i + 1] = min++;
            }
        }
        longg[0] = max;
        for (int num : longg) {
            longest.append(num + " ");
        }

        max = l;
        int count = 0;
        int index = 0;
        StringBuilder shortest = new StringBuilder();
        while (index < s.length()) {
            if (s.charAt(index) == '>') {
                shortest.append(max-- + " ");
                index++;
            }

            else {
                while (index < s.length() && s.charAt(index) == '<') {
                    count++;
                    index++;
                }
                int temp = max - count - 1;
                while (count > 0) {
                    shortest.append(max - count + " ");
                    count--;
                }
                index++;
                shortest.append(max + " ");
                max = temp;
            }
        }
        if (max == 1) {
            shortest.append(max);
        }
        w.println(shortest.toString().trim());
        w.println(longest.toString().trim());
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
