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
        Board twin = new Board(cloneArray(blocks));
        int rowA = -1, colA = -1;
        for (int row = 0; row < dimension(); row++) {
            for (int col = 0; col < dimension(); col++) {
                if (blocks[row][col] != 0) {
                    if (rowA != -1) {
                        twin.blocks[row][col] = blocks[rowA][colA];
                        twin.blocks[rowA][colA] = blocks[row][col];
                        return twin;
                    } else {
                        rowA = row;
                        colA = col;
                    }
                }
            }
        }
        return twin;
    }

    private static int[][] cloneArray(int[][] src) {
        int length = src.length;
        int[][] target = new int[length][src[0].length];
        for (int i = 0; i < length; i++) {
            System.arraycopy(src[i], 0, target[i], 0, src[i].length);
        }
        return target;
    }

    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;

        if (that.dimension() != this.dimension()) return false;

        for (int row = 0; row < dimension(); row++) {
            for (int col = 0; col < dimension(); col++) {
                if (that.blocks[row][col] != this.blocks[row][col]) return false;
            }
        }
        return true;
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
