package chapter3.section4.algo;

import edu.princeton.cs.algs4.Queue;
import sun.awt.util.IdentityLinkedList;

public class SaparateChainingST<Key, Value> {
    private static final int INITIAL_SIZE = 4;
    int n;                      // Number of key value pairs
    int m;                      // Table size
    private SequentialSearchST<Key, Value>[] st;

    public SaparateChainingST() {
        this(INITIAL_SIZE);
    }
    public SaparateChainingST(int m) {
        this.m = m;
        st = new SequentialSearchST[m];
        for (int i = 0; i < m; i++) {
            st[i] = new SequentialSearchST<>();
        }
        this.n = 0;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public Value get(Key key) {
        int pos = hash(key);
        return st[pos].get(key);
    }

    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("Key can not be null");
        }

        if (val == null) {
            delete(key);
            return;
        }

        if (n >= 10*m) resize(2*m);
//        if (n/m > 10) resize(2*m);


        int pos = hash(key);

        if (!st[pos].contains(key)) n++;
        st[pos].put(key, val);
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("Key can not be null");
        }

        int pos = hash(key);
        if (st[pos].contains(key)) n--;
        st[pos].delete(key);
        if (m > INITIAL_SIZE && n <= 2*m) resize(m/2);
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();

        for (int i = 0; i < m; i++) {
            for (Key k: st[i].keys()) {
                queue.enqueue(k);
            }
        }

        return queue;
    }


    private void resize(int size) {
        SaparateChainingST<Key, Value> var0 = new SaparateChainingST<>(size);
        for (Key k: keys()) {
            var0.put(k, get(k));
        }
        st = var0.st;
        m = var0.m;
        n = var0.n;
    }
}
