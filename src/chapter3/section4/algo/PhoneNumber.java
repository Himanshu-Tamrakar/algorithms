package chapter3.section4.algo;

import edu.princeton.cs.algs4.StdOut;

import java.util.HashSet;
import java.util.Objects;

public class PhoneNumber {
    private final int area;
    private final int exch;
    private final int ext;

    public PhoneNumber(int area, int exch, int ext) {
        this.area = area;
        this.exch = exch;
        this.ext = ext;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return area == that.area && exch == that.exch && ext == that.ext;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "area=" + area +
                ", exch=" + exch +
                ", ext=" + ext +
                '}';
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = 31 * 1 + ((Integer) area).hashCode();
        hash = 31 * 1 + ((Integer) exch).hashCode();
        hash = 31 * 1 + ((Integer) ext).hashCode();
        return hash;
//        return 31 * (area + 31 * exch) + ext;
    }

    public static void main(String[] args) {
        PhoneNumber a = new PhoneNumber(609, 258, 4455);
        PhoneNumber b = new PhoneNumber(609, 876, 5309);
        PhoneNumber c = new PhoneNumber(609, 555, 5309);
        PhoneNumber d = new PhoneNumber(215, 876, 5309);
        PhoneNumber e = new PhoneNumber(609, 876, 5309);
        StdOut.println("a = " + a);
        StdOut.println("b = " + b);
        StdOut.println("c = " + c);
        StdOut.println("d = " + d);
        StdOut.println("e = " + e);

        HashSet<PhoneNumber> set = new HashSet<PhoneNumber>();
        set.add(a);
        set.add(b);
        set.add(c);
        StdOut.println("Added a, b, and c");
        StdOut.println("contains a:  " + set.contains(a));
        StdOut.println("contains b:  " + set.contains(b));
        StdOut.println("contains c:  " + set.contains(c));
        StdOut.println("contains d:  " + set.contains(d));
        StdOut.println("contains e:  " + set.contains(e));
        StdOut.println("b == e:      " + (b == e));
        StdOut.println("b.equals(e): " + (b.equals(e)));
    }
}
