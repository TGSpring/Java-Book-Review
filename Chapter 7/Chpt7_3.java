/*
 * Tyler Spring
 * 5/4/2025
 * Chapter 7 Question 3
 * Write a method countInRange that accepts an array of integers, a minimum value, and a maximum value as parameters and returns the count of how many
 * elements from thr array fall between the minimum and maximum (inclusive).
 * For example:
 * in the array [14, 1, 22, 17, 36, 7, -43, 5], for the minimum value 4 and maximum 17, there are four elements whose values fall between 4 and 17.
 */
public class Chpt7_3 {
    public static void main(String[] args) {

        int[] nums = { 14, 1, 22, 17, 36, 7, -43, 5 };

        int max = 18;
        int min = 3;

        System.out.println(countInRange(nums, max, min));

    }

    public static int countInRange(int[] nums, int max, int min) {
        // increments when value falls between min and max.
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= min && nums[i] <= max) {
                count++;
            }
        }
        return count;
    }
}
/*
 * Time complexity is linear O(n), it has to iterate the entire array to
 * determine what values are between the hardcoded min and max.
 * 
 * Space complexity is constant O(1), the only additional space used is for a
 * few variables, including count, so the space requirement remains constant.
 */