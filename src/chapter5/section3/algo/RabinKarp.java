package chapter5.section3.algo;

public class RabinKarp {
    private final int M = 10;
    private final int Q = 997;

    public int search(String txt, String pattern) {
        int M = pattern.length();
        int N = txt.length();
        int patHash = hash(pattern, M);
        int txtHash = hash(txt, M);
        if (patHash == txtHash) return 0;

        // Check booksite
        return -1;
    }

    private int hash(String txt, int M) {
        int h = 0;
        for (int i = 0; i < M; i++) {
            h = (M * h + txt.charAt(i)) % Q;
        }
        return h;
    }
}
