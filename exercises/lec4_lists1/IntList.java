// Integer linked list implementation
public class IntList {
    // instance variables
    public int first;
    public IntList rest;

    // constructor
    public IntList(int f, IntList r){
        first = f;
        rest = r;
    }

    // return the size of the list using recursion
    public int size(){
        // base case, when there is only one element in the list
        if (this.rest == null){
            return 1;
        }
        //recursive case
        return 1 + this.rest.size();
    }

    // return the size of the list using iteration
    public int sizeIterative(){
        int s = 0;
        IntList p = this;
        while (p != null){
            s++;
            p = p.rest;
        }
        return s;
    }

    // return ith value of the list
    public int get(int i){
        // base case
        if (i == 0){
            return this.first;
        }
        return this.rest.get(i - 1);
    }

    /** Returns an IntList identical to L, but with
     * each element incremented by x. L is not allowed
     * to change. */
    public static IntList incrList(IntList L, int x) {
        IntList newL = new IntList(L.first + x, null);
        IntList p = L.rest;
        IntList q = newL;
        while (p != null){
            q.rest = new IntList(p.first + x, null);
            p = p.rest;
            q = q.rest;
        }
        return newL;
    }

    /** Returns an IntList identical to L, but with
     * each element incremented by x. Not allowed to use
     * the 'new' keyword. */
    public static IntList dincrList(IntList L, int x) {
        IntList p = L;
        while (p != null){
            p.first += x;
            p = p.rest;
        }
        return L;
    }

    // returns the string representation of IntList
    public String toString() {
        if (this.rest == null){
            return String.valueOf(first);
        }
        return first + " -> " + this.rest.toString();
    }

    public static void main(String[] args){
//        IntList L = new IntList(15, null);
//        L = new IntList(10, L);
//        L = new IntList(5, L);
//        System.out.println(L.size());

        // Test your answers by uncommenting. Or copy and paste the
        // code for incrList and dincrList into IntList.java and
        // run it in the visualizer.
//         System.out.println(L.get(1));
        IntList L = new IntList(5, null);
        L.rest = new IntList(7, null);
        L.rest.rest = new IntList(9, null);
        System.out.println(L.toString());
        System.out.println(incrList(L, 3).toString());
        System.out.println(L.toString());
        System.out.println(dincrList(L, 3));
        System.out.println(L.toString());
    }
}