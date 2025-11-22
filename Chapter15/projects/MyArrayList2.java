
import java.util.ConcurrentModificationException;

public class MyArrayList2<E> {

    private E[] elementData; // underlying array.
    private int size;       // number of elements.

    private int modCount = 0; // tracks structural mods. Helping to keep lists in-sync.

    // Default initial capacity
    private static final int DEFAULT_CAPACITY = 10;

    // Constructor
    public MyArrayList2() {
        this.elementData = (E[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    // Constructor with initial capacity.
    public MyArrayList2(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity can't be negative.");
        }
        this.elementData = (E[]) new Object[capacity];
        this.size = 0;
    }

    // Returns the number of elements.
    public int size() {
        return size;
    }

    // Ensures capacity.
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elementData.length) {
            int newCapacity = elementData.length * 2;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }

            E[] newArray = (E[]) new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newArray[i] = elementData[i];
            }

            elementData = newArray;
        }
    }

    // Gets element at index.
    public E get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    // Sets element at index, returns old value.
    public E set(int index, E element) {
        checkIndex(index);
        E old = elementData[index];
        elementData[index] = element;
        return old;
    }

    // Adds element to end.
    public void add(E element) {
        ensureCapacity(size + 1);
        elementData[size++] = element;
        modCount++; // increment for structural change
    }

    // Adds element at specific index.
    public void add(int index, E element) {
        checkAddIndex(index);
        ensureCapacity(size + 1);
        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = element;
        size++;
        modCount++; // increment for structural change
    }

    // INTEGER
    // Removes element at index, returns removed value.
    public E remove(int index) {
        checkIndex(index);
        E removed = elementData[index];
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        modCount++; // increment for structural change
        elementData[--size] = null; // Avoid memory leak. GC only works on objects with NO refs to them.
        return removed;
    }

    // Object
    // Removes element at index, returns removed value.
    public boolean remove(E element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && elementData[i] == null) || (element != null && element.equals(elementData[i]))) {

                for (int j = i; j < size - 1; j++) {
                    elementData[j] = elementData[j + 1];
                }

                elementData[--size] = null; // clear last element.
                modCount++; // increment for structural change
                return true;                // finished removing.
            }
        }
        return false;
    }

    // private remove method to prevent removing objects.
    private E removeAtIndex(int index) {
        return remove(index);
    }

    // Index bounds check helper.
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    // clear method.
    // When we build subList, calling this on a subList must translate to removing that entire range form the parent list.
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null; // Free references for GC.
        }
        modCount++; // increment for structural change
        size = 0;
    }

    // indexOf method.
    // This provides the backbone for search, contains, and for debugging subList later.
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o == null ? elementData[i] == null : o.equals(elementData[i])) {
                return i;
            }
        }
        return -1;
    }

    // contains method.
    // logically just indexOf >= 0.
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    //checkAddIndex method.
    // Mirrors java's real ArrayList.
    private void checkAddIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    // toString.
    // Self explanatory.
    // This is the only instance in my entire repo I will use StringBuilder.
    // I do not care if its slightly cheating.
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(elementData[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    // Needed because Java does not allow access directly to subClass given it is private.
    public SubList subList(int fromIndex, int toIndex) {
        return new SubList(this, fromIndex, toIndex);
    }

    //Finally implementing the subList.
    private class SubList extends MyArrayList2<E> {

        private final MyArrayList2<E> parent;
        private final int offSet;
        private int subSize;

        private int expectedModCount;

        SubList(MyArrayList2<E> parent, int fromIndex, int toIndex) {
            if (fromIndex < 0 || toIndex > parent.size() || fromIndex > toIndex) {
                throw new IndexOutOfBoundsException("fromIndex: " + fromIndex + ", toIndex: " + toIndex);
            }
            this.parent = parent;
            this.offSet = fromIndex;
            this.subSize = toIndex - fromIndex;
            this.expectedModCount = parent.modCount; // snapshot of parent state
        }

        @Override
        public E get(int index) {
            checkForComod();
            checkSubIndex(index);
            return parent.get(offSet + index);
        }

        @Override
        public E set(int index, E element) {
            checkForComod();
            checkSubIndex(index);
            return parent.set(offSet + index, element);
        }

        @Override
        public void add(int index, E element) {
            checkForComod();
            checkSubAddIndex(index);
            parent.add(offSet + index, element);
            subSize++;
            expectedModCount = parent.modCount; // update snapshot
        }

        @Override
        public E remove(int index) {
            checkForComod();
            checkSubIndex(index);
            E removed = parent.removeAtIndex(offSet + index);
            subSize--;
            expectedModCount = parent.modCount; // update snapshot
            return removed;
        }

        @Override
        public int size() {
            return subSize;
        }

        @Override
        public void clear() {
            checkForComod();
            for (int i = subSize - 1; i >= 0; i--) {
                parent.removeAtIndex(offSet + i);
            }
            subSize = 0;
            expectedModCount = parent.modCount; // update snapshot
        }

        private void checkSubIndex(int index) {
            if (index < 0 || index >= subSize) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + subSize);
            }
        }

        private void checkSubAddIndex(int index) {
            if (index < 0 || index > subSize) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + subSize);
            }
        }

        // Optionally adding these three in.
        @Override
        public int indexOf(Object o) {
            checkForComod();
            for (int i = 0; i < subSize; i++) {
                if (o == null ? parent.get(offSet + i) == null : o.equals(parent.get(offSet + i))) {
                    return i;
                }
            }
            return -1;
        }

        @Override
        public boolean contains(Object o) {
            checkForComod();
            return indexOf(o) >= 0;
        }

        private void checkForComod() {
            if (parent.modCount != expectedModCount) {
                throw new ConcurrentModificationException("Parent list modified outside of sublist.");
            }
        }

        @Override
        public String toString() {
            if (size == 0) {
                return "[]";
            }

            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < subSize; i++) {
                sb.append(parent.get(offSet + i));
                if (i < subSize - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]");
            return sb.toString();
        }

    }
}
