package chapter2.section4.solutions;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.MinPQ;

import java.util.PriorityQueue;

/*********************************************
 * If odd then middle sorted elem is median if even elems then avg of two middle is median
 ********************************************/
public class Solution30_DynamicMedian {
    private MinPQ<Integer> minPQ;
    private MaxPQ<Integer> maxPQ;
    private int cm = Integer.MIN_VALUE;

    public Solution30_DynamicMedian() {
        this.minPQ = new MinPQ<>();
        this.maxPQ = new MaxPQ<>();
    }

    public int getMedian() {
        if (minPQ.size() == maxPQ.size()) {
            return (int)((minPQ.min() + maxPQ.max()) / 2);
        } else if (minPQ.size() > maxPQ.size()) {
            return minPQ.min();
        } else {
            return maxPQ.max();
        }
    }

    public int put(int item) {
        if (item > cm) {
            minPQ.insert(item);
            if (minPQ.size() - maxPQ.size() >= 2) {
                maxPQ.insert(minPQ.delMin());
            }
        } else {
            maxPQ.insert(item);
            if (maxPQ.size() - minPQ.size() >= 2) {
                minPQ.insert(maxPQ.delMax());
            }
        }
        return this.cm = getMedian();
    }


    public static void main(String[] args) {
        Solution30_DynamicMedian sol = new Solution30_DynamicMedian();
        int a[] = {5, 15, 1, 3, 2, 8, 7, 9, 10, 6, 11, 4};
        for (int i = 0; i < a.length; i++) {
            System.out.printf("Median of: ");
            for (int j = 0; j <= i; j++) {
                System.out.printf("%d ", a[j]);
            }
            System.out.printf("is %d \n", sol.put(a[i]));
        }
    }
}
