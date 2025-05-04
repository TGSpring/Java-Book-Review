/*
 * Tyler Spring
 * 5/4/2025
 * Chapter 7 Question 2
 * Write a method called range that returns the range of values in an array of integers. The range is defined as 1 more than the difference between
 * the maximum and the minimum values in the array.
 * For example:
 * 
 * if an array called list contains the values [36, 12, 25, 19, 46, 31, 22]
 * 
 * the call or range (list) should return 35 (46 - 12 + 1).
 * You may assume that the array has at least one element.
 */
public class Chpt7_2 {
    public static void main(String[] args) {
        int[] nums = { 36, 12, 25, 19, 46, 31, 22 };
        System.out.println(range(nums));
    }

    public static int range(int[] arr) {
        int max = arr[0];
        int min = arr[0];

        for (int i = 0; i < arr.length; i++) {
            // Finds max by checking against first element.
            if (max < arr[i]) {
                max = arr[i];
            }
            // Finds min by checking against first element.
            if (min > arr[i]) {
                min = arr[i];
            }
        }
        // Subtracts range and adds 1.
        return max - min + 1;
    }
}
/*
 * Time Complexity is constant O(n), n is the size of the array. It has to
 * iterate the entire array to ensure it finds the correct minimum and maximum
 * values.
 * Space Complexity is linear O(1), hardcoded array with only 2 variables, a
 * loop and 2 conditionals.
 */