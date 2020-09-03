package chapter3.section1.solutions;

import edu.princeton.cs.algs4.StdOut;

import java.util.HashSet;
import java.util.Objects;

public class PhoneNumberIndia {
    String number;
    public PhoneNumberIndia(String s) {
        if (!validNumber(s)) throw new IllegalArgumentException("Phone number is not as expected");
        this.number = s;
    }
    private boolean validNumber(String s) {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumberIndia that = (PhoneNumberIndia) o;
        return this.number.equals(that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    public static void main(String[] args) {
        PhoneNumberIndia a = new PhoneNumberIndia("8989468903");
        PhoneNumberIndia b = new PhoneNumberIndia("6098765309");
        PhoneNumberIndia c = new PhoneNumberIndia("6095555309");
        PhoneNumberIndia d = new PhoneNumberIndia("2158765309");
        PhoneNumberIndia e = new PhoneNumberIndia("6098765309");
        StdOut.println("a = " + a);
        StdOut.println("b = " + b);
        StdOut.println("c = " + c);
        StdOut.println("d = " + d);
        StdOut.println("e = " + e);

        HashSet<PhoneNumberIndia> set = new HashSet<PhoneNumberIndia>();
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
