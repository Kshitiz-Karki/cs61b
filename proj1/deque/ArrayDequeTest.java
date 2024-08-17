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
        arr.addLast("h");
        arr.addLast("h");

        assertEquals(arr.size(), 10);
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
        assertEquals(arr.removeFirst(), "f");
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
        assertEquals(arr.removeLast(), "e");
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
    public void doubleTest() {
        ArrayDeque<Double> arr = new ArrayDeque<>();
        arr.addLast(1.8);
        arr.addFirst(5.5);
        arr.addFirst(9.0);
        arr.addLast(6.7);
        arr.printDeque();
        assertEquals(arr.removeLast(), 6.7, 0.0);
        arr.printDeque();
        assertEquals(arr.get(0), 9.0, 0.0);
    }

    @Test
    public void iteratorTest() {
        ArrayDeque<String> arr = new ArrayDeque<>();
        arr.addFirst("hey");
        arr.addFirst("Pink Floyd");
        arr.addLast("Echoes");
        arr.addLast("Time");
        for (String x: arr) {
            System.out.println(x);
        }
    }

    @Test
    public void equalsTest() {
        ArrayDeque<String> arr1 = new ArrayDeque<>();
        arr1.addFirst("hey");
        arr1.addFirst("Pink Floyd");
        arr1.addLast("Echoes");
        arr1.addLast("Time");

        ArrayDeque<String> arr2 = new ArrayDeque<>();
        arr2.addFirst("hey");
        arr2.addFirst("Pink Floyd");
        arr2.addLast("Echoes");
        arr2.addLast("Time");

        LinkedListDeque<String> lld1 = new LinkedListDeque<>();
        lld1.addFirst("hey");
        lld1.addFirst("Pink Floyd");
        lld1.addLast("Echoes");
        lld1.addLast("Time");
        System.out.println(arr1.equals(lld1));
        System.out.println(lld1.equals(arr2));
        System.out.println(arr1.equals(arr2));
    }
}
