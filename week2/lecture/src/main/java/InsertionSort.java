/**
 * Naming: At each index, insert it into proper position on the left partition.
 * <p>
 * Time cost: 1 + 2 + N ~ N^2 / 2
 */
public class InsertionSort implements Sort {
    @Override
    public void sort(Comparable[] input) {
        for (int i = 0; i < input.length; i++) {
            int j = i;

            while (j > 0 && Util.less(input[j], input[j - 1])) {
                Util.exch(input, j, j - 1);
                j--;
            }
        }
    }
}
