package chapter3.section2.algo;

import chapter3.section2.solutions.Solution6_HeightBST;
import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.util.HashMap;

public class BinarySearchTree<Key extends Comparable<Key>, Value> {
    private Node root;
    private static HashMap<String, String> st = new HashMap<>();

    /**************************************************************
     * Node Class
     **************************************************************/
    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int N;
        private int height;


        private Node(Key key, Value value, Node left, Node right, int n, int height) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            N = n;
            this.height = height;
        }
    }



    public Value get(Key key) {
        Node item = get(root, key);
        return item == null ? null : item.value;
    }
    private Node get(Node root, Key key) {
        if (root == null) return null;
        int cmp = key.compareTo(root.key);
        if (cmp > 0) return get(root.right, key);
        else if (cmp < 0) return get(root.left, key);
        else return root;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }
    private Node put(Node root, Key key, Value value) {
        if (root == null) return new Node(key, value, null, null, 1, 0);
        int cmp = key.compareTo(root.key);
        if (cmp > 0) {
            root.right =  put(root.right, key, value);
        } else {
            root.left = put(root.left, key, value);
        }
        root.N = size(root.left) + size(root.right) + 1;
        root.height = 1 + Math.max(height(root.left), height(root.right));
        return root;
    }

    public Key min() {
        Node minNode = min(this.root);
        return minNode == null ? null : minNode.key;
    }
    private Node min(Node x) {
        if (x == null) return null;
        if (x.left == null) return x;
        return min(x.left);
    }

    public Key max() {
        Node maxNode = max(this.root);
        return maxNode == null ? null : maxNode.key;
    }
    private Node max(Node x) {
        if (x == null) return null;
        if (x.right == null) return x;
        return max(x.right);
    }

    public Key floor(Key key) {
        Node floorNode = floor(this.root, key);
        return floorNode == null ? null : floorNode.key;
    }
    private Node floor(Node root, Key key) {
        if (root == null) return null;

        int cmp = key.compareTo(root.key);
        if (cmp == 0) return root;
        else if (cmp < 0) return floor(root.left, key);
        else {
            Node x = floor(root.right, key);
            if (x == null) return root;
            return x;
        }
    }

    public Key ceil(Key key) {
        Node ceilNode = ceil(this.root, key);
        return ceilNode == null ? null : ceilNode.key;
    }

    private Node ceil(Node root, Key key) {
        if (root == null) return null;
        int cmp = key.compareTo(root.key);
        if (cmp == 0) return root;
        else if (cmp > 0) {
            return ceil(root.right, key);
        } else {
            Node x = ceil(root.left, key);
            if (x == null) return root;
            return x;
        }
    }

    public Key select(int rank) {
        if (rank >= 0 && rank < this.size()) {
            return this.select(root, rank);
        } else {
            throw new IllegalArgumentException("argument to select() is invalid: " + rank);
        }
    }

    private Key select(Node x, int rank) {
        if (x == null) {
            return null;
        } else {
            int leftSize = this.size(x.left);
            if (leftSize > rank) {
                return this.select(x.left, rank);
            } else {
                return leftSize < rank ? this.select(x.right, rank - leftSize - 1) : x.key;
            }
        }
    }

    public int rank(Key key) {
        return rank(this.root, key);
    }
    private int rank(Node root, Key key) {
        if (root == null) return 0;
        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            return rank(root.left, key);
        } else {
            return cmp == 0 ? size(root.left) : 1 + size(root.left) + rank(root.right, key);
        }
    }

    public void delMin() {
        root = delMin(this.root);
    }
    private Node delMin(Node root) {
        if (root.left == null) return root.right;
        root.left = delMin(root.left);
        root.N = size(root.left) + size(root.right) + 1;
        root.height = height(root.left) + height(root.right) + 1;
        return root;
    }

    public void delMax() {
        root = delMax(this.root);
    }
    private Node delMax(Node root) {
        if (root.right == null) return root.left;
        root.right = delMax(root.right);
        root.N = size(root.left) + size(root.right) + 1;
        root.height = height(root.left) + height(root.right) + 1;
        return root;
    }

    /*****************************************************************
     * Solutions
     *****************************************************************/


    /**********************************************************************
     * Helper Method
     **********************************************************************/
    public int size() {
        return root == null ? 0 : size(root);
    }
    private int size(Node root) {
        return root == null ? 0 : root.N;
    }

    public int height(Node root) {
        if (root == null) return 0;
        return root.height;
    }

