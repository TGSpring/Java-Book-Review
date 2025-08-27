
/*
 * Tyler Spring
 * 8/17/2025
 * Chapter 12 Question 16
 * The Sierpinski carpet is a fractal that is defined as follows: The construction of the Sierpinski carpet begins with a square.
 * The square is cut into nine congruent subsquares in a 3-by-3 grid, with the central subsquare removed. The same process is then applied recursively to 
 * the eight other subsquares. Write a program to draw the carpet on a DrawingPanel recursively.
 */
import java.awt.Graphics;

public class Chpt12_Q16 {
    public static void main(String[] args) {
        int size = 600;
        DrawingPanel panel = new DrawingPanel(size, size);
        Graphics g = panel.getGraphics();

        // Starting recursive drawing.
        drawCarpet(g, 0, 0, size);
    }

    public static void drawCarpet(Graphics g, int x, int y, int size) {
        // Base case: stop when square is too small to subdivide.
        if (size < 3) {
            return;
        }

        int subSize = size / 3;

        // Draw the center hole, white square.
        g.clearRect(x + subSize, y + subSize, subSize, subSize);

        /*
         * Recursively draw the 8 surrounding squares.
         * Loop over all 9 subsquares, but skip the center where dx and dy are both
         * equal to 1.
         * For each of the outer squares, you recursively call drawCarpet to repeat the
         * process at a smaller scale.
         * 
         * For the equation:
         * 
         * Subdividing: Each recursive call divides the current square into 9 smaller
         * squares, and recurses on 8 of them.
         * 
         * Fractal Structure: This process repeats for each subsquare, creating a
         * self-similar, infinitely detailed pattern.
         * 
         * Position Calculation: The new top-left corner for each subsquare is
         * calculated as x + dx * subSize and y + dy * subSize.
         */
        for (int dx = 0; dx < 3; dx++) {
            for (int dy = 0; dy < 3; dy++) {
                if (dx == 1 && dy == 1)
                    continue;
                drawCarpet(g, x + dx * subSize, y + dy * subSize, subSize);
            }
        }
    }
}

/*
 * complexities
 * 
 * Time Complexity: At each level we make 8 recursive calls for 8 subsquares.
 * The recursion depth is n. The total number of calls is O(8^n).
 * 
 * Space Complexity: The deepest the call stack gets is n. So it is just O(n).
 */