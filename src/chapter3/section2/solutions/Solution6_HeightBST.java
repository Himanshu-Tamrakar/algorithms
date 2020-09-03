package chapter3.section2.solutions;

import chapter3.section2.algo.BinarySearchTree;
import edu.princeton.cs.algs4.BST;

public class Solution6_HeightBST<Key extends Comparable<Key>, Value> {
    /**************************************************************
     * Node Class
     **************************************************************/
    private static class Node<Key, Value> {
        private Key key;
        private Value value;
        private Node left, right;
        private int N;


        private Node(Key key, Value value, Node left, Node right, int n) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            N = n;
        }
    }

    public static int height(Node root) {
        if (root == null) return 0;
        return Math.max(1 + height(root.left), 1 + height(root.right));
    }

//    private static int height(Node)
    public static void main(String[] args) {
        BST<String, String> st = new BST<>();
    }
}
