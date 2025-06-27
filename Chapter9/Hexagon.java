/*
 * Tyler Spring
 * 6/27/2025
 * Chapter 9 Question 16
 * Write a class named Hexagon whose objects represent regular hexagons. Your 
 * class should implement the Shape interface.
 */
public class Hexagon implements Shape {
    private double sides;

    // Constructor
    public Hexagon(double sides) {
        this.sides = sides;
    }

    public double getArea() {
        return (3 * Math.sqrt(3) / 2) * Math.pow(sides, 2);
    }

    public double getPerimeter() {
        return 6 * sides;
    }
}
