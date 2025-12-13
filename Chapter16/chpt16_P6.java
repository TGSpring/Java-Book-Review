
/**
 * Tyler Spring
 * 12/3/2025
 * Chapter 16 problem 6
 * Write a method called hasTwoConsecutive that returns whether or not a list of integers has two
 * adjacent numbers that are consecutive integers (true if such a pair exists and false otherwise).
 * For example, if a variable list stores the values [1, 18, 2, 7, 8, 39, 18, 40], then call
 * list.hasTwoConsecutive() should return true because the list contains the adjacent numbers (7, 8),
 * which are a pair of consecutive numbers.
 */
public class chpt16_P6 {

    public static void main(String[] args) {
        linkedIntList list = new linkedIntList();

        list.add(1);
        list.add(18);
        list.add(2);
        list.add(7);
        list.add(8);      // consecutive pair (7, 8)
        list.add(39);
        list.add(18);
        list.add(40);

        System.out.println("List: " + list);
        System.out.println("Has consecutive? " + list.hasTwoConsecutive());
    }
}
