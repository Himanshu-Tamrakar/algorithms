package chapter4.section2.algo;

import edu.princeton.cs.algs4.DepthFirstOrder;
import edu.princeton.cs.algs4.Digraph;

// Solves the query two vertices are connected like is v connected to w, is w conneced to v, is x connected to any other vertices
// Take preprocessing time proportional to V+E
public class KosarajuSCC {

    private boolean[] marked;
    private int count;
    private int[] id;
    public KosarajuSCC(Digraph G) {
        marked = new boolean[G.V()];
        count = 0;
        id = new int[G.V()];
        DepthFirstOrder dfo = new DepthFirstOrder(G.reverse());
        for (int s: dfo.reversePost()) {
            if (!marked[s]) {
                dfs(G, s);
                ++count;
            }
        }
    }

    private void dfs(Digraph G, int s) {
        marked[s] = true;
        id[s] = count;
        for (int w: G.adj(s)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }

    // Total connected components
    public int count() {
        return this.count;
    }

}
