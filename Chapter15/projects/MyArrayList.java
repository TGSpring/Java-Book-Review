

/*My implementation of ArrayList<E> */
public class MyArrayList<E> {

    private Object[] elementData; //generic array storage.
    private int size; // number of elements in the list.

    private static final int DEFAULT_CAPACITY = 10;

    // constructs an empty list with DEFAULT_CAPACITY.
    public MyArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    // Constructs an empty list with specified capacity.
    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity must be >= 0: " + capacity);
        }
        this.elementData = new Object[capacity];
        this.size = 0;
    }

    // Returns the current number of elements.
    public int size() {
        return size;
    }

    // Checks if the list is empty.
    public boolean isEmpty() {
        return size == 0;
    }

    // Adds element to the end.
    public void add(E value) {
        ensureCapacity(size + 1);
        elementData[size++] = value;
    }

    // Ensures the array has enough capacity.
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elementData.length) {
            int newCapacity = Math.max(minCapacity, elementData.length * 2);
            Object[] newData = new Object[newCapacity];
            System.arraycopy(elementData, 0, newData, 0, size);
            elementData = newData;
        }
    }

    // Retrieves element at a given index.
    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);
        return (E) elementData[index];
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
    }

    // I know I can use StringBuilder, but that is not the best way to learn these structures.
    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        } else {
            String result = "[" + elementData[0];
            for (int i = 1; i < size; i++) {
                result += ", " + elementData[i];
            }
            result += "]";
            return result;
        }
    }

    // addAll implementation.
    public void addAll(int index, MyArrayList<E> list) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
        // Shift everything to the right by the length of the incoming list.
        ensureCapacity(size + list.size());
        for (int i = size - 1; i >= index; i--) {
            elementData[i + list.size()] = elementData[i];
        }
        for (int i = 0; i < list.size(); i++) {
            elementData[index + i] = list.get(i);
        }
        // Update size.
        size += list.size();
    }

    // containsAll implementation
    // Returns true if every element in list exists somewhere in this list.
    // Returns false if at least one element in list is missing.
    public boolean containsAll(MyArrayList<E> list) {
        for (int i = 0; i < list.size(); i++) {
            E element = list.get(i);
            boolean flag = false;
            for (int j = 0; j < size; j++) {
                if (elementData[j].equals(element)) {
                    flag = true; //Found element stop scanning.
                    break;
                }
            }
            if (!flag) {
                return false; // this element is missing.

            }
        }
        return true;    // all elements found.

    }

    // equals implementation.
    // returns true if other object is a instanceOf MyArrayList.
    // returns true if list have same size.
    // returns true if elements are equal in order.
    // Using Override due to object class having its own equals method.
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true; // Same reference.

        }
        if (!(o instanceof MyArrayList<?> other)) {
            return false;  // wrong type. This avoids casting later, do not do casting if you can help it.
        }
        if (this.size != other.size()) {
            return false; // different sizes not equal.
        }
        for (int i = 0; i < size; i++) {
            if (!elementData[i].equals(other.get(i))) {
                return false; // mismatch found not equal.
            }
        }
        return true;
    }

    /*  lastIndexOf implementation.
        Finds and returns the last index at which the 
        specified integer appears, or -1 if absent.
        If value occurs multiple times, the index of the last 
        occurrence is returned.
        Iterates backwards to find true "last" occurrence.
        Using .equals for object types.
     */
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (elementData[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    /*
     * remove implementation
     * Removes value at the given index, shifting values left.
     */
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if ((o == null && elementData[i] == null) || (o != null && o.equals(elementData[i]))) {
                // Shift elements.
                for (int j = i; j < size - 1; j++) {
                    elementData[j] = elementData[j + 1];
                }
                elementData[size - 1] = null; // clearing last slot.
                size--;
                return true;
            }
        }
        return false;
    }

    /*
     * removeAll implementation.
     */
    public void removeAll(MyArrayList<E> list) {
        for (int i = 0; i < list.size(); i++) {
            E elementToRemove = list.get(i);

            while (this.remove(elementToRemove)) {
                // While this seems like odd syntax, it is a iteration, conditional, and call in one declaration.
            }
        }
    }
}
