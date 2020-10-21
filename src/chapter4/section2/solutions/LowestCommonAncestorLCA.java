package chapter4.section2.solutions;


import edu.princeton.cs.algs4.Digraph;

// Learn More
// https://www.hackerrank.com/topics/lowest-common-ancestor
// This could not be a smartest solution.
public class LowestCommonAncestorLCA {
    private boolean[] marked;
    private boolean[] parent;
    public LowestCommonAncestorLCA(Digraph G, int v, int w) {
        marked = new boolean[G.V()];
        parent = new boolean[G.V()];
    }

    private void dfs(Digraph G, int s) {
        marked[s] = true;

        for (int w: G.adj(s)) {

        }
    }

}
