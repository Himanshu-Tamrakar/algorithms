package chapter1.section1.algo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class BinarySearchHT {
    public static int indexOf(int[] a, int key) {
        int lo = 0;
        int hi = a.length-1;
        while (lo <= hi) {
            int mid = lo + (hi-lo) / 2;
            if (key < a[mid]){
                hi = mid -1;
            } else if (key > a[mid]) {
                lo = mid + 1;
            } else {
                return a[mid];
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        File file = new File(args[0]);
        try {
            Scanner scanner = new Scanner(file);
            String[] arrItems = scanner.nextLine().split(" ");
            int[] items = new int[arrItems.length];
            for (int i = 0; i < arrItems.length; i++) {
                items[i] = Integer.parseInt(arrItems[i]);
            }

            Arrays.sort(items);

            int search = 1;
            if (BinarySearchHT.indexOf(items, search) != -1) {
                System.out.println(search);
            }
            search = 33;
            if (BinarySearchHT.indexOf(items, search) != -1) {
                System.out.print(search);
            } else {
                System.out.print("Items " + search + " not found");
            }





        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
