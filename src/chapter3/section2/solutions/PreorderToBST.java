package chapter3.section2.solutions;

public class PreorderToBST {

    private Node root;

    /**************************************************************
     * Node Class
     **************************************************************/
    private static class Node {
        private int key;
        private int value;
        private Node left, right;

        private Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }


    private static Node constructTree(int[] preorder, int lo, int hi) {
        if (lo > hi) return null;
        if (lo == hi) return new Node(preorder[lo], preorder[lo]);
        Node root = new Node(preorder[lo], preorder[lo]);
        int j;
        for (j = lo+1; j <= hi; j++) {
            if (preorder[lo] < preorder[j]) break;
        }

        if (j <= hi) {
            root.left = constructTree(preorder, lo+1, j-1);
            root.right = constructTree(preorder, j, hi);
        } else {
            Node x = root;
            for (int i = lo+1; i <= hi; i++) {
                x.left = new Node(preorder[i], preorder[i]);
                x = x.left;
            }
        }
        return root;
    }


    public static void main(String[] args) {
        int[] preorder = new int[] {6,4,2,5,8,7,9};
        Node root = constructTree(preorder, 0, 6);

        preorder = new int[] {6,4,2,8,7,9};
        root = constructTree(preorder, 0, 5);

    }
}
