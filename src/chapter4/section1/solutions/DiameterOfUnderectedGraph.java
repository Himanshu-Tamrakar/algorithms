package chapter4.section1.solutions;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMaxPQ;
import edu.princeton.cs.algs4.MaxPQ;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeMap;

// Longest Distance
public class DiameterOfUnderectedGraph {
    private boolean[] visited;
    private int[] distTo;
    private int[] edgeTo;
    private IndexMaxPQ<Integer> maxPQ;

    public DiameterOfUnderectedGraph(Graph G, int s) {
        visited = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];

        maxPQ = new IndexMaxPQ(G.V());

        maxPQ.insert(s, 0);

        while (!maxPQ.isEmpty()) {
            int vertex = maxPQ.delMax();
            visit(G, vertex);
        }

        System.out.print("Debug The largest");

    }

    private void visit(Graph G, int s) {
        visited[s] = true;

        for (int w: G.adj(s)) {
            if (!visited[w]) {
                if (distTo[w] < (distTo[s] + 1)) {
                    distTo[w] = distTo[s] + 1;
                    edgeTo[w] = s;
                }

                if (maxPQ.contains(w)) {
                    maxPQ.changeKey(w, distTo[w]);
                } else {
                    maxPQ.insert(w, distTo[w]);
                }
            }
        }
    }

    public static void main(String[] args) {


        Graph G = new Graph(6);
        G.addEdge(0, 1);
        G.addEdge(0, 2);
        G.addEdge(0, 5);
        G.addEdge(1, 2);
        G.addEdge(1, 3);
        G.addEdge(2, 3);
        G.addEdge(2, 4);
        G.addEdge(2, 5);
        G.addEdge(3, 4);
        G.addEdge(4, 5);

        // distTo[1] = 5
        DiameterOfUnderectedGraph diameterOfUnderectedGraph = new DiameterOfUnderectedGraph(G, 0);
        // distTo[0] = 5
        diameterOfUnderectedGraph = new DiameterOfUnderectedGraph(G, 1);
        // Diameter is 5
    }

}
