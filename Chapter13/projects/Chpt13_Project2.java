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

    // Helper for generating array of random integers.
    public static int[] genRandomArr(int size, int maxValue) {
        int[] arr = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(maxValue);
        }
        return arr;
    }

    // Helper for timing each sorting method.
    public static void timeSort(int[] array, Consumer<int[]> sortMethod, String sortName) {
        int[] arrCopy = Arrays.copyOf(array, array.length); // preserving original.
        long start = System.nanoTime();
        sortMethod.accept(arrCopy);
        long end = System.nanoTime();
        System.out.println(sortName + " took " + (end - start) + " ns.");
    }

    // Selection Sort method.
    public static void selectionSort(int[] arr) {
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

    public static void insertionSort(int[] arr) {
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

    //Public Method for quickSort
    public static void quickSort(int[] arr, int low, int high) {

    }

    //Private helper for quickSort
    private static int partition(int[] arr, int low, int high) {
        //implementation.
    }

}
