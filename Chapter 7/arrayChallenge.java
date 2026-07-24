
/*
Tyler Spring
7/12/2026
===============================================================================
CHAPTER 7 PROJECT – ARRAY CHALLENGE SIMULATOR
===============================================================================

PROJECT GOAL

Create a console program that guides the user through a series of array
challenges. Each challenge focuses on one Chapter 7 concept and reinforces
array algorithms through repetition.

The purpose of this project is to build confidence with arrays, traversal,
searching, nested loops, and algorithm design.

Only use concepts introduced in Chapter 7.

===============================================================================
ALLOWED CONCEPTS
===============================================================================

✓ Arrays
✓ Two-dimensional arrays
✓ Scanner
✓ Random
✓ Methods
✓ for loops
✓ if / else
✓ Nested loops
✓ Math class
✓ Reference semantics

DO NOT USE

✗ ArrayList
✗ HashMap
✗ Collections
✗ Files
✗ GUI
✗ Recursion
✗ Streams
✗ Anything introduced after Chapter 7

===============================================================================
PROGRAM FLOW
===============================================================================

Welcome to the Array Challenge Simulator!

The program walks the user through one challenge at a time.

Each challenge introduces a different array algorithm.

After completing one challenge, continue to the next until all are complete.

At the end, print a completion message.

===============================================================================
CHALLENGE 1
DISPLAY THE ARRAY
===============================================================================

Display the current array.

Learning Goal:
- Array traversal
- Indexing

Recovery Question:
How do you visit every element exactly once?

===============================================================================
CHALLENGE 2
LAST INDEX OF
===============================================================================

Ask for a number.

Find its last occurrence.

Learning Goal:
- Backward traversal
- Linear searching

Recovery Question:
Why is searching backwards easier than searching forwards?

===============================================================================
CHALLENGE 3
RANGE
===============================================================================

Find

- Minimum
- Maximum
- Range

Learning Goal:
- Tracking multiple values during one traversal

Recovery Question:
Can minimum and maximum be found in one loop?
Why?

===============================================================================
CHALLENGE 4
COUNT VALUES IN RANGE
===============================================================================

Ask the user for

Minimum

Maximum

Count how many numbers fall inside the range.

Learning Goal:
- Conditional counting

Recovery Question:
Should the comparison be

>

>=

<

<=

Why?

===============================================================================
CHALLENGE 5
IS SORTED
===============================================================================

Determine whether the array is sorted.

Learning Goal:
- Comparing neighboring elements

Recovery Question:
Why does the loop stop before the final element?

===============================================================================
CHALLENGE 6
LONGEST SORTED SEQUENCE
===============================================================================

Find the length of the longest sorted sequence.

Learning Goal:
- State tracking
- Current vs maximum values

Recovery Questions:

What information should be remembered while walking through the array?

Why is a final comparison needed after the loop?

===============================================================================
CHALLENGE 7
COLLAPSE ARRAY
===============================================================================

Create a new collapsed array.

Display

Original

Collapsed

Learning Goal:
- Building a new array
- Pair processing

Recovery Question:
Why should this method return a new array instead of modifying the original?

===============================================================================
CHALLENGE 8
SUBSEQUENCE SEARCH
===============================================================================

Ask the user for a small search array.

Determine whether it appears inside the larger array.

Learning Goal:
- Nested loops
- Pattern searching

Recovery Question:
Draw both arrays.

How does the smaller array move across the larger one?

===============================================================================
CHALLENGE 9
MATRIX ADDITION
===============================================================================

Display two predefined matrices.

Display their sum.

Learning Goal:
- Two-dimensional arrays
- Nested loops

Recovery Question:
Why are two loops required?

What does each loop represent?

===============================================================================
CHALLENGE 10
MAGIC SQUARE
===============================================================================

Display two predefined matrices.

One should be a magic square.

One should not.

Determine whether each is magic.

Learning Goal:
- Row sums
- Column sums
- Diagonal traversal

Recovery Questions:

Why does

i == j

identify the main diagonal?

Why does

i + j == size - 1

identify the other diagonal?

===============================================================================
TEST CASES
===============================================================================

Test every challenge with

✓ Empty arrays (where appropriate)
✓ Single-element arrays
✓ Duplicate values
✓ Negative numbers
✓ Already sorted arrays
✓ Reverse sorted arrays
✓ Odd-length arrays
✓ Pattern at beginning
✓ Pattern in middle
✓ Pattern at end
✓ Pattern missing
✓ Valid magic square
✓ Invalid magic square

===============================================================================
STUDY MODE CONTRACT
===============================================================================

This project exists to strengthen understanding—not to finish quickly.

When asking for help:

• Do NOT provide complete implementations unless specifically requested.
• Ask guiding questions.
• Help debug one problem at a time.
• Explain why an algorithm works.
• Point out edge cases.
• Encourage reasoning before coding.

Goal:

Finish this project understanding WHY every algorithm works—not simply having
working code.
===============================================================================
*/
import java.util.Scanner;

