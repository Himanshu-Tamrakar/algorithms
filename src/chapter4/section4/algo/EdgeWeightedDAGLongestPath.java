package chapter4.section4.algo;

import edu.princeton.cs.algs4.*;

public class EdgeWeightedDAGLongestPath {
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    public EdgeWeightedDAGLongestPath(EdgeWeightedDigraph G, int s) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];

        for (int i = 0; i < G.V(); i++) {
            distTo[i] = Double.NEGATIVE_INFINITY;
        }
        distTo[s] = 0;

        EdgeWeightedDigraphTopologicalSort topologicalSort = new EdgeWeightedDigraphTopologicalSort(G, s);
        for (int v: topologicalSort.reversePostOrder()) {
            relax(G, v);
        }
    }

    private void relax(EdgeWeightedDigraph G, int s) {
        for (DirectedEdge e: G.adj(s)) {
            int v = e.from();
            int w = e.to();
            if (distTo[w] < distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
            }
        }
    }

    public Iterable<DirectedEdge> path(int w) {
        Queue<DirectedEdge> path = new Queue<>();
        for (DirectedEdge x = edgeTo[w]; x != null; x = edgeTo[x.from()]) {
            path.enqueue(x);
        }
        return path;
    }

    public boolean hasPathTo(int w) {
        return this.distTo[w] < Double.POSITIVE_INFINITY;
    }

    public double distTo(int w) {
        return distTo[w];
    }
}
