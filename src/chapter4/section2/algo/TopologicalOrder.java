package chapter4.section2.algo;

import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.SymbolDigraph;

public class TopologicalOrder {
    private boolean[] marked;
    private int[] edgeTo;
    private Bag<Integer>[] edgeToList;
    private Stack<Integer> reversePostOrder;
    SymbolDigraph sdg;

    public TopologicalOrder(String filePath, String delim) {
        sdg = new SymbolDigraph(filePath, delim);
        Digraph G = sdg.digraph();

        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        reversePostOrder = new Stack<>();
        edgeToList = new Bag[G.V()];
        for (int i = 0; i < G.V(); i++) {
            edgeToList[i] = new Bag<Integer>();
        }
//        edgeToList[0] = null; // Says this came from no where
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) dfs(G, v);
        }
    }

    public TopologicalOrder(Digraph G) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        reversePostOrder = new Stack<>();

        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) dfs(G, v);
        }

    }

    private void dfs(Digraph G, int s) {
        marked[s] = true;
        for (int w: G.adj(s)) {
            if (!marked[w]) {
                dfs(G, w);
            }
            edgeToList[w].add(s);
        }
        reversePostOrder.push(s);
    }

    public Iterable<Integer> topologicalOrder() {
        return this.reversePostOrder;
    }

    public String nameOf(int v) {
        return sdg.nameOf(v);
    }

    public int indexOf(String v) {
        return sdg.indexOf(v);
    }

    public static void main(String[] args) {
        String file = "src/chapter4/section2/data/precedence.txt";
        String delim = "/";
        TopologicalOrder to = new TopologicalOrder(file, delim);

        for (int x: to.topologicalOrder()) {
            System.out.printf("You can start (%s) once you finish any, ", to.nameOf(x));
            for (int w: to.edgeToList[x]) {
                System.out.printf("{%s} or ", to.nameOf(w) );
            }

            System.out.println();
        }
    }


}
