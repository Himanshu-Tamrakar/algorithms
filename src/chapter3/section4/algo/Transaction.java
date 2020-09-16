package chapter3.section4.algo;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class Transaction implements Comparable<Transaction> {
    private final String who;
    private final Date when;     // date
    private final double amount;

    private final static Comparator BY_NAME = new WhoOrder();
    private final static Comparator BY_DATE = new WhenOrder();
    private final static Comparator BY_AMOUNT = new HowMuchOrder();

    public Transaction(String who, String when, double amount) {
        this.who = who;
        this.when = new Date(when);
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = 31 * hash + who.hashCode();
        hash = 31 * hash + when.hashCode();
        hash = 31 * hash + Double.hashCode(amount);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return (Double.compare(this.amount, that.amount) == 0) && (this.when.equals(that.when)) && (this.who.equals(that.who));
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "who='" + who + '\'' +
                ", when=" + when +
                ", amount=" + amount +
                '}';
    }

    @Override
    public int compareTo(Transaction that) {
        return Double.compare(amount, that.amount);
    }

    private static class WhoOrder implements Comparator<Transaction> {
        @Override
        public int compare(Transaction v, Transaction w) {
            return v.who.compareTo(w.who);
        }
    }

    private static class WhenOrder implements Comparator<Transaction> {

        @Override
        public int compare(Transaction v, Transaction w) {
            return v.when.compareTo(w.when);
        }
    }

    private static class HowMuchOrder implements Comparator<Transaction> {

        @Override
        public int compare(Transaction v, Transaction w) {
            return Double.compare(v.amount, w.amount);
        }
    }


    /**
     * Unit tests the {@code Transaction} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Transaction[] a = new Transaction[4];
        a[0] = new Transaction("Turing",   "6/17/1990",  644.08);
        a[1] = new Transaction("Tarjan",   "3/26/2002", 4121.85);
        a[2] = new Transaction("Knuth",    "6/14/1999",  288.34);
        a[3] = new Transaction("Dijkstra", "8/22/2007", 2678.40);

        StdOut.println("Unsorted");
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
        StdOut.println();

        StdOut.println("Sort by date");
        Arrays.sort(a, Transaction.BY_DATE);
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
        StdOut.println();

        StdOut.println("Sort by customer");
        Arrays.sort(a, new Transaction.WhoOrder());
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
        StdOut.println();

        StdOut.println("Sort by amount");
        Arrays.sort(a, new Transaction.HowMuchOrder());
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
        StdOut.println();
    }
}
