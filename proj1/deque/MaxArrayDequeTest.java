package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class MaxArrayDequeTest {
    @Test
    public void max1Test() {
        IntegerComparator i = new IntegerComparator();
        MaxArrayDeque<Integer> maxArr = new MaxArrayDeque<>(i);
        maxArr.addFirst(200);
        maxArr.addFirst(2);
        maxArr.addFirst(199);
        maxArr.addFirst(4);
        maxArr.addFirst(599);
        assertEquals((int) maxArr.max(), 599);
    }

    @Test
    public void max2Test() {
        StringComparator s = new StringComparator();
        MaxArrayDeque<String> maxArr = new MaxArrayDeque<>();
        maxArr.addFirst("Austria");
        maxArr.addFirst("France");
        maxArr.addFirst("Belgium");
        maxArr.addFirst("France");
        maxArr.addFirst("Denmark");
        maxArr.addFirst("Canada");
        assertEquals(maxArr.max(s), "France");
    }
}
