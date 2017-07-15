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
            int[] correctRowCol = convert1DTo2D(i);
            if (blocks[correctRowCol[0]][correctRowCol[1]] != i + 1) count++;
        }

        return count;
    }

    public int manhattan() {
        int count = 0;
        for (int row = 0; row < dimension(); row++) {
            for (int col = 0; col < dimension(); col++) {
                if (blocks[row][col] == 0) continue;
                int[] correctRowCol = convert1DTo2D(blocks[row][col] - 1);

                count += Math.abs(row - correctRowCol[0]);
                count += Math.abs(col - correctRowCol[1]);
            }
        }
        return count;
    }

    private int[] convert1DTo2D(int index) {
        int[] rowCol = new int[2];

        rowCol[0] = index / dimension();
        rowCol[1] = index % dimension();
        return rowCol;
    }

    private int convert2DTo1D(int row, int col) {
        return row * dimension() + col;
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
