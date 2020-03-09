import java.io.*;
import java.util.*;
import java.lang.*;


public class Motaracks_Birthday_1301B implements Runnable
{
    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);
        int q = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 0; i < q; i++) {
            int x = in.nextInt();
            int[] arr = new int[x];
            for (int j = 0; j < x; j++) {
                arr[j] = in.nextInt();
            }
            int[] res = (getRes(arr));
            w.print(res[0] + " ");
            w.println(res[1]);
        }
        w.flush();
        w.close();
    }

    private static int[] getRes(int[] arr) {
        int diff = 0, max = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {

            if (arr[i] != -1 && ((i > 0 && arr[i - 1] == -1) || (i < arr.length - 1 && arr[i + 1] == -1))) {
                max = Math.max(max, arr[i]);
                min = Math.min(min, arr[i]);
            }
            if (i > 0 && arr[i - 1] != -1 && arr[i] != -1) {
                diff = Math.max(diff, Math.abs(arr[i] - arr[i - 1]));
            }
        }
        //System.out.println(max + " " + min);
        if (min == Integer.MAX_VALUE) {
            return new int[]{diff, 0};
        }
        return new int[]{Math.max(diff, (max - min + 1) / 2), (max + min + 1) / 2};
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
        new Thread(null, new Motaracks_Birthday_1301B(),"Main",1<<27).start();
    }

}
