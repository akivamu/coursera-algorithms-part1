public class Board {
    private final int[][] blocks;

    public Board(int[][] blocks) {
        this.blocks = blocks;
    }

    public int dimension() {
        return blocks.length;
    }

    public int hamming() {
        int count = 0;
        for (int i = 0; i < dimension() * dimension() - 1; i++) {
            int col = i % dimension();
            int row = i / dimension();
            if (blocks[row][col] != i + 1) count++;
        }

        return count;
    }

    public int manhattan() {
        // TODO
        return 0;
    }

    public boolean isGoal() {
        return hamming() == 0;
    }

    public Board twin() {
        // TODO
        return null;
    }

    public boolean equals(Object y) {
        // TODO
        return false;
    }

    public Iterable<Board> neighbors() {
        // TODO
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : blocks) {
            for (int col : row) {
                sb.append(col);
                sb.append(" ");
            }
            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }
}
