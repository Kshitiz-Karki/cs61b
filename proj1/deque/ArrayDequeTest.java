package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void addItemsTest() {
        ArrayDeque<String> arr = new ArrayDeque<>();
        arr.addLast("a");
        arr.addLast("b");
        arr.addFirst("c");
        arr.addLast("d");
        arr.addLast("e");
        arr.addFirst("f");
        arr.addLast("g");
        arr.addLast("h");

        assertEquals(arr.size(), 8);
        System.out.println("Printing out deque: ");
        arr.printDeque();
    }

    @Test
    public void removeFirstTest() {
        ArrayDeque<String> arr = new ArrayDeque<>();
        arr.addLast("f");
        arr.addLast("c");
        arr.addLast("a");
        arr.addLast("b");
        arr.addLast("d");
        arr.addLast("e");
        System.out.println("Before removal:");
        arr.printDeque();
        assertEquals(arr.removeFirst(), "f" );
        arr.removeFirst();
        arr.removeFirst();
        System.out.println("After removal:");
        arr.printDeque();
        assertEquals(arr.size(), 3);
    }

    @Test
    public void removeLastTest() {
        ArrayDeque<String> arr = new ArrayDeque<>();
        arr.addLast("f");
        arr.addLast("c");
        arr.addLast("a");
        arr.addLast("b");
        arr.addLast("d");
        arr.addLast("e");
        System.out.println("Before removal:");
        arr.printDeque();
        assertEquals(arr.removeLast(), "e" );
        arr.removeLast();
        arr.removeLast();
        System.out.println("After removal:");
        arr.printDeque();
        assertEquals(arr.size(), 3);
    }

    @Test
    public void getTest() {
        ArrayDeque<String> arr = new ArrayDeque<>();
        arr.addLast("f");
        arr.addLast("c");
        arr.addLast("a");
        arr.addLast("b");
        arr.addLast("d");
        arr.addLast("e");
        assertEquals(arr.get(2), "a");
        assertNull(arr.get(-2));
        assertEquals(arr.get(5), "e");
    }

    @Test
    public void getLastTest() {
        ArrayDeque<String> lld1 = new ArrayDeque<>();
        lld1.addFirst("hey");
        lld1.addFirst("Pink Floyd");
        lld1.addLast("Echoes");
        lld1.addLast("Time");
        assertEquals(lld1.getLast(), "Time");
    }
}
