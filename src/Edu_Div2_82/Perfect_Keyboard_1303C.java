package Edu_Div2_82;

import java.io.*;
import java.util.*;
import java.lang.*;


public class Perfect_Keyboard_1303C implements Runnable
{
    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);
        int q = Integer.parseInt(in.nextLine()); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 0; i < q; i++) {
            String s = in.nextLine();


            getRes(s, w);
        }
        w.flush();
        w.close();
    }

    static class Node {
        char c;
        Node prev;
        Node next;
        public Node(char ch) {
            c = ch;
        }
    }

    private static void getRes(String s, PrintWriter w) {
        //whether the string can be 1 or 2 palindrome
        Node first = new Node(s.charAt(0));
        Node cur = first;

        Map<Character, Node> map = new HashMap<>();
        map.put(s.charAt(0), first);

        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            //System.out.println(c);
            if (cur.next != null && cur.next.c == c) {
                cur = cur.next;
                continue;
            }
            if (cur.prev != null && cur.prev.c == c) {
                cur = cur.prev;
                continue;
            }
            if (map.containsKey(c) || (cur.prev != null && cur.next != null)) {
                w.println("NO");
                return;
            }

            if (cur.next == null) {
                Node next = new Node(c);
                map.put(c, next);
                cur.next = next;
                next.prev = cur;
                cur = cur.next;
                continue;
            }

            if (cur.prev == null) {
                Node prev = new Node(c);
                map.put(c, prev);
                cur.prev = prev;
                prev.next = cur;
                cur = cur.prev;
                continue;
            }
        }

        StringBuilder sb = new StringBuilder();

        while (cur.prev != null) {
            cur = cur.prev;
        }
        while (cur != null) {
            sb.append(cur.c);
            cur = cur.next;
        }
        for (int i = 0; i < 26; i++) {
            if (!map.containsKey((char) ('a' + i))) {
                sb.append((char) ('a' + i));
            }
        }
        w.println("YES");
        w.println(sb.toString());
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
        new Thread(null, new Perfect_Keyboard_1303C(),"Main",1<<27).start();
    }

}
