package intlist;

public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    public static void evenOdd(IntList lst) {
        if (lst == null) {
            return;
        }
        IntList second = lst.rest;
        IntList p = second;
        while (lst.rest != null && p.rest != null) {
            lst.rest = lst.rest.rest;
            p.rest = p.rest.rest;
            lst = lst.rest;
            p = p.rest;
        }
        lst.rest = second;
    }

    public static IntList reverse(IntList list) {
        if (list == null || list.rest == null) {
            return list;
        }
        IntList last = reverse(list.rest);
        list.rest.rest = list;
        list.rest = null;
        return last;
    }

    public static IntList[] partition(IntList lst, int k) {
        IntList[] array = new IntList[k];
        int index = 0;
        IntList L = reverse(lst);
        while (L != null) {
            IntList tempArr = array[index];
            IntList tempNext = L.rest;
            array[index] = L;
            array[index].rest = tempArr;
            L = tempNext;
            index = (index + 1) % 2;
        }
        return array;
    }

    public String toString() {
        if (this.rest == null) {
            return String.valueOf(this.first);
        }
        return this.first + " -> " + this.rest.toString();
    }
}
