/*
 * Tyler Spring
 * 5/7/2025
 * Chapter 7 Question 6
 * Write a method called stdev that returns the standard deviation of an array of integers. Standard deviation is computed by taking the square root of the sum of the squares
 * of the differences between each element and the mean, divided by the one less than the number of elements. 
 * This is the formula, no, I did not type this, AI did, I do not care.
 *  stdev = √(Σ(xi – mean)² / (n – 1))
 */
public class Chpt7_5 {
    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 45, 68, 100 };
        System.out.println(stdev(nums));
    }

    public static double stdev(int[] nums) {
        int n = nums.length;
        double sum = 0;

        // Calculating mean.
        for (int x : nums) {
            sum += x;
        }
        double mean = sum / n;

        double sqSum = 0;

        // Sum up the squared differences.
        for (int x : nums) {
            sqSum += Math.pow(x - mean, 2);
        }
        // Return for stdev.
        return Math.sqrt(sqSum / (n - 1));
    }
}
/*
 * Space complexity is constant O(1), only an array and variables for the life
 * time of the method are used.
 * Time complexity is linear O(n), in the worst case because it has to iterate
 * through all the elements.
 */