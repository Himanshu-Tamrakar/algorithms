package com.himanshu;

import chapter4.section1.solutions.DeletionOrder;
import chapter4.section1.solutions.DiameterOfUnderectedGraph;
import chapter4.section1.solutions.TwoEdgeConnectivity;
import chapter4.section2.algo.DirectedCycle;
import chapter4.section2.algo.SymbolDigraph;
import chapter4.section2.algo.TopologicalOrder;
import chapter4.section2.solutions.DirectedEulerianCycle;
import chapter4.section2.solutions.HamiltonianPathInDAG;
import chapter4.section2.solutions.QueueBasedTopologicalOrderORTopologicalX;
import chapter4.section2.solutions.Solution17;
import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMaxPQ;
import org.omg.CORBA.INTERNAL;

import java.io.FileNotFoundException;
import java.util.*;

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
        QueueBasedTopologicalOrderORTopologicalX.main(new String[]{"src/chapter3/section5/data/tale.txt"});
    }
}
