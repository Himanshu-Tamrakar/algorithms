package chapter4.section4.algo;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

public class EdgeWeightedDAGSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public EdgeWeightedDAGSP(EdgeWeightedDigraph G, int s) {
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];

        for (int i = 0; i < G.V(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }

        distTo[s] = 0;
        EdgeWeightedDigraphTopologicalSort topological = new EdgeWeightedDigraphTopologicalSort(G, s);
        for (int v: topological.reversePostOrder()) {
            relax(G, v);
        }
    }

    private void relax(EdgeWeightedDigraph G, int s) {
        for (DirectedEdge e: G.adj(s)) {
            int v = e.from();
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
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

    public static void main(String[] args) {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In("src/chapter4/section4/data/tinyEWDAG.txt"));
        EdgeWeightedDAGSP sp = new EdgeWeightedDAGSP(G, 5);

        for (int v = 1; v < G.V(); v++) {
            System.out.println("Path from 5 to " + v);
            if (sp.hasPathTo(v)) {
                for (DirectedEdge e: sp.path(v)) {
                    System.out.print("[" + e + "] ");
                }
            } else {
                System.out.println("There is not directed path from 5 to " + v);
            }
            System.out.println();
        }
    }

}
