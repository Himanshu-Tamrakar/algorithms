package chapter4.section3.algo;

public class Edge implements Comparable<Edge> {
    private int v, w;
    private double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return this.weight;
    }

    public int either() {
        return this.v;
    }

    public int other(int v) {
        if (v == this.v) return w;
        return v;
    }

    @Override
    public int compareTo(Edge edge) {
        return Double.compare(this.weight, edge.weight);
    }

    @Override
    public String toString() {
        return "Edge[" +
                "v=" + v +
                ", w=" + w +
                ", weight=" + weight +
                ']';
    }

    public static void main(String[] args) {

    }
}
