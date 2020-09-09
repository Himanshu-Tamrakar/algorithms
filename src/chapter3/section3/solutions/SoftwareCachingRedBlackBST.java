package chapter3.section3.solutions;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class SoftwareCachingRedBlackBST<Key extends Comparable<Key>, Value> {
    private final boolean RED = true;
    private final boolean BLACK = false;
    private Node root;

    private Node cacheItem;

    /**************************************************************
     * Node Class
     **************************************************************/
    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int N;
        private boolean color;

        public Node(Key key, Value value, boolean color, int n) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.N = n;
        }
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.N;
    }

    public int size() {
        return size(root);
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public Value get(Key key) {
        if (isEmpty()) {
            throw new IllegalArgumentException("Tree is empty");
        }
        if (this.cacheItem != null && cacheItem.key.compareTo(key) == 0) {
            System.out.printf("Cache hit");
            return cacheItem.value;
        }
        Node var0 = get(this.root, key);
        if (var0 != null) cacheItem = var0;
        return var0 == null ? null : var0.value;
    }

    private Node get(Node x, Key key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left,key);
        else return cmp == 0 ? x : get(x.right, key);
    }

    public void put(Key key,  Value value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Can't store key or value as null");
        }
        if (cacheItem != null && cacheItem.key.compareTo(key) == 0) {
            cacheItem.value = value;
            System.out.printf("Updated cache");
        }

        this.root = put(this.root, key, value);
        this.root.color = BLACK;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            cacheItem =  new Node(key, value, RED, 1);
            return cacheItem;
        }
        int cmp = key.compareTo(x.key);

        if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }

        if (!isRED(x.left) && isRED(x.right)) {
            x = this.rotateLeft(x);
        }

        if (isRED(x.left) && isRED(x.left.left)) {
            x = this.rotateRight(x);
        }

        if (isRED(x.left) && isRED(x.right)) {
            this.flipColor(x);
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min() {
        Node min = min(this.root);
        return min == null ? null : min.key;
    }

    private Node min(Node x) {
        if (x == null) return null;
        return x.left == null ? x: min(x.left);
    }

    public Key max() {
        Node max = max(this.root);
        return max == null ? null : max.key;
    }

    private Node max(Node x) {
        if (x == null) return null;
        return x.right == null ? x : max(x.right);
    }

    public Key floor(Key key) {
        Node floor = floor(this.root, key);
        return floor == null ? null : floor.key;
    }
    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);

        if (cmp == 0) {
            return x;
        } else if (cmp < 0) {
            return floor(x.left, key);
        } else {
            Node floor = floor(x.right, key);
            return floor == null ? x : floor;
        }
    }

    public Key ceil(Key key) {
        Node ceil = ceil(this.root, key);
        return ceil == null ? null : ceil.key;
    }
    private Node ceil(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        } else if (cmp > 0) {
            return ceil(x.right, key);
        } else {
            Node ceil = ceil(x.left, key);
            return ceil == null ? x : ceil;
        }
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        keys(this.root, queue, this.min(), this.max());
        return queue;
    }

    private void keys(Node x, Queue<Key> list, Key lo, Key hi) {
        if (x == null) return;
        int cmpLo = lo.compareTo(x.key);
        int cmpHi = hi.compareTo(x.key);
        if (x.left != null) {
            keys(x.left, list, lo, hi);
        }

        if (cmpLo <= 0 && cmpHi >= 0) {
            list.enqueue(x.key);
        }

        if (x.right != null) {
            keys(x.right, list, lo, hi);
        }
    }

