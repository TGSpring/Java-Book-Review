/*
 * Tyler Spring
 * 5/11/2025
 * Chapter 7 Question 19
 * Write a method called matrixAdd that accepts a pair of two-dimensional arrays 
 * of integers as parameters, treats the arrays as two-dimensional matrixes, and returns 
 * their sum. The sum of two matrixes A and B is a matrix C, where for every row i and column j, Cij = Aij + Bij.
 * You may assume that the arrays passed as parameters have the same dimensions.
 */
public class Chpt7_9 {
    public static void main(String[] args) {
        int[][] a = {
                { 1, 2, 3 },
                { 4, 5, 6 }
        };

        int[][] b = {
                { 6, 5, 4 },
                { 3, 2, 1 }
        };
        // Was not required. Did this to work with matrices more.
        printMatrix(matrixAdd(a, b));
    }

    public static int[][] matrixAdd(int[][] a, int[][] b) {
        // Create the result matrix with the same dimensions as a or b.
        int rows = a.length;
        int cols = a[0].length;

        // Array to be returned, created with size of passed arrays.
        int[][] result = new int[rows][cols];

        // Using nested loops to visit every element and sum corresponding values to
        // store in result.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Adds corresponding column values, j, in current row value, i, sets the
                // summation to the result arrays also corresponding column value, j and current
                // row value, i.
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
    }

    // This was not required but I felt like this problem could have been much more
    // of a learning experience for 2D arrays and matrices.
    // This prints the matrix into a readable format.
    public static void printMatrix(int[][] matrix) {
        // For each row of the matrix.
        for (int[] row : matrix) {
            // Iterate each value in each row, of the matrix.
            for (int value : row) {
                // Prints each value per row.
                System.out.print(value + " ");
            }
            // When each value, per row, is printed, print a new line to format for next
            // row.
            System.out.println();
        }
    }

}
/*
 * Time complexity is Quadratic O(n * m), in terms of the total number of
 * elements in the matrices, where n is the number of rows and m is
 * the number of columns of the matrices.
 * We visit each element once to preform the summation.
 * 
 * Space complexity is Quadratic O(n * m), we allocate a matrix of the same size
 * as the input matrices.
 */