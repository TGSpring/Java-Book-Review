
/**
 * Tyler Spring
 * 
 * 
 * Multi-Function Engineering Calculator
 * Chapter 3 Project
 *
 * Goal:
 * Build a modular Java console application focused on:
 * methods
 * parameters
 * return values
 * Math class calculations
 * Scanner input/validation
 * loops and switch statements
 * clean program structure
 *
 * Main Menu:
 * 1. Distance Calculator
 * 2. Projectile Motion
 * 3. Compound Interest
 * 4. Area Calculator
 * 5. Temperature Conversion
 * 6. Exit
 *
 * Design Rules:
 * Keep main() small and organized
 * Separate input, calculations, and output
 * Calculation methods should return values
 * Validation/input handled through menu flow
 * Avoid stuffing all logic into switch cases
 *
 * Core Pattern:
 * INPUT → VALIDATE → TRANSFORM → COMPUTE → DISPLAY
 */
import java.util.Scanner;

public class EngCalc {
    private static final double GRAVITY = 9.81;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        do {
            System.out.println("\n--- Engineering Calculator ---");
            System.out.println("1. Distance Calculator");
            System.out.println("2. Projectile Motion");
            System.out.println("3. Compound Interest");
            System.out.println("4. Area Calculator");
            System.out.println("5. Temperature Conversion");
            System.out.println("6. Exit");
            System.out.print("\nChoose an option: ");
            int choice = 0;
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
            } else {
                System.out.println("invalid input. Please select menu option");
                sc.next();
                continue;
            }
            switch (choice) {
                case 1:
                    // distance module()
                    runDistance(sc);
                    break;

                case 2:
                    // Projectile module
                    runProjectile(sc);
                    break;

                case 3:
                    // compound interest
                    runCompoundInterest(sc);
                    break;

                case 4:
                    // Area Calculator
                    runArea(sc);
                    break;

                case 5:
                    // Temp conversion
                    runTemp(sc);
                    break;

                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (running);
        sc.close();

    }

    /*--------------------------------------------------------------------------------- */
    /**
     * Runs the distance Calculator module.
     * Collects two coordinate points from the user,
     * computes the Euclidean distance, and displays results.
     * 
     */
    public static void runDistance(Scanner sc) {
        double x1 = getDouble(sc, "\nEnter x1: ");
        double y1 = getDouble(sc, "Enter y1: ");
        double x2 = getDouble(sc, "Enter x2: ");
        double y2 = getDouble(sc, "Enter y2: ");

        double distance = calcDistance(x1, y1, x2, y2);

        System.out.println("Distance: " + format(distance));
    }

    /**
     * Calculates Euclidean distance between two points (x1, y1) and (x2, y2).
     * 
     * @return distance between two points.
     * 
     */
    public static double calcDistance(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;

        double distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

        return distance;
    }

    /*--------------------------------------------------------------------------------- */
    /**
     * Runs the Projectile Motion module.
     * Converts velocity and angle into horizontal/vertical components,
     * computes time of flight, max height, and range.
     */
    public static void runProjectile(Scanner sc) {
        double velocity = getDouble(sc, "\nEnter velocity: ");
        double angleDeg = getDouble(sc, "Enter launch angle (degrees): ");

        double angleRad = Math.toRadians(angleDeg);

        double verticalVelocity = velocity * Math.sin(angleRad);
        double horizontalVelocity = velocity * Math.cos(angleRad);

        double time = calcTime(verticalVelocity);
        double height = calcHeight(verticalVelocity);
        double range = calcRange(horizontalVelocity, time);

        displayProjectile(time, height, range);
    }

    /**
     * Computes horizontal range using velocity and time.
     */
    private static double calcRange(double horizontalVelocity, double time) {
        return horizontalVelocity * time;
    }

    /**
     * Computes maximum height using vertical velocity.
     */
    private static double calcHeight(double verticalVelocity) {
        return (Math.pow(verticalVelocity, 2)) / (2 * GRAVITY);
    }

    /**
     * Computes time of flight based on vertical velocity.
     */
    private static double calcTime(double verticalVelocity) {
        return (2 * verticalVelocity) / GRAVITY;
    }

    /**
     * Displays projectile motion results in a readable format.
     */
    private static void displayProjectile(double time, double height, double range) {
        System.out.println("\n--- Projectile Results ---");
        System.out.println("Time of Flight: " + format(time));
        System.out.println("Max Height: " + format(height));
        System.out.println("Range: " + format(range));
    }

    /*--------------------------------------------------------------------------------- */
    /**
     * Runs Compound Interest module.
     * Collects principal, interest rate, time, and compounding frequency.
     * Computes the future account balance, and displays results.
     * 
     */
    public static void runCompoundInterest(Scanner sc) {
        double principal = getDouble(sc, "\nEnter principal: ");
        double rate = getDouble(sc, "Enter rate: ");
        double time = getDouble(sc, "Enter time: ");
        double comp = getDouble(sc, "Enter compounding frequency: ");

        double rate2 = rate / 100.0;

        double ans = computeComp(principal, rate2, time, comp);

        displayCompInterest(ans, principal, rate2, time, comp);

    }

