import java.io.*;
import java.util.StringTokenizer;


public class FindSofita {
    private static InputReader in;
    private static PrintWriter out;


    public static void main(String[] args) throws IOException {
        in = new InputReader(System.in);
        out = new PrintWriter(System.out);

        // init letter count array and answer
        int[] sofitaNum = {0, 0, 0, 0, 0, 0};
        long ans;

        // Read value of N
        int N = in.nextInt();

        // Read next value, check if one of "SOFITA"
        for (int i = 0; i < N; ++i) {
            // Ambil char berikutnya
            char next = in.nextChar();
            switch (next) {
                case 'S' -> sofitaNum[0]++;
                case 'O' -> {
                    if (sofitaNum[0] != 0) {sofitaNum[0]--; sofitaNum[1]++;}
                }
                case 'F' -> {
                    if (sofitaNum[1] != 0) {sofitaNum[1]--; sofitaNum[2]++;}
                }
                case 'I' -> {
                    if (sofitaNum[2] != 0) {sofitaNum[2]--; sofitaNum[3]++;}
                }
                case 'T' -> {
                    if (sofitaNum[3] != 0) {sofitaNum[3]--; sofitaNum[4]++;}
                }
                case 'A' -> {
                    if (sofitaNum[4] != 0) {sofitaNum[4]--; sofitaNum[5]++;}
                }
            }
        }

        // Count how many "sofita"s possible, remove from N
        ans = N - (6L * sofitaNum[5]);


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