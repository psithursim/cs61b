package flik;


import org.junit.Assert;
import org.junit.Test;

public class Testflik {

    @Test
    public void FlikTest() {
        for (int i = 0; i < 1000; i++) {
            double num = Math.random();
            if (num < 0.5) {
                int a = (int) (Math.random() * 1000);
                int b;
                do {
                    b = (int) (Math.random() * 1000);
                } while (b == a);
                Assert.assertFalse(Flik.isSameNumber(a, b));
            } else {
                int a = (int) (Math.random() * 1000);
                int b = a;
                Assert.assertTrue(Flik.isSameNumber(a, b));
            }
        }
    }

    @Test
    public void FlikTest2() {
        int a = 884;
        int b = a;
        Assert.assertTrue(Flik.isSameNumber(a, b));
    }
}
