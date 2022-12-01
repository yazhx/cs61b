package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void addItemTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        assertEquals(0, ad.size());

        for (int i = 101; i < 109; i++) {
            ad.addFirst(i);
        }
//        for test printDeque(), need to change printDeque() method to return string.
//        assertEquals("108 107 106 105 104 103 102 101 ", ad.printDeque());
        assertEquals(108, ad.get(0).intValue());

        ArrayDeque<Integer> ad2 = new ArrayDeque<>();
        for (int i = 0; i < 10; i++) {
            ad2.addLast(i);
        }
//        assertEquals("0 1 2 3 4 5 6 7 8 9 ", ad2.printDeque());
        assertEquals(9, ad2.get(9).intValue());


        ArrayDeque<Integer> ad3 = new ArrayDeque<>();
        ad3.addFirst(100);
        ad3.addFirst(101);
        ad3.addFirst(102);
        ad3.addLast(999);
        ad3.addLast(888);
        ad3.addLast(777);
        ad3.addLast(666);
        ad3.addLast(555);
//        assertEquals("102 101 100 999 888 777 666 555 ", ad3.printDeque());
        assertEquals(999, ad3.get(3).intValue());
    }


    @Test
    public void removeTest() {
        ArrayDeque<String> ad = new ArrayDeque<>();

        ad.addLast("hello");
        ad.addLast("hell");
        ad.addLast("world~");
        ad.addLast("navigate");

       assertEquals("hello", ad.removeFirst());
       assertEquals("navigate", ad.removeLast());

//        ad.removeLast();
//        ad.removeLast();
//        ad.removeLast();
        ad.removeFirst();
        ad.removeFirst();
        ad.removeFirst();
        assertEquals(0, ad.size());

        ArrayDeque<Integer> ad2 = new ArrayDeque<>();
        for (int i = 100; i < 110; i++) {
            ad2.addLast(i);
        }
        assertEquals(109, ad2.removeLast().intValue());
        assertEquals(108, ad2.get(8).intValue());
    }
}
