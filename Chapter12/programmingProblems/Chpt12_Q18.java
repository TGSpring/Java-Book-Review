/*
 * Tyler Spring
 * 8/17/2025
 * Chapter 12 Question 18
 * Write a recursive method called waysToClimb that takes a positive integer value representing a number of stairs and prints
 * each unique way to climb a staircase of that height, taking strides of one or two stairs at a time. Do not use any loops. Output 
 * each way to climb the stairs on its own line, using a 1 to indicate a small stride of 1 stair, and a 2 to indicate a large stride of 2 stairs.
 * The order in which you output the possible ways to climb the stairs is not important, so long as you list the right overall set of ways.
 * For example, the call waysToClimb(3); should produce the following
 * [1, 1, 1]
 * [1, 2]
 * [2, 1]
 * The call waysToClimb(4); should produce the following output:
 * [1, 1, 1, 1]
 * [1, 1, 2]
 * [1, 2, 1]
 * [2, 1, 1]
 * [2, 2]
 */

/*
 * This problem kind of confused me, so I am making these notes to help.
   Base Case: If you have 0 stairs left, you found a valid way, print.
   If you have fewer than 0 stairs left, this path is invalid, do nothing.

   Recursive Case: At each step, you have two choices:
   Take a stride of 1 stair (subtract 1 from stairs left, add 1 to your path).
   Take a stride of 2 stairs (subtract 2 from stairs left, add 2 to your path).
   Recursively explore both choices.

   Tracking Path:
   You will need to keep track of the current path.
   This is usually done by passing a List<Integer> as a parameter to your recursive helper method.
 */

import java.util.ArrayList;
import java.util.List;

public class Chpt12_Q18 {
    public static void main(String[] args) {
        waysToClimb(4);
    }

    public static void waysToClimb(int stairs) {
        waysToClimbHelp(stairs, new ArrayList<>());
    }

    private static void waysToClimbHelp(int stairsLeft, List<Integer> path) {
        if (stairsLeft == 0) {
            System.out.println(path); // Prints current path.
        }
        if (stairsLeft < 0) {
            return; // Invalid path.
        }

        // Trying a stride of 1.
        path.add(1);
        waysToClimbHelp(stairsLeft - 1, path);
        path.remove(path.size() - 1); // backtracking.

        // Trying a stride of 2.
        path.add(2);
        waysToClimbHelp(stairsLeft - 2, path);
        path.remove(path.size() - 1); // Backtrack.

    }
}
/*
 * Space Complexity: O(n) the maximum depth of the recursion and the largest
 * path list is equal to the number of stairs, so space is O(n).
 * 
 * Time complexity: O(2^n), each step, you branch into two recursive calls, one
 * for a stride of 1, one for a stride of 2, this forms a binary tree of calls.
 * The total number of unique paths and recursive calls grows exponentially with
 * the number of stairs. N, the size of stairs, directly affects both
 * the depth of the recursion, space, and the number of possible ways to climb,
 * time. As n increases, the number of unique ways increases quickly.
 */