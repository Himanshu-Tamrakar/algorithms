package chapter4.section1.solutions;

import edu.princeton.cs.algs4.Graph;

import java.util.ArrayList;
import java.util.Iterator;

public class Solutions3 {
    private ArrayList<Integer>[] adj;
    private int V;
    private int E;
    public Solutions3(Graph graph) {
        adj = new ArrayList[graph.V()];
        V = graph.V();
        E = graph.E();

        for (int v = 0; v < V; v++) {
            adj[v] = new ArrayList<>();
        }

        for (int v = 0; v < graph.V(); v++) {
            for (int w: graph.adj(v)) {
                adj[v].add(w);
            }
        }
    }

    public int V() {
        return this.V;
    }

    public int E() {
        return this.E;
    }

    public void add(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        this.adj[v].add(w);
        this.adj[w].add(v);
        E++;

    }

    public Iterator<Integer> adj(int v) {
        validateVertex(v);
        return this.adj[v].iterator();
    }


    private void validateVertex(int v) {
        if (v < 0 || v >= this.V) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (this.V - 1));
        }
    }


}
