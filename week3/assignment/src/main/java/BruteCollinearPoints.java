import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    private final Point[] points;
    private final List<LineSegment> lineSegments = new ArrayList<>();

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();

        List<Point> nonRepeatedPoints = new ArrayList<>();
        for (Point point : points) {
            if (point == null) throw new IllegalArgumentException();

            for (Point addedPoint : nonRepeatedPoints) {
                if (addedPoint.compareTo(point) == 0) throw new IllegalArgumentException();
            }

            nonRepeatedPoints.add(point);
        }

        this.points = nonRepeatedPoints.toArray(new Point[0]);

        bruteForce();
    }

    private boolean isEquals(double a, double b) {
        return a == b;
    }

    private void bruteForce() {
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length && j != i; j++) {
                for (int k = 0; k < points.length && k != i && k != j; k++) {
                    // Stop if first 3 points NOT in the same line
                    if (!isEquals(points[i].slopeTo(points[j]), points[i].slopeTo(points[k]))) {
                        continue;
                    }

                    // 4th point
                    for (int m = 0; m < points.length && m != k && m != i && m != j; m++) {
                        if (isEquals(points[i].slopeTo(points[k]), points[i].slopeTo(points[m]))) {

                            // Find endpoints
                            Point[] sameSlopePoints = new Point[4];
                            sameSlopePoints[0] = points[i];
                            sameSlopePoints[1] = points[j];
                            sameSlopePoints[2] = points[k];
                            sameSlopePoints[3] = points[m];
                            Arrays.sort(sameSlopePoints);

                            lineSegments.add(new LineSegment(sameSlopePoints[0], sameSlopePoints[3]));
                        }
                    }
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return lineSegments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[0]);
    }

}
