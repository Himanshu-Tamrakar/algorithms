package chapter2.section4.algo;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class DWayMinPQHT<Item> {
    private Item[] pq;
    private int n;
    private Comparator<Item> comparator;

    public DWayMinPQHT(int N) {
        this.pq = (Item[]) new Object[N+1];
        this.n = 0;
    }

    public void insert(Item item) {
        // double size of array if necessary
        if (n == pq.length - 1) resize(2 * pq.length);

        pq[++n] = item;
        swim(n);
    }

    public Item delMin() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        Item min = pq[1];
        exch(1, n--);
        sink(1);
        pq[n+1] = null;     // to avoid loiterig and help with garbage collection
        if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);
        return min;
    }


    /***********************************************************************
     * Helper Methods
     ***********************************************************************/
    // helper function to double the size of the heap array
    private void resize(int capacity) {
        assert capacity > n;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    public int size() {
        return this.n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    private void swim(int x) {
        while (x > 1 && greater(parent(x), x)) {
            exch(parent(x), x);
            x = parent(x);
        }
    }

    private void sink(int x) {
        while (3*x <= n-1) {
            int j = 3*x-1;

            // after this, j will be the smaller than children of x
            if (j < n && greater(j, j+1)) {
                j++;
                if (j < n && greater(j, j+1)) j++;
            } else {
                if (j+2 <= n && greater(j, j+2)) j += 2;
            }

            if (!greater(x, j)) break;
            exch(x, j);
            x = j;
        }
    }

    private void exch(int i, int j) {
        Item var0 = pq[i];
        pq[i] = pq[j];
        pq[j] = var0;
    }

    private boolean greater(int i, int j) {
        if (comparator == null) {
            return ((Comparable<Item>) pq[i]).compareTo(pq[j]) > 0;
        } else {
            return comparator.compare(pq[i], pq[j]) > 0;
        }
    }

    private int parent(int x) {
        int r = x % 3;
        if (r == 2) return x / 3 + 1;
        else return x /3;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "1,3,5,7,9,2,4,6,8,10,16,14,11,13,12,15";
        System.out.printf("Enter the priority queue size\n");
        String[] items = input.split(",");

        int n = items.length;
        DWayMinPQHT<Integer> pq = new DWayMinPQHT<>(n+1);

        for (int i = 0; i < n; i++) {
            Integer item = Integer.parseInt(items[i]);
            pq.insert(item);
        }
        System.out.printf("\nLets remove items\n");

        while (pq.size() > 0) {
            System.out.printf("\nMin Item: %d\n", pq.delMin());
        }

    }
}
