package chapter5.section3.algo;

import edu.princeton.cs.algs4.*;

public class NFA {
    private char[] re;
    private Digraph G;
    private int M;

    public NFA(String regexp) {
        M = regexp.length();
        re = regexp.toCharArray();
        G = new Digraph(M+1);
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < M; i++) {
            int lp = i;
            if (re[i] == '(' || re[i] == '|') {
                stack.push(i);
            }
            else if (re[i] == ')') {
                int or = stack.pop();
                if (re[or] == '|') {
                    lp = stack.pop();
                    G.addEdge(lp, or+1);
                    G.addEdge(or, i);
                } else {
                    lp = or;
                }
            }

            if (i < M-1 && re[i+1] == '*') {
                G.addEdge(lp, i+1);
                G.addEdge(i+1, lp);
            }

            if (re[i] == '(' || re[i] == '*' || re[i] == ')') {
                G.addEdge(i, i+1);
            }
        }
    }

    public boolean recognizes(String txt) {
        Bag<Integer> pc = new Bag<>();
        DirectedDFS dfs = new DirectedDFS(G, 0);
        for (int v = 0; v < G.V(); v++) {
           if (dfs.marked(v)) pc.add(v);
        }


        for (int i = 0; i < txt.length(); i++) {
            Bag<Integer> match = new Bag<>();

            for (int v: pc) {
                if (v < M) {
                    if (re[v] == txt.charAt(i)|| re[v] == '.') {
                        match.add(v+1);
                    }
                }
            }

            pc = new Bag<>();
            dfs = new DirectedDFS(G, match);

            for (int w = 0; w < G.V(); w++) {
                if (dfs.marked(w)) pc.add(w);
            }
        }

        // Important note:
        // If you do dfs from s, then s will always present in pc. because we are add every vertecx that is markerd after dfs/
        for (int v: pc) {
            if (v == M) return true;
        }

        return false;

    }
}
