
/**
 * Tyler Spring
 * 5/10/2026
 * Create a java program that prints dynamic security scan grid.
 * print a 10x10 grid.
 * borders are #
 * interior is .
 * some cells become security nodes marked with X
 * each row must display a row number.
 * bottom must display column number.
 * the center row should contain the text: SYSTEM ACTIVE.
 * the output must stay aligned.
 * NO arrays yet.
 * NO advanced libraries
 * USE ONLY:
 *  loops
 *  nested loops
 *  conditionals
 *  print/println
 *  variables
 *  concatenation
 */
public class TerminalSecurity {

    public static void main(String[] args) {
        int size = 10;

        for (int i = 0; i < size; i++) {

            // center row.
            if (i == size / 2) {
                System.out.print(i + " ");

                for (int j = 0; j < size; j++) {
                    System.out.print("SYSTEM ACTIVE".charAt(j % 14));
                }

                System.out.println();
                continue;
            }

            // Normal row.
            System.out.print(i + " ");

            for (int j = 0; j < size; j++) {

                // border.
                if (i == 0 || i == size - 1 || j == 0 || j == size - 1) {
                    System.out.print("#");
                } // Security node 
                else if (i == j || (i == 2 && j == 7) || (i == 7 && j == 2)) {
                    System.out.print("X");
                } // Default interior.
                else {
                    System.out.print(".");
                }
            }

            System.out.println();
        }

        // Column numbers.
        System.out.print(" ");

        for (int j = 0; j < size; j++) {
            System.out.print(j);
        }

        System.out.println();

    }
}
