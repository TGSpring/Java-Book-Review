/**
 * Tyler Spring
 * 1/24/2025
 * Chapter 3 problem 2
 * 
 * Create overloaded methods calculateArea that can compute the area of:
 * A rectangle (accepts length and width as parameters).
 * A circle (accepts radius as a parameter).
 * A triangle (accepts base and height as parameters).
 * Write a main method that demonstrates calling all three versions with
 * appropriate values.
 */
import java.math.*;
public class chp3_2 {

    /**
    Area of rectangle
    Using ints for parameters with double return type 
    to demonstrate overloading with widening conversion
    */
    public static double calcArea(int len, int wid) {
        return len * wid;
    }

    //Area of circle
    public static double calcArea(double rad) {
        return Math.PI * rad * rad;

    }

    //Area of triangle
    public static double calcArea(double base, double height) {
        return 0.5 * base * height;
    }
    public static void main(String[] args) {
        System.out.println("Area of rectangle with length 5 and width 10: " + calcArea(5, 10));
        System.out.println("Area of circle with radius of 5: " + calcArea(5));
        System.out.println("Area of triangle with base of 5 and height of 10: " + calcArea(5, 10));
    }
}