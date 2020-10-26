package chapter4.section3.solutions;

import edu.princeton.cs.algs4.*;
import sun.awt.image.ImageAccessException;

public class Solution14 {

    private int[] id;
    private boolean[] marked;

    public Iterable<Edge> removeAnEdge(EdgeWeightedGraph G, PrimMST mst, Edge edgeToDelete) {
        Graph graph = new Graph(G.V());
        Queue<Edge> result = new Queue<>();

        for (Edge e: mst.edges()) {
            if (e.compareTo(edgeToDelete) != 0) {
                int v = e.either();
                int w = e.other(v);
                graph.addEdge(v, w);
                result.enqueue(e);
            }
        }


        if (graph.E() == G.V()-1) {
            System.out.println("Existing MST is new MST after deletion of edge " + edgeToDelete);
            return mst.edges();
        }

        // DSF for all components
        id = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            id[v] = -1;
        }
        marked = new boolean[G.V()];

        int count = 0;
        Queue<Integer> stack = new Queue<>();


        for (int s = 0; s < graph.V(); s++) {
            if (!marked[s]) {

                stack.enqueue(s);
                // DFS
                while (!stack.isEmpty()) {
                    int v = stack.dequeue();
                    marked[v] = true;
                    id[v] = count;
                    for (int w: graph.adj(v)) {
                        if (!marked[w]) {
                            stack.enqueue(w);
                        }
                    }
                }
            }
            count++;
        }

        // Minimum edge that will contains both the components as delete
        Edge E = null;
        for (int s = 0; s < G.V(); s++) {
            for (Edge e: G.adj(s)) {
                int v = e.either();
                int w = e.other(v);

                // So we wont pick edgeToDelete edge again
                if (id[v] != id[w] && e.compareTo(edgeToDelete) != 0) {
                    if (E == null) {
                        E = e;
                    } else if(E.compareTo(e) > 0) {
                        E = e;
                    }
                }
            }
        }
        result.enqueue(E);
        return result;
    }


    public static void main(String[] args) {
        Solution14 sol = new Solution14();

        EdgeWeightedGraph G = new EdgeWeightedGraph(new In("src/chapter4/section3/data/tinyEWG.txt"));
        PrimMST mst = new PrimMST(G);
        for (Edge e: mst.edges()) {
            int v = e.either();
            int w = e.other(v);
            System.out.printf("%d - %d, %f \n", v, w, e.weight());
        }

        System.out.println("---------------------------------");

        for (Edge e: sol.removeAnEdge(G, mst, new Edge(0, 7, 0.16))) {
            int v = e.either();
            int w = e.other(v);
            System.out.printf("%d - %d, %f \n", v, w, e.weight());
        }

        System.out.println("---------------------------------");

        for (Edge e: sol.removeAnEdge(G, mst, new Edge(5, 7, 0.28))) {
            int v = e.either();
            int w = e.other(v);
            System.out.printf("%d - %d, %f \n", v, w, e.weight());
        }

        System.out.println("---------------------------------");

        for (Edge e: sol.removeAnEdge(G, mst, new Edge(1, 5, 0.32))) {
            int v = e.either();
            int w = e.other(v);
            System.out.printf("%d - %d, %f \n", v, w, e.weight());
        }

    }
}
