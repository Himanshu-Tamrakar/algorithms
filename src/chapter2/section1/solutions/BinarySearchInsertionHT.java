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
            for (int j = i-1; j >= 0 ; j--) {
                if (less(a[j+1], a[j])) exch(a, j+1, j);
                else break;
            }
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
