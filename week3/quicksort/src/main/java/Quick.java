public class Quick {
    public static void sort(Comparable[] input) {
        sortPartition(input, 0, input.length - 1);
    }

    private static void swap(Comparable[] array, int index1, int index2) {
        Comparable tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    private static int makePartition(Comparable[] array, int startIndex, int endIndex) {
        int pivotIndex = startIndex;
        int i = startIndex + 1;
        int j = endIndex;

        while (true) {
            while (array[i].compareTo(array[pivotIndex]) < 0 && i < endIndex) {
                i++;
            }
            while (array[j].compareTo(array[pivotIndex]) > 0 && j > startIndex) {
                j--;
            }

            if (i >= j) break;
            swap(array, i, j);
        }

        swap(array, startIndex, j);

        return j;
    }

    private static void sortPartition(Comparable[] array, int startIndex, int endIndex) {
        if (startIndex >= endIndex) return;

        int pivotIndex = makePartition(array, startIndex, endIndex);

        sortPartition(array, startIndex, pivotIndex - 1);
        sortPartition(array, pivotIndex + 1, endIndex);
    }
}