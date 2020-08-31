package chapter2.section5.solutions;

import javax.naming.PartialResultException;
import java.util.Arrays;
import java.util.Comparator;

public class Solution14_ReverseDomainSort {
    private static class Domain implements Comparable<Domain> {
        String[] fields;
        int n;
        public Domain(String name) {
            fields = name.split("\\.");
            this.n = fields.length;
        }

        @Override
        public int compareTo(Domain that) {
            for (int i = 0; i < Math.min(this.n, that.n); i++) {
                int cmp = this.fields[n-i-1].compareTo(that.fields[that.n-i-1]);
                if (cmp > 0) return 1;
                else if (cmp < 0) return -1;
            }
            return this.n - that.n;
        }

        @Override
        public String toString() {
            return "Domain{" +
                    "fields=" + Arrays.toString(fields) +
                    ", n=" + n +
                    '}';
        }
    }


    public static void main(String[] args) {
        Domain[] domains = new Domain[3];
        domains[0] = new Domain("cs.princeton.edu");
        domains[1] = new Domain("cs.princeton.eda");
        domains[2] = new Domain("cs.princeton.edb");

        Arrays.sort(domains);

        for (Domain d: domains) {
            System.out.printf("%s\n", d);
        }
    }
}
