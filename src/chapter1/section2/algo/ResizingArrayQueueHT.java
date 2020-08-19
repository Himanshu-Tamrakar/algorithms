package chapter1.section2.algo;

import chapter1.section2.data.ResizingArrayStackHT;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ResizingArrayQueueHT<Item> implements Iterable<Item> {
    Item[] items;
    private int first, last;
    private int size;

    public ResizingArrayQueueHT(int size) {
        items = (Item[]) new Object[size];
        this.first = 0;
        this.last = 0;
        this.size = 0;
    }

    public void enqueue(Item item) {
        if (this.size == items.length) {
            resize(this.items.length * 2);
        }
        this.items[this.last++] = item;
        this.size++;
        this.last = this.last % items.length;
    }

    public Item dequeue() {
        if (this.size == 0) {
            throw new ArrayIndexOutOfBoundsException("Queue is empty");
        }
        if (this.size <= items.length / 4) {
            resize(this.items.length / 2);
        }
        Item item = this.items[first];
        this.items[first] = null;
        this.first = ++this.first % items.length;
        this.size--;
        return item;
    }

    private void resize(int n) {
        Item[] tempArr = (Item[]) new Object[n];
        int sz = items.length;
        for (int i = 0; i < this.size; i++) {
            tempArr[i] = this.items[(first + i) % sz];
        }
        this.items = tempArr;
        this.first = 0;
        this.last = this.size;
    }

    public Iterator<Item> iterator() {
        return new CollectItems();
    }

    private class CollectItems implements Iterator<Item> {
        private int i;
        public CollectItems() {
           this.i = 0;
        }
        @Override
        public boolean hasNext() {
            return i < size;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return items[(first+i++) % items.length];
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ResizingArrayQueueHT<String> queue = new ResizingArrayQueueHT(10);
        queue.enqueue("Hello");
        queue.enqueue("I");
        queue.enqueue("am");
        queue.enqueue("Himanshu");
        queue.enqueue("Tamrakar");

        while (scanner.hasNext()) {
            String operation = scanner.nextLine();
            if(operation.equals("+")) {
                queue.enqueue(scanner.nextLine());
            } else if (operation.equals("-")) {
                System.out.printf("%s \n", queue.dequeue());
            } else {
                for (String s: queue) {
                    System.out.printf("%s ", s);
                }
            }
        }
    }
}
