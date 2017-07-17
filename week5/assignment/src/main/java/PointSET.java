import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class PointSET {
    private final TreeSet<Point2D> points = new TreeSet<>();

    public PointSET() {
    }

    public boolean isEmpty() {
        return points.isEmpty();
    }

    public int size() {
        return points.size();
    }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        points.add(p);
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return points.contains(p);
    }

    public void draw() {
        for (Point2D point : points) {
            point.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();

        Set<Point2D> insidePoints = new TreeSet<>();
        for (Point2D point : points) {
            if (rect.contains(point)) insidePoints.add(point);
        }

        return insidePoints;
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        Point2D nearestPoint = null;

        for (Point2D point : points) {
            if (nearestPoint == null) nearestPoint = point;
            else {
                if (nearestPoint.distanceSquaredTo(p) > point.distanceSquaredTo(p)) {
                    nearestPoint = point;
                }
            }
        }

        return nearestPoint;
    }

}
