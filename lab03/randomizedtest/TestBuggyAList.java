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
        AListNoResizing<Integer> alst = new AListNoResizing<>();
        BuggyAList<Integer> blst = new BuggyAList<>();
        for (int i = 6; i < 9; i++) {
            alst.addLast(i);
            blst.addLast(i);
        }

        assertEquals(alst.size(), blst.size());
        assertEquals(alst.removeLast(), blst.removeLast());
        assertEquals(alst.removeLast(), blst.removeLast());
        assertEquals(alst.removeLast(), blst.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                correct.addLast(randVal);
                broken.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = correct.size();
                assertEquals(correct.size(), broken.size());
                System.out.println("size: " + size);
            } else if (operationNumber == 2) {
                // getLast & removeLast
                if (correct.size() > 0) {
                    System.out.println("correct getLast: " + correct.getLast());
                    System.out.println("broken getLast: " + broken.getLast());
                    assertEquals(correct.getLast(), broken.getLast());
                    correct.removeLast();
                    broken.removeLast();
                }
            }
        }
    }
}
