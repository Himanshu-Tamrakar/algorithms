package chapter2.section4.solutions;

import edu.princeton.cs.algs4.MinPQ;

public class Solution25_ComputationalNumberTheory {
    MinPQ<CubeSum> pq;

    private class CubeSum implements Comparable<CubeSum> {
        private int i, j;
        private double cubes;

        private CubeSum(int i, int j) {
            this.i = i;
            this.j = j;
            this.cubes = Math.pow(i, 3) + Math.pow(j, 3);
        }

        @Override
        public int compareTo(CubeSum that) {
            if (this.cubes > that.cubes) return 1;
            else if (this.cubes < that.cubes) return -1;
            return 0;
        }

        @Override
        public String toString() {
            return new String("i: " + i + ", j:" + j + ", cube: " + cubes);
        }
    }
    public Solution25_ComputationalNumberTheory(int n) {
        this.pq = new MinPQ<>(n+1);
        for (int i = 0; i < n; i++) {
            // because we want 0 <= a <= b <= n: this will become optimal case;
            // Can be calculated by initializing i, 0 but it will become worst case;
            CubeSum cs = new CubeSum(i, i);
//            CubeSum cs = new CubeSum(i, 0);
            pq.insert(cs);
        }

        while (!pq.isEmpty()) {
            CubeSum min = pq.delMin();
            System.out.printf("%s\n", min);
            if (min.j < n) {
                pq.insert(new CubeSum(min.i, min.j+1));
            }
        }


    }

    public static void main(String[] args) {
        Solution25_ComputationalNumberTheory sol = new Solution25_ComputationalNumberTheory(12);
    }
}
