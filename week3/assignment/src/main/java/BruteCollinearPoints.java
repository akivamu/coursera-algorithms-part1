import java.util.*;

public class BruteCollinearPoints {
    private Point[] points;
    private LineSegment[] lineSegments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
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

        bruteForce();
    }

    private void bruteForce() {
        List<LineSegment> segments = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length && j != i; j++) {
                for (int k = 0; k < points.length && k != i && k != j; k++) {
                    // Stop if first 3 points NOT in the same line
                    if (points[i].slopeTo(points[j]) != points[i].slopeTo(points[k])) {
                        continue;
                    }

                    // 4th point
                    for (int l = 0; l < points.length && l != k && l != i && l != j; l++) {
                        if (points[i].slopeTo(points[k]) == points[i].slopeTo(points[l])) {

                            // Find endpoints
                            Point[] sameSlopePoints = new Point[4];
                            sameSlopePoints[0] = points[i];
                            sameSlopePoints[1] = points[j];
                            sameSlopePoints[2] = points[k];
                            sameSlopePoints[3] = points[l];
                            Arrays.sort(sameSlopePoints);

                            segments.add(new LineSegment(sameSlopePoints[0], sameSlopePoints[3]));
                        }
                    }
                }
            }
        }
        lineSegments = segments.toArray(new LineSegment[0]);
    }

    // the number of line segments
    public int numberOfSegments() {
        return lineSegments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return lineSegments;
    }

}
