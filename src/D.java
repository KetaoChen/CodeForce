import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;


public class D implements Runnable
{
    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);
        String[] strs = in.nextLine().split(" ");
        int n = Integer.parseInt(strs[0]), k = Integer.parseInt(strs[1]);
        String s = in.nextLine();
        getRes(s, k, w);

        w.flush();
        w.close();
    }

    private static void getRes(String s, int k, PrintWriter w) {
        int l = s.length();
        int[] arr = new int[l];
        int max = 0, first = 0;
        for (int i = 0; i < l; i++) {
            arr[i] = s.charAt(i) == 'R' ? 1 : 0;
            if (arr[i] == 0) {
                max += i - first++;
            }
        }

        List<List<Integer>> list = calMin(arr);
        int min = list.size();
        // System.out.println(min + " " + max);
        if (k < min || k > max) {
            w.println(-1);
            return;
        }

//        for (List<Integer> lis : list) {
//            for (int num : lis) {
//                System.out.print(num + " ");
//            }
//            System.out.println();
//        }

        // System.out.println("This is the end of print");

        int row = 0, col = 0;
        while (max > k) {
            int len = list.get(row).size();
            int count = Math.min(len - col, max - k + 1);
            max -= count;
            k--;
            w.print(count+ " ");
            for (int i = 0; i < count; i++, col++) {
                w.print(list.get(row).get(col) + " ");
            }
            w.println();
            if (col == len) {
                row++;
                col = 0;
            }
        }

        for (; row < list.size();) {
            int len = list.get(row).size();
            for (; col < len; col++) {
                w.println(1 + " " + list.get(row).get(col));
            }
            if (col == len) {
                row++;
                col = 0;
            }
        }

    }

    private static List<List<Integer>> calMin(int[] arr) {
        List<List<Integer>> res = new ArrayList<>();
        int l = arr.length;
        while (true) {
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < l - 1; i++) {
                if (arr[i] == 1 && arr[i + 1] == 0) {
                    arr[i] = 0;
                    arr[i + 1] = 1;
                    i++;
                    temp.add(i);
                }
            }
            if (temp.size() == 0) {
                break;
            }
            res.add(temp);
        }
        return res;
    }



    // the base is n. The prime mod is mod.
    final static int p =(int) (1e9 + 7);
    public static long[] getInvArray(int n) {
        long[] inv = new long[n + 1];
        inv[1] = 1;
        for (int i = 2; i <= n; i++) {
            inv[i] = ((p - p / i) * inv[p % i] % p + p) % p;
        }
        return inv;
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
