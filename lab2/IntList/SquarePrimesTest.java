package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesTrue() {
        IntList lst = IntList.of(3, 2, 10, 99, 33, 23);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("9 -> 4 -> 10 -> 99 -> 33 -> 529", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesFalse() {
        IntList lst = IntList.of(4, 9, 14, 24, 40, 35, 100);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("4 -> 9 -> 14 -> 24 -> 40 -> 35 -> 100", lst.toString());
        assertFalse(changed);
    }
}
