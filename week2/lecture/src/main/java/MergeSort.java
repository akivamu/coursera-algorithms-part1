public class MergeSort implements Sort {
    @Override
    public void sort(Comparable[] input) {
//        v1(input);
//        v2(input, 0, input.length - 1);
        v3(input, new Comparable[input.length], 0, input.length - 1);
    }

    /**
     * At each step:
     * - divide main array into 2 halves (clone)
     * - sort these 2 halves.
     * - merge these 2 halves into main array
     * <p>
     * Cons: Each recursion creates 2 new array instances.
     * And these instances can't be GC until sorting completed (1st recursive call returned)
     */
    private void v1(Comparable[] array) {
        if (array.length == 1) return;

        int midIndex = (array.length - 1) / 2;

        // Note: These 2 cloned arrays stay in memory because this function not returns yet.
        // They can't be GC until last recursive call returns
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

        // Note: Now the cloned arrays can be GC
    }

    /**
     * Improve v1 when clone main array only after recursive call.
     * It can be GC right after merge.
     * <p>
     * At each step:
     * - sort main array for 1st half and 2nd half, in place.
     * - clone main array.
     * - merge cloned array into main array.
     */
    private void v2(Comparable[] array, int startIndex, int endIndex) {
        if (endIndex <= startIndex) return;

        int midIndex = startIndex + (endIndex - startIndex) / 2;

        v2(array, startIndex, midIndex);
        v2(array, midIndex + 1, endIndex);

        // Merge
        Comparable[] tmpArray = new Comparable[array.length];
        System.arraycopy(array, startIndex, tmpArray, startIndex, endIndex - startIndex + 1);

        int leftIndex = startIndex;
        int rightIndex = midIndex + 1;
        for (int i = startIndex; i <= endIndex; i++) {
            if (leftIndex > midIndex) array[i] = tmpArray[rightIndex++];
            else if (rightIndex > endIndex) array[i] = tmpArray[leftIndex++];
            else if (Util.less(tmpArray[leftIndex], tmpArray[rightIndex])) array[i] = tmpArray[leftIndex++];
            else array[i] = tmpArray[rightIndex++];
        }

        // Note: The tmpArray can be GC now, then last recursive call clone new array and so on...
    }

    /**
     * tmpArray is just for merging, used only in function scope. So we can create common instance for all call.
     * Saving the instance creating cost.
     */
    private void v3(Comparable[] array, Comparable[] tmpArray, int startIndex, int endIndex) {
        if (endIndex <= startIndex) return;

        int midIndex = startIndex + (endIndex - startIndex) / 2;

        v3(array, tmpArray, startIndex, midIndex);
        v3(array, tmpArray, midIndex + 1, endIndex);

        // Merge
        System.arraycopy(array, startIndex, tmpArray, startIndex, endIndex - startIndex + 1);

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
