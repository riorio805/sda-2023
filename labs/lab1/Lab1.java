import java.io.*;
import java.util.StringTokenizer;

public class Lab1 {
    final private static InputReader in = new InputReader(System.in);
    final private static PrintWriter out = new PrintWriter(System.out);


    public static void main(String[] args) throws IOException {
        // get Testcase amount
        long T = in.nextInt();
        // init variables
        long ans, N;

        // Loop through T testcases
        while(T-- > 0) {
            // get N
            N = in.nextLong();
            // count total, use asr because overflow
            ans = (N*(N+1)) >>> 1;
            // print
            out.println(ans);
        }

        // don't forget to close/flush the output
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
