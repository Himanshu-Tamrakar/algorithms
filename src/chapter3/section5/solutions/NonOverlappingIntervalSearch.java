package chapter3.section5.solutions;

import edu.princeton.cs.algs4.In;

import java.util.Scanner;
import java.util.TreeMap;

public class NonOverlappingIntervalSearch<Key extends Integer> {
    private TreeMap<Key, Key> map;
    public NonOverlappingIntervalSearch() {
        map = new TreeMap<>();
    }

    public void findInterval(Key key) {
        Key floor = floor(key);
        Key ceil = cieling(key);
        if (floor != null) {
            Key floorValue = get(floor);
            if (existIn(floorValue, floor, key)) {
                System.out.printf("Key: %s, is present in %d - %d\n", key, floorValue, floor);
                return;
            }
        }
        if (ceil != null) {
            Key ceilValue = get(ceil);
            if (existIn(ceilValue, ceil, key)) {
                System.out.printf("Key: %s, is present in %d - %d\n", key, ceilValue, ceil);
                return;
            }
        }
        System.out.printf("no interval found for key: %s\n", key);
    }


    /**
     * return true if test value lie between var0-var1 ot var1-var0
     * @param var0
     * @param var1
     * @param test
     * @return
     */
    private boolean existIn(Key var0, Key var1, int test) {
        if (var0.compareTo(var1) < 0) {
            int from = var0;
            int to = var1;
            if (from <= test && test <= to) {
                return true;
            }
        } else {
            int from = var1;
            int to = var0;
            if (from <= test && test <= to) {
                return true;
            }
        }
        return false;
    }

    /*********************************************
     * Helper methods
     *********************************************/
    public void add(Key from, Key to) {
        if (to.compareTo(from) < 0) {
            throw new IllegalArgumentException("Input is incorrect. from: " + from + ", to: " + to);
        }

        if (from == null || to == null) {
            throw new IllegalArgumentException("Input is incorrect. from: " + from + ", to: " + to);
        }

        map.put(from, to);
        map.put(to, from);
    }

    public Key get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("Input is incorrect. key: " + key);
        }
        return map.get(key);
    }

    public Key cieling(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("Input is incorrect. key: " + key);
        }
        return map.ceilingKey(key);
    }

    public Key floor(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("Input is incorrect. key: " + key);
        }
        return map.floorKey(key);
    }

    public static void main(String[] args) {
        NonOverlappingIntervalSearch obj = new NonOverlappingIntervalSearch();

        String[] input = "1643-2033,5532-7643,8999-10332,5666653-5669321".split(",");
        for (int i = 0; i < input.length; i++) {
            String[] range = input[i].split("-");
            int from = Integer.parseInt(range[0]);
            int to = Integer.parseInt(range[1]);
            obj.add(from, to);
        }


        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 10; i++) {
            System.out.printf("Enter input\n");
            obj.findInterval(scanner.nextInt());
        }

    }

}
