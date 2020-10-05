package chapter4.section1.algo;

import edu.princeton.cs.algs4.Graph;

public class DepthFirstSearchHT {
    private boolean[] marked;
    private int count;
    private int V;
    public DepthFirstSearchHT(Graph G, int s) {
        this.V = G.V();
        this.marked = new boolean[V];
        this.count = 0;
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        count++;
        for (int w: G.adj(v)) {
            if (!marked[w])  {
                dfs(G, w);
            }
        }
    }

    public boolean marked(int w) {
        return marked[w];
    }

    public int count() {
        return count;
    }

    private void validateVerter(int v) {
        if (v < 0 && v >= V) throw new IllegalArgumentException("Vertex " + v + " out of bound");
    }

    public static void main(String[] args) {

    }

}
