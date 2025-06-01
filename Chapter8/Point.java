/*
 * Tyler Spring
 * 5/28/2025
 * Chapter 8 Point class
 * Majority of the end of chapter programming questions for this section involve 
 * adding methods to the Point and PointMain classes. Each problem/addition to the code will have a legend here in the header comments,
 * this will take you to where the addition/question is in the code base with the question as a comment.
 * 
 * Question 1: Line 70
 * Question 2: Line 103
 * Question 3: Line 131
 * Question 4: Line 150
 * Question 5: Line 169
 * Question 6: Line 206
 * 
 * ASIDE: I know I said I would do complexities every problem, but so far they are all constant and I am too lazy to type that 5 extra times. 
 * Let my laziness be your learning curve.
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

    /*
     * Question 2:
     * Add the following mutator method to the Point class:
     * public void flip()
     * Negates and swaps the x/y coordinates of the Point object.
     * For example: if an object pt initially represents point (5,-3), after a call
     * of
     * pt.flip(); the object should represent (3,-5). If the same object initially
     * represents
     * the point (4,17), after a call to pt.flip(); the object should represent
     * (-17, -4);
     */

    public void flip() {
        // Temp int to preform swap.
        int swap;

        System.out.println("\nPre swap: (" + x + ", " + y + ")");
        // Learned that instead of having to do * -1, I can just do -y. Small things
        // like this are why it is important to study.
        swap = x;
        x = -y;
        y = -swap;

        System.out.println("Post Swap: (" + x + ", " + y + ")");
    }

    /*
     * Question 3
     * Add the following accessor method:
     * public int manhattanDistance(Point other)
     * Returns the "Manhattan Distance" between the current point object and the
     * given other Point object.
     * The Manhattan distance refers to the distance between two places if one can
     * travel between them only by moving horizontally
     * or vertically, as though driving on the streets of Manhattan. In our case,
     * the Manhattan distance is the sum of the absolute values
     * of the differences in their coordinates, in other words, the difference in x
     * plus the difference in y between the points.
     */

    public int manhattanDistance(Point other) {
        return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
    }

    /*
     * Question 4:
     * Add the following accessor method to the point class:
     * public boolean isVertical (Point other)
     * Returns true if the given point lines up vertically with this Point, that is,
     * if their x-coordinates are the same.
     */

    public boolean isVertical(Point other) {
        if (this.x == other.x) {
            System.out.println("\nThey are vertically lined up.");
            return true;
        } else {
            System.out.println("\nThey are not vertically lined up.");
            return false;
        }
    }

    /*
     * Question 5:
     * Add the following accessor method to the Point class:
     * public double slope(Point other)
     * Returns the slope of the line drawn between this Point and the given other
     * point. Use the formula (y2 - y1)/ (x2 - x1)
     * to determine the slope between two points (x1,y1) and (x2,y2). Note that this
     * formula fails for points with identical
     * x-coordinates, so throw an illegalArugmentException in this case.
     */

    public double slope(Point other) {
        if (this.x == other.x) {
            throw new IllegalArgumentException("Can not compute slope, same x-coordinates.");
        }
        return (other.y - this.y) / (other.x - this.x);

    }

    /*
     * Question 6:
     * Add the following accessor method to the Point class:
     * public boolean isCollinear(point p1, point p2)
     * Returns whether this Point is collinear with the given two other points.
     * Points are collinear if a straight line
     * can be drawn that connects them. Two basic examples are three points that
     * have the same x or y coordinate. The more
     * general case can be determined by calculating the slope of teh line between
     * each pair of points and checking whether this
     * slope is the same for all pairs of points. Use the formula (y2 - y1)/ (x2 -
     * x1) to determine the slope between the two points (x1,y1) and (x2,y2).
     * Since Java's double type is imprecise, round all slope values to a reasonable
     * accuracy such as four digits past the decimal point before you compare them.
     * 
     */

    public boolean isCollinear(Point p1, Point p2) {
        // Reusing slope method to test if they have the same slope.
        double slope1 = slope(p1);
        double slope2 = slope(p2);
        // Comparing the 3rd slope is not entirely needed, but I wanted to get a better
        // idea of how this method is really comparing 3 instances each time it is
        // called, outside of slope3.
        double slope3 = p1.slope(p2);

        // Rounding before comparison.
        double roundedSlope1 = Math.round(slope1 * 10000.0) / 10000.0;
        double roundedSlope2 = Math.round(slope2 * 10000.0) / 10000.0;
        double roundedSlope3 = Math.round(slope3 * 10000.0) / 10000.0;

        // If 1 is equal to 2 and 3 then 2 has to be equal to 3.
        if (roundedSlope1 == roundedSlope2 && roundedSlope1 == roundedSlope3) {

            System.out.println("They are collinear  with a slope of " + roundedSlope1);
            return true;
        }
        return false;
    }
}
