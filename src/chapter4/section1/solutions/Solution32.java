package chapter4.section1.solutions;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;

// Parallel Edge Detection
public class Solution32 {
    private class ParallelEdgeDetection {
        private int parallelEdges;
        private boolean[] marked;
        public ParallelEdgeDetection(Graph G) {
            parallelEdges = 0;
            marked = new boolean[G.V()];
            for (int v = 0; v < G.V(); v++) {
                for (int w: G.adj(v)) {
                    if (!marked[w]) marked[w] = true;
                    else parallelEdges++;
                }

                for (int w: G.adj(v)) {
                    marked[w] = false;
                }

            }

            parallelEdges /= 2;

        }



        public int getParallelEdges() {
            return this.parallelEdges;
        }
    }


    public static void main(String[] args) {
        Graph G = new Graph(new In("src/chapter4/section1/data/tinyG.txt"));
//        G.addEdge(0, 6);
//        G.addEdge(0, 2);
//        G.addEdge(0, 1);
//        G.addEdge(0, 5);
//        G.addEdge(5, 3);
//        G. addEdge(3, 4);
        G. addEdge(3, 4); // Parallel Edge
//        G.addEdge(5, 4);
        G.addEdge(5, 4); // Parallel Edge



        ParallelEdgeDetection ped = new Solution32().new ParallelEdgeDetection(G);
        System.out.printf("Total Number Of Parallel Edges %d \n", ped.getParallelEdges());
    }
}
