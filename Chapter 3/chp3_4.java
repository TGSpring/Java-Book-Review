
/**
 * Tyler Spring
 * 1/24/2025
 * 
 * Problem:
 * Write a program that calculates the distance between two points in 3D space
 * using distance formula.
 */
import java.math.*;
import java.util.Scanner;

public class chp3_4 {
    public static double calcDist(double x1, double y1, double z1, double x2, double y2, double z2) {
        double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) + Math.pow(z2 - z1, 2));
        return distance;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the coordinates of the first point(x1, y1, z1): ");
        double x1 = sc.nextDouble();
        double y1 = sc.nextDouble();
        double z1 = sc.nextDouble();

        System.out.println("Enter the coordinates of the second point (x2, y2, z2): ");
        double x2 = sc.nextDouble();
        double y2 = sc.nextDouble();
        double z2 = sc.nextDouble();

        // Calc and display distance
        double distance = calcDist(x1, y1, z1, x2, y2, z2);
        System.out.println("The distance between the two points is: " + distance);
        sc.close();
    }
}
