package Div2_616;

import java.io.*;
import java.util.*;
import java.lang.*;


public class Irreducible_Anagrams_1290B implements Runnable
{
    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);

        String s = in.nextLine();
        int q = Integer.parseInt(in.nextLine());
        //System.out.println(q);
        int[][] count = count(s);

        for (int i = 0; i < q; i++) {
            String[] str = in.nextLine().split(" ");
            int left = Integer.parseInt(str[0]);
            int right = Integer.parseInt(str[1]);
            //System.out.println(left + " " + right);
            w.println(check(s, count, left, right));
        }
        w.flush();
        w.close();
    }

    private static String check(String s, int[][] count, int left, int right) {
        if (left == right) {
            return "Yes";
        }
        int[] r = count[right];
        int[] l = count[left - 1];
        boolean have = false;
        boolean haveSecond = false;
        for (int i = 0; i < 26; i++) {
            if (r[i] - l[i] != 0) {
                if (!have) {
                    have = true;
                    continue;
                }
                if (have && !haveSecond) {
                    haveSecond = true;
                    continue;
                }
                if (have && haveSecond) {
                    return "Yes";
                }
            }
        }
        if (haveSecond) {
            if (s.charAt(left - 1) != s.charAt(right - 1)) {
                return "Yes";
            }
        }
        return "No";
    }

    private static int[][] count(String s) {
        int l = s.length();
        int[][] res = new int[l + 1][26];

        for (int i = 1; i <= l; i++) {
            for (int j = 0; j < 26; j++) {
                res[i][j] = res[i - 1][j];
            }
            res[i][s.charAt(i - 1) - 'a']++;
        }
//        for (int i = 0; i <= l; i++) {
//            for (int j = 0; j < 26; j++) {
//                System.out.print(res[i][j] + " ");
//            }
//            System.out.println();
//        }
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
        new Thread(null, new Irreducible_Anagrams_1290B(),"Main",1<<27).start();
    }

}
