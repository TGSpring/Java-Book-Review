
/**
 * Tyler Spring
 * 11/30/25
 * Chapter 16 problem 1
 * Write a method called set that accepts an index and a value and sets the list's elements at that index to have the given values.
 * You may assume that the index is between 0 inclusive and the size of the list exclusive.
 */
public class chpt16_P1 {

    public static void main(String[] args) {
        linkedIntList test1 = new linkedIntList();
        test1.add(10);
        test1.add(20);
        test1.add(30);

        System.out.println("Before set: " + test1);

        test1.set(1, 999);

        System.out.println("After set: " + test1 + "\n");
    }
}
