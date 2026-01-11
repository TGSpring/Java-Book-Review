
import java.util.NoSuchElementException;

public class LinkedListIterator2<E> {

    public class Node {

        E data;
        Node prev;
        Node next;
    }

    private Node current;
    private Node lastReturned;
    private int index;
    private boolean lastMoveWasNext;

    public LinkedListIterator2(Node head) {
        current = head;
        index = 0;
        lastReturned = null;
    }

    /**
     * Returns true if there is an element before the iterator's current
     * position.
     *
     * Think about it: - The iterator sits *between nodes*, not on a node. - If
     * the cursor is at the start of the list (index 0): - There is no element
     * before it - hasPrevious() should return false - If the cursor is anywhere
     * after the first element: - There *is* an element before it -
     * hasPrevious() should return true
     *
     * Important edge cases: - If current == null (past the end): - There may
     * still be elements before it - We should not move any nodes; this is a
     * *read-only check*
     *
     * Steps to think about when implementing: 1. Is the iterator at the very
     * start? Return false 2. Otherwise, there is a previous node: return true
     */
    public boolean hasPrevious() {
        return current != null && current.prev != null
                || (current == null && index > 0);
    }

    /**
     * Moves the iterator forward and returns the element to the right of the
     * cursor.
     *
     * 1. Check if current == null, we are past the end, throw
     * NoSuchElementException. 2. Save the current node in lastReturned (needed
     * for set/remove). 3. Move the cursor forward by updating current =
     * current.next. 4. Increment index so nextIndex() reflects the new
     * position. 5. Record the move direction (lastMoveWasNext = true) for
     * correctness in set(). 6. Return the value of the node we just visited.
     *
     * - Iterator moves "between nodes" not on nodes. - current always points to
     * the node that would be returned next. - lastReturned holds the node
     * returned by the last next() or previous() call.
     *
     * @return
     */
    public E next() {
        if (current == null) {
            throw new NoSuchElementException();
        }
        lastReturned = current;
        index++;
        current = current.next;
        lastMoveWasNext = true;
        return lastReturned.data;
    }

    /**
     * Returns the index of the element that would be returned by next().
     *
     * - Purely read-only, no nodes are moved. - Index reflects the "position
     * between nodes." - Initially 0 (cursor before the first element). -
     * Increments after each next() call.
     *
     *
     */
    public int nextIndex() {
        return index;
    }

    /**
     * Returns the index of the element that would be returned by previous().
     *
     * - If the iterator is at the start (index = 0), there is no element to the
     * left. - In this case, previousIndex() returns -1. - Otherwise, it returns
     * index - 1.
     *
     * - Purely read-only: does not move the iterator or nodes. - Mirrors
     * nextIndex() logic but for the left side of the cursor.
     *
     * @return
     */
    public int previousIndex() {
        return index - 1;
    }

    /**
     * Moves the iterator backward and returns the element to the left of the
     * cursor.
     *
     * - The iterator sits *between nodes*, not on a node. - If the cursor is at
     * the start (index = 0), there is no previous element. - Throw
     * NoSuchElementException.
     *
     * - Past-the-end-check: - If current == null (cursor past the last element)
     * and the last move was next(): - lastReturned points to the tail (last
     * node visited). - Move the cursor back to the tail. - Decrement index so
     * nextIndex()/previousIndex() remain correct. - Set lastMoveWasNext = false
     * to record direction. - Return the tail node's data.
     *
     * - Normal middle-of-list case: - The node to the left of the cursor is
     * current.prev. - Update lastReturned to current. - Move current to
     * current.prev. - Decrement index to reflect cursor moving left. - Set
     * lastMoveWasNext = false. - Return lastReturned.data.
     *
     * Important Notes: - This method does not move nodes themselves, it only
     * adjusts the cursor. - lastReturned always holds the node returned by the
     * last next() or previous() call. - Edge cases like start-of-list and
     * past-the-end are handled explicitly to avoid NullPointerException.
     *
     * @return The data of the node immediately to the left of the cursor.
     */
    public E previous() {
        if (index == 0) {
            throw new NoSuchElementException();
        }
        if (current == null) {
            Node tail = lastReturned;
            current = tail;
            index--;
            lastMoveWasNext = false;
            lastReturned = tail;
            return lastReturned.data;
        }
        lastReturned = current;
        current = current.prev;
        index--;
        lastMoveWasNext = false;
        return lastReturned.data;

    }

}
