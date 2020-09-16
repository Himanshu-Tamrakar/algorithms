package chapter3.section4.solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;

public class Solution5_PasswordChecker {
    private Scanner scanner;
    private static final int MIN_LENGTH = 8;
    private HashSet<String> dictionary;

    public Solution5_PasswordChecker() {
        try {
            dictionary = new HashSet<>();
            scanner = new Scanner(new File("src/chapter3/section4/data/sequential/ospd.txt"));
            while (scanner.hasNext()) dictionary.add(scanner.next());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean isFailMinLength(String key) {
        return key.length() < MIN_LENGTH;
    }

    private boolean isContainsInDictionary(String key) {
        return dictionary.contains(key);
    }

    private String[] refactor(String key) {
        String[] str = new String[2];
        for (int i = 0; i < key.length(); i++) {
            if (key.charAt(i) == '0' || key.charAt(i) == '1' ||
                    key.charAt(i) == '2' || key.charAt(i) == '3' ||
                    key.charAt(i) == '4' || key.charAt(i) == '5' ||
                    key.charAt(i) == '6' || key.charAt(i) == '7' ||
                    key.charAt(i) == '8' || key.charAt(i) == '9') {
                str[0] = key.substring(0, i);
                str[1] = key.substring(i+1, key.length());
                return str;
            }
        }
        return null;
    }

    private boolean isPasswordPass(String[] pass) {
        for (int i = 0; i < pass.length; i++) {
            String p = pass[i];
            if (isContainsInDictionary(p) || isFailMinLength(p)) {
                return false;
            }
        }

        return true;
    }


    public static void main(String[] args) {
        Scanner scanner;
        scanner = new Scanner(System.in);
        Solution5_PasswordChecker pass = new Solution5_PasswordChecker();

        while (scanner.hasNext()) {
            String password = scanner.next();
            String[] passwords = pass.refactor(password);
            if (passwords == null) {
                System.out.printf("password: %s, Is this valid? %b\n", password, pass.isPasswordPass(new String[] { password }));
            } else {
                boolean left, right;
                if (!passwords[0].equals("") && !passwords[1].equals("")) {
                    System.out.printf("password: %s, Is this valid? %b\n", passwords[0], pass.isPasswordPass(passwords));
                } else {
                    System.out.printf("password: %s, Is this valid? %b\n", passwords[0], pass.isPasswordPass(new String[] { passwords[0] }));
                }
            }
        }
    }
}
