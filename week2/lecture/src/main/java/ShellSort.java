/**
 * Time cost:
 */
public class ShellSort implements Sort {
    @Override
    public void sort(Comparable[] input) {
        v1(input);
    }

    private void insertionSort(Comparable[] input, int h) {
        for (int i = 0; i < input.length; i++) {
            for (int j = i + h; j < input.length && j >= h; j -= h) {
                if (Util.less(input[j], input[j - h])) {
                    Util.exch(input, j, j - h);
                }
            }
        }
    }

    private void v1(Comparable[] input) {
        int h = input.length - 1;

        while (h >= 1) {
            insertionSort(input, h);
            h /= 2;
        }
    }
}
