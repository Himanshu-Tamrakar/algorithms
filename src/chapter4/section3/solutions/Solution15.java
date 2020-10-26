package chapter4.section3.solutions;

import edu.princeton.cs.algs4.*;

public class Solution15 {

    private static boolean[] marked;
    private static Queue<Edge> cycle;
    private static Edge[] edgeTo;

    public static void addEdge(EdgeWeightedGraph G, PrimMST mst, Edge edge) {
        EdgeWeightedGraph graph = new EdgeWeightedGraph(G.V());
        graph.addEdge(edge);
        for (Edge e: mst.edges()) {
            graph.addEdge(e);
        }

        marked = new boolean[G.V()];
        cycle = null;
        edgeTo = new Edge[G.V()];
        dfs(graph, 0);

        Edge E = null;

        for (Edge e: cycle) {
            if (E == null) {
                E  = e;
            } else if (E.weight() < e.weight()) {
                E = e;
            }
        }

        System.out.println("Remove Edge " +  E);

    }


    private static void dfs(EdgeWeightedGraph G, int s) {
        marked[s] = true;

        for (Edge e: G.adj(s)) {
            int w = e.other(s);

            if (!marked[w]) {
                edgeTo[w] = e;
                dfs(G, w);
            } else if (w != edgeTo[s].other(s) && cycle == null) {
                cycle = new Queue<>();
                cycle.enqueue(e);
                int old = s;
                int startW = w;
                Edge x = edgeTo[old];
                for (; (startW != x.either() && startW != x.other(x.either()) ); x = edgeTo[old]) {
                    cycle.enqueue(x);
                    old = x.other(old);
                }
                return;
            }
        }
    }



    public static void main(String[] args) {
        Solution14 sol = new Solution14();

        EdgeWeightedGraph G = new EdgeWeightedGraph(new In("src/chapter4/section3/data/tinyEWG.txt"));
        PrimMST mst = new PrimMST(G);

        addEdge(G, mst, new Edge(4, 7, 0.1));


    }
}
