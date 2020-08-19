package chapter2.section1.algo;

public class ShellHT {
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void sort(Comparable[] a) {
       int h = 1;
       int n = a.length;
       while (h*3 < a.length/3) h = h*3 + 1;

       while (h >= 1) {
           for (int i = h; i < n; i++) {
               for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) {
                   exch(a, j, j-h);
               }
           }
           h /= 3;
//           for (int i = a.length-1; (i-h) >= 0 ; i--) {
//               int j = i;
//               while ((j-h) >= 0) {
//                   if (less(a[j], a[j-h])) exch(a, j, j-h);
//                   j = j-h;
//               }
//
//           }

       }
       printArray(a);
       assert isSorted(a);

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
        String[] items = new String[] {"S", "H", "E", "L", "L", "S", "O", "R", "T", "E", "X", "A", "P", "L", "E"};
        sort(items);
    }
}
