
/**
 * Tyler Spring
 * 11/30/2025
 * Chapter 16 problem 2
 * Write a method called min that returns the minimum value in a list of
 * integers. If the  list is empty it should throw a NoSuchElementException.
 */
public class chpt16_P2 {

    public static void main(String[] args) {
        linkedIntList test2 = new linkedIntList();

        test2.add(42);
        test2.add(7);
        test2.add(13);

        System.out.println("List: " + test2);
        System.out.println("Min: " + test2.min() + "\n");
    }
}
