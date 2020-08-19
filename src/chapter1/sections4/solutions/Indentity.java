package chapter1.sections4.solutions;

import java.util.Arrays;

public class Indentity {
    public static int findIdentityIndex(int[] a) {
        Arrays.sort(a);
        return identity(a, 0, a.length-1);
    }

    public static int identity(int[] a, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if (mid == a[mid]) return mid;
        else if (mid > a[mid]) return identity(a, mid+1, hi);
        else return identity(a, lo, mid-1);
    }

    public static void main(String[] args) {
        int[] arr = new int[] {-6, -4, -2, 0, 2, 4, 6};
        System.out.printf("%d \n", findIdentityIndex(arr));

        arr = new int[] {-6, -5, -4, -3, -2, -1, 0, 1, 2, 9};
        System.out.printf("%d \n", findIdentityIndex(arr));

        arr = new int[] {-6, -5, -4, -3, -2, -1, 0, 1, 2, 10};
        System.out.printf("%d \n", findIdentityIndex(arr));
    }
}
