package tester;

import static org.junit.Assert.*;
import org.junit.Test;
import student.StudentArrayDeque;
import edu.princeton.cs.introcs.StdRandom;

public class TestArrayDequeEC {
    @Test
    public void test() {
        StringBuilder str = new StringBuilder();
        StudentArrayDeque<Integer> sd = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ad= new ArrayDequeSolution<>();
        for (int i = 0; i < 1000; i++) {
            Integer num = StdRandom.uniform(-4,4);
            if (num <= -2) {
                sd.addLast(num);
                ad.addLast(num);
                str.append("addLast(" + num + ")\n");
                assertEquals(str + "\n",sd.size(), ad.size());
            } else if (num <= 0) {
                sd.addFirst(num);
                ad.addFirst(num);
                str.append("addFirst(" + num + ")\n");
                assertEquals(str + "\n",sd.size(), ad.size());
            } else if (num <= 2) {
                if (ad.isEmpty()) {
                    continue;
                }
                str.append("removeFirst()\n");
                assertEquals(str + "\n",ad.removeFirst(), sd.removeFirst());
            } else {
                if (ad.isEmpty()) {
                    continue;
                }
                str.append("removeLast()\n");
                assertEquals(str + "\n",ad.removeLast(), sd.removeLast());
            }
        }
    }

    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(TestArrayDequeEC.class);
    }
}
