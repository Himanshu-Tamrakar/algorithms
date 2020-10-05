package chapter4.section1.algo;

import edu.princeton.cs.algs4.*;


public class BreadthFirstSearchHT {
    private boolean[] marked;
    private int[] edgeTo;
    private int V;
    private int s;

    public BreadthFirstSearchHT(Graph G, int s) {
        this.s = s;
        this.V = G.V();
        this.marked = new boolean[V];
        this.edgeTo = new int[V];
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(s);
        edgeTo[s] = s;
        marked[s] = true;
        while (!queue.isEmpty()) {
            int v = queue.dequeue();

            for (int w: G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.enqueue(w);
                }
            }
        }

    }


    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int w) {
        if (!hasPathTo(w)) return null;

        Stack<Integer> stack = new Stack<>();
        int v = w;

        for (; v != s ; v = edgeTo[v]) {
            stack.push(v);
        }

        stack.push(v);
        return stack;
    }


    public static void main(String[] args) {
        Graph G = new Graph(new In("src/chapter4/section1/data/tinyCG.txt"));
        int s = 0;
        BreadthFirstSearchHT search = new BreadthFirstSearchHT(G, s);
        for (int v = 0; v < G.V(); v++)
        {
            StdOut.print(s + " to " + v + ": ");
            if (search.hasPathTo(v))
                for (int x: search.pathTo(v) ) {
                    if (x == s) StdOut.print(x);
                    else StdOut.print("-" + x);
                }
            StdOut.println();
        }
    }


}
