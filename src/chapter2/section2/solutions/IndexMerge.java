package chapter2.section2.solutions;

public class IndexMerge {
    public static void merge(Comparable[] a, int[] index, int[] aux, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            aux[i] = index[i];
        }
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) index[k] = aux[j++];
            else if (j > hi) index[k] = aux[i++];
            else if (less(a[aux[j]], a[aux[i]])) index[k] = aux[j++];
            else index[k] = aux[i++];
        }
    }

    public static void sort(Comparable[] a) {
        int[] index = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            index[i] = i;
        }
        int[] aux = new int[a.length];
        sort(a, index, aux, 0, a.length-1);
        printArray(a, index);
    }

    private static void sort(Comparable[] a, int[] index, int[] aux, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi-lo)/2;
        sort(a, index, aux, lo, mid);
        sort(a, index, aux, mid+1,  hi);
        merge(a, index, aux, lo, mid, hi);
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable var0 = a[i];
        a[i] = a[j]; a[j] = var0;
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
    public static void printArray(Comparable[] a, int[] index) {
        System.out.printf("Sorted array\n");
        for (int i = 0; i < a.length; i++) {
            System.out.printf("%s", a[index[i]]);
        }
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i-1])) return false;
        }
        return true;
    }


    public static void main(String[] args) {
        String input = "MERGESORTEXAMPLE";
        String[] items = input.split("");
        sort(items);
    }
}
