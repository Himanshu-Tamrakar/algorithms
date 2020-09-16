package chapter3.section4.solutions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class BloomFilter {
    private static final int ARRAY_SIZE = 1000000;
    private static final int K = 4;
    private final int M;
    private boolean[] bloomArray;

    public BloomFilter(int M) {
        this.M = M;
        bloomArray = new boolean[M];
    }

    private int hash0(URL url) {
        return (url.hashCode() * 17 & 0x7fffffff) % M;
    }

    private int hash1(URL url) {
        return (url.hashCode() * 19 & 0x7fffffff) % M;
    }

    private int hash2(URL url) {
        return (url.hashCode() * 31 & 0x7fffffff) % M;
    }

    private int hash3(URL url) {
        return (url.hashCode() * 61 & 0x7fffffff) % M;
    }

    private int[] kHashes(URL url) {
        int[] hashes = new int[K];
        for (int i = 0; i < K; i++) {
            if (i == 0) hashes[i] = hash0(url);
            else if (i == 1) hashes[i] = hash1(url);
            else if (i == 2) hashes[i] = hash2(url);
            else if (i == 3) hashes[i] = hash3(url);
        }
        return hashes;
    }

    public void insert(URL url) {
        int[] h = kHashes(url);
        for (int i = 0; i < K; i++) {
            bloomArray[h[i]] = true;
        }
    }


    private void printHashes(int[] h) {
        for (int i = 0; i < h.length; i++) {
         System.out.printf("%d ", h[i]);
        }
        System.out.printf("\n");
    }

    public boolean isPresent(URL url) {
        int[] h = kHashes(url);
        printHashes(h);
        for (int i = 0; i < K; i++) {
            if (!bloomArray[h[i]]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BloomFilter bloomFilter = new BloomFilter(100000);
        System.out.printf("Enter an url\n");
        while (scanner.hasNext()) {
            String str = scanner.next();
            try {
                URL url = new URL(str);

                if (!bloomFilter.isPresent(url)) {
                    System.out.printf("Inserting %s\n", url);
                    bloomFilter.insert(url);
                } else {
                    System.out.printf("url %s may be present so it could be malacious\n", url);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }



}
