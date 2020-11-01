package chapter4.section4.solutions;

//Shortest path with the ability to skip one edge. Given an edge-weighted digraph with nonnegative weights,
// Design an E log V algorithm for finding the shortest path from s to t where you have the option to change the weight of any one edge to 0.
//Solution. Compute the shortest path from s to every other vertex; compute the shortest path from every vertex to t. For each edge e = (v, w),
// compute the sum of the length of the shortest path from s to v and the length of the shortest path from w to t. The smallest such sum provides the shortest such path.

import edu.princeton.cs.algs4.*;

public class SkippableEdgeSP {

    private EdgeWeightedDigraph reverse(EdgeWeightedDigraph G) {
        EdgeWeightedDigraph reverse = new EdgeWeightedDigraph(G.V());
        for (DirectedEdge e: G.edges()) {
            int v = e.from();
            int w = e.to();
            reverse.addEdge(new DirectedEdge(w, v, e.weight()));
        }
        return reverse;
    }
    public Iterable<DirectedEdge> skippablePath(EdgeWeightedDigraph G, int s, int t) {
        // Dijkstra Shortest Path algoriths as there is no negetive edge
        DijkstraSP sShortestPaths = new DijkstraSP(G, s);
        DijkstraSP tShortestPaths = new DijkstraSP(reverse(G), t);


        double minimum = Double.POSITIVE_INFINITY;
        DirectedEdge minimumSkippableEdge = null;

        // Finding the edge from which distance to s to v and w to t is shortest.
        // Make that edge skippable edge
        for (DirectedEdge e: G.edges()) {
            int v = e.from();
            int w = e.to();
            if (sShortestPaths.distTo(v) + tShortestPaths.distTo(w) < minimum) {
                minimum = sShortestPaths.distTo(v) + tShortestPaths.distTo(w);
                minimumSkippableEdge = e;
            }
        }

        Stack<DirectedEdge> spath = new Stack<>();
        Stack<DirectedEdge> tpath = new Stack<>();

        // Retrive path from s to v
        for (DirectedEdge e: sShortestPaths.pathTo(minimumSkippableEdge.from())) {
            spath.push(e);
        }

        // Retrive path from t to w from reverse Graph G
        for (DirectedEdge e: tShortestPaths.pathTo(minimumSkippableEdge.to())) {
            tpath.push(e);
        }

        // Merge the path and return
        for (DirectedEdge e: tpath) {
            spath.push(e);
        }
        return spath;
    }

    public static void main(String[] args) {

    }
}
