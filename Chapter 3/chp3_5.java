/**
 * Tyler Spring
 * 1/26/2025
 * Chapter 3, Exercise 5
 * 
 * Returning Values from Methods
 * Problem:
 * Write a method getSmallestDifference that takes two arrays of integers as
 * parameters and returns the smallest absolute difference between any two
 * numbers (one from each array).
 * For example:
 * 
 * java
 * Copy
 * Edit
 * int[] array1 = {10, 20, 14, 16, 18};
 * int[] array2 = {30, 23, 54, 33, 96};
 * System.out.println(getSmallestDifference(array1, array2)); // Output: 3
 */

import java.util.Arrays;
// import statement removed as Math.abs is in java.lang package
// import statement removed as Math.abs is in java.lang package
public class chp3_5 {
    public static int getSmallestDiff(int[] arr1, int[] arr2) {
        if (arr1.length == 0 || arr2.length == 0) {
            return -1; // Return -1 if either array is empty
        }
        int smallestDiff = Integer.MAX_VALUE;

        for(int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                int diff = Math.abs(arr1[i] - arr2[j]);
                if(diff < smallestDiff) {
                    smallestDiff = diff;
                }
            }
        }
        return smallestDiff;
    }

    public static void main(String[] args) {
        int[] array1 = {10, 20, 14, 16, 18};
        int[] array2 = {30, 23, 54, 33, 96};
        System.out.println(getSmallestDiff(array1, array2)); // Output: 3
    }
}
