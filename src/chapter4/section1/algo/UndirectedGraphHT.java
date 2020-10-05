package chapter4.section1.algo;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Graph;

import java.util.Iterator;

public class UndirectedGraphHT {
    private Bag<Integer>[] adj;
    private int V;
    private int E;

    public UndirectedGraphHT(int V) {
        this.V = V;
        this.E = 0;
        this.adj = new Bag[V];
    }

    public int V() {
        return this.V;
    }

    public int E() {
        return this.E;
    }

    public void add(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        this.adj[v].add(w);
        this.adj[w].add(v);
        E++;

    }

    public Iterator<Integer> adj(int v) {
        validateVertex(v);
        return this.adj[v].iterator();
    }


    private void validateVertex(int v) {
        if (v < 0 || v >= this.V) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (this.V - 1));
        }
    }

    public static void main(String[] args) {
        
    }



}
