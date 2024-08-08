package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  @Test
    public void testThreeAddThreeRemove() {
      AListNoResizing<Integer> arrNoResize = new AListNoResizing<>();
      BuggyAList<Integer> buggyAList = new BuggyAList<>();

      arrNoResize.addLast(4);
      arrNoResize.addLast(5);
      arrNoResize.addLast(6);
      buggyAList.addLast(4);
      buggyAList.addLast(5);
      buggyAList.addLast(6);

      assertEquals(arrNoResize.size(), buggyAList.size());
      assertEquals(arrNoResize.removeLast(), buggyAList.removeLast());
      assertEquals(arrNoResize.removeLast(), buggyAList.removeLast());
      assertEquals(arrNoResize.removeLast(), buggyAList.removeLast());
  }

  @Test
  public void randomizedTest() {
    AListNoResizing<Integer> L = new AListNoResizing<>();
    BuggyAList<Integer> B = new BuggyAList<>();
    int N = 5000;
    for (int i = 0; i < N; i += 1) {
      int operationNumber = StdRandom.uniform(0, 4);
      if (operationNumber == 0) {
        // addLast
        int randVal = StdRandom.uniform(0, 100);
        L.addLast(randVal);
        B.addLast(randVal);
      } else if (operationNumber == 1) {
        // size
        int size1 = L.size();
        int size2 = B.size();
        assertEquals(size1, size2);
      } else if (operationNumber == 2) {
        //getLast
        if (L.size() > 0 && B.size() > 0) {
          int lLast = L.getLast();
          int bLast = B.getLast();
          assertEquals(lLast, bLast);
        }
      } else if (operationNumber == 3) {
        //removeLast
        if (L.size() > 0 && B.size() > 0) {
          int x = L.removeLast();
          int y = B.removeLast();
          assertEquals(x, y);
        }
      }
    }
  }
}
