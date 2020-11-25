package com.himanshu;

import chapter5.section1.algo.*;
import chapter5.section1.solutions.FrequencyCountQuick3String;
import chapter5.section2.algo.TSTHT;
import chapter5.section2.algo.TrieHT;
import chapter5.section3.algo.BoyerMooreHT;
import chapter5.section3.algo.BruteforceSearch;
import chapter5.section3.algo.Grep;
import chapter5.section3.algo.KMPHT;
import edu.princeton.cs.algs4.TrieST;

import java.io.FileNotFoundException;

public class Main {
    private static class Node implements Comparable<Node> {
        int vertex;
        int distance;
        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || this.getClass() != o.getClass()) return false;
            Node that = (Node) o;
            return this.vertex == that.vertex;
        }

        @Override
        public int compareTo(Node node) {
            if (this.distance < node.distance) return -1;
            else if (this.distance > node.distance) return 1;
            else return 0;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Grep.main(new String[]{"src/chapter3/section5/data/tale.txt"});

    }
}
