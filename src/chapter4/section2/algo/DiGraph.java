package chapter4.section2.algo;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;

import java.util.Iterator;

public class DiGraph {
    private int v;
    private int e;
    private Bag<Integer>[] adj;

    public DiGraph(int v) {
        this.v = v;
        this.e = 0;
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<>();
        }
    }

    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        this.e++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public int V() {
        return this.v;
    }

    public int E() {
        return this.e;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int v = 0; v < this.v; v++) {
            s.append(v + " -> ");
            for (int w: adj[v]) {
                s.append(w + " ");
            }
            s.append(System.getProperty("line.separator"));
        }
        return s.toString();
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= this.v) throw new IllegalArgumentException("Vertext can not be grater than or equal to:" + this.v);
    }

}
