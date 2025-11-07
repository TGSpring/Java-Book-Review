
/*
 * Tyler Spring
 * 10/30/25
 * Chapter 15 problem 10
 * Write a method called longestSortedSequence that returns the length of the longest sorted sequence within a list 
 * of integers. For example: if a variable called list store [1, 3, 5, 2, 9, 7, -3, 0, 42, 308, 17], then the call
 * of list.longestSortedSequence() would return 4 because it is the length of the longest sorted sequence 
 * within this list. If the list is empty, you method should return 0. Notice that for nonempty
 * list the method will always return a value of at least 1 because any individual element constitutes 
 * a sorted sequence.
 */
public class ArrayIntListP10 {

    public static void main(String[] args) {
        ArrayIntList list = new ArrayIntList();
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(2);
        list.add(9);
        list.add(7);
        list.add(-3);
        list.add(0);
        list.add(42);
        list.add(308);
        list.add(17);

        System.out.println(list.longestSortedSequence()); // should print 4
    }

}
