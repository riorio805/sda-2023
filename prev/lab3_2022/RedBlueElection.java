import java.io.*;
import java.util.StringTokenizer;


public class RedBlueElection {
    final static InputReader in = new InputReader(System.in);
    final static PrintWriter out = new PrintWriter(System.out);


    // O(n^2), worst case when islands are all blue
    static int maxRedHelper (char[] islands, int start) {
        // Check islands is empty, return 0 (base case)
        if (start >= islands.length - 1) {
            return 0;
        }
        int next = -1;
        int total = 0;

        // loop through once, take the most right s.t. red > blue

        for (int i = start; i < islands.length; i++) {
            if (islands[i] == 'R') total++;
            else total--;
            if (total > 0) {
                next = i + 1;
            }
        }

        // calculate the rest using recursion
        // If no red > blue, take 1 from left
        if (next == -1) return maxRedHelper(islands, ++start);
        else return (next - start + maxRedHelper(islands, next));
    }

    static int maxRed (char[] islands) {
        return maxRedHelper(islands, 0);
    }

    public static void main(String[] args) throws IOException {
        int ans;
        int N = in.nextInt();
        char[] islands = new char[N];

        for (int i = 0; i < N; i++) {
            islands[i] = in.nextChar();
        }

        ans = maxRed(islands);
        // output
        out.println(ans);
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

        public char nextChar() {
            return next().charAt(0);
        }
    }
}