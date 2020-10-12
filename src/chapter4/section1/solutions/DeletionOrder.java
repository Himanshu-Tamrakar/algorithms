package chapter4.section1.solutions;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

// Solved Using 2EdgeConnectivity Problem
// All the back edge going upward not to their v can be deleted
public class DeletionOrder {
    private int bridgeEdge;
    private boolean[] marked;
    private  int[] arrivalTime;
    private int ctn = 0;
    private int[] edgeTo;
    public DeletionOrder(AdjecencyListGraph G){
        bridgeEdge = 0;
        marked = new boolean[G.V()];
        arrivalTime = new int[G.V()];
        edgeTo = new int[G.V()];
        deleteUntillConnected(G, 0);
    }

    // Unordered graoh with remove edge feature
    private static class AdjecencyListGraph {
        private static final String NEWLINE = System.getProperty("line.separator");
        private final int V;
        private int E;
        private Bag<Integer>[] adj;

        public AdjecencyListGraph(int V) {
            if (V < 0) {
                throw new IllegalArgumentException("Number of vertices must be nonnegative");
            } else {
                this.V = V;
                this.E = 0;
                this.adj = (Bag[])(new Bag[V]);

                for(int v = 0; v < V; ++v) {
                    this.adj[v] = new Bag();
                }

            }
        }

        public AdjecencyListGraph(In in) {
            if (in == null) {
                throw new IllegalArgumentException("argument is null");
            } else {
                try {
                    this.V = in.readInt();
                    if (this.V < 0) {
                        throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");
                    } else {
                        this.adj = (Bag[])(new Bag[this.V]);

                        int E;
                        for(E = 0; E < this.V; ++E) {
                            this.adj[E] = new Bag();
                        }

                        E = in.readInt();
                        if (E < 0) {
                            throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");
                        } else {
                            for(int i = 0; i < E; ++i) {
                                int v = in.readInt();
                                int w = in.readInt();
                                this.validateVertex(v);
                                this.validateVertex(w);
                                this.addEdge(v, w);
                            }

                        }
                    }
                } catch (NoSuchElementException var6) {
                    throw new IllegalArgumentException("invalid input format in Graph constructor", var6);
                }
            }
        }

        public int V() {
            return this.V;
        }

        public int E() {
            return this.E;
        }

        private void validateVertex(int v) {
            if (v < 0 || v >= this.V) {
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (this.V - 1));
            }
        }

        public void addEdge(int v, int w) {
            this.validateVertex(v);
            this.validateVertex(w);
            ++this.E;
            this.adj[v].add(w);
            this.adj[w].add(v);
        }

        public Iterable<Integer> adj(int v) {
            this.validateVertex(v);
            return this.adj[v];
        }

        // Just removing edge
        public void removeEdge(int v, int w) {
            Bag<Integer> bag = new Bag<>();
            for (int x: adj(v)) {
                if (x != w) bag.add(x);
            }
            adj[v] = bag;
            bag = new Bag<>();

            for (int x: adj(w)) {
                if (x != v) bag.add(x);
            }
            adj[w] = bag;
        }

        public int degree(int v) {
            this.validateVertex(v);
            return this.adj[v].size();
        }

        public String toString() {
            StringBuilder s = new StringBuilder();
            s.append(this.V + " vertices, " + this.E + " edges " + NEWLINE);

            for(int v = 0; v < this.V; ++v) {
                s.append(v + ": ");
                Iterator var3 = this.adj[v].iterator();

                while(var3.hasNext()) {
                    int w = (Integer)var3.next();
                    s.append(w + " ");
                }

                s.append(NEWLINE);
            }

            return s.toString();
        }

    }



    // 2EdgeConnectivity dfs
    public int deleteUntillConnected(AdjecencyListGraph G, int s) {
        marked[s] = true;
        arrivalTime[s] = ctn++;
        int deepestBackEdge = arrivalTime[s];

        for (int w: G.adj(s)) {
            if (!marked[w]) {
                edgeTo[w] = s;
                deepestBackEdge = Math.min(deepestBackEdge, deleteUntillConnected(G, w));
            } else if (edgeTo[s] != w) { // dfs(v) [w, x] => dfs(w) [v, z] so i checking when dfs(w) v is not adjecent
                deepestBackEdge = Math.min(deepestBackEdge, arrivalTime[w]);
                G.removeEdge(s, w);
                System.out.printf("Deleted: %d - %d\n", s, w);
            }
        }
//        if (deepestBackEdge >= arrivalTime[s] && edgeTo[s] != s) // edge[s] != s is for starting node. Back edge for starting node is node it self and it will be equal to
//        {
//            bridgeEdge++;
//        }
        return deepestBackEdge;

    }

    public static void main(String[] args) {
        AdjecencyListGraph G = new AdjecencyListGraph(new In("src/chapter4/section1/data/tinyCG.txt"));

//        AdjecencyListGraph G = new AdjecencyListGraph(4);
//        G.addEdge(0, 1);
//        G.addEdge(2, 1);
//        G.addEdge(2, 3);
        DeletionOrder dO = new DeletionOrder(G);
    }
}


