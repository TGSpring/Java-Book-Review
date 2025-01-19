/**
 * Tyler Spring
 * 1/19/2025
 * The case study involves calculating the motion of a projectile based on its initial velocity, launch
 * angle, and the laws of physics. It highlights the importance of organizing code into reusable,
 * modular methods.
 */

import java.util.Scanner;

public class caseStudy {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        boolean continueSim = true;

        //Loop logic to allow multiple projectiles
        while(continueSim) {

        //Input
        double velocity = getInput("Enter initial velocity (m/s): ", input);
        double angle = getInput("Enter launch angle (degrees): ", input);

        //Convert angle to radians
        double angleInRads = Math.toRadians(angle);

        //Calculations
        double timeOfFlight = calcTimeOfFlight(velocity, angleInRads);
        double maxHeight = calcHeight(velocity, angleInRads);
        double range = calcRange(velocity, angleInRads);

        //Output
        displayResults(timeOfFlight, maxHeight, range);

        //Ask if user wants to continue.
        continueSim = askToCont(input);
        }
        input.close();
    }

    public static boolean askToCont(Scanner input) {
        System.out.print("Do you want to simulate another projectile? (yes/no)");
        String response = input.next();
        return response.equalsIgnoreCase("yes");
    }


    //Method to gather input
    public static double getInput (String prompt, Scanner input) {
        System.out.print(prompt);
        return input.nextDouble();
    }

    //Method to calculate time of timeOfFlight
    public static double calcTimeOfFlight(double velocity, double angleInRads) {
        return (2 * velocity * Math.sin(angleInRads)) / 9.8;
    }

    //Method to calculate maximum height
    public static double calcHeight(double velocity, double angleInRads) {
        return (Math.pow(velocity * Math.sin(angleInRads), 2)) / (2 * 9.8);
    }

    //Method to calculate range.
    public static double calcRange(double velocity, double angleInRads) {
        return (Math.pow(velocity, 2) * Math.sin(2 * angleInRads)) / 9.8;
    }

    //Method to display results.
    public static void displayResults(double timeOfFlight, double maxHeight, double range) {
        System.out.printf("Time of Flight: %.2f seconds%n", timeOfFlight);
        System.out.printf("Maximum Height reached: %.2f meters%n", maxHeight);
        System.out.printf("Range: %.2f meters%n", range);

    }
}