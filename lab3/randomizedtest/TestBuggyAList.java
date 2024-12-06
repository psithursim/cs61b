package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> test = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                test.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                if (L.size() > 0 && test.size() > 0) {
                    assertEquals(L.removeLast(), test.removeLast());
                }
            } else {
                // size
                int size = L.size();
                int size1 = test.size();
                assertEquals(size, size1);
            }
        }
    }
}
