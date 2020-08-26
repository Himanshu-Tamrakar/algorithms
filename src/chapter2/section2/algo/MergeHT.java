package chapter2.section2.algo;

public class MergeHT {
    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid+1;
        Comparable[] aux = new Comparable[a.length];
        for (int k = 0; k < a.length; k++) {
            aux[k] = a[k];
        }

        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }
    }

    private static void inPlaceMerge(Comparable[] a, int lo, int mid, int hi) {
        while (lo < mid && mid <= hi) {
            if (less(a[mid], a[lo])) {
                exch(a, lo, mid);
                for (int i = mid+1; i <= hi && less(a[i], a[i-1]); i++) {
                    exch(a, i, i-1);
                }
            }
            lo++;
        }
    }

    private static boolean less(Comparable p, Comparable q) {
        return p.compareTo(q) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length-1);
    }

    public static void sort(Comparable[] a, int lo, int hi){
        if (lo >= hi) return;
        int mid = lo + (hi -lo)/2;
        sort(a, lo, mid);
        sort(a, mid+1, hi);
        merge(a, lo, mid, hi);
    }
    public static void printArray(Comparable[] a) {
        System.out.printf("Sorted array\n");
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
        Integer[] items = new Integer[] {1, 3, 5, 9, 4, 6, 8};
        sort(items);
//        inPlaceMerge(items, 0, 4, 6);
        assert isSorted(items);
        printArray(items);
    }


}
