package chapter4.section2.solutions;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.KosarajuSharirSCC;

public class Solution17 {
    public static void main(String[] args) {
        String file = "src/chapter4/section2/data/mediumDG.txt";
        Digraph G = new Digraph(new In(file));

        KosarajuSharirSCC kosarajuSharirSCC = new KosarajuSharirSCC(G);

        System.out.println(kosarajuSharirSCC.count());
    }
}
