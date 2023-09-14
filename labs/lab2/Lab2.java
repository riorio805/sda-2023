import java.io.*;
import java.util.StringTokenizer;


public class Lab2 {
    final private static InputReader in = new InputReader(System.in);
    final private static PrintWriter out = new PrintWriter(System.out);


    public static void main(String[] args) throws IOException {
        // get sequence length
        long N = in.nextInt();
        // init variables
        boolean ssExist = false;    // is there a valid subsequence found yet
        long ans=0, num;     // answer and next numbers
        long mxmsum = 0, cursum = 0;
        long count = N;

        long maxnum = Long.MIN_VALUE;

        while (count-- > 0) {
            num = in.nextLong();
            // check if next has same parity
            if ( ( num - N) % 2 == 0) {
                maxnum = Math.max(num, maxnum);
                cursum += num;
                mxmsum = Math.max(cursum, mxmsum);
                cursum = Math.max(cursum, 0);
                ssExist = true;
            }
            // if not, end subsequence, reset count
            else {
                ans = Math.max(ans, mxmsum);
                mxmsum = cursum = 0;
            }
        }
        if (maxnum < 0 && ssExist) ans = maxnum;
        else ans = Math.max(ans, mxmsum);


        // print answer, don't forget to close output
        out.println(ans);
        out.close();
    }


    // taken from https://codeforces.com/submissions/Petr
    // together with PrintWriter, these input-output (IO) is much faster than the usual Scanner(System.in) and System.out
    // please use these classes to avoid your fast algorithm gets Time Limit Exceeded caused by slow input-output (IO)
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}
