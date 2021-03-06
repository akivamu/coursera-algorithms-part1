import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PointSETTest {
    @Test
    public void testCornerCases() {
        PointSET pointSET = new PointSET();

        try {
            pointSET.insert(null);
            Assert.fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }

        try {
            pointSET.contains(null);
            Assert.fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }

        try {
            pointSET.range(null);
            Assert.fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }

        try {
            pointSET.nearest(null);
            Assert.fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void testSimple() {
        PointSET pointSET = new PointSET();

        Assert.assertTrue(pointSET.isEmpty());
        Assert.assertEquals(0, pointSET.size());

        // Insert
        Point2D point1 = new Point2D(0.1, 0.1);
        pointSET.insert(point1);
        Assert.assertFalse(pointSET.isEmpty());
        Assert.assertEquals(1, pointSET.size());

        Point2D point2 = new Point2D(0.1, 0.1);
        pointSET.insert(point2);
        Assert.assertFalse(pointSET.isEmpty());
        Assert.assertEquals(1, pointSET.size());

        Point2D point3 = new Point2D(0.1, 0.2);
        pointSET.insert(point3);
        Assert.assertFalse(pointSET.isEmpty());
        Assert.assertEquals(2, pointSET.size());

        // Contains
        Assert.assertTrue(pointSET.contains(new Point2D(0.1, 0.1)));
        Assert.assertTrue(pointSET.contains(new Point2D(0.1, 0.2)));
        Assert.assertFalse(pointSET.contains(new Point2D(0.1, 0.3)));

        pointSET.draw();
    }

    @Test
    public void testSimpleRange() {
        PointSET pointSET = new PointSET();
        pointSET.insert(new Point2D(0, 0));
        pointSET.insert(new Point2D(0, 1));
        pointSET.insert(new Point2D(1, 0));
        pointSET.insert(new Point2D(0.5, 0.5));
        pointSET.insert(new Point2D(0.25, 0.25));
        pointSET.insert(new Point2D(0.5000001, 0));

        RectHV rect = new RectHV(0, 0, 0.5, 0.5);
        List<Point2D> insidePoints = new ArrayList<>();
        for (Point2D point2D : pointSET.range(rect)) {
            insidePoints.add(point2D);
        }
        Assert.assertEquals(3, insidePoints.size());
        for (Point2D point2D : insidePoints) {
            System.out.println(point2D);
        }

        pointSET.draw();
    }

    @Test
    public void testSimpleNearest() {
        PointSET pointSET = new PointSET();

        Assert.assertEquals(null, pointSET.nearest(new Point2D(0, 0)));

        pointSET.insert(new Point2D(0, 0));
        pointSET.insert(new Point2D(0, 1));
        pointSET.insert(new Point2D(1, 0));
        pointSET.insert(new Point2D(0.5, 0.5));
        pointSET.insert(new Point2D(0.25, 0.25));
        pointSET.insert(new Point2D(0.5000001, 0));

        Assert.assertEquals(new Point2D(0.5, 0.5), pointSET.nearest(new Point2D(0.5, 0.5)));
        Assert.assertEquals(new Point2D(0.5, 0.5), pointSET.nearest(new Point2D(0.51, 0.51)));

        Assert.assertNotEquals(new Point2D(0.5, 0.5), pointSET.nearest(new Point2D(0.25, 0.25)));
        Assert.assertEquals(new Point2D(0.25, 0.25), pointSET.nearest(new Point2D(0.25, 0.25)));

        Assert.assertEquals(new Point2D(0.5, 0.5), pointSET.nearest(new Point2D(1, 1)));

        pointSET.draw();
    }

    @Test
    public void testNearestFromFiles() {
        runNearestFromFile("circle4.txt", new Point2D(0.81, 0.30), new Point2D(1.0, 0.5));
        runNearestFromFile("circle10.txt", new Point2D(0.81, 0.30), new Point2D(0.975528, 0.345492));
        runNearestFromFile("circle10k.txt", new Point2D(0.81, 0.30), new Point2D(0.761250, 0.317125));
        runNearestFromFile("circle100.txt", new Point2D(0.81, 0.30), new Point2D(0.922164, 0.232087));
        runNearestFromFile("circle1000.txt", new Point2D(0.81, 0.30), new Point2D(0.920472, 0.229439));
        runNearestFromFile("circle10000.txt", new Point2D(0.81, 0.30), new Point2D(0.920132, 0.228911));
    }

    private void runNearestFromFile(String fileName, Point2D testPoint, Point2D nearestPoint) {
        PointSET pointSET = new PointSET();

        In in = new In(fileName);
        while (!in.isEmpty()) {
            pointSET.insert(new Point2D(in.readDouble(), in.readDouble()));
        }

        Assert.assertEquals(nearestPoint, pointSET.nearest(testPoint));

        pointSET.draw();
    }
}
