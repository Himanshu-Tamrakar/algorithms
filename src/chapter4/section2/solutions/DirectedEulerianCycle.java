package chapter4.section2.solutions;

import java.util.LinkedList;

//Prove that a digraph G has a directed Eulerian cycle if and only if vertex in G has its indegree equal to its outdegree and
// and all vertices with nonzero degree belong to the same strong component.
public class DirectedEulerianCycle {
    private static class DirectedGraph {
        private LinkedList<Integer>[] adj;
        private int[] indegree;
        private int V;
        private int E;

        public DirectedGraph(int v) {
            adj = new LinkedList[v];
            indegree = new int[v];
            this.V = v;
            this.E = 0;
            for (int i = 0; i < v; i++) {
                adj[i] = new LinkedList<>();
            }
        }

        public DirectedGraph reverse() {
            DirectedGraph graph = new DirectedGraph(this.V);
            for (int v = 0; v < this.V; v++) {
                for (int w: this.adj(v)) {
                    graph.addEdge(w, v);
                }
            }

            return graph;
        }

        public int indegree(int v) {
            return this.indegree[v];
        }

        public int V() {
            return this.V;
        }

        public int E() {
            return this.E;
        }

        public void addEdge(int v, int w) {
            this.adj[v].add(w);
            this.E++;
            this.indegree[w]++;
        }

        public Iterable<Integer> adj(int v) {
            return this.adj[v];
        }

    }

    private static void dfs(DirectedGraph G, int s, boolean[] marked) {
        marked[s] = true;
        for (int w: G.adj(s)) {
            if (!marked[w]) dfs(G, w, marked);
        }
    }

    private static boolean isSCC(DirectedGraph G) {
        boolean[] marked = new boolean[G.V()];
        dfs(G, 0, marked);
        for (int i = 0; i < marked.length; i++) {
            if (!marked[i]) {
                return false;
            }
        }

        marked = new boolean[G.V()];
        DirectedGraph reverseG = G.reverse();
        dfs(reverseG, 0, marked);

        for (int i = 0; i < marked.length; i++) {
            if (!marked[i]) {
                return false;
            }
        }

        return true;

    }

    public static boolean isEulerianCycle(DirectedGraph G) {
        if (!isSCC(G)) {
            System.out.println("This is not strongly connected component");
            return false;
        }

        for (int v = 0; v < G.V(); v++) {
            if (G.indegree(v) != G.adj[v].size()) {
                System.out.println("There is not Eulerian cycle because for some vertex iindegree and out degree is not equal");
                return false;
            }

        }

        return true;
    }


    public static void main(String[] args) {
        DirectedGraph G = new DirectedGraph(4);
        G.addEdge(0, 1);
        G.addEdge(1, 2);
        G.addEdge(2, 3);
        G.addEdge(3,  0);
        if (isEulerianCycle(G)) {
            System.out.println("This contains a Eulerian cycle, Because Graph is strongly connected and each vertex has indegree and out degree same");
        }
    }
}
