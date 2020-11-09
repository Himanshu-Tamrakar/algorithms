package chapter5.section1.solutions;

public class FrequencyCountQuick3String {
    public static void sort(String[] a) {
        sort(a, 0, a.length-1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        if (lo == hi) System.out.printf("%s : %d\n", a[lo], 1);
        if (lo >= hi) {
            return;
        }

        int lt = lo;
        int gt = hi;
        int i = lo + 1;

        int v = charAt(a[lo], d);

        while (i <= gt) {
            int t = charAt(a[i], d);
            if (t < v) exch(a, lt++, i++);
            else if(t > v) exch(a, i, gt--);
            else i++;
        }

        sort(a, lo, lt-1, d);
        if (v >= 0) {
            sort(a, lt, gt, d + 1);
        } else {
            System.out.printf("%s : %d\n", a[lt], gt-lt+1);
        }

        sort(a, gt + 1, hi, d);
    }
    private static int charAt(String s, int d) {
        if (d < 0 || d >= s.length()) return -1;
        return s.charAt(d);
    }

    private static void exch(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        String[] arr = new String[] {
                "edu.princeton.cs",
                "com.apple",
                "edu.princeton.cs",
                "com.cnn",
                "com.google",
                "edu.uva.cs",
                "edu.princeton.cs",
                "edu.princeton.cs.www",
                "edu.uva.cs",
                "edu.uva.cs",
                "edu.uva.cs",
                "com.adobe",
                "edu.princeton.cs"
        };

        sort(arr);
    }
}
