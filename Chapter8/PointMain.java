/*
 * Tyler Spring
 * 5/28/2025
 * Chapter 8 Point class
 * Majority of the end of chapter programming questions for this section involve 
 * adding methods to the Point and PointMain classes. Each problem/addition to the code will have a legend here in the header comments,
 * this will take you to where the addition/question is in the code base with the question as a comment.
 * 
 * To run use these commands:
 *  cd C:\Users\tyler\OneDrive\Desktop\JavaBookReview
 *  javac Chapter8\*.java
 *  java Chapter8.PointMain
 * 
 * I know that this is not efficient, but I did not think messing with the JSON configs for this problem set was a good use of time.
 */
package Chapter8;

//A program tha deals with points.
public class PointMain {
    public static void main(String[] args) {
        // create two Point objects.
        Point p1 = new Point(7, 2);
        Point p2 = new Point(4, 3);
        Point p3 = new Point(10, 5);

        // print each point and its distance from the origin
        System.out.println("p1 is " + p1);
        System.out.println("Distance from origin = " + p1.distanceFromOrigin());

        System.out.println("p2 is " + p2);
        System.out.println("Distance from origin = " + p2.distanceFromOrigin());

        // Translate each point to a new location
        p1.translate(11, 6);
        p2.translate(1, 7);

        // Print the points again
        System.out.println("p1 is " + p1);
        System.out.println("p2 is " + p2);

        // Question 1 Point class line 70
        System.out.println("\nQuadrant " + p1.quadrant());

        // Question 2 Point class line 103
        p1.flip();

        // Question 3 Point class line 131
        System.out.println("\nManhattan Distance " + p1.manhattanDistance(p2));

        // Question 4 Point class line 150
        p1.isVertical(p2);

        // Question 5 Point class line 169
        System.out.println("\nThe slope is: " + p1.slope(p2) + "\n");

        // This is a test for the exception thrower, it works.
        // System.out.println("\nThe slope is: " + p1.slope(p1));

        // Question 6 Point class line 206
        System.out.println(p1.isCollinear(p2, p3));
    }
}
