package chapter2.section3.solutions;

import edu.princeton.cs.algs4.StdRandom;

public class NutsAndBoltsSolution13 {

    public static void sort(Comparable[] nuts, Comparable[] bolts) {
        sort(nuts, bolts, 0, nuts.length-1);
    }

    private static void sort(Comparable[] nuts, Comparable[] bots, int lo, int hi) {
        if (lo >= hi) return;
        int j = partition(nuts, bots[lo], lo, hi);
        partition(bots, nuts[j], lo, hi);
        sort(nuts, bots, lo, j-1);
        sort(nuts, bots, j+1, hi);
    }

    private static int partition(Comparable[] a, Comparable pivit, int lo, int hi) {
        int i = lo;
        int j = hi;
        int exhangeWith = -1;

        for (int k = lo; k <= hi; k++) {
            if (a[k].compareTo(pivit) == 0) {
                exhangeWith = k;
            }
        }

        if (exhangeWith == -1) {
            throw new ArrayIndexOutOfBoundsException("There is some problem with the partitions earlier! Check the algorithms");
        }

        while (true) {
            while (i <= hi && a[i].compareTo(pivit) <= 0 ) {
                if (i == hi) break;
                i++;
            }
            while (j > lo && a[j].compareTo(pivit) >= 0) {
                j--;
            }
            if (i >= j) break;
            exch(a, i, j);
        }

        if (exhangeWith >= i) {
            exch(a, i, exhangeWith);
            return i;
        } else if (exhangeWith <= j) {
            exch(a, j, exhangeWith);
            return j;
        } else {
            return exhangeWith;
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
//        Integer[] nuts = new Integer[] {5, 9, 7, 8, 4, 3, 1, 6, 2};
//        Integer[] bots = new Integer[] {7, 4, 5, 3, 1, 6, 8, 9, 2};
//        sort(nuts, bots, 0, nuts.length-1);


        for (int l = 0; l < 100; l++) {
            System.out.printf("%d. Start\n", l);
            Integer[] nuts = new Integer[] {6,4,7,2,9,5,1,8,3};
            Integer[] bots = new Integer[] {5,6,2,1,4,3,9,7,8};
            StdRandom.shuffle(nuts);
            StdRandom.shuffle(bots);

            for (int i = 0; i < nuts.length; i++) {
                System.out.printf("%d ", nuts[i]);
            }
            System.out.printf("\n");
            for (int i = 0; i < bots.length; i++) {
                System.out.printf("%d ", bots[i]);
            }


            sort(nuts, bots, 0, nuts.length-1);
            System.out.printf("\n");
            for (int i = 0; i < nuts.length; i++) {
                System.out.printf("%d ", nuts[i]);
            }
            System.out.printf("\n");
            for (int i = 0; i < bots.length; i++) {
                System.out.printf("%d ", bots[i]);
            }
            System.out.printf("\n%d. End\n", l);
            assert isSorted(nuts);
            assert isSorted(bots);
        }
        System.out.printf("Success");

    }
}
