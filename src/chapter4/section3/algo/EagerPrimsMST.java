package chapter4.section3.algo;

import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;


public class EagerPrimsMST {
    private boolean[] marked;
    private IndexMinPQ<Double> minPQ;
    private double[] distTO;
    private Edge[] edgeTo;

    public EagerPrimsMST(EdgeWeightedGraph G) {
        marked = new boolean[G.V()];
        minPQ = new IndexMinPQ(G.V());
        distTO = new double[G.V()];
        edgeTo = new Edge[G.V()];
        for (int i = 0; i < G.V(); i++) {
            distTO[i] = Double.POSITIVE_INFINITY;
        }


        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                distTO[s] = 0.0;
                visit(G, s);
                while (!minPQ.isEmpty()) {
                    visit(G, minPQ.delMin());
                }
            }
        }
    }

    private void visit(EdgeWeightedGraph G, int s) {
        marked[s] = true;
        for (Edge e: G.adj(s)) {
            int w = e.other(s);
            if (marked[w]) continue;
            if (e.weight() < distTO[w]) {
                distTO[w] = e.weight();
                edgeTo[w] = e;
                if (minPQ.contains(w)) {
                    minPQ.changeKey(w, distTO[w]);
                } else {
                    minPQ.insert(w, distTO[w]);
                }

            }
        }
    }


    public Iterable<Edge> edges() {
        Queue<Edge> mst = new Queue<>();
        for (int v = 0; v < edgeTo.length; v++) {
            if (edgeTo[v] != null) mst.enqueue(edgeTo[v]);
        }
        return mst;
    }


    public static void main(String[] args) {
        EdgeWeightedGraph G = new EdgeWeightedGraph(new In("src/chapter4/section3/data/tinyEWG.txt"));
        EagerPrimsMST mst = new EagerPrimsMST(G);
        for (Edge e: mst.edgeTo) {
            System.out.println(e);
        }

        System.out.println("-----------");


        PrimMST m = new PrimMST(G);
        for (Edge e: m.edges()) {
            System.out.println(e);
        }

    }
}
