public class QuickSort implements Sort {
    public void sort(Comparable[] input) {
        sortPartition(input, 0, input.length - 1);
    }

    /**
     * Find the final position to place element at `startIndex`. This position is called pivot.
     * This pivot divide array into left and right partitions.
     * All elements in left partition are less than pivot.
     * All elements in right partition are large than pivot.
     */
    private static int makePartition(Comparable[] array, int startIndex, int endIndex) {
        int pivotIndex = startIndex;
        int i = startIndex + 1;
        int j = endIndex;

        while (true) {
            while (Util.less(array[i], array[pivotIndex]) && i < endIndex) {
                i++;
            }
            while (Util.less(array[pivotIndex], array[j]) && j > startIndex) {
                j--;
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