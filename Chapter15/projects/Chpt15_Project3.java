/*
 * Tyler Spring
 * 11/14/2025
 * Chapter 15 Project 3
 * The actual ArrayList class in the java.util package has a method called subList that returns a view 
 * of a subportion of a list through a given range of indexes. It can be useful to think of part of a list as if 
 * it were its own list, complete with its own set of indexes and values. The sublist is "backed" by the 
 * original list, meaning that it is not a copy; if any change is made to the sublist, the original 
 * list is also affected.
 * 
 * In order to implement this method, you will need to write an inner class inside ArrayList<E> that extends 
 * ArrayList and implements the behavior of the sublist. Override the methods for getting and setting values 
 * at particular indexes, as well as the size method, so that they reflect the sublist's index range and size. 
 * Also modify the outer ArrayList<E> class so that it always refers to its own elements through the use of these
 * methods. The outer class should be given the following new method that returns an object of your new inner subList
 * class:
 * public ArrayList<E> subList(int fromIndex, int toIndex)
 */
public class Chpt15_Project3 {

    public static void main(String[] args) {
        MyArrayList2<String> tester = new MyArrayList2<String>();

        System.out.println("###### FIRST SET OF TESTS #######");
        // Testing add.
        tester.add("Hello");
        System.out.println(tester);

        // Testing get.
        System.out.println(tester);

        // Testing set.
        tester.set(0, "World");

        // Testing remove.
        System.out.println(tester);

        // Second set of tests.
        System.out.println("\n" + "###### SECOND SET OF TESTS #######");
        MyArrayList2<String> tester2 = new MyArrayList2<String>();
        tester2.add("A");
        tester2.add("B");
        tester2.add("C");

        tester2.remove("B");
        System.out.println(tester2);

        // Third set of tests.
        System.out.println("\n" + "###### THIRD SET OF TESTS #######");
        MyArrayList2<Integer> nums = new MyArrayList2<Integer>();

        for (int i = 0; i < 50; i++) {
            nums.add(i);
        }
        System.out.println(nums.size());

        // Fourth set of tests.
        System.out.println("\n" + "###### FOURTH SET OF TESTS #######");
        MyArrayList2<String> tester3 = new MyArrayList2<>();
        tester3.add("Dog");
        tester3.add("Cat");
        tester3.add("Bird");

        System.out.print(tester3.contains("Cat") + "\n"
                + tester3.contains("Fish" + "\n"
                        + tester3.contains("Bird")));

        // Fifth set of tests.
        System.out.println("\n" + "###### FIFTH SET OF TESTS #######");
        MyArrayList2<String> tester4 = new MyArrayList2<>();
        tester4.add("A");
        tester4.add("B");
        tester4.add("C");
        tester4.add("D");

        MyArrayList2<String> s1 = tester4.subList(1, 3);

        System.out.println(s1.get(0));

        tester4.add("X");

        try {
            s1.get(0);
        } catch (Exception e) {
            System.out.println("COMOD OK: " + e);
        }

        // Sixth set of tests.
        System.out.println("\n" + "###### SIXTH SET OF TESTS #######");
        MyArrayList2<String> tester5 = new MyArrayList2<>();
        tester5.add("A");
        tester5.add("B");
        tester5.add("C");
        tester5.add("D");
        tester5.add("E");

        MyArrayList2<String> s2 = tester5.subList(1, 4);

        System.out.println(s2);

    }
}
/*
 * =========================
 * MyArrayList2 Complexity
 * =========================
 *
 * Outer MyArrayList2<E>
 *
 * add(E element)         : O(1) amortized time, O(1) space
 *                          Occasionally triggers array resize (O(n)), but average is O(1)
 *
 * add(int index, E elem) : O(n) time, O(1) space
 *                          Shifts elements to make room at index
 *
 * get(int index)          : O(1) time, O(1) space
 *
 * set(int index, E elem)  : O(1) time, O(1) space
 *
 * remove(int index)       : O(n) time, O(1) space
 *                          Shifts elements down after removal
 *
 * remove(E element)       : O(n) time, O(1) space
 *                          Linear search for element, worst case at end or not present
 *
 * clear()                 : O(n) time, O(1) space
 *                          Sets all elements to null
 *
 * contains(E element) / indexOf(E element) : O(n) time, O(1) space
 *
 * subList(from, to)       : O(1) time, O(1) space
 *                          Creates a SubList view, does not copy elements
 *
 * -------------------------
 *
 * Inner SubList<E>
 *
 * get(int index)          : O(1) time, O(1) space
 *
 * set(int index, E elem)  : O(1) time, O(1) space
 *
 * add(int index, E elem)  : O(n) time, O(1) space
 *                          Calls parent.add, may shift elements in parent array
 *
 * remove(int index)       : O(n) time, O(1) space
 *                          Calls parent.remove, shifts elements in parent
 *
 * clear()                 : O(n) time, O(1) space
 *                          Removes each element individually via parent
 *
 * indexOf(E element) / contains(E element) : O(n) time, O(1) space
 *                          Linear search in sublist range
 *
 * size()                  : O(1) time, O(1) space
 *
 * toString()              : O(n) time, O(1) space
 *                          Iterates through sublist elements to build string
 *
 * Notes:
 * - Array resizing in outer list is amortized, not every add triggers O(n)
 * - SubList is a view backed by parent array; uses only O(1) extra memory
 * - Operations on sublist may affect parent and vice versa
 */
