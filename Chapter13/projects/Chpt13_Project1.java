
/*
 * Tyler Spring
 * 9/10/2025
 * Chapter 13 Project 1
 * 
 * Write a program that reads a series of input lines and sorts them into
 * alphabetical order, ignoring the case
 * of words. The program should use the merge sort algorithm so that it
 * efficiently sorts even a large file.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Chpt13_Project1 {
    public static void main(String[] args) throws FileNotFoundException {

        // ----------------------------
        // READING INPUT FROM FILE
        // ----------------------------
        // NOTES:
        // This program will read input from a file.
        // An ArrayList is used because its size is dynamic.
        // If we used a regular array, we would need to know
        // the number of lines in advance.

        Scanner reader = new Scanner(new File("lines.txt"));
        ArrayList<String> lines = new ArrayList<>();

        while (reader.hasNextLine()) {
            lines.add(reader.nextLine());
        }
        reader.close();

        // ----------------------------
        // CONVERT TO ARRAY FOR SORTING
        // ----------------------------
        // QUICK CHECK:
        // lines.toArray(new String[0]) converts the ArrayList to a String[].
        // Calling lines.toArray() with no arguments returns Object[],
        // which is not type safe for Strings.
        // Passing new String[0] gives Java a template for the type.

        String[] sortArr = lines.toArray(new String[0]);

        // ----------------------------
        // TEST OUTPUT
        // ----------------------------
        // NOTES:
        // One line per output for readability.

        for (String s : sortArr) {
            System.out.println(s);
        }
    }

    // ----------------------------
    // MERGE SORT WRAPPER
    // ----------------------------
    // NOTES:
    // Merge sort recursively divides the array until each subarray
    // has only one element, then merges them back in order.
    public static void mergeSort(String[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    // ----------------------------
    // RECURSIVE MERGE SORT
    // ----------------------------
    private static void mergeSort(String[] array, int low, int high) {
        // Base Case.
        if (low >= high)
            return;

        // Find the midpoint.
        int mid = (low + high) / 2;

        // Recursively sort left half.
        mergeSort(array, low, mid);

        // Recursively sort right half.
        mergeSort(array, mid + 1, high);

        // Merge the two halves.
        merge(array, low, mid, high);
    }

    // ----------------------------
    // MERGE HELPER (to be implemented)
    // ----------------------------
    // NOTES:
    // - A temporary array is needed to hold elements while merging.
    // Think of it like a placeholder, similar to swapping variables.
    // - Use compareToIgnoreCase here to pick the next element in order.
    // - Track indexes i for the left half and j for the right half.
    // When i > mid, left half is exhausted; when j > high, right half is exhausted.
    // Copy remaining elements from the non-empty half.
}
