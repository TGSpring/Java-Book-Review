
import java.util.Random;
import java.util.Scanner;

/**
 * Tyler Spring
 * NetWork Intrusion Monitor
 * Create a java program that simulates a primitive intrusion monitoring
 * terminal.
 * Program must ask for:
 * number of scan cycles
 * a danger threshold
 *
 * During each scan cycle:
 * use a loop to simulate scans.
 * For each cycle:
 * Generate a random danger value random number from 1 - 100
 *
 * Detection Rules:
 * If danger level is:
 * Even = add to evenThreatSum
 * Odd = add to oddThreatSum
 *
 * If danger level exceeds threshold:
 * Print !!! ALERT !!! and increment an alert counter
 *
 * If danger level is exactly: 13 66 or 99 Print Critical signature detected.
 *
 * Special Loop requirements: at least one continue at least one break
 *
 * After loop finishes print:
 *
 * ===== SCAN REPORT =====
 * Total Alerts:
 * Even Threat Sum:
 * Odd Threat Sum:
 * Highest Threat:
 * Average Threat:
 *
 */
public class NetworkIntrusion {

    public static void main(String[] args) {
        /**
         * These variables are declared outside the loop so they can maintain
         * state across all cycles. They act as accumulators and trackers for the
         * simulation (even/odd totals, alerts, and max threat). Keeping them outside
         * the loop ensure values persist and can be updated each iteration.
         */
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int evenThreatSum = 0;
        int oddThreatSum = 0;
        int alert = 0;
        int highestThreat = 0;

        System.out.println("Enter number of Cycles to be scanned:");
        int cycles = sc.nextInt();

        System.out.println("Enter danger threshold:");
        int danger = sc.nextInt();

        /**
         * This loop represents the core simulation engine.
         * Each iteration is one scan where the system generates a new threat value.
         * randNum simulates a sensor reading, and the loop counter controls total
         * runtime.
         * Printing each cycle acts as a log output to observe system behavior over
         * time.
         */
        for (int i = 0; i < cycles; i++) {
            int randNum = rand.nextInt((100 - 1) + 1) + 1;
            System.out.println("Cycle " + i + " Threat: " + randNum);

            if (randNum > highestThreat) {
                highestThreat = randNum;
            }

            /**
             * This block checks for critical threat levels.
             * If the threat exceeds the danger threshold, an alert is triggered and the
             * severity is logged.
             * The alert counter tracks how many critical events have occurred.
             * If the system reaches 3 critical alerts, the simulation is forcibly
             * terminated (failsafe shutdown).
             */
            if (randNum >= danger) {
                System.out.println("ALERT: Threat exceeds threshold by " + (randNum - danger));
                alert++;
                if (alert >= 3) {
                    System.out.println("SCAN TERMINATED: Alert limit reached.");
                    break;
                }
            }

            /**
             * This block filters out low-value threat readings considered non-critical
             * noise.
             * Values below 10 are excluded from further processing to prevent skewing
             * system analysis.
             * The continue statement skips the remaining logic for this cycle.
             */
            if (randNum < 10) {
                continue;

            }

            /**
             * This block checks for predefined critical signatures.
             * These specific values act as known system flags that indicate high-risk or
             * notable events.
             * When detected, the system logs a critical signature alert for monitoring
             * purposes.
             */
            if (randNum == 13 || randNum == 66 || randNum == 99) {
                System.out.println("Critical signature detected");
            }
            /**
             * This block classifies each threat value as either even or odd.
             * It maintains running totals for both categories, effectively aggregating the
             * data stream.
             * This allows the system to analyze distribution patterns across all scanned
             * values.
             */
            if (randNum % 2 == 0) {
                evenThreatSum += randNum;
            } else {
                oddThreatSum += randNum;
            }

        }

        System.out.println("=== SCAN REPORT ===");
        System.out.println("Total Alerts: " + alert);
        System.out.println("Even Threat Sum: " + evenThreatSum);
        System.out.println("Odd Threat Sum: " + oddThreatSum);
        System.out.println("Highest Threat: " + highestThreat);

        sc.close();
    }
}
