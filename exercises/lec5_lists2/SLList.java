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
    private IntNode first; //Private variables and methods can only be accessed by code inside the same .java file
    private int size;
    //constructor for an empty list
    public SLList() {
        first = null;
        size = 0;
    }
    //constructor for non empty list
    public SLList(int x) {
        first = new IntNode(x, null);
        size = 1;
    }
    //instance/static methods
    //adds an item to the front of the list
    public void addFirst(int x) {
        first = new IntNode(x, first);
        size++;
    }
    //retrieves the first item from the list
    public int getFirst() {
        return first.item;
    }
    //adds an item to the last of the list
    public void addLast(int x) {
        size++;
        if (first == null) {
            first = new IntNode(x, null);
            return;
        }
        //traverse to the end of the list
        IntNode p = first;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
    }
    //gets the size of the list
    private static int size(IntNode p) {
        if (p.next == null) {
            return 1;
        }
        return 1 + size(p.next);
    }
    public int size() {
//        return size(first); //method 1
        return size; // using caching (practice of saving important data to speed up retrieval)
    }
}
