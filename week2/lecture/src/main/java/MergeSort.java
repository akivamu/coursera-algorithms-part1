public class MergeSort implements Sort {
    @Override
    public void sort(Comparable[] input) {
        v1(input);
    }

    private void v1(Comparable[] array) {
        if (array.length == 1) return;

        int midIndex = (array.length - 1) / 2;

        Comparable[] leftArray = new Comparable[midIndex + 1];
        System.arraycopy(array, 0, leftArray, 0, leftArray.length);

        Comparable[] rightArray = new Comparable[array.length - leftArray.length];
        System.arraycopy(array, midIndex + 1, rightArray, 0, rightArray.length);

        // Sort 2 partitions
        v1(leftArray);
        v1(rightArray);

        // Merge
        int leftIndex = 0;
        int rightIndex = 0;
        for (int i = 0; i < array.length; i++) {
            if (rightIndex > rightArray.length - 1 || leftIndex < leftArray.length && Util.less(leftArray[leftIndex], rightArray[rightIndex])) {
                array[i] = leftArray[leftIndex];
                leftIndex++;
            } else if (leftIndex > midIndex || rightIndex < rightArray.length && Util.less(rightArray[rightIndex], leftArray[leftIndex])) {
                array[i] = rightArray[rightIndex];
                rightIndex++;
            }
        }
    }
}
