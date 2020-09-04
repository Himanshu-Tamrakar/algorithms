package chapter3.section2.solutions;

import chapter3.section2.algo.BinarySearchTree;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class NonRecursiceBSTHT<Key extends Comparable<Key>, Value> {

    private Node root;
    /**************************************************************
     * Node Class
     **************************************************************/
    private class Node {
        private Key key;
        private Value value;
        private Node left, right;

        private Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    public Value get(Key key) {
        Node node = get(this.root, key);
        return node == null ? null : node.value;
    }
    private Node get(Node root, Key key) {
        while (root != null) {
            int cmp = key.compareTo(root.key);
            if (cmp == 0) {
                return root;
            } else if (cmp > 1) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return null;
    }

    public void put(Key key, Value value) {
        put(this.root, key, value);
    }
    private void put(Node x, Key key, Value value) {
        Node var0 = new Node(key, value);

        if (this.root == null) {
            this.root = var0;
            return;
        }

        Node parent = null;
        while (x != null) {
            parent = x;
            int cmp = key.compareTo(x.key);
            if (cmp == 0) {
                x.value = value;
                return;
            } else if (cmp < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        int cmp = key.compareTo(parent.key);
        if (cmp > 0){
            parent.right = var0;
        } else {
            parent.left = var0;
        }
    }

    /**
     * Clever Logic: reference https://algs4.cs.princeton.edu/32bst/NonrecursiveBST.java.html
     */
    private void keys(Node root) {
        Queue<Key> queue = new Queue<>();
        Stack<Node> stack = new Stack<>();
        Node x = root;

        while (!stack.isEmpty()) {
            if (x != null) {
                stack.push(x);
                x = x.left;
            } else  {
                x = stack.pop();
                queue.enqueue(x.key);
                x = x.right;
            }
        }
    }

    public static void main(String[] args) {
        System.out.printf("S E A R C H E X A M P L E");
        String test = "S E A R C H E X A M P L E";
        String[] keys = test.split( " ");
        int n = keys.length;
        NonRecursiceBSTHT<String, Integer> bst = new NonRecursiceBSTHT<>();

        for (int i = 0; i < n; i++)
            bst.put(keys[i], i);

        System.out.printf("\nget %d \n", bst.get("A"));
    }
}
