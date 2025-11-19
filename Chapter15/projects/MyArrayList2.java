
public class MyArrayList2<E> {

    private E[] elementData; // underlying array.
    private int size;       // number of elements.

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
    }

    // INTEGER
    // Removes element at index, returns removed value.
    public E remove(int index) {
        checkIndex(index);
        E removed = elementData[index];
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
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

}
