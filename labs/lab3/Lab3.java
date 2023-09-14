import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.ArrayDeque;

public class Lab3 {
    final private static InputReader in = new InputReader(System.in);
    final private static PrintWriter out = new PrintWriter(System.out);
    private static final ArrayDeque<ArrayDeque<Integer>> buildings = new ArrayDeque<>();
    private static final ArrayDeque<Integer> tops = new ArrayDeque<>();
    private static final ArrayDeque<long[]> totals = new ArrayDeque<>();
    private static boolean faceRight = true, hasWon = false;

    // Standard implementation, O(min(Si, C))
    static long Ssss(int Si){
        ArrayDeque<Integer> currentBuilding = buildings.pollFirst();

        // Process
        long points = 0;
        while (!currentBuilding.isEmpty() && Si-- > 0) {
            points += currentBuilding.pollFirst();
        }

        // kanan
        if (faceRight && !currentBuilding.isEmpty()) {
            buildings.addLast(currentBuilding);
        }
        // kiri
        if (!faceRight && !currentBuilding.isEmpty()) {
            buildings.addFirst(currentBuilding);
        }
        if (!faceRight && buildings.size() > 1) {
            buildings.addFirst(buildings.pollLast());
        }

        return points;
    }

    // Optimized implementation, O(1)
    static long S(int Si){
        // top: how many floors are left
        // total: list, total[n] = how many points gained if n floors destroyed
        Integer top = tops.pollFirst();
        long[] total = totals.pollFirst();
        long points;

        // Calculate total points, update top
        // check top <= Si (building will be destroyed)
        // points = (Next points - current points) for the building
        if (top  <= Si) {
            points = total[total.length - 1] - total[total.length - top - 1];
            top = 0;
        }
        else {
            points = total[total.length - top - 1 + Si] - total[total.length - top - 1];
            top -= Si;
        }

        // If building is not empty, put back in the original spot
        if (top != 0) {
            if (faceRight) {
                tops.addLast(top);
                totals.addLast(total);
            }
            else {
                tops.addFirst(top);
                totals.addFirst(total);
            }
        }
        // Check left and able to be moved, move back to front
        if (!faceRight && totals.size() > 1) {
            tops.addFirst(tops.pollLast());
            totals.addFirst(totals.pollLast());
        }

        return points;
    }


    public static void main(String[] args) throws IOException {
        // Read input
        long T = in.nextLong();
        int X = in.nextInt();
        int C = in.nextInt();
        int Q = in.nextInt();

        for (int i = 0; i < X; i++) {
//            long[] temp = new long[C+1];
            ArrayDeque<Integer> temp2 = new ArrayDeque<>();
//            temp[0] = 0;
//            long total = 0;
            // Insert into ADT
            for (int j = 1; j <= C; j++) {
                int next = in.nextInt();
//                total += next;
                temp2.addLast(next);
//                temp[j] = total;
            }
//            tops.addLast(C);
//            totals.addLast(temp);
            buildings.addLast(temp2);
        }


        // Process the query
        for (int i = 0; i < Q; i++) {
            String perintah = in.next();
            if (perintah.equals("GA")) {
                faceRight = !faceRight;
                out.println( faceRight ? "KANAN" : "KIRI" );
            } else if (perintah.equals("S")) {
                int Si = in.nextInt();
                if (hasWon) {
                    out.println("MENANG");
                    continue;
                }

                long res = Ssss(Si);
                T -= res;
                if (T <= 0 || buildings.isEmpty()) {
                    out.println("MENANG");
                    hasWon = true;
                    continue;
                }
                out.println(res);
            }
        }

        // print answer, don't forget to close output
        out.close();
    }


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

