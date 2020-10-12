package chapter4.section1.solutions;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;

public class TwoEdgeConnectivity {
    private int bridgeEdge;
    private boolean[] marked;
    private  int[] arrivalTime;
    private int ctn = 0;
    private int[] edgeTo;
    public TwoEdgeConnectivity(Graph G){
        bridgeEdge = 0;
        marked = new boolean[G.V()];
        arrivalTime = new int[G.V()];
        edgeTo = new int[G.V()];

        dfs(G, 0);
    }


    private int dfs(Graph G, int s) {
        marked[s] = true;
        arrivalTime[s] = ctn++;
        int deepestBackEdge = arrivalTime[s];

        for (int w: G.adj(s)) {
            if (!marked[w]) {
                edgeTo[w] = s;
                deepestBackEdge = Math.min(deepestBackEdge, dfs(G, w));
            } else if (edgeTo[s] != w) { // dfs(v) [w, x] => dfs(w) [v, z] so i checking when dfs(w) v is not adjecent
                deepestBackEdge = Math.min(deepestBackEdge, arrivalTime[w]);
            }
        }

        if (deepestBackEdge >= arrivalTime[s] && edgeTo[s] != s) // edge[s] != s is for starting node. Back edge for starting node is node it self and it will be equal to
        {
            bridgeEdge++;
        }
        return deepestBackEdge;
    }


    public static void main(String[] args) {
        Graph G = new Graph(new In("src/chapter4/section1/data/tinyCG.txt"));
//        Graph G = new Graph(4);
//        G.addEdge(0,1);
//        G.addEdge(1, 2);
//        G.addEdge(3,2);
        TwoEdgeConnectivity tec = new TwoEdgeConnectivity(G);

        System.out.printf("Total Number of Bridge Edges %d", tec.bridgeEdge);
    }
}
