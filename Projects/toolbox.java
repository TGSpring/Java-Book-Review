
/**
 * Tyler Spring
 * 1/17/2025
 * tools class to store all the methods for numberCruncher.
 */
import java.util.Scanner;

public class toolbox {
    public static void powerCalc(double base, double exponent) {
        System.out.println("Power is: " + Math.pow(base, exponent));
    }

    // Prints the sum of even and odds numbers up to limit.
    public static void sumEvenOdd(int limit) {
        int evenSum = 0;
        int oddSum = 0;

        for (int i = 0; i <= limit; i++) {
            if (i % 2 == 0) {
                evenSum += i;
            } else {
                oddSum += i;
            }
        }
        System.out.printf("Sum of even numbers: %d\nSum of odd numbers: %d\n", evenSum, oddSum);

    }

    // Prints a random number between min and max
    public static void randNum(int min, int max) {
        int range = (max - min) + 1;
        int rand = (int) (Math.random() * range) + min;
        System.out.println("Random number between " + min + " and " + max + " is " + rand);
    }

    // Returns true if number is prime, otherwise false.
    public static boolean isPrime(int pNum) {
        if (pNum <= 1) {
            return false;
        } else {
            for (int i = 2; i * i <= pNum; i++) {
                if (pNum % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    // Prints the reverse of a number
    public static void revNum(int n) {
        int og = n;
        int rev = 0;
        while (n != 0) {
            rev = rev * 10 + n % 10;
            n = n / 10;
        }
        System.out.println("Reversed number of " + og + " is " + rev);
    }

    // Prints a triangle or rectangle pattern of rows.
    public static void printRow(int rows) {
        System.out.println("Enter T for triangle or R for rectangle: ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        if (input.equalsIgnoreCase("T")) {
            for (int i = 1; i <= rows; i++) {
                for (int j = 0; j < i; j++) {
                    System.out.print("*");
                }
                System.out.println();
            }
        } else if (input.equalsIgnoreCase("R")) {
            for (int r = 0; r < rows; r++) {
                for (int k = 0; k < rows; k++) {
                    System.out.print("*");
                }
                System.out.println();
            }
        } else {
            System.out.println("Invalid input.");
        }
    }

    // Prints the approximate value of pi based on value "terms".
    public static void ApxPi(int input) {
        double sum = 0;
        for (int i = 0; i <= input; i++) {
            int denominator = 2 * i + 1;

            if (i % 2 == 0) {
                sum += 1.0 / denominator;
            } else {
                sum -= 1.0 / denominator;
            }
        }
        double pi = 4 * sum;
        System.out.println("Pi is approximately: " + pi);
    }
}
