package chapter4.section2.algo;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.SymbolGraph;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;
import java.util.TreeMap;

public class SymbolDigraph {
    private TreeMap<String, Integer> map;
    private String[] reverseMap;
    private Digraph G;


    public SymbolDigraph(String fileUrl, String delim) throws FileNotFoundException {
        map = new TreeMap<>();

        File file = new File(fileUrl);
        FileInputStream fis = new FileInputStream(file);
        Scanner scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");
        scanner.useLocale(Locale.US);

        while (scanner.hasNext()) {
            String[] input = scanner.nextLine().split(delim);

            for (int i = 0; i < input.length; i++) {
                if (!map.containsKey(input[i])) {
                    map.put(input[i], map.size());
                }
            }
        }

        reverseMap = new String[map.size()];
        G = new Digraph(map.size());

        for (String key: map.keySet()) {
            reverseMap[map.get(key)] = key;
        }

        fis = new FileInputStream(new File(fileUrl));
        scanner = new Scanner(new BufferedInputStream(fis));

        while (scanner.hasNext()) {
            String[] inputs = scanner.nextLine().split(delim);

            String v = inputs[0];

            for (int i = 1; i < inputs.length; i++) {
                String w = inputs[i];
                G.addEdge(map.get(v), map.get(w));
            }
        }
    }


    public Digraph digraph() {
        return this.G;
    }

    public int indexOf(String v) {
        return map.get(v);
    }

    public String nameOf(int v) {
        return reverseMap[v];
    }

    public boolean contains(String s) {
        return this.map.containsKey(s);
    }


    public static void main(String[] args) throws FileNotFoundException {
        String filename = "src/chapter4/section2/data/routes.txt";
        String delimiter = " ";
        SymbolDigraph sg = new SymbolDigraph(filename, delimiter);
        Digraph graph = sg.digraph();

        while(!StdIn.isEmpty()) {
            String t = StdIn.readLine();
            Iterator var6 = graph.adj(sg.indexOf(t)).iterator();

            while(var6.hasNext()) {
                int v = (Integer)var6.next();
                StdOut.println("   " + sg.nameOf(v));
            }
        }

    }
}
