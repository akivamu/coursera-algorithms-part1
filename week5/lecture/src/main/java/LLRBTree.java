public class LLRBTree<Key extends Comparable<Key>, Value> {
    private Node root;

    public void put(Key key, Value value) {
        findAndPutIntoNode(root, key, value);
    }

    private Node findAndPutIntoNode(Node node, Key key, Value value) {
        if (node == null) return new Node(key, value);

        if (key.compareTo(node.key) < 0) node.left = findAndPutIntoNode(node.left, key, value);
        else if (key.compareTo(node.key) > 0) node.right = findAndPutIntoNode(node.right, key, value);
        else node.value = value;

        if (isRed(node.right) && !isRed(node.left)) node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right)) flipColors(node);

        return node;
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

    private Node rotateLeft(Node parentNode) {

        Node newParentNode = parentNode.right;
        parentNode.right = newParentNode.left;
        newParentNode.left = parentNode;
        newParentNode.color = parentNode.color;
        parentNode.color = Node.RED;

        return newParentNode;
    }

    private Node rotateRight(Node parentNode) {

        Node newParentNode = parentNode.left;
        parentNode.left = newParentNode.right;
        newParentNode.right = parentNode;
        newParentNode.color = parentNode.color;
        parentNode.color = Node.RED;

        return newParentNode;
    }

    private void flipColors(Node node) {
        if (!isRed(node)
                && isRed(node.left)
                && isRed(node.right)) {
            node.color = Node.RED;
            node.left.color = Node.BLACK;
            node.right.color = Node.BLACK;
        }
    }

    private boolean isRed(Node node) {
        return node != null && node.color == Node.RED;
    }

    private class Node {
        private static final boolean RED = true;
        private static final boolean BLACK = false;
        private final Key key;
        private Value value;
        private Node left, right;
        private boolean color = RED;

        private Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }
}
