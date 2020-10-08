package chapter4.section1.algo;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;
import java.util.TreeMap;

public class SymbolGraph {
    Scanner scanner = null;
    private TreeMap<String, Integer> map;
    private String[] inverseMap;
    Graph G;

    public SymbolGraph(String url, String delimeter) throws FileNotFoundException {
        map = new TreeMap<>();

        /******************* Reading File**********************/
        File file = new File(url);
        FileInputStream fis = new FileInputStream(file);
        this.scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");
        this.scanner.useLocale(Locale.US);
        /******************* Reading File**********************/


        // Creating Map String to Integer
        while (scanner.hasNext()) {
            String[] a = scanner.nextLine().split(delimeter);

            for (int v = 0; v < a.length; v++) {
                if (!map.containsKey(a[v])) {
                    map.put(a[v], map.size());
                }
            }
        }


        // Creating Map Integer to String
        inverseMap = new String[map.size()];
        for (String key: map.keySet()) {
            inverseMap[map.get(key)] =  key;
        }

        G = new Graph(inverseMap.length);

        /******************* Reading File**********************/
        file = new File(url);
        fis = new FileInputStream(file);
        this.scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");
        this.scanner.useLocale(Locale.US);
        /******************* Reading File**********************/
        while (scanner.hasNext()) {
            String[] inputs = scanner.nextLine().split(delimeter);
            String v = inputs[0];

            for (int w = 1; w < inputs.length; w++) {
                G.addEdge(map.get(v), map.get(inputs[w]));
            }
        }

    }

    public Graph graph() {
        return this.G;
    }

    public int indexOf(String v) {
        return map.get(v);
    }

    public String nameOf(int v) {
        return inverseMap[v];
    }


    public static void main(String[] args) throws FileNotFoundException {
//        String filename = "src/chapter4/section1/data/routes.txt";
//        String delim = " ";
        String filename = "src/chapter4/section1/data/movies.txt";
        String delim = "/";
        SymbolGraph sg = new SymbolGraph(filename, delim);
        Graph G = sg.graph();
        while (StdIn.hasNextLine())
        {
            String source = StdIn.readLine();
            for (int w : G.adj(sg.indexOf(source)))
                StdOut.println(" " + sg.nameOf(w));
        }
    }
}
