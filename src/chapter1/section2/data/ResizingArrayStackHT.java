package chapter1.section2.data;

import chapter1.section2.algo.FixedCapacityStackHT;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ResizingArrayStackHT<Item> implements Iterable<Item> {
    Item[] items;
    int n;

    public ResizingArrayStackHT(int size) {
        this.items = (Item[]) new Object[size];
        this.n = 0;
    }

    public ResizingArrayStackHT() {
        this(10);
    }

    public int size() {
        return this.n;
    }
    public void push(Item item) {
        if (n == items.length) {
            resize(n * 2);
        }
        this.items[n++] = item;
    }

    public Item pop() {
        if (n <= items.length / 4) {
            resize(items.length / 2);
        }
        Item item = items[--n];
        items[n] = null;
        return item;
    }

    private void resize(int size) {
        Item[] tempArr = (Item[]) new Object[size];
        for (int i = 0; i < items.length; i++) {
            tempArr[i] = items[i];
        }
        items = tempArr;
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
            ResizingArrayStackHT<String> stackHT = new ResizingArrayStackHT<>();
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
