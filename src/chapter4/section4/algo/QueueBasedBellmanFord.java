package chapter4.section4.algo;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

// Single source sortest distance for graph having cycle, negetive weight, positive weight but not negetive cycle.
// Present of negetive cycle in a graph make sortest path problem hard to solve and till now we have only algorithms to solve is run exponential. See chapter 6.
// Bellman-Ford negative cycle detection. Show that if any edge is relaxed during the Vth pass of the generic Bellman-Ford algorithm, then the edgeTo[] array has a directed cycle and any such cycle is a negative cycle.
public class QueueBasedBellmanFord {
    private boolean[] Q;
    private Queue<Integer> queue;
    private double[] distTo;
    private DirectedEdge[] edgeTo;

    public QueueBasedBellmanFord(EdgeWeightedDigraph G, int s) {
        Q = new boolean[G.V()];
        queue = new Queue<>();
        queue.enqueue(s);
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];

        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }

        distTo[s] = 0;
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            relax(G, v);
            Q[v] = false;
        }
    }

    private void relax(EdgeWeightedDigraph G, int s) {
        for (DirectedEdge e: G.adj(s)) {
            int v = e.from();
            int w = e.to();

            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;

                if (!Q[w]) {
                    queue.enqueue(w);
                }
            }
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

        for (DirectedEdge x = edgeTo[w]; x != null; x = edgeTo[x.from()]) {
            queue.enqueue(x);
        }
        return queue;
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In("src/chapter4/section4/data/tinyEWDn.txt"));
        QueueBasedBellmanFord sp = new QueueBasedBellmanFord(G, 0);

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
