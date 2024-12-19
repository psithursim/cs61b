package deque;

import org.junit.Assert;
import org.junit.Test;

public class MaxArrayDequeTest {
    @Test
    public void testStringMaxArrayDeque() {
        MyComparator<String> st = new MyComparator<>();
        MaxArrayDeque<String> ms = MaxArrayDeque.of("I", "have", "an", "elephant");
        String expected = "have";
        String actual = ms.max(st);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testIntMaxArrayDeque() {
        MyComparator<Integer> st = new MyComparator<>();
        MaxArrayDeque<Integer> ms = MaxArrayDeque.of(100, 25, 87, 99);
        int expected = 100;
        int actual = ms.max(st);
        Assert.assertEquals(expected, actual);
    }
}
