package chapter2.section2.solutions;

public class FasterMergeSolu10 {
//    private static Comparable[] aux;
    private static boolean less(Comparable p, Comparable q) {
        return p.compareTo(q) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        Comparable[] aux = new Comparable[a.length];
        for (int i = 0; i < a.length; i++) {
            aux[i] = a[i];
        }

        for (int i = mid+1; i <= hi; i++) {
            aux[i] = a[hi-(i-mid)+1];
        }

        int i = lo, j = hi;
        while (lo <= hi) {
            if (less(aux[lo], aux[hi])) a[i++] = aux[lo++];
            else a[i++] = aux[hi--];
        }
    }

    public static void sort(Comparable[] a) {
//        aux = new Comparable[a.length];
//        for (int i = 0; i < a.length; i++) {
//            aux[i] = a[i];
//        }
        sort(a, 0, a.length-1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi-lo)/2;
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

    public  static void main(String[] args) {
        String input = "MERGESORTEXAMPLE";
        String[] items = input.split("");
        sort(items);
        assert isSorted(items);
        printArray(items);
    }
}
