package chapter4.section3.solutions;

import edu.princeton.cs.algs4.*;

/**
 * Reference:
 * https://stackoverflow.com/questions/10791689/how-to-find-feedback-edge-set-in-undirected-graph
 */
public class MinimumWeightFeedbackEdgeSet {

    UF uf;
    MinPQ<Edge> minPQ;
    Queue<Edge> feedbackEdgeMinWeight;
    Queue<Edge> mst;
    public MinimumWeightFeedbackEdgeSet(EdgeWeightedGraph G) {

        // Negate edge weights
        EdgeWeightedGraph graph = new EdgeWeightedGraph(G.V());
        for (Edge e: G.edges()) {
            int v = e.either();
            int w = e.other(v);
            double weight = -e.weight();
            graph.addEdge(new Edge(v, w, weight));
        }

        uf = new UF(graph.V());
        minPQ = new MinPQ<>();
        feedbackEdgeMinWeight = new Queue<>();
        mst = new Queue<>();

        for (Edge e: graph.edges()) {
            minPQ.insert(e);
        }

        while (!minPQ.isEmpty()) {
            Edge e = minPQ.delMin();
            int v = e.either();
            int w = e.other(v);

            if (uf.find(v) == uf.find(w)) {
                feedbackEdgeMinWeight.enqueue(e);
                continue;
            }
            uf.union(v, w);
            mst.enqueue(e);
        }


    }

    public Iterable<Edge> minFeedback() {
        return this.feedbackEdgeMinWeight;
    }

    public static void main(String[] args) {
        EdgeWeightedGraph G = new EdgeWeightedGraph(6);
        G.addEdge(new Edge(0, 1, 1));
        G.addEdge(new Edge(1, 2, 2));
        G.addEdge(new Edge(2, 3, 3));
        G.addEdge(new Edge(3, 4, 4));
        G.addEdge(new Edge(4, 5, 5));
        G.addEdge(new Edge(5, 0, 6));

        MinimumWeightFeedbackEdgeSet minimumWeightFeedbackEdgeSet = new MinimumWeightFeedbackEdgeSet(G);

        for (Edge e: minimumWeightFeedbackEdgeSet.minFeedback()) {
            System.out.println(e);
        }
    }
}
