package chapter4.section3.algo;

import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;

public class KruskalMST {
    private Queue<Edge> mst;
    private MinPQ<Edge> minPQ;

    public KruskalMST(EdgeWeightedGraph G) {
        UF uf = new UF(G.V());
        mst = new Queue<>();
        minPQ = new MinPQ<>();

        for (int v = 0; v < G.V(); v++) {
            for (Edge e: G.adj(v)) {
                if (v < e.other(v)) minPQ.insert(e);
            }
        }

        while (!minPQ.isEmpty() && mst.size() < G.V()) {
            Edge e = minPQ.delMin();
            int v = e.either();
            int w = e.other(v);
            if (uf.find(v) != uf.find(w)) {
                mst.enqueue(e);
                uf.union(v, w);
            }
        }

    }

    public static void main(String[] args) {

    }
}
