// Demonstrates shape classes.
public class ShapesMain {
    public static void main(String[] args) {
        // Perfect time here. YOu can not create an instance of an abstract class or
        // interface directly. You must instantiate a concrete subclass.
        Octagon oct = new Octagon(8);
        System.out.println(oct.getArea() + " area.");
        System.out.println(oct.getPerimeter() + " perimeter.");

        Hexagon hex = new Hexagon(6);
        System.out.println(hex.getArea() + " area.");
        System.out.println(hex.getPerimeter() + " perimeter.");
    }
}
