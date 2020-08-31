package chapter2.section5.solutions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Soluton16_California {

    private static final Comparator<String> CANDIDATE_COMPARATOR = new CandidateComparator();
    private static class CandidateComparator implements Comparator<String> {
        private final String ordering = "RWQOJMVAHBSGZXNTCIEKUPDYFL";

        @Override
        public int compare(String s, String t1) {
            int n = Math.min(s.length(), t1.length());
            for (int i = 0; i < n; i++) {
                int i1 = ordering.indexOf(s.charAt(i));
                int i2 = ordering.indexOf(t1.charAt(i));
                if (i1 > i2) return 1;
                else if (i1 < i2) return -1;
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] candidates = "a b c d e f g h i j k l m n o p q r s t u v w x y z".toUpperCase().split(" ");
        int n = candidates.length;
        Arrays.sort(candidates, Soluton16_California.CANDIDATE_COMPARATOR);
        for (int i = 0; i < n; i++)
            System.out.printf(candidates[i]);
    }
}
