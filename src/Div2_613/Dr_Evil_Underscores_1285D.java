package Div2_613;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;


public class Dr_Evil_Underscores_1285D implements Runnable
{
    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);
        int N = in.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = in.nextInt();
        }
        getRes(arr, w);

        w.flush();
        w.close();
    }

    static class Node {
        Node[] son;
        public Node() {
            son = new Node[2];
        }
    }

    private static void getRes(int[] arr, PrintWriter w) {
        Node root = new Node();
        for (int num : arr) {
            Node cur = root;
            for (int i = 31; i >= 0; i--) {
                int d = num >> i & 1;
                if (cur.son[d] == null) cur.son[d] = new Node();
                cur = cur.son[d];
            }
        }

        int[] res = helper(root, 31);
        w.println(res[0] + " " + res[1]);
    }

    private static int[] helper(Node root, int bit) {
        if (bit == -1) return new int[]{0, 0};
        if (root.son[0] == null) {
            int[] temp = helper(root.son[1], bit - 1);
            // System.out.println(bit + " 0 is null");
            return new int[]{temp[0], (1 << bit) + temp[1]};
        }
        if (root.son[1] == null) {
            int[] temp = helper(root.son[0], bit - 1);
            // System.out.println(bit + " 1 is null");
            return new int[]{temp[0], temp[1]};
        }
        int[] left = helper(root.son[0], bit - 1);
        int[] right = helper(root.son[1], bit - 1);
        // System.out.println(left[1] + " " + right[1]);
        if (left[0] < right[0]) {
            return new int[]{left[0] + (1 << bit), (1 << bit) + left[1]};
        }
        return new int[]{right[0] + (1 << bit), right[1]};
    }

    private static void getRes2(int[] arr, PrintWriter w) {
        List<Integer> list = new ArrayList<>();
        int max = 0;
        for (int num : arr) {
            list.add(num);
            int len = 0;
            while (num > 0) {
                len++;
                num >>= 1;
            }
            max = Math.max(max, len);
        }
        if (max == 0) {
            w.println(0);
            return;
        }
        // System.out.println(3 >> 4 & 1);
        int res = helper2(list, max - 1);

        w.println(res);
    }

    private static int helper2(List<Integer> list, int digit) {
        if (list.size() == 0) return Integer.MAX_VALUE;

        List<Integer> list0 = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        for (int num : list) {
            if ((num >> digit & 1) == 1) list1.add(num);
            else list0.add(num);
        }

        int res = (list0.size() == 0 || list1.size() == 0) ? 0 : 1 << digit;
        if (digit == 0) return res;

        // System.out.println(digit + " " + list0.size() + " " + list1.size());
        return res + Math.min(helper2(list1, digit - 1), helper2(list0, digit - 1));
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
        new Thread(null, new Dr_Evil_Underscores_1285D(),"Main",1<<27).start();
    }

}
