
/**
 * Tyler Spring
 * 12/28/2025
 * Chapter 16 Problem 19
 * Write a method called rotate that moves the value at the front of a list to the end of the list.
 * For example, if a variable called list stores the values [8, 23, 19, 7, 45, 98, 102, 4], then the call
 * of list.rotate(); should move the value 8 from the front of the list to teh back of the list,
 * changing the list to store [23, 19, 7, 45, 98, 102, 8]. If the method is called for a list of 0
 * elements or 1 element it should have no effect on the list. You may neither construct any new nodes
 * to solve this problem nor change any of the data values stored in the nodes. You must solve the
 * problem by rearranging the links of the list.
 */
public class chpt16_P19 {

    public static void main(String[] args) {
        linkedIntList list = new linkedIntList();

        list.add(23);
        list.add(19);
        list.add(7);
        list.add(45);
        list.add(98);
        list.add(102);
        list.add(8);

        System.out.println("List before rotation: " + list);

        list.rotate();

        System.out.println("List after rotation: " + list);
    }
}
