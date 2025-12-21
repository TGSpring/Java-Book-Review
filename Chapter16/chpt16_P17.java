
/**
 * Tyler Spring
 * 12/21/2025
 * Chapter 16 Problem 17
 * Write a method called removeRange that accepts a starting and ending index as parameters and removes the elements
 * at those indexes (inclusive) from the list. For example, if a variable list stores the values
 * [8, 13, 17, 4, 9, 12, 98, 41, 7, 23, 0, 92], the call of listRange.removeRange(3, 8); should remove the values
 * between index 3 and index 8 (the values 4 and 7), leaving the list of [8, 13, 23, 0, 92]. The method should throw an
 * illegalArgumentException if either of the positions is negative. Otherwise you may assume that the positions represent
 * a legal range of the list (0 <= start <= end < size).
 */
public class chpt16_P17 {

    public static void main(String[] args) {
        linkedIntList listRange = new linkedIntList();

        listRange.add(8);
        listRange.add(13);
        listRange.add(17);
        listRange.add(4);
        listRange.add(9);
        listRange.add(12);
        listRange.add(98);
        listRange.add(41);
        listRange.add(7);
        listRange.add(23);
        listRange.add(0);
        listRange.add(92);

        System.out.println("Before remove: " + listRange);

        listRange.removeRange(3, 8);

        System.out.println("After remove: " + listRange);

    }
}
