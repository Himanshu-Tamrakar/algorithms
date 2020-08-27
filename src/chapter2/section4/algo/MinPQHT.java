package chapter2.section4.algo;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MinPQHT<Item> implements Iterable<Item>{
    private Item[] pq;
    private int n;
    private Comparator<Item> comparator;

    public MinPQHT(int N) {
        this.pq = (Item[]) new Object[N+1];
        this.n = 0;
    }

    public void insert(Item item) {
        // double size of array if necessary
        if (n == pq.length - 1) resize(2 * pq.length);

        pq[++n] = item;
        swim(n);
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public Item remove() {
        Item min = pq[1];
        exch(1, n);
        pq[n--] = null;
        sink(1);
        return min;
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

    /***********************************************************************
     * Helper Methods
     ***********************************************************************/
    private void swim(int x) {
        while (x > 1 && greater(x/2, x)) {
            exch(x/2, x);
            x = x/2;
        }
    }

    private void sink(int x) {
        while (2*x <= n) {
            int j = 2*x;
            if (j < n && greater(j, j+1)) j++;
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
        }
        else {
            return comparator.compare(pq[i], pq[j]) > 0;
        }
    }

    /******************************************************************
     * Iterator Implementation
     ******************************************************************/

    @Override
    public Iterator<Item> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Item> {
        private MinPQHT<Item> copy;
        private HeapIterator() {
            copy = new MinPQHT<>(size());
            for (int i = 1; i <= size(); i++) {
                copy.insert(pq[i]);
            }
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public Item next() {
            return copy.remove();
        }
    }



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Enter the priority queue size\n");
        int n = scanner.nextInt();
        MinPQHT<Integer> pq = new MinPQHT<>(n);
        for (int i = 0; i < n; i++) {
            Integer item = scanner.nextInt();
            pq.insert(item);
        }
        System.out.printf("\nLets remove items\n");
        for (Integer v: pq) {
            System.out.printf("\nMin Item: %d\n", v);
        }
//        while (pq.size() > 0) {
//            System.out.printf("\nMin Item: %d\n", pq.remove());
//        }

    }

}
