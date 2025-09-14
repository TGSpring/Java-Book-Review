/*
 * Tyler Spring
 * 9/12/2025
 * Chapter 13 Project 2
 * Perform a "Sort Detective" challenge to run several sorting algorithms without knowing which is which. Try to figure out which sorting algorithm is which on the basis 
 * of the runtime and characteristics of each algorithm. Search the web for "sort detective" for more ideas on such a project.
 */

import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;

public class Chpt13_Project2 {
    public static void main(String[] args) {
        int[] ogArray = genRandomArr(200, 500);

        // Calls for timing of each sorting method.
        timeSort(ogArray, arr -> selectionSort(arr), "Selection Sort");
        timeSort(ogArray, arr -> insertionSort(arr), "Insertion Sort");
        timeSort(ogArray, arr -> quickSort(arr, 0, arr.length - 1), "Quick Sort");
    }

    // Generates an array of size random integers from 0 to maxValue - 1.
    public static int[] genRandomArr(int size, int maxValue) {
        int[] arr = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(maxValue);
        }
        return arr;
    }

    // Measures the time it takes to sort a copy of array using the given
    // sortMethod.
    public static void timeSort(int[] array, Consumer<int[]> sortMethod, String sortName) {
        // Creates a separate copy of the array so that each sort
        // starts with the same input. Consumer <int[]> is a Java feature that lets you
        // pass a method as a parameter.
        int[] arrCopy = Arrays.copyOf(array, array.length);
        long start = System.nanoTime();
        sortMethod.accept(arrCopy);
        long end = System.nanoTime();
        System.out.println(sortName + " took " + (end - start) + " ns.");
    }

    // Selection Sort: Repeatedly finds the smallest remaining element and places it
    // as the next position.
    public static void selectionSort(int[] arr) {
        /*
         * Outer loop i moves through each position in the array, treating is as the
         * position where the next
         * smallest element should go.
         * Inner loop j searches the unsorted portion of the array for the smallest
         * element.
         * Smallest tracks the index of the current smallest element.
         * Swap after inner loop, Puts the smallest element in the correct place.
         * Always performs the same number of comparisons.
         * Very predictable, but not good for large arrays.
         */
        for (int i = 0; i < arr.length - 1; i++) {
            int smallest = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[smallest]) {
                    smallest = j;
                }
            }

            // Swap after inner loop for smallest value.
            int temp = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = temp;
        }
    }

    // Builds a sorted portion of the array one element at a time by inserting the
    // next element in correct position.
    public static void insertionSort(int[] arr) {
        /*
         * Treats the first element as already sorted.
         * Key is the current element to insert into the sorted portion.
         * Inner while loop, moves larger elements one step right to make space for key.
         * arr[j + 1] = key;, Places the key in the correct position.
         */
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i]; // Current element to insert. This starts at index 1.
            int j = i - 1; // last index of sorted portion. This starts at index 0.

            // Shift elements greater than key to the right.
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            // insert key at correct position.
            arr[j + 1] = key;
        }
    }

    // Quick sort recursively partitions array around a pivot point and sorts the
    // partitions.
    public static void quickSort(int[] arr, int low, int high) {
        /*
         * Recursively sorts left and right subarrays.
         * Base case: low >= high, subarray of size 1, already sorted.
         * Usually much faster than selection or insertion.
         * Recursive calls divide array into smaller subarrays around the pivot.
         * Average performance is O(n log n) because the array is roughly halved each
         * time.
         */
        if (low < high) {
            int p = partition(arr, low, high);
            quickSort(arr, low, p - 1); // Left side of pivot.
            quickSort(arr, p + 1, high); // Right side of pivot.
        }
    }

    // Private helper for quickSort
    private static int partition(int[] arr, int low, int high) {
        /*
         * Chooses pivot as the last element.
         * Moves all smaller elements to the left, larger to the right.
         * Swaps pivot to its correct position.
         */
        int pivot = arr[high]; // choose the last element as the pivot.
        int i = low - 1; // boundary for smaller than pivot region.

        // i represents the last index of elements <= pivot.
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i = i + 1;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }

        }

        // After the loop, pivot is swapped to position i + 1, its final sorted index.

        int temp2 = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp2;

        return i + 1;

    }

}
/*
 * Complexities
 * 
 * Selection sort:
 * 
 * Time: Best and worse case are both O(n^2). Even if the array is sorted, every
 * element is always checked.
 * Space: Constant O(1), sorts in place uses only a few extra variables,
 * smallest
 * and temp.
 * 
 * Insertion Sort:
 * 
 * Time: Best case is linear O(n), if the array is already sorted, inner loop
 * doesn't run.
 * Worst case is O(n^2), if the array is sorted in reverse, every element must
 * shift all previous elements.
 * Space: Constant O(1), also in-place uses only key and j variables.
 * 
 * Quick Sort:
 * 
 * Time: Best case is O(n log n), pivot splits array roughly in half each time.
 * Worst case is O(n^2), pivot is always the smallest or largest element,
 * already sorted or in reverse.
 * Space: On average is O(log n), but for worst case it can be linear O(n),
 * due to recursive calls.
 * 
 */