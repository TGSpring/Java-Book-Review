/**
 * Tyler Spring
 * 1/29/2025
 * Chapter 3 Exercise 8
 * 
 * 8. Using Math.pow()
 * Problem:
 * Write a program that calculates the compound interest for an investment over
 * a given number of years.
 */
import java.util.Scanner;

public class chp3_8 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the initial principal balance: ");
        double p = sc.nextDouble();

        System.out.println("Enter the annual interest rate (as a decimal): ");
        double r = sc.nextDouble();

        System.out.println("Enter the number of times interest applied per year: " );
        double n = sc.nextDouble();

        System.out.println("Enter the number of years: ");
        double t = sc.nextDouble();

        double a = p * Math.pow(1 + r/n, n*t);
        System.out.println("Final amount: " + a);

        sc.close();
    }
}
