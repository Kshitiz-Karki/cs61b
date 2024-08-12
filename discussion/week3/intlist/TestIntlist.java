package intlist;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestIntlist {
    @Test
    public void testIntList() {
        IntList L = new IntList(5, null);
        L = new IntList(2, L);
        L = new IntList(4, L);
        L = new IntList(1, L);
        assertEquals(L.toString(), "1 -> 4 -> 2 -> 5");
    }

    @Test
    public void testEvenOdd() {
        IntList L = new IntList(5, null);
        L = new IntList(2, L);
        L = new IntList(4, L);
        L = new IntList(1, L);
        L = new IntList(3, L);
        L = new IntList(0, L);
        IntList.evenOdd(L);
        assertEquals(L.toString(), "0 -> 1 -> 2 -> 3 -> 4 -> 5");
    }

    @Test
    public void testReverse() {
        IntList L = new IntList(5, null);
        L = new IntList(2, L);
        L = new IntList(4, L);
        L = new IntList(1, L);
        assertEquals(IntList.reverse(L).toString(), "5 -> 2 -> 4 -> 1");
    }

    @Test
    public void testPartition() {
        IntList L = new IntList(1, null);
        L = new IntList(2, L);
        L = new IntList(3, L);
        L = new IntList(4, L);
        L = new IntList(5, L);
        assertEquals(L.toString(), "5 -> 4 -> 3 -> 2 -> 1");
        IntList[] arr = IntList.partition(L, 2);
        assertEquals(arr[0].toString(), "5 -> 3 -> 1");
        assertEquals(arr[1].toString(), "4 -> 2");
    }
}
