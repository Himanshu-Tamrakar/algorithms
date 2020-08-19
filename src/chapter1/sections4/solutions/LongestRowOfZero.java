package chapter1.sections4.solutions;

/**
 * Longest row of 0s. Given an N-by-N matrix of 0s and 1s such that in each row no 0 comes before a 1, find the row with the most 0s in O(N) time.
 */
public class LongestRowOfZero {
    public static void main(String[] args) {
        int N = 10;
        int[][] a = new int[][] {
                {0,1,1,1,1,1,1,1,1,1},
                {0,0,1,1,1,1,1,1,1,1},
                {0,0,0,1,1,1,1,1,1,1},
                {0,0,0,0,1,1,1,1,1,1},
                {0,0,0,0,0,1,1,1,1,1},
                {0,0,0,0,0,0,1,1,1,1},
                {0,0,0,0,0,0,0,1,1,1},
                {0,0,0,0,0,0,0,0,1,1},
                {0,0,0,0,0,0,0,0,0,1},
                {0,0,0,0,0,0,0,1,1,1},
        };
        int ans = -1;
        for (int i = 0; i < N; i++) {
            if (a[i][i] == 0) ans = i;
            else break;
        }
        System.out.printf("%d", ans);
    }
}
