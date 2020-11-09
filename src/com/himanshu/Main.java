package com.himanshu;

import chapter5.section1.algo.*;
import chapter5.section1.solutions.FrequencyCountQuick3String;

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

        FrequencyCountQuick3String.main(new String[]{"src/chapter3/section5/data/tale.txt"});
    }
}
