/*
 * Tyler Spring
 * 5/14/2025
 * Chapter 7 Question 20
 * Write a method called isMagicSquare that accepts a two-dimensional array of integers as a 
 * parameter and returns true if it is a magic square. A square matrix is a magic square if all 
 * of its row, column, and diagonal sums are equal.
 * For example:
 * [[2, 7, 6], [9, 5, 1], [4, 3, 8]]
 * is a square matrix because all eight of the sums are exactly 15.
 */
public class Chpt7_10 {
    public static void main(String[] args) {
        int[][] a = {
                { 2, 7, 6 },
                { 9, 5, 1 },
                { 4, 3, 8 }
        };

        System.out.println(isMagicSquare(a));
    }

    public static boolean isMagicSquare(int[][] a) {
        int rows = a.length;
        int cols = a[0].length;

        // Checks if matrix is a square (same number of rows and columns).
        for (int i = 0; i < a.length; i++) {
            if (a[i].length != a.length) {
                return false;
            }

        }
        // Taking sum of first row.
        int targetSum = 0;
        for (int r = 0; r < cols; r++) {
            targetSum += a[0][r];
        }

        int diag1Sum = 0; // Sum of main diagonal (top-left to bottom-right).
        int diag2Sum = 0; // Sum of anti-diagonal (top-right to bottom-left).

        for (int i = 0; i < rows; i++) {
            int rowSum = 0;
            int colSum = 0;

            for (int j = 0; j < cols; j++) {
                // Row sum.
                rowSum += a[i][j];

                // Column sum.
                colSum += a[j][i];

                // Diagonal sum.
                if (i == j) {
                    diag1Sum += a[i][j];
                }
                if (i + j == rows - 1) {
                    diag2Sum += a[i][j];
                }
            }

            // Check if current row/column sum matches target.
            if (rowSum != targetSum || colSum != targetSum) {
                return false;
            }
        }

        // Check the diagonal sums
        if (diag1Sum != targetSum || diag2Sum != targetSum) {
            return false;
        }

        return true;
    }
}
/*
 * Time Complexity is O(n^2), where n is the number of rows or columns in the
 * matrix.
 * - We loop over each row and column once in a nested loop structure.
 * 
 * Space Complexity is linear O(1) we only use a fixed number of extra
 * variables, not counting input.
 */