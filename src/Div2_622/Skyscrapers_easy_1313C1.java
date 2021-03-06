package Div2_622;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Stack;


public class Skyscrapers_easy_1313C1 implements Runnable {
    private static void getRes(PrintWriter w, int[] arr) {
        int[] nextSmallFromLeft = new int[arr.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < arr.length; i++) {
            while (stack.peek() != -1 && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            nextSmallFromLeft[i] = stack.peek();
            stack.push(i);
        }

        while (stack.isEmpty()) {
            stack.pop();
        }
        stack.push(arr.length);
        int[] nextSmallFromRight = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            while (stack.peek() != arr.length && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            nextSmallFromRight[i] = stack.peek();
            stack.push(i);
        }

        long[] valFromLeft = new long[arr.length];
        for (int i = 0; i < arr.length; i++) {
            long dp = nextSmallFromLeft[i] == -1 ? 0 : valFromLeft[nextSmallFromLeft[i]];
            valFromLeft[i] = dp + (long) (i - nextSmallFromLeft[i]) * (long) arr[i];
        }

        long[] valFromRight = new long[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            long dp = nextSmallFromRight[i] == arr.length ? 0 : valFromRight[nextSmallFromRight[i]];
            valFromRight[i] = dp + (long) (nextSmallFromRight[i] - i) * (long) arr[i];
        }

        long max = 0L;
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            long sum = valFromLeft[i] + valFromRight[i] - arr[i];
            if (sum > max) {
                max = sum;
                index = i;
            }
        }

        int[] res = new int[arr.length];
        int m = arr[index];
        res[index] = m;
        for (int j = index - 1; j >= 0; j--) {
            m = arr[j] <= m ? arr[j] : m;
            res[j] = m;
        }
        m = arr[index];
        for (int j = index + 1; j < arr.length; j++) {
            m = arr[j] <= m ? arr[j] : m;
            res[j] = m;
        }

        for (int i = 0; i < res.length; i++) {
            w.println(res[i] + " ");
        }

    }

    public static void main(String[] args) throws Exception {
        new Thread(null, new Skyscrapers_easy_1313C1(), "Main", 1 << 27).start();
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

        getRes(w, arr);
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

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1)
                throw new InputMismatchException();

            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }

                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        public int nextInt() {
            int c = read();

            while (isSpaceChar(c))
                c = read();

            int sgn = 1;

            if (c == '-') {
                sgn = -1;
                c = read();
            }

            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            while (!isSpaceChar(c));

            return res * sgn;
        }

        public long nextLong() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;

            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            while (!isSpaceChar(c));
            return res * sgn;
        }

        public double nextDouble() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            double res = 0;
            while (!isSpaceChar(c) && c != '.') {
                if (c == 'e' || c == 'E')
                    return res * Math.pow(10, nextInt());
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            if (c == '.') {
                c = read();
                double m = 1;
                while (!isSpaceChar(c)) {
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

        public String readString() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            }
            while (!isSpaceChar(c));

            return res.toString();
        }

        public boolean isSpaceChar(int c) {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public String next() {
            return readString();
        }

        public interface SpaceCharFilter {
            boolean isSpaceChar(int ch);
        }
    }

}
