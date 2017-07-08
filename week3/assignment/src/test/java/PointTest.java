import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;

public class PointTest {

    @Test
    public void testCompareTo() {
        Point p0 = new Point(2, 2);

        // Same
        Assert.assertTrue(p0.compareTo(new Point(2, 2)) == 0);

        // y0 < y1
        Assert.assertTrue(p0.compareTo(new Point(1, 3)) < 0);
        Assert.assertTrue(p0.compareTo(new Point(2, 3)) < 0);
        Assert.assertTrue(p0.compareTo(new Point(3, 3)) < 0);

        // y0 > y1
        Assert.assertTrue(p0.compareTo(new Point(1, 1)) > 0);
        Assert.assertTrue(p0.compareTo(new Point(2, 1)) > 0);
        Assert.assertTrue(p0.compareTo(new Point(3, 1)) > 0);

        // y0 == y1
        Assert.assertTrue(p0.compareTo(new Point(1, 2)) > 0);
        Assert.assertTrue(p0.compareTo(new Point(3, 2)) < 0);
    }

    @Test
    public void testSlopeTo() {
        Point p0 = new Point(2, 2);

        // Horizontal
        Assert.assertEquals(0, p0.slopeTo(new Point(3, 2)), 0);
        Assert.assertEquals(0, p0.slopeTo(new Point(1, 2)), 0);

        // Vertical
        Assert.assertEquals(Double.POSITIVE_INFINITY, p0.slopeTo(new Point(2, 1)), 0);
        Assert.assertEquals(Double.POSITIVE_INFINITY, p0.slopeTo(new Point(2, 3)), 0);

        // Degenerate (equal)
        Assert.assertEquals(Double.NEGATIVE_INFINITY, p0.slopeTo(new Point(2, 2)), 0);

        // Positive slope
        Assert.assertTrue(p0.slopeTo(new Point(3, 3)) > 0);
        Assert.assertTrue(p0.slopeTo(new Point(30, 3)) > 0);

        // Negative slope
        Assert.assertTrue(p0.slopeTo(new Point(3, 1)) < 0);
        Assert.assertTrue(p0.slopeTo(new Point(30, 1)) < 0);
    }

    @Test
    public void testSlopeOrder() {
        int x0 = 2;
        int y0 = 2;
        Point p0 = new Point(x0, y0);
        Comparator<Point> slopeOrder = p0.slopeOrder();

        Point verticalTopPoint = new Point(x0, y0 + 1);
        Point verticalBotPoint = new Point(x0, y0 - 1);
        Point horizontalRightPoint = new Point(x0 + 1, y0);
        Point horizontalLeftPoint = new Point(x0 - 1, y0);

        // 2 points in horizontal line
        Assert.assertEquals(0, slopeOrder.compare(horizontalRightPoint, horizontalLeftPoint));
        Assert.assertEquals(0, slopeOrder.compare(horizontalLeftPoint, horizontalRightPoint));

        // 2 points in vertical line
        Assert.assertEquals(0, slopeOrder.compare(verticalTopPoint, verticalBotPoint));
        Assert.assertEquals(0, slopeOrder.compare(verticalBotPoint, verticalTopPoint));

        // 3 points degenerate
        Assert.assertEquals(0, slopeOrder.compare(p0, p0));

        // p1 in vertical, p2 in Quadrant I
        for (int y = y0 + 1; y < y0 + 100; y++) {
            Assert.assertTrue(slopeOrder.compare(verticalTopPoint, new Point(x0 + 1, y)) > 0);
            Assert.assertTrue(slopeOrder.compare(verticalBotPoint, new Point(x0 + 1, y)) > 0);
        }

        // p1 in vertical, p2 in Quadrant IV
        for (int y = y0 - 1; y > y0 - 100; y--) {
            Assert.assertTrue(slopeOrder.compare(verticalTopPoint, new Point(x0 + 1, y)) > 0);
            Assert.assertTrue(slopeOrder.compare(verticalBotPoint, new Point(x0 + 1, y)) > 0);
        }

        // p1 in vertical, p2 horizontal
        Assert.assertTrue(slopeOrder.compare(verticalTopPoint, horizontalRightPoint) > 0);
        Assert.assertTrue(slopeOrder.compare(verticalBotPoint, horizontalRightPoint) > 0);

        // p1 horizontal, p2 in Quadrant I
        for (int y = y0 + 1; y < y0 + 100; y++) {
            Assert.assertTrue(slopeOrder.compare(horizontalRightPoint, new Point(x0 + 1, y)) < 0);
            Assert.assertTrue(slopeOrder.compare(horizontalLeftPoint, new Point(x0 + 1, y)) < 0);
        }

        // p1 horizontal, p2 in Quadrant IV
        for (int y = y0 - 1; y > y0 - 100; y--) {
            Assert.assertTrue(slopeOrder.compare(horizontalRightPoint, new Point(x0 + 1, y)) > 0);
            Assert.assertTrue(slopeOrder.compare(horizontalLeftPoint, new Point(x0 + 1, y)) > 0);
        }
    }
}