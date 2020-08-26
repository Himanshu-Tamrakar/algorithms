package chapter2.section3.solutions;

public class Solution5 {
    public static void sort(Comparable[] a) {
        int lo = 0, hi = a.length-1;
        int lt = lo, gt = hi, i = lo+1;

        Comparable v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) {
                exch(a, lt++, i++);
            } else if (cmp > 0) {
                exch(a, i, gt--);
            } else {
                i++;
            }
        }

        for (int j = 0; j < a.length; j++) {
            System.out.printf("%s", a[j]);
        }

    }
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable var0 = a[i];
        a[i] = a[j]; a[j] = var0;
    }

    public static void main(String[] args) {
        //Only two distinct keys
//        String[] items = new String[] { "2", "1", "1", "2", "1", "1", "1", "2"};
//        String[] items = new String[] { "2", "1", "1", "1", "1", "1", "1", "2"};
        String[] items = new String[] { "2", "1", "1", "2", "1", "1", "1", "2"};
//        String[] items = new String[] { "1", "1", "1", "1", "1", "1", "1", "1"};
        sort(items);
    }
}
