package chapter2.section2.solutions;

import edu.princeton.cs.algs4.Inversions;

public class InversionSolution19 {
    private static final int CUTOFF = 7;
    private static int inversions = 0;

    private static void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              dst[k] = src[j++];
            else if (j > hi)               dst[k] = src[i++];
            else if (less(src[j], src[i])) {
                dst[k] = src[j++];
                inversions += mid - i + 1;
            }
            else                           dst[k] = src[i++];
        }
    }
    public static void sort(Comparable[] a) {
        Comparable[] aux = a.clone();
        sort(aux, a, 0, a.length-1);
    }

    private static void sort(Comparable[] src, Comparable[] dst, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi-lo)/2;
        sort(dst, src, lo, mid);
        sort(dst, src, mid+1, hi);

        if (less(src[mid], src[mid+1])) {
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
            return;
        }
        merge(src, dst, lo, mid, hi);
    }

    private static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable var0 = a[i];
        a[i] = a[j]; a[j] = var0;
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
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
        String input = "EGA";
        String[] items = input.split("");
        sort(items);
        assert isSorted(items);
        printArray(items);
        System.out.printf("\nTotal number of inversions are %d\n", inversions);
//        Inversions.main(new String[] {"4", "5", "1"});

    }
}
