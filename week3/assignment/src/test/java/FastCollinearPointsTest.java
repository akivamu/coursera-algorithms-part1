import org.junit.Assert;
import org.junit.Test;

public class FastCollinearPointsTest {

    @Test(expected = IllegalArgumentException.class)
    public void testCornerCaseNullArray() {
        new FastCollinearPoints(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCornerCaseArrayContainNullItem() {
        new FastCollinearPoints(new Point[]{
                new Point(1, 1),
                null,
                new Point(3, 3),
                null
        });
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCornerCaseArrayContainDuplicatedItem() {
        new FastCollinearPoints(new Point[]{
                new Point(1, 1),
                new Point(2, 2),
                new Point(2, 2),
                new Point(3, 3),
        });
    }

    @Test
    public void test1() {
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(new Point[]{
                new Point(1, 1),
                new Point(2, 2),
                new Point(3, 3),
                new Point(4, 4)
        });

        Assert.assertEquals(1, fastCollinearPoints.numberOfSegments());

        LineSegment[] lineSegments = fastCollinearPoints.segments();
        Assert.assertEquals(1, lineSegments.length);

        LineSegment lineSegment = lineSegments[0];
        Assert.assertEquals("(1, 1) -> (4, 4)", lineSegment.toString());
    }

    @Test
    public void test3() {
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(new Point[]{
                new Point(1, 1),
                new Point(2, 2),
                new Point(3, 3),
                new Point(4, 4),
                new Point(4, 5),
                new Point(3, 4),
        });

        Assert.assertEquals(1, fastCollinearPoints.numberOfSegments());

        LineSegment[] lineSegments = fastCollinearPoints.segments();
        Assert.assertEquals(1, lineSegments.length);

        Assert.assertEquals("(1, 1) -> (4, 4)", lineSegments[0].toString());
    }
}
