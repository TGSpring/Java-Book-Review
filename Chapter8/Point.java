/*
 * Tyler Spring
 * 5/28/2025
 * Chapter 8 Point class
 * Majority of the end of chapter programming questions for this section involve 
 * adding methods to the Point and PointMain classes. Each problem/addition to the code will have a legend here in the header comments,
 * this will take you to where the addition/question is in the code base with the question as a comment.
 * 
 * Question 1: Line 70
 * 
 * 
 * 
 * To run use these commands:
 *  cd C:\Users\tyler\OneDrive\Desktop\JavaBookReview
 *  javac Chapter8\*.java
 *  java Chapter8.PointMain
 * 
 * I know that this is not efficient, but I did not think messing with the JSON configs for this problem set was a good use of time.
 */
package Chapter8;

public class Point {

    // A Point object represents a pair of (x, y) coordinates.
    private int x;
    private int y;

    // constructs a new point at the origin (0,0)
    public Point() {
        this(0, 0); // calls Point (int, int) constructor
    }

    // constructs a new point with the given (x, y) location
    public Point(int x, int y) {
        setLocation(x, y);
    }

    // returns the distance between this Point and (0, 0)
    public double distanceFromOrigin() {
        return Math.sqrt(x * x + y * y);
    }

    // returns the x-coordinate of this point
    public int getX() {
        return x;
    }

    // returns the y-coordinate of this point
    public int getY() {
        return y;
    }

    // sets this point's (x, y) location to the given values
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // returns a String representation of this point
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // shifts this point's location by the given amount
    public void translate(int dx, int dy) {
        setLocation(x + dx, y + dy);
    }

    /*
     * Question 1: Add the following accessor method to the Point class.
     * public int quadrant()
     * Returns which quadrant of the x/y plane the current point object falls in.
     * Quadrant 1 contains all points whose
     * x and y values are both positive. Quadrant 2 contains all points with
     * negative x but positive y. Quadrant 3
     * contains all points with negative x and y values. Quadrant 4 contains all
     * points with positive x but negative y.
     * If the point lies directly on the x or y axis, return 0.
     * 
     * Complexity for space and time is O(1) constant, only a few comparisons. Time
     * remains the same and only a fixed amount of space used.
     * 
     * I'm aware doing complexity analysis for these methods is probably overkill.
     * But my hope is one person see's this and thinks, "at least it's consistent".
     */

    public int quadrant() {

        if (x == 0 || y == 0) {
            return 0;
        } else if (x > 0 && y > 0) {
            return 1;
        } else if (x < 0 && y > 0) {
            return 2;
        } else if (x < 0 && y < 0) {
            return 3;
        } else {
            return 4;
        }
    }
}
