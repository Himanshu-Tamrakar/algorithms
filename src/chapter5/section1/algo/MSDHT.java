package chapter5.section1.algo;

public class MSDHT {
    static String[] aux;
    public static void sort(String[] a) {
        aux = new String[a.length];
        sort(a, 0, a.length-1, 0);
    }

    public static void sort(String[] a, int lo, int hi, int index) {
        if (lo >= hi) {
            return;
        }

        int[] count = new int[256 + 2];
        // +2 because, we are making -1 from charAt to zero. So need to store data at 1 not at zero
        for (int i = lo; i <= hi; i++) {
            count[charAt(a[i], index) + 2]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i-1];
        }

        // +1 not -1 as in key index because we are making it +2 in line 15:
        for (int i = lo; i <= hi; i++) {
            aux[count[charAt(a[i], index) + 1]++] = a[i];
        }

        for (int i = lo; i <= hi; i++) {
            a[i] = aux[i - lo];
        }

        // Recursion: Start
        for (int i = 0; i < count.length-1; i++) {
            sort(a, lo + count[i], lo + count[i + 1] - 1, index + 1);
        }
        // Recursion: End
    }

    private static int charAt(String s, int index) {
        if (index >= s.length()) {
            return -1;
        }
        return s.charAt(index);
    }

    public static void main(String[] args) {
        String[] arr = new String[] {
                "she",
                "sells",
                "seashells",
                "by",
                "the",
                "sea",
                "shore",
                "the",
                "shells",
                "she",
                "sells",
                "are",
                "surely",
                "seashells"
        };

        sort(arr);
        for (String s: arr) {
            System.out.printf("%s\n", s);
        }
    }
}
