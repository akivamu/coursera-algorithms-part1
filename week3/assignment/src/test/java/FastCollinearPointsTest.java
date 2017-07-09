import edu.princeton.cs.algs4.In;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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

    @Test
    public void testAllInputFile() {
        testFile("input8.txt", new String[]{
                "(3000, 4000) -> (20000, 21000)",
                "(10000, 0) -> (0, 10000)",
        });

        testFile("input40.txt", new String[]{
                "(2000, 24000) -> (25000, 24000)",
                "(1000, 17000) -> (29000, 17000)",
                "(2000, 29000) -> (28000, 29000)",
                "(1000, 17000) -> (1000, 31000)",
        });
    }

    private void testFile(String fileName, String[] output) {
        // Read input file
        In in = new In(fileName);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        List<String> result = Arrays.asList(output);

        // Brute
        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
        Assert.assertEquals(output.length, bruteCollinearPoints.numberOfSegments());
        for (int i = 0; i < output.length; i++) {
            Assert.assertTrue(result.contains(bruteCollinearPoints.segments()[i].toString()));
        }

        // Fast
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        Assert.assertEquals(output.length, fastCollinearPoints.numberOfSegments());
        for (int i = 0; i < output.length; i++) {
            Assert.assertTrue(result.contains(fastCollinearPoints.segments()[i].toString()));
        }
    }
}
