package chapter2.section5.solutions;

import java.util.Arrays;
import java.util.Comparator;

public class RhymingWords {
    public static void main(String[] args) {
        String[] rhyming = new String[] {
                "Cat",
                "Sat",
                "Boat",
                "Coat",
                "Ball",
                "All",
                "Cave",
                "Gave",
                "Cook",
                "Look",
                "hook",
                "Skip",
                "Drip",
                "Lip",
                "Bird",
                "Heard",
                "Bed",
                "Red","Said",
                "Hill", "Will"
        };

        for (int i = 0; i < rhyming.length; i++) {
            rhyming[i] = reverse(rhyming[i]);
        }

        Arrays.sort(rhyming);

        for (String s: rhyming) {
            System.out.printf("%s\n", reverse(s));
        }


    }

    private static String reverse(String s) {
        StringBuilder str = new StringBuilder(s);
        return new String(str.reverse());
    }
}
