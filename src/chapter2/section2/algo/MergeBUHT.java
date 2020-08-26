package chapter2.section2.algo;

public class MergeBUHT {

    private static boolean less(Comparable p, Comparable q) {
        return p.compareTo(q) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

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

    public static void sort(Comparable[] a){
        for (int sz = 1; sz < a.length; sz = sz + sz) {
            for (int lo = 0; lo < a.length; lo += sz + sz) {
                merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, a.length-1));
            }
        }
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
        assert isSorted(items);
        printArray(items);
    }
}
