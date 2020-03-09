package Div2_618;

import java.io.*;
import java.util.*;
import java.lang.*;


public class Anu_Has_a_Function_1299A implements Runnable
{
    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);
        int q = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        int[] arr = new int[q];
        for (int i = 0; i < q; i++) {
            arr[i] = in.nextInt();
        }

        w.println(print(arr));

        w.flush();
        w.close();
    }

    private static String print(int[] a) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : a) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int[] count = new int[32];
        for (int num : a) {
            if (map.get(num) > 1) {
                continue;
            }
            int d = 0;
            int temp = num;
            while (temp > 0) {
                count[d++] += (temp & 1);
                temp = (temp >> 1);
            }
        }

//        for (int c : count) {
//            System.out.print(c + " ");
//        }
//        System.out.println();

        long max = 0;
        int first = -1;
        for (int num : a) {
            if (map.get(num) > 1) {
                continue;
            }
            int[] c = new int[32];
            int temp = num;
            int d = 0;
            while (temp > 0) {
                c[d++] += (temp & 1);
                temp = (temp >> 1);
            }

            long val = 0;
            for (int i = d - 1; i >= 0; i--) {
                val = val * 2;
                if (count[i] == 1 && c[i] == 1) {
                    val += 1;
                }
            }
            //System.out.println(num + " " + val);
            if (val > max) {
                max = val;
                first = num;
            }

        }


        boolean add = false;
        StringBuilder sb = new StringBuilder();
        if (first == -1) {
            add = true;
        }
        else {
            sb.append(first);
        }

        for (int i = a.length - 1; i >= 0; i--) {
            if (a[i] == first && !add) {
                add = true;
                continue;
            }
            sb.append(" " + a[i]);
        }

        return sb.toString().trim();
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
        new Thread(null, new Anu_Has_a_Function_1299A(),"Main",1<<27).start();
    }

}
