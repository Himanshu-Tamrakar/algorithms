package chapter1.section5.algo;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class WeightedQuickUnionHT {
    private int[] parent;
    private int[] weight;

    public WeightedQuickUnionHT(int size) {
        this.parent = new int[size];
        this.weight = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            weight[i] = 1;
        }
    }

    public int find(int p) {
        int root = p;
        while (root != parent[root])
            root = parent[root];
        while (p != root) {
            int newp = parent[p];
            parent[p] = root;
            p = newp;
        }
        return root;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if(rootP == rootQ) return;

        int pw = weight[rootP];
        int qw = weight[rootQ];

        if (pw < qw) {
            parent[rootP] = rootQ;
            weight[rootQ] = Math.max(qw, qw+pw);
        } else {
            parent[rootQ] = rootP;
            weight[rootP] = Math.max(pw, qw+pw);
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);

        int n = in.readInt();
        WeightedQuickUnionHT uf = new WeightedQuickUnionHT(n);
        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();
            if (uf.find(p) == uf.find(q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
            n--;
        }
        in.close();

        System.out.printf("Action Implementation result\n");
        in = new In(args[0]);
        n = in.readInt();
        WeightedQuickUnionUF unionUF = new WeightedQuickUnionUF(n);
        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();
            if (unionUF.find(p) == unionUF.find(q)) continue;
            unionUF.union(p, q);
            StdOut.println(p + " " + q);
        }


        System.out.printf("");

    }


}
