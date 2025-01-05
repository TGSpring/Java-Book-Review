/**
 * Tyler Spring
 * 1/5/2025
 * 2.3
 * Cumulative Sum
 */
public class chpt2_3 {

    public static int iterSum(int n) {
      int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum +=i;
        }
        return sum;
    }


    public static int mathSum(int n) {
        return n * (n + 1) / 2;
    }
    public static void main(String[] args) {
        int n = 5; 
        int res = iterSum(n);
        int mRes = mathSum(n);
        System.out.printf("Iterative sum of " + n + " is " + res +
        "\n" + "Mathematical sum of " + n + " is " + mRes);
    }
}
/* Iterative Approach: Uses time complexity of O(n) because it iterates through all the
 * numbers from 1 to n.
 * 
 * Mathematical Approach: Uses time complexity of O(1) because it uses a formula to calculate
 */