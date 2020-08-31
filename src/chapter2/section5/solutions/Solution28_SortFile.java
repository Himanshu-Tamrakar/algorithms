package chapter2.section5.solutions;

import edu.princeton.cs.algs4.MinPQ;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution28_SortFile {
    public static void main1(String[] args) {
        File file = new File("src/chapter2/section5/solutions");
        PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                if (s.compareTo(t1)  > 0) return -1;
                else if (s.compareTo(t1) < 0) return 1;
                else return 0;
            }
        });

        for (String name: file.list()) {
            pq.add(name);
        }

        while (!pq.isEmpty()) {
            System.out.printf("%s\n", pq.poll());
        }
    }

    public static void main(String[] args) {
        File dir = new File(args[0]);
        if (!dir.exists()) {
            throw new IllegalArgumentException("Directory path can not read");
        }
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("Expeciting folder path that contains fils");
        }
        File[] files = dir.listFiles();
        Arrays.sort(files);
        for (File file: files) {
            System.out.printf("%s\n", file.getName());
        }

    }
}
