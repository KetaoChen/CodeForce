import java.io.*;
import java.util.InputMismatchException;


public class E implements Runnable
{
    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        w = new PrintWriter(System.out);
        t = in.nextInt();
        for (int i = 0; i < t; i++) {
            n = in.nextInt();
            k = in.nextInt();
            w.println(getRes());
        }

        w.flush();
        w.close();
    }

    static PrintWriter w;
    static int t, k, n;
    static Integer[] arr;

    private static String getRes() {
        if (k * (k + 1) / 2 > n) return "-1";

        int mid = n / (k + 1);
        int start = mid - (k + 1) / 2;

        System.out.println(mid);
        System.out.println(mid - k / 2);
        for (int i = 0; i <= 9; i++) {
            if (check(start)) return convert(start);
            start++;
        }

        return "-1";
    }

    private static boolean check(int num) {

        int sum = 0;
        String s = convert(num);
        System.out.println(num + " " + s);
        for (int i = 0; i <= k; i++) {
            for (char c : s.toCharArray()) {
                sum += c - '0';
            }
            s = add(s);
        }

        return sum == n;
    }

    private static String convert(int num) {
        if (num == 0) return "0";
        StringBuilder sb = new StringBuilder();
        while (num >= 9) {
            sb.append(9);
            num -= 9;
        }
        if (num > 0) sb.append(num);
        return sb.reverse().toString();
    }

    private static String add(String s) {
        StringBuilder sb = new StringBuilder();
        int carry = 1;
        int i = s.length() - 1;
        while (i >= 0 || carry > 0) {
            int sum = i >= 0 ? s.charAt(i--) - '0' + carry : carry;
            carry = sum / 10;
            sb.append(sum % 10);
        }
        return sb.reverse().toString();
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
        new Thread(null, new E(),"Main",1<<27).start();
    }

}
