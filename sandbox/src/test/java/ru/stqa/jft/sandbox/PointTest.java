package ru.stqa.jft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

    @Test
    public void testDistance1() {
        Point p1 = new Point(0,0);
        Point p2 = new Point(3.0,4);
        Assert.assertEquals(p1.distance(p2), 5.0);
    }

    @Test
    public void testDistance2() {
        Point p1 = new Point(1,2.0);
        Point p2 = new Point(7.0,10.0);
        Assert.assertEquals(p1.distance(p2), 10.0);
    }
}
