package chapter4.section3.solutions;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.Queue;

import java.util.HashSet;

/**
 * If graph has cycle and in cycle edges two edges weights are same then smt can not be unique
 */
public class UniqueMST {
    private boolean[] marked;
    private Edge[] edgeTo;
    public UniqueMST(EdgeWeightedGraph G) {
        marked = new boolean[G.V()];
        edgeTo = new Edge[G.V()];

        dfs(G, 0);

    }

    private void dfs(EdgeWeightedGraph G, int s) {
        marked[s] = true;

        for (Edge e: G.adj(s)) {
            int w = e.other(s);

            if (!marked[w]) {
                edgeTo[w] = e;
                dfs(G, w);
            } else if (edgeTo[s] != null && w != edgeTo[s].other(s)) {
                Queue<Edge> cycle = new Queue<>();
                cycle.enqueue(e);
                int old = s;
                int startW = w;
                Edge x = edgeTo[s];
                for (; (startW != x.either() && startW != x.other(x.either()) ); x = edgeTo[old]) {
                    cycle.enqueue(x);
                    old = x.other(old);
                }
                cycle.enqueue(x);
                HashSet<Double> set = new HashSet<>();

                for (Edge edge: cycle) {
                    if (set.size() == 0) {
                        set.add(edge.weight());
                    } else if (set.contains(e.weight())) {
                        System.out.println("In this graph! We have same weight edge in cycled edge. So there can be multiple mst");
                    } else {
                        set.add(e.weight());
                    }
                }

            }
        }
    }


    public static void main(String[] args) {
        EdgeWeightedGraph G = new EdgeWeightedGraph(3);
        G.addEdge(new Edge(0, 1, 2));
        G.addEdge(new Edge(0, 2, 2));
        G.addEdge(new Edge(1, 2, 1));

        UniqueMST uniqueMST = new UniqueMST(G);
    }
}
