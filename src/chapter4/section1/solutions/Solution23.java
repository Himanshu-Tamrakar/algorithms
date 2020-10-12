package chapter4.section1.solutions;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.SymbolGraph;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Solution23 {
    Graph G;
    SymbolGraph sg;
    BreadthFirstPaths bfs;
    int[] bacon;
    public Solution23(String fileUrl, String delim, String source) throws FileNotFoundException {
        sg = new SymbolGraph(fileUrl, delim);
        G = sg.graph();
        if (!sg.contains(source)) {
            System.out.println(source + " not in database.");
            return;
        }

        bfs = new BreadthFirstPaths(G, sg.indexOf(source));

        // Histogram for bacon 100
        int MAX_BACON = 100;
        int[] histo = new int[MAX_BACON + 1];

        // Adding All the perfomers;
        FileInputStream fis = new FileInputStream(new File(fileUrl));
        Scanner scanner = new Scanner(new BufferedInputStream(fis));
        HashSet<String> performer = new HashSet<>();
        HashSet<String> movie = new HashSet<>();
        while (scanner.hasNext()) {
            String[] inputs =  scanner.nextLine().split(delim);
            if (!movie.contains(inputs[0])) movie.add(inputs[0]);

            for (int i = 1; i < inputs.length; i++) {
                if (!performer.contains(inputs[i])) performer.add(inputs[i]);
            }
        }

        for (String performerName: performer) {
            int graphIndex = sg.indexOf(performerName);
            int bacon = Math.min(MAX_BACON, bfs.distTo(graphIndex));
            histo[bacon]++;
        }

        for (int i = 0; i < histo.length; i++) {
            System.out.printf("Bacon %d has %d times\n", i, histo[i]);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String filename = "src/chapter4/section1/data/movies.txt";
        String delim = "/";
        String bfsSource = "Bacon, Kevin";

        Solution23 sol = new Solution23(filename, delim, bfsSource);
    }





}
