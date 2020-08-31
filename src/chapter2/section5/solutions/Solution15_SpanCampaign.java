package chapter2.section5.solutions;

import java.util.Arrays;

public class Solution15_SpanCampaign {
    private static class Domain implements Comparable<Domain> {
        String[] fields;
        public Domain(String name) {
            fields = name.split("@");
        }

        @Override
        public int compareTo(Domain that) {
            int cmp = this.fields[1].compareTo(that.fields[1]);
            if (cmp > 0) return 1;
            else if (cmp < 0) return -1;
            else return 0;
        }

        @Override
        public String toString() {
            return "Domain{" +
                    "fields=" + Arrays.toString(fields) +
                    '}';
        }
    }

    public static void main(String[] args) {
        Domain[] domains = new Domain[3];
        domains[0] = new Domain("himanshu.tamrakar@outlook.com");
        domains[1] = new Domain("himanshu.tamrakar@decimal.co.in");
        domains[2] = new Domain("himanshu.tamrakar@gmail.com");

        Arrays.sort(domains);

        for (Domain d: domains) {
            System.out.printf("%s\n", d);
        }
    }
}
