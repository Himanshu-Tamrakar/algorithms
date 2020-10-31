package chapter4.section4.algo;

import edu.princeton.cs.algs4.DirectedEdge;

public class DirectedEdgeHT {
    private int v, w;
    private double weight;

    public DirectedEdgeHT(int from, int to, double weight) {
        this.v = from;
        this.w = to;
        this.weight = weight;
    }

    public int from() {
        return this.v;
    }

    public int to() {
        return this.w;
    }

    public double weight() {
        return this.weight;
    }

    @Override
    public String toString() {
        return "[" + v + ", " + w + " " + String.format("%5.2f", weight) + "] ";
    }

    public static void main(String[] args) {
        DirectedEdgeHT e = new DirectedEdgeHT(0, 1, 0.1);
        System.out.println(e);
    }
}
