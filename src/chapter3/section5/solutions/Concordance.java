package chapter3.section5.solutions;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Concordance {
    public static void main(String[] args) {
        File file = new File(args[0]);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int i = 0;
        HashMap<String, Bag<Integer>> set = new HashMap<>();
        String words = scanner.useDelimiter(Pattern.compile("\\A")).next();
        String[] w = Pattern.compile("\\p{javaWhitespace}+").split(words);
        for (String s: w) {
            if (!set.containsKey(s)) set.put(s, new Bag<>());
            Bag<Integer> bag = set.get(s);
            bag.add(i++);
        }
    }
}
