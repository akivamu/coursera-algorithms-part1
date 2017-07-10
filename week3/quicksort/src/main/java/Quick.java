public class Quick {
    public static void sort(int[] input) {
        sortPartition(input, 0, input.length - 1);
    }

    private static void swap(int[] array, int index1, int index2) {
        int tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    private static int makePartition(int[] array, int startIndex, int endIndex) {
        int pivotValue = array[startIndex];
        int i = startIndex + 1;
        int j = endIndex;

        while (true) {
            while (array[i] < pivotValue && i < endIndex) {
                i++;
            }
            while (array[j] > pivotValue && j > startIndex) {
                j--;
            }

            if (i >= j) break;
            swap(array, i, j);
        }

        swap(array, startIndex, j);

        return j;
    }

    private static void sortPartition(int[] array, int startIndex, int endIndex) {
        if (startIndex >= endIndex) return;

        int pivotIndex = makePartition(array, startIndex, endIndex);

        sortPartition(array, startIndex, pivotIndex - 1);
        sortPartition(array, pivotIndex + 1, endIndex);
    }
}