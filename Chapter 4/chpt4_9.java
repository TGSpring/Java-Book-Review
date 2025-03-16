/**
 * Tyler Spring
 * 3/16/2025
 * Chapter 4 question 9
 * BMI Case Study & Logic Applications
 * Height Converter
 * 
 * Write a method convertHeight(double feet, double inches) that converts height
 * to meters (1 inch = 0.0254m, 1 foot = 12 inches).
 * BMI Calculator with Categories
 */
public class chpt4_9 {
    public static void main(String[] args) {
        System.out.println(convertHeight(6.0, 1.0));
    }

    public static double convertHeight(double feet, double inches) {
        double totalMeter = (feet * 12) + inches;
        return totalMeter * 0.0254;
    }
}
/**
 * Analysis
 * Input size:
 * - 2 doubles, feet and inches
 * - number of operations does not depend on size of input.
 * 
 * Operations performed:
 * - conversion of feet and inches into meters, multiplication, addition, and
 * multiplication.
 * - They do not vary by input.
 * 
 * Loop or Recursion:
 * - No iteration at all.
 * - 2 Operations every call.
 * 
 * Space:
 * - No structures created.
 * - fixed allocation
 * 
 * Constants/Variables:
 * - Constant time.
 * 
 * Time complexity: O(1)
 * Space complexity: O(1)
 */