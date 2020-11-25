package chapter5.section3.algo;

public class BoyerMooreHT {
    private int[] right;
    private final int R = 256;
    private String pattern;
    public BoyerMooreHT(String pattern) {
        this.pattern = pattern;
        right = new int[R];
        for (int i = 0; i < R; i++) {
            right[i] = -1;
        }

        /**
         * Construct right array
         */

        for (int i = 0; i < pattern.length(); i++) {
            right[pattern.charAt(i)] = i;
        }

    }

    public int search(String txt) {
        int N = txt.length();
        int M = pattern.length();
        int skip = 0;
        int i, j;
        for (i = 0, j = M-1; i <= N-M && j >= 0; i += skip) {
            if (txt.charAt(i + j) == pattern.charAt(j)) {
                j--;
                skip = 0;
            } else {
                skip = Math.max(1, j - right[txt.charAt(i + j)]);
            }


        }
        if ( j < 0) return i;
        else return -1;
    }

    public static void main(String[] args) {
//        String pattern = "ATTACK AT DOWN";
//        String txt = "THE BASIC IDEA BEHIND THE ATTACK AT DOWN ALGORITHM DISCOVERED BT KNUTH, MORRIS AND PRATTIS THIS: WHENEVER WE DERTECT A MISMATCH, WE ALREADY KNOW SOME OF THE CHARACTERS IN THE TEXT(SINCE THEU MATCHED THE PATTERN CHARACTORS PRIOR TOT HE MISMATCH).WE CAN TAKE ADVANTAGE OG THIS INFORMATION TO AVIOD BACKTRACKING UP THE TEXT POINTER OVER ALL THOSE KNOWN CHARACTERS.";
        String pattern = "AA";
        String txt = "KAAA";
        BoyerMooreHT boyerMooreHT = new BoyerMooreHT(pattern);

        System.out.printf("%s Present at %d \n", pattern, boyerMooreHT.search(txt));
    }
}
