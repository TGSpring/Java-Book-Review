
/**
 * Tyler Spring
 * 12/28/2025
 * Chapter 16 Problem 21
 * Write a method called reverse that reverses the order of the elements in the list, (This is very
 * challenging!) For example, if the variable  list initially stores the values [1, 8, 19, 4, 17], the call
 * of list.reverse(); should change the list to store [17, 4, 19, 8, 1].
 */
public class chpt16_21 {

    public static void main(String[] args) {

        linkedIntList rev = new linkedIntList();

        rev.add(1);
        rev.add(8);
        rev.add(19);
        rev.add(4);
        rev.add(17);

        System.out.println("Pre reverse: " + rev);

        rev.reverse();

        System.out.println("Post reverse: " + rev);
    }
}
