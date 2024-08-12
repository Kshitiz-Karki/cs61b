package fillgrid;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestFillGrid {
    @Test
    public void testFillGrid() {
        int[] LL = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 0 };
        int[] UR = { 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
        int[][] S = {
                { 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0}
        };
        int[][] expected = {
                {0, 11, 12, 13, 14},
                {1, 0, 15, 16, 17},
                {2, 3, 0, 18, 19},
                {4, 5, 6, 0, 20},
                {7, 8, 9, 10, 0}
        };
        FillGrid.fillGrid(LL, UR, S);
        assertArrayEquals(S, expected);
    }
}
