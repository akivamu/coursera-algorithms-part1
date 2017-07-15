public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    public int size() {
        return root == null ? 0 : root.size;
    }

    public void put(Key key, Value val) {
        Node newNode = new Node(key, val);
        newNode.size = 1;

        if (root == null) {
            root = newNode;
        } else {
            put(root, newNode);
        }
    }

    private void put(Node node, Node newNode) {
        if (newNode.key.compareTo(node.key) == 0) {
            node.value = newNode.value;
        } else if (newNode.key.compareTo(node.key) < 0) {
            if (node.left == null) node.left = newNode;
            else put(node.left, newNode);
            node.size++;
        } else if (newNode.key.compareTo(node.key) > 0) {
            if (node.right == null) node.right = newNode;
            else put(node.right, newNode);
            node.size++;
        }
    }

    public Value get(Key key) {
        Node node = root;
        while (node != null) {
            if (key.compareTo(node.key) < 0) {
                node = node.left;
            } else if (key.compareTo(node.key) > 0) {
                node = node.right;
            } else {
                return node.value;
            }
        }

        return null;
    }

    public void delete(Key key) {
        // TODO
    }

    public Value min() {
        if (root == null) return null;

        Node node = root;
        while (true) {
            if (node.left == null) break;
            node = node.left;
        }

        return node.value;
    }

    public Value max() {
        if (root == null) return null;

        Node node = root;
        while (true) {
            if (node.right == null) break;
            node = node.right;
        }

        return node.value;
    }

    public Key floor(Key key) {
        Node node = floor(root, key);
        if (node == null) return null;
        return node.key;
    }

    private Node floor(Node node, Key key) {
        if (node == null) return null;

        if (key.compareTo(node.key) == 0) return node;

        if (key.compareTo(node.key) < 0) return floor(node.left, key);

        Node tmp = floor(node.right, key);
        if (tmp == null) return node;
        else return tmp;
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node node, Key key) {
        if (node == null) return 0;

        if (key.compareTo(node.key) == 0) {
            return node.left == null ? 0 : node.left.size;
        }
        if (key.compareTo(node.key) < 0) {
            return rank(node.left, key);
        }
        return (node.left == null ? 0 : node.left.size) + 1 + rank(node.right, key);
    }

    public Iterable<Key> iterator() {
        return null;
    }

    private class Node {
        private final Key key;
        private Value value;
        private Node left, right;
        private int size;

        private Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }
}
