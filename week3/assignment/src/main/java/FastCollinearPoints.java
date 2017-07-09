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
        return a == b;
    }

    private Point[] getSortedPointsBySlopeOrder(Point originPoint) {
        List<Point> others = new ArrayList<>(Arrays.asList(points));
        others.remove(originPoint);
        Point[] otherPoints = others.toArray(new Point[0]);
        Arrays.sort(otherPoints, originPoint.slopeOrder());
        return otherPoints;
    }

    private void scan() {
        if (points.length < 4) return;

        for (Point p : points) {
            Point[] otherPoints = getSortedPointsBySlopeOrder(p);

            double lastSlope = p.slopeTo(otherPoints[0]);
            int firstPointIndex = 0;
            int pointsCount = 1;
            for (int i = 1; i < otherPoints.length; i++) {
                double curSlope = p.slopeTo(otherPoints[i]);

                if (isEquals(lastSlope, curSlope)) {
                    pointsCount++;
                }

                if (!isEquals(lastSlope, curSlope) || i == otherPoints.length - 1) {
                    if (pointsCount >= 3) {
                        boolean saveSegment = true;
                        Point lastPoint = null;
                        for (int k = firstPointIndex; k < firstPointIndex + pointsCount; k++) {
                            if (otherPoints[k].compareTo(p) < 0) {
                                saveSegment = false;
                                break;
                            }
                            if (lastPoint == null || otherPoints[k].compareTo(lastPoint) > 0) {
                                lastPoint = otherPoints[k];
                            }
                        }

                        if (saveSegment) {
                            lineSegments.add(new LineSegment(p, lastPoint));
                        }
                    }
                    lastSlope = curSlope;
                    firstPointIndex = i;
                    pointsCount = 1;
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
