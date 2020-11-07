package chapter5.section1.algo;

public class ThreeWayCharectorQuickSort {
    public static void sort(String[] a) {
        sort(a, 0, a.length-1, 0);
    }

    public static void sort(String[] a, int lo, int hi, int d) {
        if (lo >= hi) return;

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

        sort(a, lo, lt - 1, d);
        if (v >= 0) sort(a, lt, gt, d + 1);
        sort(a, gt + 1, hi, d);
    }

    private static int charAt(String s, int d) {
        if (d < s.length()) return s.charAt(d);
        else return -1;
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
                "edu.princeton.ee"
        };

        sort(arr);

        for (String s: arr) {
            System.out.printf("%s\n", s);
        }
    }
}
