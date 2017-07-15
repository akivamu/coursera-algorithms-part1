import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;


public class Solver {
    private MinPQ<Node> pq = new MinPQ<>();
    private Node finalNode = null;

    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

    public Solver(Board initial) {
        pq.insert(makeNode(initial, null));

        while (!pq.isEmpty()) {
            Node node = pq.delMin();

            if (node.board.isGoal()) {
                finalNode = node;
                break;
            }

            for (Board neighbor : node.board.neighbors()) {
                Node newNode = makeNode(neighbor, node);
                if (node.prevNode != null && newNode.board.equals(node.prevNode.board)) continue;
                pq.insert(newNode);
            }
        }
    }

    private Node makeNode(Board board, Node prevNode) {
        Node node = new Node();
        node.board = board;
        node.prevNode = prevNode;
        if (prevNode != null) {
            node.moves = prevNode.moves + 1;
        }
        node.priority = node.board.hamming() + node.moves;

        return node;
    }

    public boolean isSolvable() {
        return finalNode != null;
    }

    public int moves() {
        return isSolvable() ? finalNode.moves : -1;
    }

    public Iterable<Board> solution() {
        if (!isSolvable()) return null;

        Stack<Board> steps = new Stack<>();
        Node node = finalNode;

        while (node != null) {
            steps.push(node.board);
            node = node.prevNode;
        }
        return steps;
    }

    private class Node implements Comparable<Node> {
        private Board board;
        private Node prevNode;
        private int moves;
        private int priority;

        @Override
        public int compareTo(Node o) {
            return priority - o.priority;
        }
    }
}
