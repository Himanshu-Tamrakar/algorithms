package chapter4.section3.algo;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class EdgeWeightedGraph {
    private static final String NEWLINE = System.getProperty("line.separator");
    private Bag<Edge>[] adj;
    private int V;
    private int E;

    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<>();
        }
    }

    public EdgeWeightedGraph(In in) {
        this(in.readInt());
        int E = in.readInt(); // next int consider to be total number of edges
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            addEdge(new Edge(v, w, weight));
        }
    }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);

        adj[v].add(e);
        adj[w].add(e);
        this.E++;
    }

    public Iterable<Edge> adj(int v) {
        return this.adj[v];
    }

    public int V() {
        return this.V;
    }

    public int E() {
        return this.E;
    }

    public Iterable<Edge> edges() {
        Queue<Edge> queue = new LinkedList<>();
        for (int v = 0; v < this.V; v++) {
            for (Edge e: this.adj(v)) {
                if (v > e.other(v)) queue.add(e);
            }
        }
        return queue;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int v = 0; v < V; v++) {
            s.append(v + " => ");
            for (Edge e: this.adj(v)) {
                s.append(NEWLINE);
                s.append("     " + e + " ");
            }
            s.append(NEWLINE);
        }

        return s.toString();
    }

    public static void main(String[] args) {
        EdgeWeightedGraph G = new EdgeWeightedGraph(new In("src/chapter4/section3/data/tinyEWG.txt"));
        System.out.println(G);
    }
}
