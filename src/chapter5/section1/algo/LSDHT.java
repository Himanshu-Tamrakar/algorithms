package chapter5.section1.algo;

public class LSDHT {
    public static void sort(String[] a) {
        int strLen = a[0].length();
        int R = 256;
        int[] count;
        String[] var0 = new String[a.length];

        for (int i = strLen-1; i >= 0 ; i--) {
            count = new int[R+1];
            // Key Index Sorting: Start
            for (int j = 0; j < a.length; j++) {
                count[charAt(a[j], i)]++;
            }

            for (int j = 1; j < count.length; j++) {
                count[j] += count[j-1];
            }

            for (int j = 0; j < a.length; j++) {
                int index = charAt(a[j], i)-1;
                var0[count[index]++] = a[j];
            }

            for (int j = 0; j < a.length; j++) {
                a[j] = var0[j];
            }
            // Key Index Sorting: End
        }
    }

    private static int charAt(String s, int index) {
        if (index >= s.length()) {
            throw new IndexOutOfBoundsException("");
        }
        return s.charAt(index);
    }

    public static void main(String[] args) {
        String[] arr = new String[] {
                "4PGC938",
                "2IYE230",
                "3CIO720",
                "1ICK750",
                "1OHV845",
                "4JZY524",
                "1ICK750",
                "3CIO720",
                "1OHV845",
                "1OHV845",
                "2RLA629",
                "2RLA629",
                "3ATW723"
        };

        sort(arr);

        for (String s: arr) {
            System.out.printf("%s\n", s);
        }
    }


}
