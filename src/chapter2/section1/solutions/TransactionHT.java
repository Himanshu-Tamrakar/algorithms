package chapter2.section1.solutions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class TransactionHT implements Comparable<TransactionHT> {
    private final String who;
    private final double amount;
    private final Date when;

    public TransactionHT(String who, double amount, Date date) {
        if (Double.isNaN(amount) || Double.isInfinite(amount)) {
            throw new IllegalArgumentException("Amount can not be nan or infinite");
        }
        this.amount = amount;
        this.when = date;
        this.who = who;
    }

    public TransactionHT(String transaction) {
        String[] a = transaction.split("\\s+");
        who    = a[0];
        when   = new Date(a[1]);
        amount = Double.parseDouble(a[2]);
        if (Double.isNaN(amount) || Double.isInfinite(amount))
            throw new IllegalArgumentException("Amount cannot be NaN or infinite");
    }
    /**
     * Returns the name of the customer involved in this transaction.
     *
     * @return the name of the customer involved in this transaction
     */
    public String who() {
        return who;
    }

    /**
     * Returns the date of this transaction.
     *
     * @return the date of this transaction
     */
    public Date when() {
        return when;
    }

    /**
     * Returns the amount of this transaction.
     *
     * @return the amount of this transaction
     */
    public double amount() {
        return amount;
    }

    /**
     * Returns a string representation of this transaction.
     *
     * @return a string representation of this transaction
     */
    @Override
    public String toString() {
        return String.format("%-10s %10s %8.2f", who, when, amount);
    }


    @Override
    public int compareTo(TransactionHT that) {
        return Double.compare(this.amount, that.amount());
    }

    public int hasCode() {
        int hash = 1;
        hash = 31 * hash + who.hashCode();
        hash = 31 * hash + when.hashCode();
        hash = 31 * hash + Double.hashCode(amount);
        return hash;
    }

    /**
     * compared trasaction by name
     */

    private static class WhoOrder implements Comparator<TransactionHT> {

        @Override
        public int compare(TransactionHT v, TransactionHT w) {
            return v.who().compareTo(w.who());
        }

    }

    /**
     * compare via trasaction date
     */

    private static class WhenOrder implements Comparator<TransactionHT> {

        @Override
        public int compare(TransactionHT v, TransactionHT w) {
            return v.when().compareTo(w.when());
        }
    }

    /**
     * Compare via transaction amount
     */
    private static class HowMuchOrder implements Comparator<TransactionHT> {

        @Override
        public int compare(TransactionHT v, TransactionHT w) {
            return Double.compare(v.amount(), w.amount());
        }
    }

    public static void main(String[] args) {
        TransactionHT[] a = new TransactionHT[4];
        a[0] = new TransactionHT("Turing 6/16/1990 644.00");
        a[1] = new TransactionHT("Tarjan 6/16/2002 4121.00");
        a[2] = new TransactionHT("Knuth 6/16/1999 288.00");
        a[3] = new TransactionHT("Dijkastra 6/16/2007 2688.00");

        System.out.printf("Unsorted\n");
        for (int i = 0; i < a.length; i++) {
            System.out.printf("%s\n", a[i]);
        }

        System.out.printf("Sort by date\n");
        Arrays.sort(a, new TransactionHT.WhenOrder());
        for (int i = 0; i < a.length; i++) {
            System.out.printf("%s\n", a[i]);
        }

        System.out.printf("Sort by Name\n");
        Arrays.sort(a, new TransactionHT.WhoOrder());
        for (int i = 0; i < a.length; i++) {
            System.out.printf("%s\n", a[i]);
        }

        System.out.printf("Sort by Amount\n");
        Arrays.sort(a, new TransactionHT.HowMuchOrder());
        for (int i = 0; i < a.length; i++) {
            System.out.printf("%s\n", a[i]);
        }
    }
}
