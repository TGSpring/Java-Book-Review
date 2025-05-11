/*
 * Tyler Spring
 * 5/11/2025
 * Chapter 7 Question 15
 * Write a method called collapse that accepts an array of integers as a parameter and returns a new array containing the result of replacing each pair of integers 
 * with the sum of that pair.
 * For example:
 * if an array called list stores the values [7, 2, 8, 9, 4, 13, 7, 1, 9, 10]
 * then the call of collapse(list) should return a new array containing [9, 17, 17, 8, 19].
 * The first pair from the original list is collapsed into 9(7 + 2), the second pair is collapse into 17(8 + 9), and so on.
 * If the list stores an odd number of elements, the final element is not collapsed.
 * For example:
 * if the list had been [1, 2, 3, 4, 5] then the call would return [3, 7, 5].
 * Your method should not change the array that is passed as a parameter.
 */

import java.util.Arrays;

public class Chpt7_8 {
    public static void main(String[] args) {
        // array to be passed as parameter.
        int[] nums = { 7, 2, 8, 9, 4, 13, 7, 1, 9, 10, 14 };

        // array be returned.
        int[] result = collapse(nums);

        System.out.println(Arrays.toString(result));

    }

    public static int[] collapse(int[] nums) {
        // Array to be created and returned.
        int[] result;

        if (nums.length % 2 == 0) {
            // Even length array is halved.
            result = new int[nums.length / 2];
        } else {
            // Odd length array is halved, then added to.
            result = new int[nums.length / 2 + 1];
        }

        for (int i = 0; i < nums.length - 1; i += 2) {
            // The loop is iterating by 2, each iteration the it is finding the sum of the
            // current element
            // in nums at i, and adding it to the next one, i + 1.
            // Then it maps that summation to results by dividing i, which is incrementing
            // by 2, by 2.
            result[i / 2] = nums[i] + nums[i + 1];
        }

        // Odd length check. Handling final element.
        if (nums.length % 2 != 0) {
            result[result.length - 1] = nums[nums.length - 1];
        }

        return result;
    }
}
/*
 * Time complexity is linear O(n), n is the size of the input array. We have to
 * iterate the entire array two elements at a time.
 * 
 * Space complexity is linear but, O(n / 2), for the result array, this is
 * because we are creating an array that is half the size of n.
 */