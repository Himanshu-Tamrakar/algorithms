package chapter3.section5.algo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class LookupCSV {

    /**
     * LookupCSV.main(new String[]{"0", "1", "src/chapter3/section5/data/ip.csv"});
     * LookupCSV.main(new String[]{"0", "1", "src/chapter3/section5/data/DJIA.csv"});
     */
    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("need input file, key and value index");
        }
        int keyIndex;
        int valueIndex;
        Scanner scanner = null;


        try {
            keyIndex = Integer.parseInt(args[0]);
            valueIndex = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new NumberFormatException("Send index value in string format");
        }

        try {
            scanner = new Scanner(new File(args[2]));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        HashMap<String, String> set = new HashMap<>();

        while (scanner.hasNext()){
            String[] items = scanner.nextLine().split(",");
            set.put(items[keyIndex], items[valueIndex]);
        }

        scanner = new Scanner(System.in);
        System.out.printf("Enter key to find in set");
        while (scanner.hasNext()) {
            String key = scanner.next();
            if (set.containsKey(key)) {
                System.out.printf("key %s, value %s\n", key, set.get(key));
            } else {
                System.out.printf("key %s, does not present\n", key);
            }
        }



    }
}
