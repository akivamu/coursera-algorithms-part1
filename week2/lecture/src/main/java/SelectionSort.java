/**
 * Naming: At each index, `select` smallest from remaining.
 * <p>
 * Time cost: (N-1) + (N-2) + (N-3) + ... + 2 + 1 = N*(N+1)/2 ~ N^2 / 2
 */
public class SelectionSort implements Sort {
    @Override
    public void sort(Comparable[] input) {
        for (int i = 0; i < input.length - 1; i++) {
            int smallestItemIndex = i;
            for (int j = i + 1; j < input.length; j++) {
                if (Util.less(input[j], input[smallestItemIndex])) {
                    smallestItemIndex = j;
                }
            }

            if (smallestItemIndex != i) Util.exch(input, i, smallestItemIndex);
        }
    }
}
