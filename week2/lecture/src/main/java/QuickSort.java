public class QuickSort implements Sort {
    public void sort(Comparable[] input) {
        sortPartition(input, 0, input.length - 1);
    }

    public Comparable findKthSmallestItem(Comparable[] input, int k) {
        int startIndex = 0;
        int endIndex = input.length - 1;

        while (true) {
            int pivotIndex = makePartition(input, startIndex, endIndex);

            if (pivotIndex > k) {
                endIndex = pivotIndex - 1;
            } else if (pivotIndex < k) {
                startIndex = pivotIndex + 1;
            } else {
                return input[pivotIndex];
            }
        }
    }

    /**
     * Find the final position to place element at `startIndex`. This position is called pivot.
     * This pivot divide array into left and right partitions.
     * All elements in left partition are less than pivot.
     * All elements in right partition are large than pivot.
     */
    private static int makePartition(Comparable[] array, int startIndex, int endIndex) {
        int pivotIndex = startIndex;
        int i = startIndex;
        int j = endIndex + 1;

        while (true) {
            while (++i < endIndex) {
                if (!Util.less(array[i], array[pivotIndex])) break;
            }

            while (--j > startIndex) {
                if (!Util.less(array[pivotIndex], array[j])) break;
            }

            if (i >= j) break;
            Util.exch(array, i, j);
        }

        Util.exch(array, startIndex, j);

        return j;
    }

    private static void sortPartition(Comparable[] array, int startIndex, int endIndex) {
        if (startIndex >= endIndex) return;

        // Find pivot point, that divide array into 2 partitions
        int pivotIndex = makePartition(array, startIndex, endIndex);

        // Sort left partition
        sortPartition(array, startIndex, pivotIndex - 1);

        // Sort right partition
        sortPartition(array, pivotIndex + 1, endIndex);
    }
}