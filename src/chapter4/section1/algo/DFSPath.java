package chapter4.section1.algo;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

//import java.util.Iterator;
//import java.util.Stack;

public class DFSPath {
    private boolean[] marked; // For dfs to cheche already visited
    private int[] edgeTo; // Tell the path from destination to source
//    private int[] components; // Tell whether two vertives belong to the same component or not
//    private int componentNumber; // Current connected compoent number
    private int V;
    private int s;

    public DFSPath(Graph G, int s) {
        this.s = s;
        this.V = G.V();
        this.marked = new boolean[V];
        this.edgeTo = new int[V];
//        this.components = new int[V];
//        this.componentNumber = 0;
        for (int v = 0; v < V; v++) {
            if (!marked[v]) {
                dfs(G, v);
//                componentNumber++;
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
//        components[v] = componentNumber;

        for (int w: G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
                edgeTo[w] = v;
            }
        }
    }

    // Has a path from s to w, assuming graph is connected
    public boolean hasPathTo(int w) {
        return marked[w];
    }

    // Path from s to w. Assuming graph is connected. Can be modifiy as we have connected components
    public Iterable<Integer> pathTo(int w) {
        if (!hasPathTo(w)) return null;

        Stack<Integer> path = new Stack<>();
        int v = w;
        for (; v != s ; v = edgeTo[v]) {
            path.push(v);
        }
        path.push(s);
        return path;
    }

//    public boolean connected(int v, int w) {
//        return components[v] == components[w];
//    }

    public static void main(String[] args)
    {

        Graph G = new Graph(new In("src/chapter4/section1/data/tinyCG.txt"));
        int s = 0;
        DFSPath search = new DFSPath(G, s);
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
