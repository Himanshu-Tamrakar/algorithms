package chapter4.section3.algo;


import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;

// Minimum Spanning Tree and Single Source Sortest Path from Prims and Dijkstra are different problem.
// Dijkstra calculate the minimum distance from source vertext to other vertex where as MST will calculate the sortest
// Distance from MST tree to other vertex.
public class LazyPrimsMST {
    private MinPQ<Edge> pq;
    private boolean[] marked;
    private Queue<Edge> mst;

    public LazyPrimsMST(EdgeWeightedGraph G) {
        marked = new boolean[G.V()];
        pq = new MinPQ<>();
        mst = new Queue<>();
        visit(G, 0);

        while (!pq.isEmpty()) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (marked[v] && marked[w]) continue;
            mst.enqueue(e);
            if (!marked[v]) visit(G, v);
            if (!marked[w]) visit(G, w);
        }
    }

    private void visit(EdgeWeightedGraph G, int s) {
        marked[s] = true;

        for (Edge e: G.adj(s)) {
            if (!marked[e.other(s)]) pq.insert(e);
        }
    }


    public static void main(String[] args) {

    }
}
