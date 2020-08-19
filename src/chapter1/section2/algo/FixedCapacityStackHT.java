package chapter1.section2.algo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FixedCapacityStackHT<Item> implements Iterable<Item> {
    Item[] items;
    int n;

    public FixedCapacityStackHT(int size) {
        this.items = (Item[]) new Object[size];
        this.n = 0;
    }

    public int size() {
        return this.n;
    }

    public void push(Item item) {
        if (n == this.items.length) {
            throw new ArrayIndexOutOfBoundsException("Stack is full");
        }
        this.items[n++] = item;
    }

    public Item pop() {
        if (this.n == 0) {
            throw new ArrayIndexOutOfBoundsException("Stack is empty");
        }
        Item item = this.items[--n];
        this.items[n] = null;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ReverseArray();
    }

    private class ReverseArray implements Iterator<Item> {
        private int N = n;
        @Override
        public boolean hasNext() {
            if (N == 0) return false;
            return true;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return items[--N];
        }
    }

    public static void main(String[] args) {
        try {
            File file = new File(args[0]);
            Scanner scanner = new Scanner(file);
            String[] arrItems = scanner.nextLine().split(" ");
            FixedCapacityStackHT<String> stackHT = new FixedCapacityStackHT<>(10);
            for (int i = 0; i < arrItems.length; i++) {
                stackHT.push(arrItems[i]);
            }

            Iterator<String> itr = stackHT.iterator();
            while (itr.hasNext()) {
                System.out.printf("%s ", itr.next());
            }

            System.out.printf("\n");

            for (String s: stackHT) {
                System.out.printf("%s ", s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
