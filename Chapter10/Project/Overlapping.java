
/*
 * Tyler Spring
 * 7/2/2025
 * Chapter 10 Project 4 Overlapping.
 * Write a class that models a list of possibly overlapping rectangular two-dimensional window regions, like the windows for the programs open on your computer.
 * The order of the rectangles in the list implies the order in which they would display on the screen (sometimes called z-order), from 0 on the bottom to size() - 1 on top.
 * Each rectangle stores its (x,y) position, width, and height. Your rectangle list class should have a method that takes a Point as a parameter, treats it as though the user
 * clicked that Point on the screen, and moves the topmost rectangle touching that Point to the front of the list.
 */

import java.util.ArrayList;
import java.util.Arrays;

public class Overlapping {
    public static void main(String[] args) {
        ArrayList<Rectangle> rects = new ArrayList<Rectangle>();

        Rectangle rect1 = new Rectangle(1, 2, 5, 3);
        Rectangle rect2 = new Rectangle(5, 7, 9, 6);
        Rectangle rect3 = new Rectangle(50, 25, 30, 70);

        // Yes, I am still refusing to type out 3 .add statements.
        rects.addAll(Arrays.asList(rect1, rect2, rect3));

        // Creating Point object.
        Point ps = new Point(6, 8);

        System.out.println("Before: " + rects + "\n");
        // Finding topMost rectangle, returns z-order.
        topMost(rects, ps);
        System.out.println("\nAfter: " + rects);

    }

    public static void topMost(ArrayList<Rectangle> rects, Point ps) {
        /*
         * To find the topmost rectangle we need to start at the last index, and iterate
         * backwards.
         * While doing this, comparing to the given Point "ps". When found the loop
         * breaks.
         */

        for (int i = rects.size() - 1; i >= 0; i--) {
            if (rects.get(i).contains(ps)) {

                // When topmost is found, it is moved to the end of this list to maintain
                // z-order.
                Rectangle topRect = rects.remove(i);
                rects.add(topRect);
                System.out.println("Triggered rectangle: " + topRect);
                break;

            }
        }
    }
}

// Class for rectangles.
class Rectangle {
    private int x, y, height, width;

    public Rectangle(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    /*
     * Used getters here just to keep with previous logic of encapsulation and
     * making fields private.
     * 
     * Returns true if the given Point p is inside this rectangle.
     * Checks if p's x coord is between the rectangle's left (x) and right (x +
     * width) edges,
     * and if p's y coord is between the rectangle's top (y) and bottom (y + height)
     * edges.
     * If both conditions are true, the point is inside (or on the edge of) the
     * rectangle.
     */
    public boolean contains(Point p) {
        return p.getX() >= x && p.getX() <= x + width && p.getY() >= y && p.getY() <= y + height;
    }

    // Printing TopMost.
    @Override
    public String toString() {
        return "Rectangle[x=" + x + ", y=" + y + ", w=" + width + ", h=" + height + "]";
    }

}

// Point class, this could have been inside of Rectangle, but for a clearer and
// more module project, I went this path.
class Point {
    private int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}