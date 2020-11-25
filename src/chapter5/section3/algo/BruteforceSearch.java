package chapter5.section3.algo;

public class BruteforceSearch {
    public static int search(String txt, String pat) {
        int N = txt.length();
        int M = pat.length();
        int i = 0; int j = 0;
        for (; j < M && i < N-M; i++) {
            if (txt.charAt(i) == pat.charAt(j)) {
                j++;
            }
            else {
                i -= j;
                j = 0;
            }
        }
        if (j == M) {
            return i - j;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        String txt = "suppose\n" +
                "that you search for the pattern pattern in the text of this paragraph. There are 191\n" +
                "characters up to the end of the first occurrence of the pattern, only 7 of which are the\n" +
                "character p (and there are no occurrences of pa ), so the total number of character com-\n" +
                "pares is 191+7, for an average of 1.036 compares per character in the text. On the other\n" +
                "hand, there is no guarantee that the algorithm will always be so efficient. For example, a\n" +
                "pattern might begin with a long string of A s. If it does, and the text also has long strings\n" +
                "of A s, then the substring search";

        String pattern = "pattern";

        System.out.printf("Sub string starts from %d\n", BruteforceSearch.search(txt, pattern));
        System.out.printf("Sub string starts from %d\n", BruteforceSearch.search(txt, "dungeon"));
    }
}
