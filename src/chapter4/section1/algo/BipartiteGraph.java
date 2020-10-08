package chapter4.section1.algo;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.ST;

import java.util.HashMap;

public class BipartiteGraph {
    private final boolean BLACK = false;
    private final boolean RED = true;

    private boolean[] color;
    private boolean[] marked;
    private boolean isBipartite;

    public BipartiteGraph(Graph G) {
        this.color = new boolean[G.V()];
        this.marked = new boolean[G.V()];
        this.isBipartite = true;
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;

        for (int w: G.adj(v)) {
            if (!marked[w]) {
                color[w] = !color[v];
                dfs(G, w);
            } else if(color[w] == color[v]) {
                this.isBipartite = false;
            }
        }
    }

    public boolean isGraphBipartite() {
        return this.isBipartite;
    }
}
