package chapter1.section1.solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SattoloHT {

    public static void cycle(int[] a) {
        for (int i = a.length; i > 0 ; i--) {
            int r = (int) (Math.random() * (i - 1));
            int swap = a[r];
            a[r] = a[i-1];
            a[i-1] = swap;
        }
    }

    public static void main(String[] args) {
        File file = new File(args[0]);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            String[] arrItems = scanner.nextLine().split(" ");
            int[] items = new int[arrItems.length];
            for (int i = 0; i < arrItems.length; i++) {
                items[i] = Integer.parseInt(arrItems[i]);
            }
            for (int n: items) {
                System.out.printf("%d ", n);
            }
            System.out.println();
            SattoloHT.cycle(items);
            for (int n: items) {
                System.out.printf("%d ", n);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

}
