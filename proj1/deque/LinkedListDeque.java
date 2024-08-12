package deque;
/*
Doubly Linked list with a circular sentinel node
 */
public class LinkedListDeque<T> {
    private class IntNode {
        //instance variables
        public IntNode prev;
        public T item;
        public IntNode next;
        //constructor
        public IntNode(IntNode p, T i, IntNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    //instance variables
    private IntNode sentinel;
    private int size;

    //constructor - empty
    public LinkedListDeque() {
        sentinel = new IntNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    //instance/static methods
    //Adds an item of type T to the front of the deque assuming that item is never null
    public void addFirst(T item) {
        size++;
        IntNode x = new IntNode(sentinel, item, sentinel.next);
        sentinel.next = x;
        x.next.prev = x;
    }

    /*
    Adds an item of type T to the back of the deque assuming that item is never null
     */
    public void addLast(T item) {
        size++;
        IntNode x = new IntNode(sentinel.prev, item, sentinel);
        sentinel.prev.next = x;
        sentinel.prev = x;
    }

    //Returns true if deque is empty, false otherwise
    public boolean isEmpty() {
        return size == 0;
    }

    //Returns the number of items in the deque
    public int size() {
        return size;
    }

    /*
    Prints the items in the deque from first to last, separated by a space.
    Once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        if (size > 0) {
            if (size == 1) {
                System.out.println(sentinel.next.item);
                return;
            }
            IntNode p = sentinel.next;
            while (p != sentinel) {
                System.out.print(p.item + " ");
                p = p.next;
            }
            System.out.println();
        }
    }

    /*
    Removes and returns the item at the front of the deque. If no such item exists, returns null
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size--;
        IntNode first = sentinel.next;
        sentinel.next = first.next;
        first.next.prev = sentinel;
        first.next = first.prev = null;
        return first.item;
    }

    /*
    Removes and returns the item at the back of the deque. If no such item exists, returns null
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size--;
        IntNode last = sentinel.prev;
        sentinel.prev = last.prev;
        last.prev.next = sentinel;
        last.prev = last.next = null;
        return last.item;
    }

    /*
    Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    If no such item exists, returns null.
     */
    public T get(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        int i = 0;
        IntNode p = sentinel.next;
        while (i != index) {
            i++;
            p = p.next;
        }
        return p.item;
    }

    private T getRecursiveHelper(int index, IntNode p) {
        if (index == 0) {
            return p.item;
        }
        return getRecursiveHelper(index - 1, p.next);
    }

    public T getRecursive(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        return getRecursiveHelper(index, sentinel.next);
    }
}
