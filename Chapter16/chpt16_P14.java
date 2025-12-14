
/**
 * Tyler Spring
 * 12/14/2025
 * Chapter 16 problem 14
 * Write a method called removeAll that removes all occurrences of a particular value. For example,
 * if a variable list the values [3, 9, 4, 2, 3, 8, 17, 4, 3, 18], the call of list.removeAll(3);
 * would change the list to store [9, 4, 2, 8, 17, 4, 18].
 */
public class chpt16_P14 {

    public static void main(String[] args) {
        linkedIntList list = new linkedIntList();

        list.add(3);
        list.add(9);
        list.add(4);
        list.add(2);
        list.add(3);
        list.add(8);
        list.add(17);
        list.add(4);
        list.add(3);
        list.add(18);

        System.out.println("List before removeAll: " + list);

        list.removeAll(3);

        System.out.println("List after removeAll: " + list);

    }
}
