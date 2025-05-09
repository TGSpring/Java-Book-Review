/*
 * Tyler Spring
 * 5/9/2025
 * Chapter 7 Question 13
 * Write a method called longestSortedSequence that accepts an array of integers as a parameter and returns the length of the longest sorted 
 * (nondecreasing) sequence of integers in the array.
 * For example:
 * in the array [3, 8, 10, 1, 9, 14, -3, 0, 14, 207, 56, 98, 12]
 * the longest sorted sequence in the array has four values in it -3, 0, 14, 207
 * so your method would return 4 if passed this array. Sorted means nondecreasing, so a sequence could contain duplicates. Your method should return 0 if passed an empty array.
 */
public class Chpt7_7 {
    public static void main(String[] args) {
        int[] nums = { 3, 8, 10, 1, 9, 14, -3, 0, 14, 207, 56, 98, 12 };
        System.out.println(longestSortedSequence(nums));
    }

    public static int longestSortedSequence(int[] nums) {
        // if array is empty.
        if (nums.length == 0) {

            return 0;
        }
        // Length to be returned.
        int maxLength = 0;
        // Tracks length during current iteration.
        int curLength = 1;
        // Iterate array of integers.
        for (int i = 0; i < nums.length - 1; i++) {

            // Checks if next element is larger or equal to current element.
            if (nums[i + 1] >= nums[i]) {
                // Not required to print the values, but I found it very helpful in debugging
                // this problem.
                // System.out.println(nums[i + 1] + " " + nums[i]);

                // Increment length count if true.
                curLength++;

            } else {
                // Checks currently assigned max length.
                maxLength = Math.max(maxLength, curLength);

                // Resets length for next sequence.
                curLength = 1;
            }

        }
        // Final check for max length. You could just check once in the if block, but
        // this validates data safely.
        maxLength = Math.max(maxLength, curLength);

        return maxLength;

    }
}
/*
 * Time complexity is linear O(n), worst case is no matches and iterates entire
 * array.
 * Space complexity is constant O(1), fixed size array and fixed variables.
 */