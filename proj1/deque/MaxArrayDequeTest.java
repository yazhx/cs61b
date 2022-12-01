package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;

public class MaxArrayDequeTest {
    @Test
    public void addItem(){
        MaxArrayDeque<String> lst = new MaxArrayDeque<>(
                new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.length() - o2.length();
                    }
                }
        );

        assertEquals(0, lst.size());

        lst.addFirst("hello");
        lst.addFirst("hell");
        lst.addFirst("world~");
        lst.addLast("navigate");

        assertEquals("navigate", lst.max());

        lst.removeFirst();
        lst.removeLast();
        assertEquals(2, lst.size());

        lst.removeLast();
        lst.removeLast();
        lst.removeLast();
        lst.removeLast();
        assertEquals(0, lst.size());
    }
}
