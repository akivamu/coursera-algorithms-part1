import java.util.*;

public class FastCollinearPoints {
    private Point[] points;
    private LineSegment[] lineSegments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();

        Set<Point> nonRepeatedPoints = new HashSet<>();
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

    private void scan() {
        List<LineSegment> segments = new ArrayList<>();

        for (Point p : points) {
            List<Point> others = new ArrayList<>(Arrays.asList(points));
            others.remove(p);
            Point[] otherPoints = others.toArray(new Point[0]);
            Arrays.sort(otherPoints, p.slopeOrder());

            for (int i = 0; i < otherPoints.length - 2; i++) {
                if (p.slopeTo(otherPoints[i]) == p.slopeTo(otherPoints[i + 1])
                        && p.slopeTo(otherPoints[i]) == p.slopeTo(otherPoints[i + 2])) {
                    // Find endpoints
                    Point[] sameSlopePoints = new Point[4];
                    sameSlopePoints[0] = p;
                    sameSlopePoints[1] = otherPoints[i];
                    sameSlopePoints[2] = otherPoints[i + 1];
                    sameSlopePoints[3] = otherPoints[i + 2];
                    Arrays.sort(sameSlopePoints);

                    if (p.compareTo(sameSlopePoints[0]) == 0) {
                        segments.add(new LineSegment(sameSlopePoints[0], sameSlopePoints[3]));
                    }
                }
            }
        }
        lineSegments = segments.toArray(new LineSegment[0]);
    }

    public int numberOfSegments() {
        return lineSegments.length;
    }

    public LineSegment[] segments() {
        return lineSegments;
    }
}
