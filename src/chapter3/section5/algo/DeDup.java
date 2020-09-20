package chapter3.section5.algo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class DeDup {
    private static Scanner scanner;

    static {
        try {
            scanner = new Scanner(new File("src/chapter3/section5/data/tinyTale.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HashSet<String> set = new HashSet();
        while (scanner.hasNext()) {
            String key = scanner.next();
            if (!set.contains(key)) {
                set.add(key);
                System.out.printf("%s\n", key);
            }
        }
    }
}
