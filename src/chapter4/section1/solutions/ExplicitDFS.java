package chapter4.section1.solutions;

import edu.princeton.cs.algs4.*;

public class ExplicitDFS {
    private boolean[] marked;
    private int[] edgeTo;
    public ExplicitDFS(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        dfs(G, s);
    }

    // Check Book site: Non recursive 28 problem
    private void dfs(Graph G, int s) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(s);
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (!marked[v]) {
                marked[v] = true;
                for (int w : G.adj(v)) {
                    if (!marked[w]) {
                        edgeTo[w] = v;
                        stack.push(w);
                    }
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        Stack<Integer> container = new Stack<>();
        int x = v;

        for (; x != edgeTo[x]; x = edgeTo[x]) {
            container.push(x);
        }

        container.push(x);
        return container;
    }


    public static void main(String[] args) {
        Graph G = new Graph(new In("/home/decimal/Learning/Java/algorithms/src/chapter4/section1/data/tinyCG.txt"));
        ExplicitDFS edfs = new ExplicitDFS(G, 0);
        DepthFirstPaths dfs = new DepthFirstPaths(G, 0);

        if (edfs.hasPathTo(5)) {
            for (int w: edfs.pathTo(5)) {
                System.out.printf("%d ", w);
            }
        }

        System.out.printf("\n-----------------\n");

        if (dfs.hasPathTo(5)) {
            for (int w: dfs.pathTo(5)) {
                System.out.printf("%d ", w);
            }
        }

    }

}