//    public int height() {
//        return height(this.root);
//    }
//
//    public int height(Node root) {
//        if (root == null) return -1;
//        return 1 + Math.max( height(root.left), height(root.right));
//    }




    /**************************************************************************
     * Drawing tree
     **************************************************************************/
    private void drawTree1(Node root, double x, double y, double h) {
        if (root == null ) return;
        StdDraw.setPenRadius(.015);
        StdDraw.point(x, y);
        if (root.left != null) {
            StdDraw.setPenRadius(.005);
            h = StdRandom.uniform(0.01, 0.02);
            StdDraw.line(x,y, x-.018, y-.025-h);
            drawTree1(root.left, x-0.018, y-0.025-h, h);
        }
        if (root.right != null) {
            StdDraw.setPenRadius(.005);
            h = StdRandom.uniform(0.01, 0.05);
            StdDraw.line(x,y, x+.018, y-.02-h);
            drawTree1(root.right, x+0.018, y-0.02-h, h+0.015);
        }
    }

    private void draw1(Node root, String keys) {
        StdDraw.setCanvasSize(1280, 680);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(.2, .98, keys);

        if (this.root != null) {
            StdDraw.setPenRadius(.015);
            StdDraw.point(.5, .95);
        }
        if (this.root.left != null) {
            StdDraw.setPenRadius(.005);
            StdDraw.line(.5, .95, .25, .90);

            StdDraw.setPenRadius(.015);
            StdDraw.point(.25, .90);
            this.drawTree1(this.root.left, .25, .90, 0.05);
        }
        if (this.root.right != null) {
            StdDraw.setPenRadius(.005);
            StdDraw.line(.5, .95, .75, .90);
            StdDraw.setPenRadius(.015);
            StdDraw.point(.75, .90);
            this.drawTree1(this.root.right, .75, .90, 0.05);
        }
    }

    private void drawTree(Node root, double x, double y, double px, double py) {
        if (root == null ) return;
        StdDraw.setPenRadius(.015);
        StdDraw.point(x, y);
        if (root.left != null) {
            StdDraw.setPenRadius(.005);
            StdDraw.line(x,y, x-.03, y-.05);
            drawTree(root.left, x-0.03, y-0.05, x, y);
        }
        if (root.right != null) {
            StdDraw.setPenRadius(.005);
            StdDraw.line(x,y, x+.03, y-.05);
            drawTree(root.right, x+0.03, y-0.05, x, y);
        }
    }

    private void draw(Node root) {
        StdDraw.setCanvasSize(1280, 680);
        StdDraw.setPenColor(StdDraw.BLACK);

        if (this.root != null) {
            StdDraw.setPenRadius(.015);
            StdDraw.point(.5, .95);
        }
        if (this.root.left != null) {
            StdDraw.setPenRadius(.005);
            StdDraw.line(.5, .95, .25, .90);

            StdDraw.setPenRadius(.015);
            StdDraw.point(.25, .90);
            this.drawTree(this.root.left, .25, .90, .5, .95);
        }
        if (this.root.right != null) {
            StdDraw.setPenRadius(.005);
            StdDraw.line(.5, .95, .75, .90);
            StdDraw.setPenRadius(.015);
            StdDraw.point(.75, .90);
            this.drawTree(this.root.right, .75, .90, .5, .95);
        }
    }

    /*****************************************************************************
     * Draw tree End
     *****************************************************************************/

    public static void main(String[] args) {
        System.out.printf("S E A R C H E X A M P L E");
        String test = "S E A R C H E X A M P L E";
        String[] keys = test.split( " ");
        int n = keys.length;
        BinarySearchTree<String, Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < n; i++)
            bst.put(keys[i], i);

        bst.draw1(bst.root, test);
        System.out.printf("\nheight of tree is %d\n", bst.height(bst.root));

        System.out.printf("min %s\n", bst.min());
        System.out.printf("max %s\n", bst.max());
        System.out.printf("floor of %s\n", bst.floor("A"));
        System.out.printf("floor of %s\n", bst.floor("F"));
        System.out.printf("floor of %s\n", bst.floor("Z"));
        System.out.printf("floor of %s\n", bst.floor("T"));

        System.out.printf("ceil of %s\n", bst.ceil("A"));
        System.out.printf("ceil of %s\n", bst.ceil("F"));
        System.out.printf("ceil of %s\n", bst.ceil("T"));

        System.out.printf("Key at rank %d of %s\n", 5, bst.select(5));
        System.out.printf("Key at rank %d of %s\n", 7, bst.select(7));
        System.out.printf("Key at rank %d of %s\n", 0, bst.select(0));

        System.out.printf("rank of key %s is %d\n", "E", bst.rank("E"));
        System.out.printf("rank of key %s is %d\n", "L", bst.rank("L"));
        System.out.printf("rank of key %s is %d\n", "A", bst.rank("A"));
        System.out.printf("rank of key %s is %d\n", "G", bst.rank("G"));

        System.out.printf("Now with actual api\n");
        System.out.printf("----------------------------------------------------------------------\n");
        System.out.printf("----------------------------------------------------------------------\n");
        BST<String, Integer> st = new BST<>();
        for (int i = 0; i < n; i++)
            st.put(keys[i], i);
        System.out.printf("min %s\n", st.min());
        System.out.printf("max %s\n", st.max());
        System.out.printf("floor of %s\n", st.floor("A"));
        System.out.printf("floor of %s\n", st.floor("F"));
        System.out.printf("floor of %s\n", st.floor("Z"));
        System.out.printf("floor of %s\n", st.floor("T"));
        System.out.printf("ceil of %s\n", st.ceiling("A"));
        System.out.printf("ceil of %s\n", st.ceiling("F"));
        System.out.printf("ceil of %s\n", st.ceiling("T"));

        System.out.printf("Key at rank %d of %s\n", 5, st.select(5));
        System.out.printf("Key at rank %d of %s\n", 7, st.select(7));
        System.out.printf("Key at rank %d of %s\n", 0, st.select(0));
        System.out.printf("\nheight of tree is %d\n", st.height());


    }
}
