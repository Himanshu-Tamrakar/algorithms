package chapter3.section4.algo;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class SequentialSearchST<Key, Value> {
    private Node first;
    private int N;

    private class Node {
        private Key key;
        private Value value;
        private Node next;

        private Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        return get(first, key) != null;
    }

    public Value get(Key key) {
        Node x = get(this.first, key);
        return x == null ? null : x.value;
    }

    private Node get(Node x, Key key) {
        while (x != null) {
            if (key.equals(x.key)) {
                return x;
            }
            x = x.next;
        }
        return x;
    }


    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("Key can not be null");
        }

        if (value == null) {
            delete(key);
            return;
        }

        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.value = value;
                return;
            }
        }
        first = new Node(key, value, first);
        N++;
    }

    public void delete(Key key) {
        first = delete(first, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;

        if (x.key.equals(key)) {
            N--;
            return x.next;
        }

        x.next = delete(x.next, key);
        return x;
    }

    public Iterable<Key> keys()  {
        Queue<Key> queue = new Queue<Key>();
        for (Node x = first; x != null; x = x.next)
            queue.enqueue(x.key);
        return queue;
    }


    public static void main(String[] args) {
        SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
        try {
            Scanner scanner = new Scanner(new File("src/chapter3/section4/data/sequential/sequention.txt"));
            int i = 0;
            while (scanner.hasNext()) {
                String var0 = scanner.next();
                st.put(var0, i++);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }


}
