package chapter4.section2.solutions;

import edu.princeton.cs.algs4.DepthFirstDirectedPaths;
import edu.princeton.cs.algs4.DepthFirstOrder;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Topological;

import java.util.Iterator;

// Solution: Compute a topological sort and check if there is an edge between each consecutive pair of vertices in the topological order.
// 0 -> 1, 0->2, 2->3, 3->4, 4->5, 3->5 is not hamiltonial path in this DAG
// Hamiltonian Path is NP-hard on digraphs with cycles, and on undirected graphs.
public class HamiltonianPathInDAG {
    public static void main(String[] args) {
        Digraph G = new Digraph(6);
        G.addEdge(0, 1);
        G.addEdge(0, 2);
        G.addEdge(1, 2);
        G.addEdge( 2, 3);
        G.addEdge(3, 4);
        G.addEdge(4, 5);
        G.addEdge(3, 5 );

        // Topological Order
        Topological topological = new Topological(G);
        Iterator<Integer> itr = topological.order().iterator();
        int v = 0;
        if (itr.hasNext()) {
            v = itr.next();
        }

        while (itr.hasNext()) {
            boolean check = false;
            int w = itr.next();
            for (int x: G.adj(v)) {
                 if (x == w) check = true;
            }

            if (!check) {
                System.out.println("There is no edge between topological order of vertex");
                break;
            }

            v = w;
        }



    }
}
