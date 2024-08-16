package deque;

public class ArrayDeque<T> implements Deque<T> {
    //instance variables
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    //constants / static variables
    private static final int INITIAL_SIZE = 8;
    private static final int USAGE_FACTOR = 4;
    private static final int INITIAL_SIZE_DOUBLE = 16;
    //constructor
    public ArrayDeque() {
        size = 0;
        items = (T []) new Object[INITIAL_SIZE];
        nextFirst = 3;
        nextLast = 4;
    }
    //instance/static methods

    /*
    resize (grow or shrink) items array if there is no space left or there are too
    many empty spaces
     */
    private void resize(int newSize) {
        T[] newItems = (T []) new Object[newSize];
        for (int i = 0; i < size; i++) {
            newItems[i] = items[(i + 1 + nextFirst) % items.length];
        }
        nextFirst = newSize - 1;
        nextLast = size;
        items = newItems;
    }

    /*
    Adds an item of type T to the front of the deque assuming that item is never null
     */
    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        size++;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
    }

    /*
    Adds an item of type T to the back of the deque assuming that item is never null
     */
    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        size++;
        nextLast = (nextLast + 1 + items.length) % items.length;
    }

    /*
    Returns the number of items in the deque
     */
    @Override
    public int size() {
        return size;
    }

    /*
    Prints the items in the deque from first to last, separated by a space.
    Once all the items have been printed, print out a new line.
     */
    @Override
    public void printDeque() {
        for (int i = 1; i <= size; i++) {
            System.out.print(items[(i + nextFirst) % items.length] + " ");
        }
        System.out.println();
    }

    /*
    Removes and returns the item at the front of the deque. If no such item exists, returns null
     */
    @Override
    public T removeFirst() {
        if (isEmpty()) { //(nextFirst + 1) % items.length == nextLast
            return null;
        }
        if (size <= items.length / USAGE_FACTOR && items.length >= INITIAL_SIZE_DOUBLE) {
         resize(items.length / USAGE_FACTOR);
        }
        size--;
        nextFirst = (nextFirst + 1) % items.length;
        T item = items[nextFirst];
        items[nextFirst] = null;
        return item;
    }

    /*
    Removes and returns the item at the back of the deque. If no such item exists, returns null
     */
    @Override
    public T removeLast() {
        if (isEmpty()) { //(nextFirst + 1) % items.length == nextLast
            return null;
        }
        if (size <= items.length / USAGE_FACTOR && items.length >= INITIAL_SIZE_DOUBLE) {
            resize(items.length / USAGE_FACTOR);
        }
        size--;
        nextLast = (nextLast - 1 + items.length) % items.length;
        T item = items[nextLast];
        items[nextLast] = null;
        return item;
    }

    /*
    Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    If no such item exists, returns null
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[(nextFirst + 1 + index) % items.length];
    }

    /** returns last item in the list */
    public T getLast() {
        if (isEmpty()) { //(nextFirst + 1) % items.length == nextLast
            return null;
        }
        return items[(nextLast - 1 + items.length) % items.length];
    }
}
