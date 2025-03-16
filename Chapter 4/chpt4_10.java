/**
 * Tyler Spring
 * 3/16/2025
 * Chapter 4 question 10
 * BMI Calculator with Categories
 * 
 * Write a method bmiCategory(double weight, double heightMeters) that
 * calculates BMI and returns "Underweight", "Normal weight", "Overweight", or
 * "Obese" based on standard BMI categories.
 */
public class chpt4_10 {
    public static void main(String[] args) {
        System.out.println(bmiCategory(150, 1.5));
    }

    public static String bmiCategory(double weight, double heightMeters) {
        double num = (weight / (heightMeters * heightMeters));

        if (num < 18.5) {
            return "Underweight";
        }
        if (num < 25) {
            return "Normal Weight";
        }
        if (num < 30) {
            return "Overweight";
        } else
            return "Obese";
    }
}
/**
 * Analysis
 * Input Size Consideration:
 * 
 * How many inputs does your function receive?
 * - 2
 * Does the number of operations depend on any variable length property of the
 * input (e.g., the number of digits or characters)?
 * - No
 * 
 * Operations Performed:
 * 
 * What are the specific mathematical operations your method performs?
 * - Division and multiplication
 * Are these operations constant (e.g., multiplication, addition), or do they
 * vary with the input?
 * - constant
 * 
 * Loop or Recursion Check:
 * 
 * Does your method use any loops or recursive calls?
 * - No
 * If not, how many operations are executed regardless of the input values?
 * - 2
 * Space Allocation:
 * 
 * Apart from the input values, do you allocate any additional data structures
 * (arrays, lists, etc.)?
 * - No
 * How much extra memory do you allocate—is it fixed regardless of input?
 * - 1 variable num in the bmi method
 * 
 * Constant Factors vs. Variable Factors:
 * 
 * After answering the above, can you determine if the execution time grows with
 * the input size (n) or stays constant, no matter what values are supplied?
 * - Time and Space complexity are O(1).
 */