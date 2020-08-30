package chapter2.section4.algo;


public class HeapSortHT<Item extends Comparable<Item>> {

    public static void sort(Comparable[] items) {
        int n = items.length-1;
        for (int i = n/2; i >= 1; i--) {
            sink(items, i, n);
        }

        int k = n;
        while (k > 1){
            exch(items, 1, k--);
            sink(items, 1, k);
        }
    }

    /***********************************************************************
     * Helper Methods
     ***********************************************************************/
    private static void sink(Comparable[]pq, int x, int n) {
        while (2*x <= n) {
            int j = 2*x;
            if (j < n && greater(pq, j, j+1)) j++;
            if (!greater(pq, x, j)) break;
            exch(pq, x, j);
            x = j;
        }
    }

    private static void exch(Comparable[]pq, int i, int j) {
        Comparable var0 = pq[i];
        pq[i] = pq[j];
        pq[j] = var0;
    }

    private static boolean greater(Comparable[]pq, int i, int j) {
            return pq[i].compareTo(pq[j]) > 0;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[] {-1, 3,1,2,4,9,8};
        sort(arr);
        for (Integer i: arr) {
            System.out.printf("%d ", i);
        }
    }
}
