/**
 * Compilation:  javac SpellCorrector.java
 * Execution:    java SpellCorrector misspellings.txt document.txt
 * Dependencies: ST.java In.java
 * Data files:   https://introcs.cs.princeton.edu/java/44st/misspellings.txt
 *               https://introcs.cs.princeton.edu/java/44st/document.txt
 */
package chapter3.section5.solutions;
import edu.princeton.cs.algs4.ST;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SpellCorrector {
    public static void main(String[] args) {
        HashMap<String, String> set = new HashMap<>();
        String[] words = null;
        Scanner scanner = null;
        String[] text = null;
        try {
            scanner = new Scanner(new File("src/chapter3/section5/data/misspellings.txt"));
            words = Pattern.compile("\\n").split(scanner.useDelimiter("\\A").next());
            for (int i = 0; i < words.length; i++) {
                String[] s = words[i].split(" ");
                set.put(s[0], s[1]);
            }

            System.out.printf("%s\n", set.get("hvaing"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            scanner = new Scanner(new File("src/chapter3/section5/data/document.txt"));
            text = Pattern.compile("\\p{javaWhitespace}").split(scanner.useDelimiter("\\A").next());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < text.length; i++) {
            System.out.printf("%s ", text[i]);
        }

        System.out.printf("\n");

        for (int i = 0; i < text.length; i++) {
            if (set.containsKey(text[i])) {
                String s = set.get(text[i]);
                s = s.substring(1, s.length()-1);
                System.out.printf("%s ", s);
            } else {
                System.out.printf("%s ", text[i]);
            }
        }


    }
 }