public class arrayChallenge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] numbers = { 7, 4, 12, 9, 4, 15, 20, 8, 20 };

        // Challenge 1 - 6: 1D array traversal an searching.
        displayNumbers(numbers);
        lastIndexOf(numbers, sc);
        range(numbers);
        countInRange(numbers, sc);
        isSorted(numbers);
        longestSeq(numbers);
        System.out.print("Original Array: ");
        displayNumbers(numbers);

        // Challenge 7: Collapse array by combining adjacent pairs.
        // Returns a new array instead of modifying the original.
        int[] collapsed = collapseArr(numbers);
        System.out.print("\nCollapsed Array: ");
        displayNumbers(collapsed);

        // Challenge 8: Search for a smaller array pattern inside a larger array.
        System.out.println("Size of target array:");
        int size = getInt(sc, 1, 1000);
        int[] target = new int[size];
        for (int i = 0; i < target.length; i++) {
            System.out.println("Enter values for target array:");
            target[i] = getInt(sc, -1000, 1000);
        }
        boolean res = subArray(numbers, target);

        if (res) {
            System.out.println("Target array was found.");
        } else {
            System.out.println("Target array was not found.");
        }

        // Challenge 9: Create two matrices and add them element-by-element.
        System.out.println("How many rows for the matrix:");
        int rows = getInt(sc, 1, 1000);

        System.out.println("How many columns for the matrix:");
        int cols = getInt(sc, 1, 1000);

        int[][] a = new int[rows][cols];
        int[][] b = new int[rows][cols];

        fillMatrix(sc, a);
        fillMatrix(sc, b);

        int[][] result = addMatrix(a, b);
        displayNumbers(result);

        // Challenge 10: Test whether matrices meet magic square requirements.
        int[][] magic = {
                { 8, 1, 6 },
                { 3, 5, 7 },
                { 4, 9, 2 }
        };

        int[][] notMagic = {
                { 8, 1, 6 },
                { 3, 5, 8 },
                { 4, 9, 2 }
        };

        displayNumbers(magic);
        System.out.println(isMagic(magic));

        displayNumbers(notMagic);
        System.out.println(isMagic(notMagic));
    }

    // displayNumbers method.
    // Traverses the array once from index 0 to the end.
    // Each element is printed exactly once.
    private static void displayNumbers(int[] numbers) {
        if (numbers.length == 0) {
            System.out.println("{}");
            return;
        }
        System.out.print(numbers[0]);
        for (int i = 1; i < numbers.length; i++) {
            System.out.print(", " + numbers[i]);
        }

    }

    // lastIndexOf method.
    // Searches backwards because first match found is automatically the last
    // occurrence.
    private static void lastIndexOf(int[] numbers, Scanner sc) {
        System.out.println("\nEnter a target value: ");
        int target = getInt(sc, -1000, 1000); // The range of input is not the goal here.

        for (int i = numbers.length - 1; i >= 0; i--) {
            if (numbers[i] == target) {
                System.out.println("Target of " + target + " found at index " + i);
                return;

            }
        }
        System.out.println("Target value does not exist in array.");

    }

    // range method.
    // Tracks minimum and maximum values during a single traversal.
    // No need for separate searches because both values can be updated together.
    private static void range(int[] arr) {
        if (arr.length == 0) {
            System.out.println("Array is empty.");
            return;
        }
        int min = arr[0];
        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
            if (arr[i] < min)
                min = arr[i];
        }
        int range = max - min;
        System.out.println("Max: " + max);
        System.out.println("Min: " + min);
        System.out.println("Range: " + range);
    }

    // countInRange method.
    // Tracks minimum and maximum values during a single traversal.
    // No need for separate searches because both values can be updated together.
    private static void countInRange(int[] arr, Scanner sc) {
        if (arr.length == 0) {
            System.out.println("Array is empty.");
            return;
        }
        System.out.println("Enter min value for range: ");
        int min = getInt(sc, -1000, 1000);

        System.out.println("Enter max value for range: ");
        int max = getInt(sc, -1000, 1000);

        if (max < min) {
            System.out.println("Invalid range values. Max is less than min.");
            return;
        }

        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= min && arr[i] <= max) {
                count++;
            }
        }
        System.out.println("Count in range: " + count);
    }

    // isSorted method.
    // Compares neighboring elements.
    // If any element is larger than the next element, the array cannot be sorted.
    private static void isSorted(int[] arr) {
        if (arr.length == 0) {
            System.out.println("Array is empty.");
            return;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                System.out.println(false);
                return;
            }
        }
        System.out.println(true);
    }

    // longestSeq method
    // currentLength tracks the sequence currently being examined.
    // longestLength stores the largest sequence found so far.
    private static void longestSeq(int[] arr) {
        if (arr.length == 0) {
            System.out.println("Array is empty.");
            return;
        }

        int currentLength = 1;
        int longestLength = 1;

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] <= arr[i + 1]) {
                currentLength++;
            } else {
                if (currentLength > longestLength) {
                    longestLength = currentLength;
                }
                currentLength = 1;
            }
        }

        // Final comparison handles cases where longest sequence reaches the end of the
        // array.
        if (currentLength > longestLength) {
            longestLength = currentLength;
        }
        System.out.println("The longest sorted sequence is a length of: " + longestLength);
    }

    // collapseArr method.
    // Creates a new collapsed array because the original array should remain
    // unchanged.
    private static int[] collapseArr(int[] arr) {
        int numPairs = arr.length / 2;
        if (arr.length % 2 != 0) {
            numPairs++;
        }

        int[] collArr = new int[numPairs];

        // i tracks positions in the original array.
        // j tracks positions in the new collapsed array.

        int i = 0;
        int j = 0;
        while (i + 1 < arr.length) {
            collArr[j] = arr[i] + arr[i + 1];
            i += 2;
            j++;
        }

        if (arr.length % 2 != 0) {
            collArr[j] = arr[i];
        }
        return collArr;
    }

    // subArray method.
    // Outer loop moves the target window across the larger array.
    // Inner loop compares each target value against the current position.
    private static boolean subArray(int[] arr, int[] target) {

        if (arr.length == 0 || target.length > arr.length || target.length == 0) {
            return false;
        }

        for (int i = 0; i <= arr.length - target.length; i++) {
            boolean found = true;
            for (int j = 0; j < target.length; j++) {
                if (arr[i + j] != target[j]) {
                    found = false;
                    break;
                }
            }
            if (found) {
                return true;
            }
        }
        return false;
    }

    // method for addMatrix
    // Two-dimensional arrays require nested loops.
    // outer loop controls rows, inner loop controls columns.
    private static int[][] addMatrix(int[][] a, int[][] b) {

        int result[][] = new int[a.length][a[0].length];

        for (int row = 0; row < a.length; row++) {
            for (int col = 0; col < a[row].length; col++) {
                result[row][col] = a[row][col] + b[row][col];
            }
        }
        return result;
    }

    // method for magic square.
    // A magic square requires every row, column, and diagonal to have the same sum.
    private static boolean isMagic(int[][] matrix) {
        int magicSum = 0;
        int rowSum = 0;
        int colSum = 0;
        int diaSum = 0;

        // First row establishes the expected sum for all other checks.
        for (int col = 0; col < matrix[0].length; col++) {
            magicSum += matrix[0][col];
        }

        // Each row must equal magic sum.
        for (int row = 1; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                rowSum += matrix[row][col];
            }

            if (rowSum != magicSum) {
                return false;
            }

            rowSum = 0;
        }

        // Each column is checked by keeping the column fixed.
        // while moving through every row.
        for (int col = 0; col < matrix[0].length; col++) {
            for (int row = 0; row < matrix.length; row++) {
                colSum += matrix[row][col];
            }

            if (colSum != magicSum) {
                return false;
            }
            colSum = 0;
        }

        // Main diagonal occurs when row and column indexes are equal:
        // matrix[0][0], matrix[1][1], matrix[2][2].
        for (int i = 0; i < matrix.length; i++) {
            diaSum += matrix[i][i];
        }

        if (diaSum != magicSum) {
            return false;
        }
        diaSum = 0;

        // Opposite diagonal moves top-right to bottom-left.
        // Column index decreases as row index increases.
        for (int i = 0; i < matrix.length; i++) {
            diaSum += matrix[i][matrix.length - 1 - i];
        }

        if (diaSum != magicSum) {
            return false;
        }

        return true;
    }

    // Input validation method.
    // Continuously asks for input until a valid integer
    // within the allowed range is entered.
    private static int getInt(Scanner sc, int min, int max) {

        while (true) {
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Enter an integer.");
                sc.nextLine();
                continue;
            }

            int input = sc.nextInt();

            if (input < min || input > max) {
                System.out.println("Out of range. Enter a value between " + min + " and " + max);
                sc.nextLine();
                continue;
            }
            sc.nextLine();
            return input;
        }
    }

    // method for filling matrices.
    private static void fillMatrix(Scanner sc, int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.println("Enter a value for row " + i + " column " + j);
                a[i][j] = getInt(sc, -1000, 1000);
            }
        }
    }

    // over loaded displayNumbers method for matrices.
    // displayNumbers method.
    private static void displayNumbers(int[][] matrix) {
        if (matrix.length == 0) {
            System.out.println("{}");
            return;
        }
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }
}
