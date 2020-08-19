package chapter2.section1.solutions;

public class BinarySearchInsertionHT {
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void sort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            int lo = 0;
            int hi = i-1;

            while(lo<=hi) {
                int mid = lo + (hi-lo)/2;
                if (a[mid].compareTo(a[i]) < 0) lo = mid+1;
                else if (a[mid].compareTo(a[i]) > 0) hi = mid-1;
                else lo = mid;
            }

            Comparable temp = a[i];
            for (int j = i; j > lo ; j--) {
                a[j] = a[j-1];
            }
            a[lo] = temp;
        }
        assert isSorted(a);
        printArray(a);
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
        Integer[] items = new Integer[] {1, 5, 3, 9, 6, 8,4};
        sort(items);
    }
}
