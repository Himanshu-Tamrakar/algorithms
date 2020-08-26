package chapter2.section3.algo;

public class QuickHT {
    private static final int CUTT_OFF = 15;
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi;

        while (i < j) {
            while (i <= hi && a[i].compareTo(a[lo]) <= 0 ) {
                if (i == hi) break;
                i++;
            }  
            while (j > lo && a[j].compareTo(a[lo]) >= 0) {
                j--;
            }
            if (j > i) exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable var0 = a[i];
        a[i] = a[j]; a[j] = var0;
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo+CUTT_OFF >= hi) {
            insersionSort(a, lo, hi);
            return;
        }
//        if (lo >= hi) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
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

    private static void insersionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1]) ; j--) {
                exch(a, j-1, j);
            }
        }
    }

    public static void main(String[] args) {
        String item = "QUICKSORTEXAMPLE";
        String[] items = item.split("");
        sort(items);
        assert isSorted(items);
        printArray(items);

        item = "123456789";
        items = item.split("");
        sort(items);
        assert isSorted(items);
        printArray(items);

        item = "987654321";
        items = item.split("");
        sort(items);
        assert isSorted(items);
        printArray(items);

        item = "487311111";
        items = item.split("");
        sort(items);
        assert isSorted(items);
        printArray(items);
    }
}
