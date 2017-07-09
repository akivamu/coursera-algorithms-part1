import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {
    private final Point[] points;
    private final List<LineSegment> lineSegments = new ArrayList<>();

    public FastCollinearPoints(Point[] points) {
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

        scan();
    }

    private boolean isEquals(double a, double b) {
        return (Math.abs(a - b) < 0.000001);
    }

    private void scan() {
        for (Point p : points) {
            List<Point> others = new ArrayList<>(Arrays.asList(points));
            others.remove(p);
            Point[] otherPoints = others.toArray(new Point[0]);
            Arrays.sort(otherPoints, p.slopeOrder());

            for (int i = 0; i < otherPoints.length - 2; i++) {
                if (isEquals(p.slopeTo(otherPoints[i]), p.slopeTo(otherPoints[i + 1]))
                        && isEquals(p.slopeTo(otherPoints[i]), p.slopeTo(otherPoints[i + 2]))) {
                    // Find endpoints
                    Point[] sameSlopePoints = new Point[4];
                    sameSlopePoints[0] = p;
                    sameSlopePoints[1] = otherPoints[i];
                    sameSlopePoints[2] = otherPoints[i + 1];
                    sameSlopePoints[3] = otherPoints[i + 2];
                    Arrays.sort(sameSlopePoints);

                    if (p.compareTo(sameSlopePoints[0]) == 0) {
                        lineSegments.add(new LineSegment(sameSlopePoints[0], sameSlopePoints[3]));
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
