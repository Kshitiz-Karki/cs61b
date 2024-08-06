/*
Title: SLList (Singly Linked) with a sentinel node
Invariants:
    1.  The sentinel reference always points to a sentinel node
    2.  The front item (if it exists), is always at sentinel.next.item
    3.  The size variable is always the total number of items that have been added
 */


public class SLList {
    /*
    If the nested class (IntNode) has no need to use any of the instance methods or variables of SLList,
    you may declare the nested class static, as follows.
    Declaring a nested class as static means that methods inside the static class can not access any of the instance members of the enclosing class.
    But it can still access static members as seen in the Government.java .
    This saves a bit of memory, because each IntNode no longer needs to keep track of how to access its enclosing SLList.
     */
    public static class IntNode {
        //instance variables
        public int item;
        public IntNode next;
        //constructor
        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    //instance variables
    /*
    Private variables and methods can only be accessed by code inside the same .java file
     */
    private IntNode sentinel; // a special node that is always there, introduced to remove the effects of special case like if the list is empty, etc
    private int size;

    //constructor for an empty list
    public SLList() {
        sentinel = new IntNode(10, null); // sentinel.item can be any integer and is not used elsewhere
        size = 0;
    }

    //constructor for non empty list
    public SLList(int x) {
        sentinel = new IntNode(10, null);
        sentinel.next = new IntNode(x, null);
        size = 1;
    }

    //(exercise B.2) constructor when the input is array of integers
    public SLList(int[] arr) {
        size = arr.length;
        sentinel = new IntNode(10, null);
        IntNode p = sentinel;
        for (int j : arr) {
            p.next = new IntNode(j, null);
            p = p.next;
        }
    }


    //instance/static methods

    //adds an item to the front of the list
    public void addFirst(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
        size++;
    }

    //retrieves the first item from the list
    public int getFirst() {
        return sentinel.next.item;
    }

    //adds an item to the last of the list
    public void addLast(int x) {
        size++;
        //traverse to the end of the list
        IntNode p = sentinel;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
    }

    //gets the size of the list
    /*
    Here, we have two methods, both named size. This is allowed in Java, since they have different parameters.
    We say that two methods with the same name but different signatures are overloaded
     */
    private static int size(IntNode p) {
        if (p == null) {
            return 0;
        }
        return 1 + size(p.next);
    }
    public int size() {
//        return size(sentinel.next); //method 1
        return size; // using caching (practice of saving important data to speed up retrieval)
    }

    /*
    delete the first element in the list
    exercise B.1
     */
    public void deleteFirst() {
        if (sentinel.next != null) {
            size--;
            sentinel.next = sentinel.next.next;
        }
    }

    //exercise A.1
    public void addAdjacent() {
        if (size >= 2) {
            IntNode p = sentinel.next;
            IntNode temp;
            IntNode prev = sentinel;
            while (p.next != null) {
                if (p.item == p.next.item) {
                    temp = new IntNode(p.item + p.next.item, p.next.next);
                    prev.next = temp;
                    p = temp;
                    size--;
                }else {
                    prev = p;
                    p = p.next;
                }
            }
        }
    }

    //string representation of the list
    private static String toString(IntNode p) {
        //base case if the list is empty
        if (p == null) {
            return "list is empty";
        }
        //base case if the list has only one element
        if (p.next == null) {
            return String.valueOf(p.item);
        }
        //recursive case
        return p.item + " -> " + toString(p.next);
    }

    public String toString() {
        return toString(sentinel.next);
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 1, 2, 3};
//        int[] a = new int[]{6, 1, 1, 2, 3};
//        int[] a = new int[]{1, 1};
        SLList list = new SLList(a);
//        list.addLast(50);
//        list.addLast(40);
//        list.deleteFirst();
        System.out.println("list size: " + list.size());
        System.out.println("before: " + list.toString());
        list.addAdjacent();
        System.out.println("addAdjacent() :-");
        System.out.println("list size: " + list.size());
        System.out.println("after : " + list.toString());
    }
}
