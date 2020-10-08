package chapter4.section1.algo;


import edu.princeton.cs.algs4.DegreesOfSeparation;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Interesting challenge uses bipartite graph -> BFS for shortest path and boom = result;
 */
public class DegreeOfSaparation {
    Scanner scanner;
    TreeMap<String, Integer> st;
    String[] reverseSt;
    Graph G;

    boolean[] marked;
    int[] edgeTo;
    int[] distTo;

    public DegreeOfSaparation(String fileUrl, String delim, String bfsSource) throws FileNotFoundException {
        st = new TreeMap<>();

        File file = new File(fileUrl);
        FileInputStream fileInputStream = new FileInputStream(file);
        scanner = new Scanner(new BufferedInputStream(fileInputStream), "UTF-8");
        scanner.useLocale(Locale.US);

        while (scanner.hasNext()) {
            String[] inputs = scanner.nextLine().split(delim);
            for (int i = 0; i < inputs.length; i++) {
                if (!st.containsKey(inputs[i])) st.put(inputs[i], st.size());
            }
        }

        reverseSt = new String[st.size()];

        for (String key: st.keySet()) {
            reverseSt[st.get(key)] = key;
        }

        G = new Graph(reverseSt.length);
        file = new File(fileUrl);
        fileInputStream = new FileInputStream(file);
        scanner = new Scanner(new BufferedInputStream(fileInputStream), "UTF-8");
        scanner.useLocale(Locale.US);

        while (scanner.hasNext()) {
            String[] inputs = scanner.nextLine().split(delim);
            String v = inputs[0];
            for (int i = 1; i < inputs.length; i++) {
                String w = inputs[i];
                G.addEdge(st.get(v), st.get(w));
            }
        }

        bfs(G, bfsSource);
    }

    public int indexOf(String key) {
        return st.get(key);
    }

    public String nameOf(int v) {
        return reverseSt[v];
    }

    public Graph graph() {
        return this.G;
    }

    private void bfs(Graph G, String name) {
        int s = indexOf(name);
        edgeTo = new int[st.size()];
        marked = new boolean[st.size()];
        distTo = new int[st.size()];

        Queue<Integer> queue = new Queue<>();

        queue.enqueue(s);
        marked[s] = true;
        edgeTo[s] = s;
        distTo[s] = 0;


        while (!queue.isEmpty()) {
            int v = queue.dequeue();

            for (int w: G.adj(v)) {
                if (!marked[w]) {
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    edgeTo[w] = v;
                    queue.enqueue(w);
                }
            }
        }

    }

    public Iterable<String> kevinBecon(String from) {
        int w = indexOf(from);
        int s = indexOf("Bacon, Kevin");
        Queue<String> stack = new Queue<>();
        int x = w;
        for ( ;x != s; x = edgeTo[x]) {
            stack.enqueue(reverseSt[x]);
        }
        stack.enqueue(reverseSt[x]);
        return stack;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String filename = "src/chapter4/section1/data/movies.txt";
        String delim = "/";
        String bfsSource = "Bacon, Kevin";
        DegreeOfSaparation degreeOfSaparation = new DegreeOfSaparation(filename, delim, bfsSource);

        for (String s: degreeOfSaparation.kevinBecon("Kidman, Nicole")) {
            System.out.printf("%s\n", s);
        }

        System.out.print("----------------\n");

        DegreesOfSeparation.main(new String[] {filename, delim, bfsSource});
    }

}
