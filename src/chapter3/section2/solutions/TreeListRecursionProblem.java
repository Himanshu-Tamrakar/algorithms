package chapter3.section2.solutions;


import chapter3.section2.algo.BinarySearchTree;

public class TreeListRecursionProblem<Key extends Comparable<Key>, Value> {
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

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }
    private Node put(Node root, Key key, Value value) {
        if (root == null) return new Node(key, value);
        int cmp = key.compareTo(root.key);
        if (cmp > 0) {
            root.right =  put(root.right, key, value);
        } else if (cmp < 0) {
            root.left = put(root.left, key, value);
        } else {
            root.value = value;
        }
        return root;
    }

    private Node treeToList(Node root) {
        if (root == null) return null;

        Node childNode = treeToList(root.left);
        if (childNode != null) {
            childNode.right = root;
            root.left = childNode;
        }

        childNode = treeToList(root.right);
        if (childNode != null) {
            childNode.left = root;
            root.right = childNode;
            return childNode;
        }
        return root;
    }

    public static void main(String[] args) {
        String test = "S E A R C H E X A M P L E";
        String[] keys = test.split(" ");
        int n = keys.length;
        TreeListRecursionProblem<String, Integer> bst = new TreeListRecursionProblem<>();
        for (int i = 0; i < n; i++)
            bst.put(keys[i], i);
    }


}
