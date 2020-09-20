package chapter3.section5.solutions;

import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.Iterator;

public class FrequencyTable<Key extends Comparable<Key>> {
    private HashMap<Key, Integer> st;
    public FrequencyTable() {
        this.st = new HashMap<>();
    }

    public void hit(Key key) {
        if (key == null) throw new IllegalArgumentException("Key can not be null");
        if (!st.containsKey(key)) st.put(key, 1);
        else st.put(key, st.get(key) + 1);
    }

    public int count(Key key) {
        if (st.containsKey(key)) return st.get(key);
        return 0;
    }
    // print all the keys to standard output
    public void show() {
        for (Iterator<Key> it = st.keySet().iterator(); it.hasNext(); ) {
            Key key = it.next();
            StdOut.println(st.get(key) + " " + key);
        }
    }

    public static void main(String[] args) {

    }
}
