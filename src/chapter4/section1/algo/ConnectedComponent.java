package chapter4.section1.algo;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class ConnectedComponent {
    private boolean[] marked;
    private int[] id;
    int count;

    public ConnectedComponent(Graph G) {
        this.count = 0;
        this.marked = new boolean[G.V()];
        this.id = new int[G.V()];

        for (int i = 0; i < G.V(); i++) {
            if (!marked[i]) {
                dfs(G, i);
                count++;
            }
        }

    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w: G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }

    public int count() {
        return count;
    }

    public static void main(String[] args)
    {
        Graph G = new Graph(new In("src/chapter4/section1/data/tinyG.txt"));
        ConnectedComponent cc = new ConnectedComponent(G);
        int M = cc.count();
        StdOut.println(M + " components");
        Bag<Integer>[] components;
        components = (Bag<Integer>[]) new Bag[M];
        for (int i = 0; i < M; i++)
            components[i] = new Bag<Integer>();
        for (int v = 0; v < G.V(); v++)
            components[cc.id(v)].add(v);
        for (int i = 0; i < M; i++)
        {
            for (int v: components[i])
                StdOut.print(v + " ");
            StdOut.println();
        }
    }
}
