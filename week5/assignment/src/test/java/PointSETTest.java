import edu.princeton.cs.algs4.Point2D;
import org.junit.Assert;
import org.junit.Test;

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
    }
}
