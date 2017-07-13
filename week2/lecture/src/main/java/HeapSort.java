public class HeapSort implements Sort {
    @Override
    public void sort(Comparable[] input) {
        // Construct heap
        for (int i = input.length; i >= 0; i--) {
            sink(input, i, input.length);
        }

        // Move max element to end
        int curHeapLength = input.length;
        while (curHeapLength > 1) {
            Util.exch(input, 0, curHeapLength - 1);
            curHeapLength--;
            sink(input, 0, curHeapLength);
        }
    }

    private void sink(Comparable[] array, int index, int heapLength) {
        while (2 * index <= heapLength - 1) {
            int indexOfFirstChild = 2 * index;
            int swapIndex = indexOfFirstChild;

            // 2nd child
            if (indexOfFirstChild + 1 <= heapLength - 1) {
                if (Util.less(array[indexOfFirstChild], array[indexOfFirstChild + 1])) {
                    swapIndex = indexOfFirstChild + 1;
                }
            }

            if (!Util.less(array[index], array[swapIndex])) break;
            Util.exch(array, index, swapIndex);
            index = swapIndex;
        }
    }
}
