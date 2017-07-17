import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Set;
import java.util.TreeSet;

public class KdTree {
    private Node root;
    private int size;

    public KdTree() {
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();

        if (root == null) {
            root = new Node(p);
            root.isHorizontal = false;
            root.rect = new RectHV(0, 0, 1, 1);
            size++;
        } else {
            insertToNode(root, p);
        }
    }

    private void insertToNode(Node node, Point2D p) {
        if (!node.point.equals(p)) {
            if (!node.isHorizontal) {
                // Vertical split
                if (p.x() < node.point.x()) {
                    if (node.left == null) {
                        node.left = new Node(p);
                        node.left.isHorizontal = !node.isHorizontal;
                        node.left.rect = new RectHV(node.rect.xmin(), node.rect.ymin(), node.point.x(), node.rect.ymax());
                        size++;
                    } else {
                        insertToNode(node.left, p);
                    }
                } else {
                    if (node.right == null) {
                        node.right = new Node(p);
                        node.right.isHorizontal = !node.isHorizontal;
                        node.right.rect = new RectHV(node.point.x(), node.rect.ymin(), node.rect.xmax(), node.rect.ymax());
                        size++;
                    } else {
                        insertToNode(node.right, p);
                    }
                }
            } else {
                // Horizontal split
                if (p.y() < node.point.y()) {
                    if (node.left == null) {
                        node.left = new Node(p);
                        node.left.isHorizontal = !node.isHorizontal;
                        node.left.rect = new RectHV(node.rect.xmin(), node.rect.ymin(), node.rect.xmax(), node.point.y());
                        size++;
                    } else {
                        insertToNode(node.left, p);
                    }
                } else {
                    if (node.right == null) {
                        node.right = new Node(p);
                        node.right.isHorizontal = !node.isHorizontal;
                        node.right.rect = new RectHV(node.rect.xmin(), node.point.y(), node.rect.xmax(), node.rect.ymax());
                        size++;
                    } else {
                        insertToNode(node.right, p);
                    }
                }
            }
        }
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();

        return containsInNode(root, p);
    }

    private boolean containsInNode(Node node, Point2D p) {
        if (node == null) return false;
        else {
            if (p.equals(node.point)) return true;
            else {
                if (!node.isHorizontal) {
                    // Vertical split
                    if (p.x() < node.point.x()) {
                        return containsInNode(node.left, p);
                    } else {
                        return containsInNode(node.right, p);
                    }
                } else {
                    // Horizontal split
                    if (p.y() < node.point.y()) {
                        return containsInNode(node.left, p);
                    } else {
                        return containsInNode(node.right, p);
                    }
                }
            }
        }
    }

    public void draw() {
        draw(root);
    }

    private void draw(Node node) {
        if (node == null) return;

        StdDraw.setPenColor(StdDraw.BLACK);
        node.point.draw();

        if (node.isHorizontal) {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(node.rect.xmin(), node.point.y(), node.rect.xmax(), node.point.y());
        } else {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(node.point.x(), node.rect.ymin(), node.point.x(), node.rect.ymax());
        }

        draw(node.left);
        draw(node.right);
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        Set<Point2D> insidePoints = new TreeSet<>();

        findInNode(root, rect, insidePoints);

        return insidePoints;
    }

    private void findInNode(Node node, RectHV rect, Set<Point2D> insidePoints) {
        if (node == null || !rect.intersects(node.rect)) return;

        if (rect.contains(node.point)) insidePoints.add(node.point);
        findInNode(node.left, rect, insidePoints);
        findInNode(node.right, rect, insidePoints);
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (root == null) return null;
        return findNearestPoint(root, root, p).point;
    }

    private Node findNearestPoint(Node node, Node curNearest, Point2D searchPoint) {
        if (node == null) return curNearest;

        if (searchPoint.distanceSquaredTo(node.point) < searchPoint.distanceSquaredTo(curNearest.point)) {
            curNearest = node;
        }

        if (searchPoint.distanceSquaredTo(curNearest.point) < node.rect.distanceSquaredTo(searchPoint)) {
            return curNearest;
        }

        curNearest = findNearestPoint(node.left, curNearest, searchPoint);
        curNearest = findNearestPoint(node.right, curNearest, searchPoint);

        return curNearest;
    }

    private class Node {
        private final Point2D point;
        private Node left, right;
        private boolean isHorizontal = true;
        private RectHV rect;

        public Node(Point2D point) {
            this.point = point;
        }
    }
}
