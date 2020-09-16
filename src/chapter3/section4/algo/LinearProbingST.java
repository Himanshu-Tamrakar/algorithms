package chapter3.section4.algo;

import edu.princeton.cs.algs4.Queue;

import javax.naming.InitialContext;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class LinearProbingST<Key, Value> {
    private static final int INITIAL_SIZE = 4;
    private Key[] keys;
    private Value[] values;
    private int n, m;

    public LinearProbingST(int size) {
        this.m = size;
        this.n = 0;
        keys = (Key[]) new Object[size];
        values = (Value[]) new Object[size];
    }

    private LinearProbingST() {
        this(INITIAL_SIZE);
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    private void resize(int size) {
        LinearProbingST<Key, Value> var0 = new LinearProbingST<>(size);
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                var0.put(keys[i], values[i]);
            }
        }
        keys = var0.keys;
        values = var0.values;
        n = var0.size();
        m = var0.m;
    }

    public Value get(Key key) {
        int pos = hash(key);
        for (int i = pos; keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                return values[i];
            }
        }
        return null;
    }

    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("key can not be null");
        }

        if (value == null) {
            delete(key);
            return;
        }

        if (n >= m/2) resize(2*m);

        int pos = hash(key);
        int i;
        for ( i = pos; keys[i] != null; i = (i+1) % m) {
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }
        }

        keys[i] = key;
        values[i] = value;
        n++;

    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key can not be null");
        }

        if (!contains(key)) return;

        int i = hash(key);
        while (keys[i] != null) {
            i = (i+1) % m;
        }

        keys[i] = null;
        values[i] = null;

        i = (i + 1) % m;

        while (keys[i] != null) {
            Key keyToRefresh = keys[i];
            Value valueToRefresh = values[i];
            keys[i] = null;
            values[i] = null;
            n--;
            put(keyToRefresh,valueToRefresh);
            i = (i+1) % m;
        }
        n--;
        if (n > 0 && n <= m/8) resize(m/2);
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < m; i++)
            if (keys[i] != null) queue.enqueue(keys[i]);
        return queue;
    }

    public static void main(String[] angs) {
        try {
            LinearProbingST<String, Integer> st = new LinearProbingST<String, Integer>();

            Scanner scanner = new Scanner(new File("src/chapter3/section4/data/sequential/sequention.txt"));
            int i = 0;
            while (scanner.hasNext()) {
                st.put(scanner.next(), i++);
            }

            for (String s: st.keys()) {
                System.out.printf("%s, %d\n", s, st.get(s));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
