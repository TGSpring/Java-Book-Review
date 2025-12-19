
/**
 * Tyler Spring
 * 12/19/2025
 * Chapter 16 problem 16
 * Write a method called removeEvens that removes the values in even-numbered indexes from a list,
 * returning a new list that contains those values in their original order. For examples, consider
 * a variable list1 that stores the values [8, 13, 17, 4, 9, 12, 98, 41, 7, 23, 0, 92] and imagine that
 * the following call is made: LinkedIntList list2 = list1.removeEvens();
 * After the call, list1 should store [13, 4, 12, 41, 23, 92] and list2 should store [8, 17, 9, 98, 7, 0].
 * You may not call any methods of the class other than the constructor to solve this problem. You
 * may not create any new nodes nor change the values stored in data fields to solve this problem.
 * You must solve it by rearranging the links of the list.
 */
public class chpt16_P16 {

    public static void main(String[] args) {
        linkedIntList list1 = new linkedIntList();
        list1.add(8);
        list1.add(13);
        list1.add(17);
        list1.add(4);
        list1.add(9);
        list1.add(12);
        list1.add(98);
        list1.add(41);
        list1.add(7);
        list1.add(23);
        list1.add(0);
        list1.add(92);

        linkedIntList list2 = list1.removeEvens();

        System.out.println("List 2:" + list2);
        System.out.println("List 1 after: " + list1);
    }
}
