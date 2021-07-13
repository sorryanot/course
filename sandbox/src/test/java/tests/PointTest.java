package tests;

import Point.Point;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

    @Test
    public void pointTest() {

        Point p1 = new Point(0, 0);
        Point p2 = new Point(10, 9);

        Assert.assertEquals(Point.distance(p1,p2),13.45362404707371);
        Assert.assertTrue(Point.distance(p1,p2)>0);

    }
}
