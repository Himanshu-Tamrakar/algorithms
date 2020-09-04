package chapter3.section2.solutions;

public class FindTwoSwapKeyInBST {
    /**
     *Find two swapped keys in a BST. Given a BST in which two keys in two nodes have been swapped, find the two keys.
     *
     * Solution. Consider the inorder traversal a[] of the BST. There are two cases to consider.
     * Suppose there is only one index p such that a[p] > a[p+1]. Then swap the keys a[p] and a[p+1]. Otherwise,
     * there are two indices p and q such a[p] > a[p+1] and a[q] > a[q+1]. Let's assume p < q. Then,
     * swap the keys a[p] and a[q+1].
     *
     */
}
