package chapter4.section2.solutions;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

public class Solution3 {
    private int V;
    private int E;
    private Bag<Integer>[] adj;
    private int[] indegree;
    public Solution3(Digraph G) {
        this.V = G.V();
        this.E = G.E();
        adj = new Bag[G.V()];
        indegree = new int[G.V()];


        for (int v = 0; v < G.V(); v++) {
            adj[v] = new Bag<>();
            indegree[v] = G.indegree(v);
        }

        for (int v = 0; v < G.V(); v++) {
            Stack<Integer> stack = new Stack<>();

            for (int w: G.adj(v)) {
                stack.push(w);
            }

            for (int w: stack) {
                adj[v].add(w);
            }
        }


    }
}
