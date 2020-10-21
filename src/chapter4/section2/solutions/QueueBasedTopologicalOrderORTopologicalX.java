package chapter4.section2.solutions;

import edu.princeton.cs.algs4.DepthFirstOrder;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Topological;

import javax.net.ssl.SSLContext;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * If graph has cycle then there is no way to find topological order
 * It is mandatory that atleast one edge in connected component has indegree to zero to make sure it has not a cycle
 */

public class QueueBasedTopologicalOrderORTopologicalX {
    private Queue<Integer> order;
    private int[] indegree;
    private int[] rank;

    public QueueBasedTopologicalOrderORTopologicalX(Digraph G) {

        indegree = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            indegree[v] = G.indegree(v);
        }

        order = new LinkedList<>();
        int count = 0;
        rank = new int[G.V()];

        Queue<Integer> queue = new LinkedList<>();
        for (int v = 0; v < G.V(); v++) {
            if (G.indegree(v) == 0) queue.add(v);
        }

        while (!queue.isEmpty()) {
            int v = queue.poll();
            order.add(v);
            rank[v] = count++;
            for (int w: G.adj(v)) {
                indegree[w]--;
                if (indegree[w] == 0) queue.add(w);
            }
        }

        if (count != G.V()) {
            order = null;
        }

    }


    public boolean hasTopologicalOrder() {
        return order != null;
    }

    public Iterable<Integer> topologicalOrder() {
        return this.order;
    }


    public static void main(String[] args) {
        Digraph G = new Digraph(4);
//        G.addEdge(0, 1);
//        G.addEdge(0, 3);
//        G.addEdge(1, 2);
//        G.addEdge(2, 3);
        G.addEdge(0, 1);
        G.addEdge(0, 3);
        G.addEdge(3, 2);
        G.addEdge(1, 2);

        QueueBasedTopologicalOrderORTopologicalX queueBasedTopologicalOrderORTopologicalX = new QueueBasedTopologicalOrderORTopologicalX(G);
        if (queueBasedTopologicalOrderORTopologicalX.hasTopologicalOrder()) {
            for (int x: queueBasedTopologicalOrderORTopologicalX.topologicalOrder()) {
                System.out.printf("%d ", x);
            }
        }
        System.out.println();
        System.out.println("Just changing insertion order----------------------------------");

        G = new Digraph(4);
        G.addEdge(0, 1);
        G.addEdge(0, 3);
        G.addEdge(3, 2);
        G.addEdge(1, 2);

        queueBasedTopologicalOrderORTopologicalX = new QueueBasedTopologicalOrderORTopologicalX(G);
        if (queueBasedTopologicalOrderORTopologicalX.hasTopologicalOrder()) {
            for (int x: queueBasedTopologicalOrderORTopologicalX.topologicalOrder()) {
                System.out.printf("%d ", x);
            }
        }

        System.out.println();
        System.out.println("DFS based Topogical Order----------------------------------");

        G = new Digraph(4);
        G.addEdge(0, 1);
        G.addEdge(0, 3);
        G.addEdge(3, 2);
        G.addEdge(1, 2);
        Topological topological = new Topological(G);
        if (topological.hasOrder()) {
            for (int x: topological.order()) {
                System.out.printf("%d ", x);
            }
        }

        System.out.println();
        System.out.println("Just changing insertion order----------------------------------");

        G = new Digraph(4);
        G.addEdge(0, 1);
        G.addEdge(0, 3);
        G.addEdge(3, 2);
        G.addEdge(1, 2);

        topological = new Topological(G);
        if (topological.hasOrder()) {
            for (int x: topological.order()) {
                System.out.printf("%d ", x);
            }
        }

    }
}
