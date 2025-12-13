
/**
 * Tyler Spring
 * 12/12/2025
 * Chapter 16 problem 13
 * Write a method called transferFrom that accepts a second linkedList as a parameter and that moves values
 * from the second list to this list. You are to attach the second list's elements to the end of this list.
 * You are also to empty the second list. For example: suppose two lists called list1 and list2
 * store [8, 17, 2, 4] and [1, 2, 3], respectively. The call of list1.transferFrom(list2); should change
 * list1 to [8, 17, 2, 4, 1, 2, 3] and list2 to an empty list, []. The order of the arguments matters;
 * list2.transferFrom(list1) should change list1 to and empty list and list 2 to [1, 2, 3, 8, 17, 2, 4].
 * Either of the two lists could be empty, but you can assume neither list is null. YOu are not to create
 * any new nodes. Your method should simply change links of the lists to join them together.
 */
public class chpt16_P13 {

    public static void main(String[] args) {

        linkedIntList list1 = new linkedIntList();
        linkedIntList list2 = new linkedIntList();

        list1.add(8);
        list1.add(17);
        list1.add(2);
        list1.add(4);

        list2.add(1);
        list2.add(2);
        list2.add(3);

        list1.transferFrom(list2);
        // list2.transferFrom(list1);

        System.out.println("List1 to list2:" + list1);
        // System.out.println("List2 to list1:" + list2);

        // I know the commented out lines cause a runtime bug, but I did not know that for the first 2 hours. 
        // Leaving it in anyways.
    }
}
