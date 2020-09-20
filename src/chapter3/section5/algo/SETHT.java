package chapter3.section5.algo;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.TreeSet;

public class SETHT<Key> {
    private TreeSet<Key> set;

    public SETHT() {
        set = new TreeSet<>();
    }

    public void add(Key key) {
        if (key == null) throw new IllegalArgumentException("Key can not be null");
        set.add(key);
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("Key is null");
        return set.contains(key);
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("Key can not be null");
        set.remove(key);
    }

    public int size() {
        return set.size();
    }

    public boolean isEmpty() {
        return set.isEmpty();
    }

    public Iterator<Key> iterator() {
        return set.iterator();
    }

    public Key max() {
        return set.last();
    }

    public Key min() {
        return set.first();
    }

    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("Key can not be null");
        Key ceil = set.ceiling(key);
        if (ceil == null) throw new NoSuchElementException("All the keys are less than that key");
        return ceil;
    }

    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("Key can not be null");
        Key floor = set.floor(key);
        if (floor == null) throw new NoSuchElementException("All the keys are greater than that key");
        return floor;
    }

    public SETHT<Key> union(SETHT<Key> that) {
        SETHT<Key> newSet = new SETHT<>();
        Iterator<Key> itr = this.iterator();
        while (itr.hasNext()) {
            newSet.add(itr.next());
        }
        itr = that.iterator();
        while (itr.hasNext()) {
            newSet.add(itr.next());
        }
        return newSet;
    }

    public SETHT<Key> intersection(SETHT<Key> that) {
        SETHT<Key> c = new SETHT<>();

        Iterator<Key> itr;

        if (this.size() < that.size()) {
            itr = this.iterator();
            while (itr.hasNext()) {
                Key k = itr.next();
                if (that.contains(k)) c.add(k);
            }
        } else {
            itr = that.iterator();
            while (itr.hasNext()) {
                Key k = itr.next();
                if (this.contains(k)) c.add(k);
            }
        }
        return c;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        SETHT that = (SETHT) o;
        return this.set.equals(that.set);
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("hashCode() is not supported because sets are mutable");
    }

    @Override
    public String toString() {
        return "SETHT{" +
                "set=" + set +
                '}';
    }


    public static void main(String[] args) {
        SETHT<String> set = new SETHT<String>();
        StdOut.println("set = " + set);

        // insert some keys
        set.add("www.cs.princeton.edu");
        set.add("www.cs.princeton.edu");    // overwrite old value
        set.add("www.princeton.edu");
        set.add("www.math.princeton.edu");
        set.add("www.yale.edu");
        set.add("www.amazon.com");
        set.add("www.simpsons.com");
        set.add("www.stanford.edu");
        set.add("www.google.com");
        set.add("www.ibm.com");
        set.add("www.apple.com");
        set.add("www.slashdot.com");
        set.add("www.whitehouse.gov");
        set.add("www.espn.com");
        set.add("www.snopes.com");
        set.add("www.movies.com");
        set.add("www.cnn.com");
        set.add("www.iitb.ac.in");


        StdOut.println(set.contains("www.cs.princeton.edu"));
        StdOut.println(!set.contains("www.harvardsucks.com"));
        StdOut.println(set.contains("www.simpsons.com"));
        StdOut.println();

        StdOut.println("ceiling(www.simpsonr.com) = " + set.ceiling("www.simpsonr.com"));
        StdOut.println("ceiling(www.simpsons.com) = " + set.ceiling("www.simpsons.com"));
        StdOut.println("ceiling(www.simpsont.com) = " + set.ceiling("www.simpsont.com"));
        StdOut.println("floor(www.simpsonr.com)   = " + set.floor("www.simpsonr.com"));
        StdOut.println("floor(www.simpsons.com)   = " + set.floor("www.simpsons.com"));
        StdOut.println("floor(www.simpsont.com)   = " + set.floor("www.simpsont.com"));
        StdOut.println();

        StdOut.println("set = " + set);
        StdOut.println();

        // print out all keys in this set in lexicographic order
        Iterator itr = set.iterator();
        while (itr.hasNext()) System.out.printf("%s\n", itr.next());
//        StdOut.println();
//        SET<String> set2 = new SET<String>(set);
//        StdOut.println(set.equals(set2));
    }
}
