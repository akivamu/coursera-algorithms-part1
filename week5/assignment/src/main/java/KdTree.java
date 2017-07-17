import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

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
            size++;
        } else {
            insertToNode(root, p);
        }
    }

    private void insertToNode(Node node, Point2D p) {
        if (node.point.equals(p)) return;
        else {
            if (!node.isHorizontal) {
                // Vertical split
                if (p.x() < node.point.x()) {
                    if (node.left == null) {
                        node.left = new Node(p);
                        node.left.isHorizontal = !node.isHorizontal;
                        size++;
                    } else {
                        insertToNode(node.left, p);
                    }
                } else {
                    if (node.right == null) {
                        node.right = new Node(p);
                        node.right.isHorizontal = !node.isHorizontal;
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
                        size++;
                    } else {
                        insertToNode(node.left, p);
                    }
                } else {
                    if (node.right == null) {
                        node.right = new Node(p);
                        node.right.isHorizontal = !node.isHorizontal;
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
        // TODO
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();

        // TODO
        return null;
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        // TODO
        return null;
    }

    private class Node {
        private Point2D point;
        private Node left, right;
        private boolean isHorizontal = true;

        public Node(Point2D point) {
            this.point = point;
        }
    }
}
