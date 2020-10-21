package chapter4.section2.algo;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import sun.awt.X11.XStateProtocol;

public class DirectedCycle {
    private boolean[] marked;
    private boolean[] onStack;
    private boolean iSCycle;
    private int[] edgeTo;
    private Stack<Integer> cycle;

    public DirectedCycle(Digraph G) {
        marked = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.iSCycle = false;
        this.cycle = new Stack<>();
        dfs(G, 0);
    }


    private void dfs(Digraph G, int s) {
        marked[s] = true;
        onStack[s] = true;
        for (int w: G.adj(s)) {
            if (!marked[w]) {
                edgeTo[w] = s;
                dfs(G, w);
            } else if (onStack[w] && !this.iSCycle){
             this.iSCycle = true;
             this.cycle.push(w);
             int x = s;
             for (;w != x; x = edgeTo[x]) {
                this.cycle.push(x);
             }
             this.cycle.push(x);
             return;
            }
        }
        onStack[s] = false;
    }

    public boolean hasCycle() {
        return this.iSCycle;
    }

    public Iterable<Integer> cycle() {
        return this.cycle;
    }

    public static void main(String[] args) {
        Digraph G = new Digraph(new In("src/chapter4/section2/data/tinyDG.txt"));
        DirectedCycle dc = new DirectedCycle(G);

        if (dc.hasCycle()) {
            for (int x: dc.cycle()) {
                System.out.printf("%d -> ", x);
            }
        }
    }
}
