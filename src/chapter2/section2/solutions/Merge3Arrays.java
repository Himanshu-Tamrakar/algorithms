package chapter2.section2.solutions;

import java.util.Arrays;

public class Merge3Arrays {
    public static void merge(Comparable[] a, int lo, int mid, int hi1, int hi2) {
        merge(a, lo, mid, hi1);
        merge(a, lo, hi1, hi2);
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        Comparable[] aux = new Comparable[a.length];
        for (int i = 0; i < a.length; i++) {
            aux[i] = a[i];
        }

        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable var0 = a[i];
        a[i] = a[j]; a[j] = var0;
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void main(String[] args) {
        String input = "EEGMRORSTAEELMPPX";
        String[] var1 = input.split("");

        merge(var1, 0, 4, 8, 16);
        for (String s: var1) {
            System.out.printf("%s", s);
        }

    }
}
