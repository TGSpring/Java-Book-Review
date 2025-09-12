
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

        mergeSort(sortArr);

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
    // MERGE HELPER
    // ----------------------------
    // NOTES:
    // - A temporary array is needed to hold elements while merging.
    // Think of it like a placeholder, similar to swapping variables.
    // - Use compareToIgnoreCase here to pick the next element in order.
    // - Track indexes i for the left half and j for the right half.
    // When i > mid, left half is exhausted; when j > high, right half is exhausted.
    // Copy remaining elements from the non-empty half.

    private static void merge(String[] array, int low, int mid, int high) {
        String[] temp = new String[high - low + 1];

        int i = low; // pointer for left half.
        int j = mid + 1; // pointer for right half.
        int k = 0; // pointer for temp.

        while (i <= mid && j <= high) {
            if (array[i].compareToIgnoreCase(array[j]) <= 0) {
                temp[k] = array[i];
                i++;
            } else {
                temp[k] = array[j];
                j++;
            }
            k++;
        }

        // Left half copying leftovers.
        while (i <= mid) {
            temp[k] = array[i];
            i++;
            k++;
        }

        // Right half copying leftovers.
        while (j <= high) {
            temp[k] = array[j];
            j++;
            k++;
        }

        // Copying back into the original array.
        for (int m = 0; m < temp.length; m++) {
            array[low + m] = temp[m];
        }
    }
}

/*
 * Time Complexity:
 * - Each merge sort call splits the array in half.
 * - Number of levels in the recursion tree is equal to log2(n), for an array of
 * size n.
 * - Merging.
 * - Each level of recursion merges all n elements at once.
 * - So per level, work = O(n).
 * - Total work.
 * - Levels * work per level = O(n * log n).
 * - Time complexity is O(n log n).
 * 
 * Space Complexity:
 * - Each merge call creates a temp array of size (high - low + 1).
 * - Because the recursion is not in-place, the total extra memory used at any
 * one time is roughly
 * proportional to n, the size of the array being merged at the top level.
 * - Recursion adds log n levels of function calls, each with a few variables.
 * This is small compared to n -> still
 * O(n) total extra space.
 * - Space complexity is O(n).
 */