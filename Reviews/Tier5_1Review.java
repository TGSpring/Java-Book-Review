
import java.util.Deque;
import java.util.LinkedList;

//Idk how to name these anymore just go with it.
/* Sliding window Max.
     Find max number in every window of size k.
 */
public class Tier5_1Review {

    public static void main(String[] args) {
        int[] arr = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;

        // dq stores indices, not values.
        // Maintaining deque so that the largest element in the current window is always at the front.
        Deque<Integer> dq = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            // Any index at the front of the deque that is older than the window size is removed.
            while (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }
            // remove indices smaller than the current element from the back.
            // If arr[i] is bigger, those elements can never be the max in this window or future.
            while (!dq.isEmpty() && arr[dq.peekLast()] < arr[i]) {
                dq.pollLast();
            }
            dq.offerLast(i);
            if (i >= k - 1) {
                System.out.println(arr[dq.peekFirst()] + " ");

            }

        }
    }
}
