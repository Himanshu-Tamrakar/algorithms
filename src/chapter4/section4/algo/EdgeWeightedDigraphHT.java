package chapter4.section4.algo;

import chapter4.section3.algo.Edge;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Queue;

import java.util.LinkedList;
import java.util.List;

public class EdgeWeightedDigraphHT {
    private static final String NEWLINE = System.getProperty("line.separator");
    private Bag<DirectedEdgeHT>[] adj;
    private int E;
    private int V;
    public EdgeWeightedDigraphHT(int v) {
        this.V = v;
        this.E = 0;
        this.adj = new Bag[this.V];

        for (int i = 0; i < this.V; i++) {
            adj[i] = new Bag<DirectedEdgeHT>();
        }
    }

    private void addEdge(DirectedEdgeHT e) {
        validate(e.from());
        validate(e.to());
        int v = e.from();
        this.adj[v].add(e);
        this.E++;
    }

    private Iterable<DirectedEdgeHT> adj(int v) {
        return this.adj[v];
    }

    private Iterable<DirectedEdgeHT> edges() {
        List<DirectedEdgeHT> list = new LinkedList<DirectedEdgeHT>();
        for (int v = 0; v < this.V; v++) {
            for (DirectedEdgeHT e: adj(v)) {
                list.add(e);
            }
        }
        return list;
    }

    public int V() {
        return this.V;
    }

    public int E() {
        return this.E;
    }

    private void validate(int v) {
        if (v < 0 || v >= V ) {
            throw new IllegalArgumentException("Verext index " + v + " out of range; Range is 0 to " + (this.V-1));
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        for (int v = 0; v < this.V; v++) {
            s.append(v + " => ");
            for (DirectedEdgeHT e: adj(v)) {
                s.append(e);
                s.append(" ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public static void main(String[] agrs) {
        EdgeWeightedDigraphHT G = new EdgeWeightedDigraphHT(4);
        G.addEdge(new DirectedEdgeHT(0, 1 , 1));
        G.addEdge(new DirectedEdgeHT(1, 2 , 2));
        G.addEdge(new DirectedEdgeHT(2, 3 , 3));
//        G.addEdge(new DirectedEdgeHT(4, 3 , 3));

        System.out.println(G);
    }
}
