
/**
 * Tyler Spring
 * 12/12/2025
 * Chapter 16 problem 10
 * Write a method called stretch that takes an integer n as a parameter and that increases a list of
 * integers by a factor of n by replacing each integer in the list with n copies of that integer.
 * For example, if a variable called list stores [18, 7, 4, 24, 11] and we make the call of list.stretch(3);
 * the list should be changed to store [18, 18, 18, 7, 7, 7, 4, 4, 4, 24, 24, 24, 11, 11 , 11].
 * If n is zero or negative, the list should become empty.
 */
public class chpt16_P10 {

    public static void main(String[] args) {
        linkedIntList stretchList = new linkedIntList();
        linkedIntList baseCase = new linkedIntList();

        baseCase.add(0);
        baseCase.add(-1);

        System.out.println("Testing base pre: " + baseCase);

        baseCase.stretch(0);

        System.out.println("Testing base post: " + baseCase);

        stretchList.add(18);
        stretchList.add(7);
        stretchList.add(4);
        stretchList.add(24);
        stretchList.add(11);
        System.out.println("Pre Stretch: " + stretchList);

        stretchList.stretch(3);

        System.out.println("Post Stretch: " + stretchList);

    }
}
