
/*
 * Tyler Spring
 * 10/30/25
 * Chapter 15 Problem 3
 * Write a method called replaceAll that accepts two integer values as parameters and replaces all occurrences of the first value in the list with the second value.
 * For example, if a variable called list stores [11, -7, 3, 42, 3, 0, 14, 3], the call of list.replaceAll(3, 999); should change the list to store [11, -7, 999, 0, 14, 999].
 */
public class ArrayIntListP3 {

    public static void main(String[] args) {
        ArrayIntList list = new ArrayIntList();
        list.add(3);
        list.add(7);
        list.add(3);

        if (!list.isEmpty()) {
            list.replaceAll(3, 999);
        }
        System.out.println(list);
    }
}
