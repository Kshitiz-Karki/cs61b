package deque;

import java.util.Iterator;

/*
Doubly Linked list with a circular sentinel node
 */
public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class IntNode {
        //instance variables
        private IntNode prev;
        private T item;
        private IntNode next;
        //constructor
        IntNode(IntNode p, T i, IntNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    //instance variables
    private final IntNode sentinel;
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
    @Override
    public void addFirst(T item) {
        size++;
        IntNode x = new IntNode(sentinel, item, sentinel.next);
        sentinel.next = x;
        x.next.prev = x;
    }

    /*
    Adds an item of type T to the back of the deque assuming that item is never null
     */
    @Override
    public void addLast(T item) {
        size++;
        IntNode x = new IntNode(sentinel.prev, item, sentinel);
        sentinel.prev.next = x;
        sentinel.prev = x;
    }

    //Returns the number of items in the deque
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
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size--;
        IntNode first = sentinel.next;
        sentinel.next = first.next;
        first.next.prev = sentinel;
        first.next = null;
        first.prev = null;
        return first.item;
    }

    /*
    Removes and returns the item at the back of the deque. If no such item exists, returns null
     */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size--;
        IntNode last = sentinel.prev;
        sentinel.prev = last.prev;
        last.prev.next = sentinel;
        last.prev = null;
        last.next = null;
        return last.item;
    }

    /*
    Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    If no such item exists, returns null.
     */
    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
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

    private class LinkedListIterator implements Iterator<T> {
        private int position;

        LinkedListIterator() {
            position = 0;
        }

        public boolean hasNext() {
            return position < size;
        }

        public T next() {
            T returnItem = get(position);
            position++;
            return returnItem;
        }
    }

    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Deque) {
            Deque<T> list = (Deque<T>) o;
            if (this.size != list.size()) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (this.get(i) != list.get(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
