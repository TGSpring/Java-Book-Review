/*
 * Tyler Spring
 * 6/27/2025
 * Chapter 9 Question 15
 * Write a class named Octagon whose objects represent regular octagons. Your class should implement the Shape interface defined in this chapter, including methods fro its area
 * and perimeter. An Octagon object is defined by its side length.
 */

//Shape being an interface means Octagon Implements Shape, not extends.

public class Octagon implements Shape {
    private double sides;

    // Constructs Octagon
    public Octagon(double sides) {
        this.sides = sides;
    }

    public double getArea() {
        return 2 * (1 + Math.sqrt(2)) * Math.pow(sides, 2);
    }

    public double getPerimeter() {
        return 8 * sides;
    }
}