    /**
     * Computes the future account balance using
     * the compound interest formula.
     * 
     * @return final account balance after compounding.
     */
    private static double computeComp(double principal, double rate2, double time, double comp) {

        return principal * Math.pow(1 + rate2 / comp, comp * time);
    }

    /**
     * Displays compound interest inputs and results in a readable format.
     * 
     */
    private static void displayCompInterest(double ans, double principal, double rate2, double time, double comp) {
        System.out.println("\n--- Compound Interest Results ---");
        System.out.println("principal: " + principal);
        System.out.println("rate: " + rate2);
        System.out.println("time: " + time);
        System.out.println("comp: " + comp);
        System.out.println("Final Balance: " + ans);
    }

    /*--------------------------------------------------------------------------------- */
    /**
     * Runs the Area Calculator module.
     * Allows user to choose a shape (circle, square, triangle)
     * and routes to the appropriate calculation method.
     */
    public static void runArea(Scanner sc) {
        System.out.println("\n--- Area Calculator ---");
        System.out.println("1. Circle");
        System.out.println("2. Square");
        System.out.println("3. Triangle\n");

        int shape = getInt(sc, "Select a shape: ");

        switch (shape) {

            case 1:
                runCircle(sc);
                break;

            case 2:
                runSquare(sc);
                break;

            case 3:
                runTriangle(sc);
                break;

            default:
                System.out.println("Invalid choice.");
                break;

        }
    }

    /**
     * AREA OF CIRCLE
     * Gets radius input and computes circle area.
     */
    private static void runCircle(Scanner sc) {
        double radius = getDouble(sc, "Enter Radius: ");
        double area = calcCircleArea(radius);
        displayCircle(area);
    }

    /**
     * Calculates area of a circle using formula: A = PI * r^2
     */
    private static double calcCircleArea(double radius) {
        return Math.PI * radius * radius;
    }

    /**
     * Displays computed circle area.
     */
    private static void displayCircle(double area) {
        System.out.println("The area of the circle is: " + area);
    }

    /**
     * AREA OF SQUARE
     * Calculates area using side length.
     */
    public static void runSquare(Scanner sc) {
        double side = getDouble(sc, "Enter side: ");
        double area = calcSqArea(side);
        displaySq(area);
    }

    /**
     * Calculates area of a square: A = s^2.
     */
    private static double calcSqArea(double side) {
        return side * side;
    }

    /**
     * Displays computed square area.
     */
    private static void displaySq(double area) {
        System.out.println("The area of the square is: " + area);
    }

    /**
     * AREA OF TRIANGLE
     * Uses base and height to compute area.
     */
    private static void runTriangle(Scanner sc) {
        double base = getDouble(sc, "Enter base: ");
        double height = getDouble(sc, "Enter height: ");
        double area = calcTri(base, height);
        displayTri(area);
    }

    /**
     * Calculates triangle area: A = 1/2 * b * h.
     */
    private static double calcTri(double base, double height) {
        return 0.5 * base * height;
    }

    /**
     * Displays computed triangle area.
     */
    private static void displayTri(double area) {
        System.out.println("The area of the Triangle is: " + area);
    }

    /*--------------------------------------------------------------------------------- */

    /**
     * Runs the Temperature Conversion module.
     * Takes a single temperature input and computes:
     * - Celsius to Fahrenheit conversion.
     * - Fahrenheit to Celsius conversion.
     */
    private static void runTemp(Scanner sc) {
        double temp = getDouble(sc, "Enter Temperature: ");

        double celToF = (temp * 9.0 / 5.0) + 32;
        double fToCel = (temp - 32) * 5.0 / 9.0;

        displayTemp(celToF, fToCel);
    }

    /**
     * Displays temperature conversion results.
     * Shows both Celsius to Fahrenheit and Fahrenheit to Celsius
     * calculations for comparison.
     */
    private static void displayTemp(double celToF, double fToCel) {
        System.out.println("\n--- Temperature Conversion ---");
        System.out.println("Celsius to Fahrenheit: " + celToF);
        System.out.println("Fahrenheit to Celsius: " + fToCel);
    }

    /*--------------------------------------------------------------------------------- */
    /**
     * Formats small floating-point errors to clean zero values.
     */
    private static double format(double value) {
        if (Math.abs(value) < 1e-10) {
            return 0.0;
        }
        return value;
    }

    /**
     * Prompts the user for a valid double value.
     * Continues prompting until a valid numeric input is entered.
     *
     * @param sc     Scanner used for user input
     * @param prompt message displayed to the user
     * @return validated double value entered by the user
     */
    public static double getDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);

            if (sc.hasNextDouble()) {
                double val = sc.nextDouble();
                sc.nextLine(); // Prevent newline issues.
                return val;
            } else {
                System.out.println("Invalid input. Enter a number.");
                sc.next(); // consume invalid input.
            }
        }
    }

    /**
     * Prompts the user for a valid integer value.
     * Continues prompting until a valid numeric input is entered.
     *
     * @param sc     Scanner used for user input
     * @param prompt message displayed to the user
     * @return validated integer value entered by the user
     */
    public static int getInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);

            if (sc.hasNextInt()) {
                int val = sc.nextInt();
                sc.nextLine(); // Prevent newline issues.
                return val;
            } else {
                System.out.println("Invalid input. Enter a number for a shape.");
                sc.next(); // consumes invalid input.
            }
        }
    }

}
