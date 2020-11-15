package chapter5.section1.solutions;

/**
 *
 * Given an array a[] of N 64-bit integers and a target value T, determine whether there are two distinct integers i and j such that a[i] + a[j] equals T. Your algorithm should run in linear time in the worst case.
 *
 * Solution. Radix sort the array in linear time.
 * Scan a pointer i from left to right and a pointer j from right to left: consider a[i] + a[j].
 * If it is bigger than T, advance the j pointer; if it is smaller than T, advance the i pointer; if it is equal to T, we have found the desired indices.
 *
 *
 * Note that the array of integers can be radix sorted in linear time and constant extra space using the
 * advanced radix sorting algorithm of Franceschini, Muthukrishnan, and Patrascu.
 */
public class TwoSumLinearTime {
}
