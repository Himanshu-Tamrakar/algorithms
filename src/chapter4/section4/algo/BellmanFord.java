package chapter4.section4.algo;

import edu.princeton.cs.algs4.*;

// Single source sortest distance for graph having cycle, negetive weight, positive weight but not negetive cycle.
// Present of negetive cycle in a graph make sortest path problem hard to solve and till now we have only algorithms to solve is run exponential. See chapter 6.
// Bellman-Ford negative cycle detection. Show that if any edge is relaxed during the Vth pass of the generic Bellman-Ford algorithm, then the edgeTo[] array has a directed cycle and any such cycle is a negative cycle.
public class BellmanFord {
    private double[] distTo;
    private DirectedEdge[] edgetTo;
    public BellmanFord(EdgeWeightedDigraph G, int s) {
        distTo = new double[G.V()];
        edgetTo = new DirectedEdge[G.V()];

        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0;

        for (int pass = 0; pass < G.V(); pass++) {
            for (int v = 0; v < G.V(); v++) {
                for (DirectedEdge e: G.adj(v)) {
                    relax(e);
                }
            }
        }


    }

    private void relax(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgetTo[w] = e;
        }
    }

    public double distTo(int w) {
        return distTo[w];
    }

    public boolean hasPathTo(int w) {
        return distTo[w] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> path(int w) {
        Queue<DirectedEdge> queue = new Queue<>();

        for (DirectedEdge x = edgetTo[w]; x != null; x = edgetTo[x.from()]) {
            queue.enqueue(x);
        }
        return queue;
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In("src/chapter4/section4/data/tinyEWDn.txt"));
        BellmanFord sp = new BellmanFord(G, 0);
        for (int v = 0; v < G.V(); v++) {
            System.out.printf("\ndistance from 0 to %d, is %f \n", v, sp.distTo[v]);
            if (sp.hasPathTo(v)) {
                for (DirectedEdge e: sp.path(v)) {
                    System.out.printf("%s ", e);
                }
            }

        }
    }


}
