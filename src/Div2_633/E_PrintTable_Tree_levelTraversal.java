package Div2_633;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Stack;


public class E_PrintTable_Tree_levelTraversal implements Runnable
{
    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            getRes(in.nextLong(), w);
        }
//        Set<Integer> set = new HashSet<>();
//        int index = 1;
//        for (int i = 1; i <= 512; i++) {
//            if (set.contains(i)) continue;
//            set.add(i);
//            StringBuilder sb = new StringBuilder(Integer.toBinaryString(i));
//            for (int j = i + 1; j <= 1024; j++) {
//                if (!set.contains(j) && !set.contains(i ^ j) && (i ^ j) > j) {
//                    set.add(j);
//                    set.add(i ^ j);
//                    sb.append("," + Integer.toBinaryString(j) + "," + Integer.toBinaryString(i ^ j));
//                    // System.out.println(index + " " + i + " " + j + " " + (i ^ j));
//                    break;
//                }
//            }
//            System.out.println(index++ + " " + sb.toString());
//            // System.out.println();
//        }

        w.flush();
        w.close();
    }

    private static void getRes(long i, PrintWriter w) {
        int[] ini = {1, 2, 3};
        int[][] increment = {{0, 1, 2, 3}, {0, 2, 3, 1}, {0, 3, 1, 2}};
        // find the i th number in this four-sides tree.
        long n = (i - 1) / 3 + 1;
        // System.out.println("this is: " + n);
        int col = (int) ((i - 1) % 3);

        long cur = 1;
        while (n > cur) {
            n -= cur;
            cur *= 4;
        }

        Stack<Integer> stack = new Stack<>();
        n--;
        while (cur > 1) {
            stack.push(increment[col][(int)(n % 4)]);
            n /= 4;
            cur /= 4;
        }
        long res = ini[col];
        while (!stack.isEmpty()) {
            res = res * 4 + stack.pop();
        }

        w.println(res);
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
        new Thread(null, new E_PrintTable_Tree_levelTraversal(),"Main",1<<27).start();
    }

}
