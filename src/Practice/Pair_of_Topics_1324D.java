import java.io.*;
import java.util.InputMismatchException;
import java.util.Random;

public class Pair_of_Topics_1324D implements Runnable {
    private static void sort(int[] c, int start, int end) {
        if (start >= end) {
            return;
        }
        Random r = new Random();
        int p = r.nextInt(end - start);
        int pivot = c[start + p];
        int left = start;
        int right = end;
        while (left <= right) {
            while (left <= right && c[left] < pivot) {
                left++;
            }
            while (left <= right && c[right] > pivot) {
                right--;
            }
            if (left <= right) {
                int temp = c[left];
                c[left] = c[right];
                c[right] = temp;
                left++;
                right--;
            }
        }
        sort(c, start, right);
        sort(c, left, end);
    }

    private static int find(int[] c, int target) {
        int left = 0;
        int right = c.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (c[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return c[left] >= target ? left : c.length;
    }

    public static void main(String[] args) throws Exception {
        new Thread(null, new Pair_of_Topics_1324D(), "Main", 1 << 27).start();
    }

    @Override
    public void run() {
        InputReader in = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);
        int t = in.nextInt();
        int[] a = new int[t];
        int[] b = new int[t];
        int[] c = new int[t];
        for (int i = 0; i < t; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 0; i < t; i++) {
            b[i] = in.nextInt();
        }
        for (int i = 0; i < t; i++) {
            c[i] = a[i] - b[i];
        }
        sort(c, 0, t - 1);
//        for (int i = 0; i < t; i++) {
//            System.out.println(c[i]);
//        }
        // we want to find out the number of numbers which make the sum larger than 0
        long res = 0;
        for (int i = 0; i < t; i++) {
            if (c[i] > 0) {
                res += t - i - 1;
            } else {
                int index = find(c, -c[i] + 1);
                res += t - index;
            }
        }

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
            } while (!isSpaceChar(c));

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
            } while (!isSpaceChar(c));
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
            } while (!isSpaceChar(c));

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
