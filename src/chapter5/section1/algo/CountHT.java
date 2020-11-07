package chapter5.section1.algo;

import edu.princeton.cs.algs4.Alphabet;

public class CountHT {
    public static void main(String[] args) {
        Alphabet binary = Alphabet.BINARY;
        System.out.printf("%d %s\n", binary.toIndex('1'), binary.toChar(0));



        Alphabet alphabet = new Alphabet("ACTG");
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < 20; i++) {
            int n = (int) Math.ceil(Math.random() * 4);
            switch (n) {
                case 1: {
                    s.append("A");
                    break;
                }
                case 2: {
                    s.append("C");
                    break;
                } case 3: {
                    s.append("T");
                    break;
                } case 4: {
                    s.append("G");
                } default:{

                }
            }
        }
        System.out.printf("%s\n",s);

        int[] count = new int[alphabet.R()];

        for (int i = 0; i < s.length(); i++) {
            count[alphabet.toIndex(s.charAt(i))]++;
        }

        for (int i = 0; i < count.length; i++) {
            System.out.printf("%s - %d\n", alphabet.toChar(i), count[i]);
        }
    }
}