//    rank 4 means fifth elem
    private Node select(Node x, int rank) {
        if (x == null) return null;

        int leftSize = size(x.left);
        if (leftSize > rank) {
            return select(x.left, rank);
        } else {
            return leftSize < rank ? select(x.right, rank-leftSize-1) : x;
        }
    }

    private int rank(Node x, Key key) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return rank(x.left, key);
        } else {
            return cmp > 0 ? 1 + size(x.left) + rank(x.right, key) : size(x.left);
        }
    }

    /***********************************************************************
     * Helper methods
     ***********************************************************************/
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = x.left.color;
        x.left.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = x.right.color;
        x.right.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    private void flipColor(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    private boolean isRED(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    /**
     * Draw Tree
     */
    private void drawTree(Node root, double x, double y, double h) {
        if (root == null ) return;
        StdDraw.setPenRadius(.015);
        StdDraw.point(x, y);
        if (root.left != null) {
            StdDraw.setPenRadius(.005);
            h = StdRandom.uniform(0.01, 0.02);
            StdDraw.line(x,y, x-.018, y-.025-h);
            drawTree(root.left, x-0.018, y-0.025-h, h);
        }
        if (root.right != null) {
            StdDraw.setPenRadius(.005);
            h = StdRandom.uniform(0.01, 0.05);
            StdDraw.line(x,y, x+.018, y-.02-h);
            drawTree(root.right, x+0.018, y-0.02-h, h+0.015);
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
            this.drawTree(this.root.left, .25, .90, 0.05);
        }
        if (this.root.right != null) {
            StdDraw.setPenRadius(.005);
            StdDraw.line(.5, .95, .75, .90);
            StdDraw.setPenRadius(.015);
            StdDraw.point(.75, .90);
            this.drawTree(this.root.right, .75, .90, 0.05);
        }
    }


    public static void main(String[] args) {
        SoftwareCachingRedBlackBST<String, Integer> bst = new SoftwareCachingRedBlackBST<>();
        edu.princeton.cs.algs4.RedBlackBST<String, Integer> st = new edu.princeton.cs.algs4.RedBlackBST<>();
        String input = "SEARCHEXAMPLE";
        String[] a = input.split("");
        for (int i = 0; i < a.length; i++) {
            bst.put(a[i], i);
            st.put(a[i], i);
        }
//        bst.draw1(bst.root, input);
        System.out.printf("\nTesting get and put from actual and mine\n");
        System.out.printf("mine => get: %s, value: %d\nactu => get: %s, value: %d\n", "E", bst.get("E"), "E", st.get("E"));
        System.out.printf("mine => get: %s, value: %d\nactu => get: %s, value: %d\n", "A", bst.get("A"), "A", st.get("A"));

        System.out.printf("\nTesting max and min from actual and mine\n");
        System.out.printf("mine => min: %s\nactu => min: %s\n", bst.min(), st.min());
        System.out.printf("mine => max: %s\nactu => max: %s\n", bst.max(), st.max());

        System.out.printf("\nTesting floor from actual and mine\n");
        System.out.printf("mine => floor: %s, value: %s\nactu => floor: %s, value: %s\n", "T", bst.floor("T"), "T", st.floor("T"));
        System.out.printf("mine => floor: %s, value: %s\nactu => floor: %s, value: %s\n", "L", bst.floor("L"), "L", st.floor("L"));
        System.out.printf("mine => floor: %s, value: %s\nactu => floor: %s, value: %s\n", "H", bst.floor("H"), "H", st.floor("H"));
        System.out.printf("mine => floor: %s, value: %s\nactu => floor: %s, value: %s\n", "G", bst.floor("G"), "G", st.floor("G"));

        System.out.printf("\nTesting ceil from actual and mine\n");
        System.out.printf("mine => ceil: %s, value: %s\nactu => ceil: %s, value: %s\n", "T", bst.ceil("T"), "T", st.ceiling("T"));
        System.out.printf("mine => ceil: %s, value: %s\nactu => ceil: %s, value: %s\n", "L", bst.ceil("L"), "L", st.ceiling("L"));
        System.out.printf("mine => ceil: %s, value: %s\nactu => ceil: %s, value: %s\n", "H", bst.ceil("H"), "H", st.ceiling("H"));
        System.out.printf("mine => ceil: %s, value: %s\nactu => ceil: %s, value: %s\n", "G", bst.ceil("G"), "G", st.ceiling("G"));

        Iterator itr = bst.keys().iterator();
        while (itr.hasNext()) {
            System.out.printf("%s", itr.next());
        }
        System.out.printf("\n");

        itr = st.keys().iterator();
        while (itr.hasNext()) {
            System.out.printf("%s", itr.next());
        }

    }


}
