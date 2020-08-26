package chapter2.section3.algo;

public class Quick3HT {

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int lt = lo;
        int gt = hi;
        int i = lo + 1;
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

        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable var0 = a[i];
        a[i] = a[j]; a[j] = var0;
    }

    public static void printArray(Comparable[] a) {
        System.out.printf("\nSorted array\n");
        for (int i = 0; i < a.length; i++) {
            System.out.printf("%s ", a[i]);
        }
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i-1])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String input = "1211111";
        String[] items = input.split("");
        sort(items);
        assert isSorted(items);
        printArray(items);
    }
}
