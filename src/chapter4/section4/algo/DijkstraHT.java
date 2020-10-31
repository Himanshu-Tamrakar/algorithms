package chapter4.section4.algo;

import edu.princeton.cs.algs4.*;

public class DijkstraHT {
    private boolean[] marked;
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> minPQ;

    public DijkstraHT(EdgeWeightedDigraph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        minPQ = new IndexMinPQ<>(G.V());
        for (int i = 0; i < G.V(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0;
        minPQ.insert(s, 0.0);

        while (!minPQ.isEmpty()) {
            scan(G, minPQ.delMin());
        }

    }

    private void scan(EdgeWeightedDigraph G, int s) {
//        marked[s] = true;
        for (DirectedEdge e: G.adj(s)) {
//            if (marked[e.to()]) continue;
            relax(e);
        }
    }

    private void relax(DirectedEdge e) {
        int v = e.from();
        int w = e.to();

        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (minPQ.contains(w)) {
                minPQ.decreaseKey(w, distTo[w]);
            } else {
                minPQ.insert(w, distTo[w]);
            }
        }
    }

    public double distTo(int w) {
        return distTo[w];
    }

    public Iterable<DirectedEdge> path(int w) {
        Queue<DirectedEdge> path = new Queue<>();

        for (DirectedEdge x = edgeTo[w]; x != null; x = edgeTo[x.from()]) {
            path.enqueue(x);
        }

        return path;
    }

    public boolean hasPathTo(int w) {
        return distTo[w] < Double.POSITIVE_INFINITY;
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In("src/chapter4/section4/data/tinyEWD.txt"));
        DijkstraHT sp = new DijkstraHT(G, 0);

        for (int v = 1; v < G.V(); v++) {
            System.out.println("Path from 0 to " + v);
            if (sp.hasPathTo(v)) {
                for (DirectedEdge e: sp.path(v)) {
                    System.out.print("[" + e + "] ");
                }
            } else {
                System.out.println("There is not directed path from 0 to " + v);
            }
            System.out.println();
        }
    }


 }
