import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private final Node finalNode;

    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();

        MinPQ<Node> pq = new MinPQ<>();
        pq.insert(new Node(initial, null));

        MinPQ<Node> auxPq = new MinPQ<>();
        auxPq.insert(new Node(initial.twin(), null));

        while (!pq.isEmpty() || !auxPq.isEmpty()) {
            Node node = pq.delMin();
            Node auxNode = auxPq.delMin();

            if (node.board.isGoal()) {
                finalNode = node;
                return;
            }

            if (auxNode.board.isGoal()) {
                finalNode = null;
                return;
            }

            for (Board neighbor : node.board.neighbors()) {
                Node newNode = new Node(neighbor, node);
                if (node.prevNode != null && newNode.board.equals(node.prevNode.board)) continue;
                pq.insert(newNode);
            }

            for (Board neighbor : auxNode.board.neighbors()) {
                Node newNode = new Node(neighbor, auxNode);
                if (auxNode.prevNode != null && newNode.board.equals(auxNode.prevNode.board)) continue;
                auxPq.insert(newNode);
            }
        }

        this.finalNode = null;
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

    private class Node implements Comparable<Node> {
        private final Board board;
        private final Node prevNode;
        private final int moves;
        private final int priority;

        private Node(Board board, Node prevNode) {
            this.board = board;
            this.prevNode = prevNode;
            this.moves = prevNode == null ? 0 : prevNode.moves + 1;
            this.priority = this.board.manhattan() + this.moves;
        }

        @Override
        public int compareTo(Node that) {
            return priority - that.priority;
        }
    }
}
