package chapter4.section4.algo;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class EdgeWeightedDigraphTopologicalSort {
    private boolean[] marked;
    private Stack<Integer> order;

    public EdgeWeightedDigraphTopologicalSort(EdgeWeightedDigraph G, int s) {
        marked = new boolean[G.V()];
        order = new Stack<>();
        dfs(G, s);
    }


    private void dfs(EdgeWeightedDigraph G, int s) {
        marked[s] = true;

        for (DirectedEdge e: G.adj(s)) {
            int v = e.from();
            int w = e.to();
            if (!marked[w]) {
                dfs(G, w);
            }
            order.push(v);
        }
    }

    public Iterable<Integer> reversePostOrder() {
        return this.order;
    }

    public static void main(String[] args) {

    }
}
