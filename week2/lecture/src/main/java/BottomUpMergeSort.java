public class BottomUpMergeSort implements Sort {
    @Override
    public void sort(Comparable[] input) {
        Comparable[] tmpArray = new Comparable[input.length];

        int subArraySize = 1;
        while (subArraySize < input.length) {
            for (int startIndex = 0; startIndex < input.length; startIndex += 2 * subArraySize) {
                int endIndex = Math.min(input.length - 1, startIndex + 2 * subArraySize - 1);
                int midIndex = startIndex + subArraySize - 1;

                System.arraycopy(input, startIndex, tmpArray, startIndex, endIndex - startIndex + 1);
                merge(input, tmpArray, startIndex, midIndex, endIndex);
            }
            subArraySize *= 2;
        }
    }

    private void merge(Comparable[] array, Comparable[] tmpArray, int startIndex, int midIndex, int endIndex) {
        int leftIndex = startIndex;
        int rightIndex = midIndex + 1;
        for (int i = startIndex; i <= endIndex; i++) {
            if (leftIndex > midIndex) array[i] = tmpArray[rightIndex++];
            else if (rightIndex > endIndex) array[i] = tmpArray[leftIndex++];
            else if (Util.less(tmpArray[leftIndex], tmpArray[rightIndex])) array[i] = tmpArray[leftIndex++];
            else array[i] = tmpArray[rightIndex++];
        }
    }
}
