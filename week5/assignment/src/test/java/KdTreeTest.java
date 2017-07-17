import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class KdTreeTest {
    @Test
    public void testCornerCases() {
        KdTree kdTree = new KdTree();

        try {
            kdTree.insert(null);
            Assert.fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }

        try {
            kdTree.contains(null);
            Assert.fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }

        try {
            kdTree.range(null);
            Assert.fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }

        try {
            kdTree.nearest(null);
            Assert.fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void testSimple() {
        KdTree kdTree = new KdTree();

        Assert.assertTrue(kdTree.isEmpty());
        Assert.assertEquals(0, kdTree.size());


        // Insert
        Point2D point1 = new Point2D(0.1, 0.1);
        kdTree.insert(point1);
        Assert.assertFalse(kdTree.isEmpty());
        Assert.assertEquals(1, kdTree.size());

        Point2D point2 = new Point2D(0.1, 0.1);
        kdTree.insert(point2);
        Assert.assertFalse(kdTree.isEmpty());
        Assert.assertEquals(1, kdTree.size());

        Point2D point3 = new Point2D(0.1, 0.2);
        kdTree.insert(point3);
        Assert.assertFalse(kdTree.isEmpty());
        Assert.assertEquals(2, kdTree.size());

        // Contains
        Assert.assertTrue(kdTree.contains(new Point2D(0.1, 0.1)));
        Assert.assertTrue(kdTree.contains(new Point2D(0.1, 0.2)));
        Assert.assertFalse(kdTree.contains(new Point2D(0.1, 0.3)));

        kdTree.draw();
    }

    @Test
    public void testSimpleRange() {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0, 0));
        kdTree.insert(new Point2D(0, 1));
        kdTree.insert(new Point2D(1, 0));
        kdTree.insert(new Point2D(0.5, 0.5));
        kdTree.insert(new Point2D(0.25, 0.25));
        kdTree.insert(new Point2D(0.5000001, 0));

        RectHV rect = new RectHV(0, 0, 0.5, 0.5);
        List<Point2D> insidePoints = new ArrayList<>();
        for (Point2D point2D : kdTree.range(rect)) {
            insidePoints.add(point2D);
        }
        Assert.assertEquals(3, insidePoints.size());
        for (Point2D point2D : insidePoints) {
            System.out.println(point2D);
        }

        kdTree.draw();
    }

    @Test
    public void testSimpleNearest() {
        KdTree kdTree = new KdTree();

        Assert.assertEquals(null, kdTree.nearest(new Point2D(0, 0)));

        kdTree.insert(new Point2D(0, 0));
        kdTree.insert(new Point2D(0, 1));
        kdTree.insert(new Point2D(1, 0));
        kdTree.insert(new Point2D(0.5, 0.5));
        kdTree.insert(new Point2D(0.25, 0.25));
        kdTree.insert(new Point2D(0.5000001, 0));

        Assert.assertEquals(new Point2D(0.5, 0.5), kdTree.nearest(new Point2D(0.5, 0.5)));
        Assert.assertEquals(new Point2D(0.5, 0.5), kdTree.nearest(new Point2D(0.51, 0.51)));

        Assert.assertNotEquals(new Point2D(0.5, 0.5), kdTree.nearest(new Point2D(0.25, 0.25)));
        Assert.assertEquals(new Point2D(0.25, 0.25), kdTree.nearest(new Point2D(0.25, 0.25)));

        Assert.assertEquals(new Point2D(0.5, 0.5), kdTree.nearest(new Point2D(1, 1)));

        kdTree.draw();
    }

    @Test
    public void testNearestFromFiles() {
        runNearestFromFile("circle4.txt", new Point2D(0.81, 0.30), new Point2D(1.0, 0.5));
        runNearestFromFile("circle10.txt", new Point2D(0.81, 0.30), new Point2D(0.975528, 0.345492));
        runNearestFromFile("circle10k.txt", new Point2D(0.81, 0.30), new Point2D(0.761250, 0.317125));
        runNearestFromFile("circle100.txt", new Point2D(0.81, 0.30), new Point2D(0.922164, 0.232087));
        runNearestFromFile("circle1000.txt", new Point2D(0.81, 0.30), new Point2D(0.920472, 0.229439));
        runNearestFromFile("circle10000.txt", new Point2D(0.81, 0.30), new Point2D(0.920132, 0.228911));
        runNearestFromFile("input10K.txt", new Point2D(0.81, 0.30), new Point2D(0.800759, 0.296734));
        runNearestFromFile("input20K.txt", new Point2D(0.81, 0.30), new Point2D(0.812527, 0.29948));
        runNearestFromFile("input100K.txt", new Point2D(0.81, 0.30), new Point2D(0.809736, 0.299609));
        runNearestFromFile("input800K.txt", new Point2D(0.81, 0.30), new Point2D(0.810137, 0.300592));
        runNearestFromFile("input1M.txt", new Point2D(0.81, 0.30), new Point2D(0.809886, 0.3003));
    }

    private void runNearestFromFile(String fileName, Point2D testPoint, Point2D nearestPoint) {
        KdTree kdTree = new KdTree();

        In in = new In(fileName);
        while (!in.isEmpty()) {
            kdTree.insert(new Point2D(in.readDouble(), in.readDouble()));
        }

        Assert.assertEquals(nearestPoint, kdTree.nearest(testPoint));

//        kdTree.draw();
    }
}
