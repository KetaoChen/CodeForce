import java.io.*;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;


public class E implements Runnable
{
    private static int getRes(int[] arr, Map<String, Integer> memo) {
        // memo the res.
        // greedy eliminate the smallest number
        int l = arr.length;
        if (l == 1) {
            return 0;
        }
        String s = print(arr);
        if (memo.containsKey(s)) {
            return memo.get(s);
        }

        int min = arr[0];
        for (int i = 1; i < l; i++) {
            min = Math.min(min, arr[i]);
        }

        int[] copy = arr.clone();
        int len = copy.length;

        for (int i = 0; i < l - 1; i++) {
            if (arr[i] == min && arr[i] == arr[i + 1]) {
                arr[i]++;
                arr[i + 1] = -1;
                l--;
            }
        }
        if (l == arr.length) {
            return 0;
        }
        int[] n1 = new int[l];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != -1) {
                n1[index++] = arr[i];
            }
        }

        int res = getRes(n1, memo) + arr.length - l;
        for (int i = 0; i < len - 1; i++) {
            if (copy[i] == min && copy[i] == copy[i + 1]) {
                copy[i]++;
                copy[i + 1] = -1;
                len--;
            }
        }
        int[] n2 = new int[len];
        index = 0;
        for (int i = 0; i < copy.length; i++) {
            if (copy[i] != -1) {
                n1[index++] = copy[i];
            }
        }
        res = Math.max(res, getRes(n2, memo) + copy.length - len);
        memo.put(s, res);
        return res;
    }

    private static String print(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int num : arr) {
            sb.append(num);
            sb.append(',');
        }
        return sb.toString();
    }

    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);
        int t = in.nextInt();
        int[] arr = new int[t];
        for (int i = 0; i < t; i++) {
            arr[i] = in.nextInt();
        }
        Map<String, Integer> memo = new HashMap<>();
        int res = getRes(arr, memo);
        w.println(res);
        w.flush();
        w.close();
    }

    static class InputReader {
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
