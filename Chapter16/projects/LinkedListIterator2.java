
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
    private Node head;
    private Node tail;
    private int size;

    public LinkedListIterator2(Node head) {
        this.head = head;
        current = head;
        index = 0;
        lastReturned = null;

        // Set tail to the last node in the list
        tail = head;
        if (tail != null) {
            while (tail.next != null) {
                tail = tail.next;
            }
        }
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
            current = tail;
            lastReturned = current;
        } else {
            lastReturned = current.prev; // the node to the left.
            current = current.prev; // move cursor left.
        }

        index--;
        lastMoveWasNext = false;
        return lastReturned.data;

    }

    /**
     * Inserts a new element immediately to the left of the iterator's cursor.
     *
     * Mental model reminder: - The iterator sits *between nodes*, not on a
     * node. - `current` always points to the node that would be returned by
     * next(). - add() inserts BEFORE `current`.
     *
     * High-level rules from the ListIterator contract: - The cursor does NOT
     * move to the new node. - The index increases by 1. - lastReturned becomes
     * invalid (set/remove not allowed immediately after).
     *
     * There are exactly three structural cases to consider:
     *
     * 1) Insert at the head: - Happens when the cursor is before the first
     * element. - This is detected by `current == head`. - The new node becomes
     * the new head.
     *
     * 2) Insert in the middle: - Happens when the cursor is between two
     * existing nodes. - `current != null` and `current != head`. - The new node
     * is inserted between `current.prev` and `current`.
     *
     * 3) Insert at the tail: - Happens when the cursor is past the end of the
     * list. - `current == null`. - The new node is appended after the existing
     * tail.
     *
     * In all cases: - Only pointers are rewired; no traversal occurs. - head
     * and tail references are updated when necessary. - index is incremented. -
     * lastReturned is reset to null.
     */
    public void add(E value) {
        Node newNode = new Node();
        newNode.data = value;

        if (current == null) { // past the end -> append
            newNode.prev = tail;
            newNode.next = null;
            if (tail != null) {
                tail.next = newNode;
            }
            tail = newNode;
            if (head == null) {
                head = newNode;
            }
        } else if (current.prev == null) { // insert at head
            newNode.prev = null;
            newNode.next = current;
            current.prev = newNode;
            head = newNode;
        } else { // normal middle insert
            newNode.prev = current.prev;
            newNode.next = current;
            current.prev.next = newNode;
            current.prev = newNode;
        }

        index++;
        lastReturned = null;

    }

    /**
     * Replaces the value of the last element returned by next() or previous().
     *
     * Rules enforced by the iterator contract: - set() is only legal if a node
     * was returned by the most recent next() or previous() call. - add()
     * invalidates lastReturned. - Calling set() without a valid lastReturned is
     * an error.
     *
     * Behavior: - Does NOT move the cursor. - Does NOT change the structure of
     * the list. - Simply updates the data field of the lastReturned node.
     *
     * @throws IllegalStateException if next() or previous() has not been
     * called, or if add() was called after the last traversal.
     */
    public void set(E value) {
        if (lastReturned == null) {
            throw new IllegalStateException();
        }
        lastReturned.data = value;

    }
}
