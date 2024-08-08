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

//      int i = 1;
//      while (i <= 17) {
//        buggyAList.addLast(i);
//        arrNoResize.addLast(i);
//        i++;
//      }
//    //items.length = 32
//      assertEquals(buggyAList.size(), 17);
//      assertEquals(arrNoResize.size(), 17);
//      int j = 1;
//      while (j <= 11) {
//        buggyAList.removeLast();
//        arrNoResize.removeLast();
//        j++;
//      }
//      assertEquals(buggyAList.size(), 6);
//      assertEquals(arrNoResize.size(), 6);
//      assertEquals(arrNoResize.getLast(), buggyAList.getLast());
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
        System.out.println("L.addLast(" + randVal + ")");
        System.out.println("B.addLast(" + randVal + ")");
      } else if (operationNumber == 1) {
        // size
        int size1 = L.size();
        int size2 = B.size();
        assertEquals(size1, size2);
        System.out.println("size1: " + size1);
        System.out.println("size2: " + size2);
        assertEquals(size1, size2);
      } else if (operationNumber == 2) {
        //getLast
        if (L.size() > 0 && B.size() > 0) {
          int lLast = L.getLast();
          int bLast = B.getLast();
          System.out.println("L.getLast(" + lLast + ")");
          System.out.println("B.getLast(" + bLast + ")");
          assertEquals(lLast, bLast);
        }
      } else if (operationNumber == 3) {
        //removeLast
        if (L.size() > 0 && B.size() > 0) {
          int x = L.removeLast();
          int y = B.removeLast();
          System.out.println("L.removeLast(" + x + ")");
          System.out.println("B.removeLast(" + y + ")");
          assertEquals(x, y);
        }
      }
    }
  }
}
