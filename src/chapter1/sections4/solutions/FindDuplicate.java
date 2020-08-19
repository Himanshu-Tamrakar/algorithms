package chapter1.sections4.solutions;

public class FindDuplicate {
    /**
     * Find a duplicate. Given an array of N elements in which each element is an integer between 1 and N, write an algorithm to determine if there are any duplicates. Your algorithm should run in linear time and use O(1) extra space. Hint: you may destroy the array.
     * @param args
     */
    public static void main(String[] args) {
        int N = (int) Math.floor(Math.random() * 100);
        int[] a = new int[] {0,3,1,2,1,4,2};

        for (int i = 1; i < a.length; i++) {
            if (a[Math.abs(a[i])] > 0) {
                a[Math.abs(a[i])] = -a[Math.abs(a[i])];
            } else {
                System.out.printf("%s is duplicate at %d \n", Math.abs(a[i]), i);
            }
        }
    }
}
