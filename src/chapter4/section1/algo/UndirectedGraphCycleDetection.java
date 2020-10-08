package chapter4.section1.algo;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;


// Assuming no self loop and no parallel edges
public class UndirectedGraphCycleDetection {
    private boolean[] marked;
    private int[] edgeTo;
    Stack<Integer> cycle = null;

    public UndirectedGraphCycleDetection(Graph G) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        dfs(G, 0, 0);
    }

    private void dfs(Graph G, int v, int immediateParent) {
        marked[v] = true;

        for (int w: G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w, v);
            } else if (immediateParent != w && cycle == null) {
                // It means a link is going from child to some grand parent
                cycle = new Stack<>();
                cycle.push(w);
                int x = v;
                for (;x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(x);
            }
        }
    }


    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) {
        Graph G = new Graph(new In("src/chapter4/section1/data/cycle.txt"));

        UndirectedGraphCycleDetection cycleDetection = new UndirectedGraphCycleDetection(G);

        System.out.printf("Is this graph contains a cycle? Ans. is %b\nand cycle is \n", cycleDetection.hasCycle());

        if (cycleDetection.hasCycle()) {
            for (int v: cycleDetection.cycle()) {
                System.out.printf("%d ", v);
            }
        }

    }
}
