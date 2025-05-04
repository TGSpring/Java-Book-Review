/*
 * Tyler Spring
 * 5/4/2025
 * Chapter 7 Question 1
 * Write a method called lastIndexOf that accepts an array of integers and an integer value as its parameters 
 * and returns the last index aat which the value occurs in the array.
 * The method should return -1 if the value is not found.
 * For example:
 * [74, 85, 102, 99, 101, 85, 56]
 * the last index of the value 85 is 5.
 */

public class Chpt7_1 {
    public static void main(String[] args) {
        int[] nums = { 74, 85, 102, 99, 101, 85, 56 };
        int value = 85;
        System.out.println(lastIndexOf(nums, value));
    }

    public static int lastIndexOf(int[] arr, int value) {

        // Iterating backwards, if you want to find the last occurrence, it would be at
        // the end of the array.
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == value) {
                return i;
            }
        }

        return -1;
    }
}
/*
 * Space Complexity is constant O(1), array and target value are hard coded, no
 * structures other a for loop.
 * Time Complexity is linear O(n), in the worst case it does not find the value
 * and has to iterate the entire list. Best case
 * is that it finds the value early.
 */