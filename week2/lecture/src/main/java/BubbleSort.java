/**
 * Time cost: (N-1) + (N-2) + (N-3) + ... + 2 + 1 = N*(N+1)/2 ~ N^2 / 2
 */
public class BubbleSort implements Sort {
    @Override
    public void sort(Comparable[] input) {
        v3(input);
    }

    private void v1(Comparable[] input) {
        boolean justSwap;
        do {
            justSwap = false;
            // Bubble the biggest to last
            for (int i = 0; i < input.length - 1; i++) {
                if (Util.less(input[i + 1], input[i])) {
                    Util.exch(input, i, i + 1);
                    justSwap = true;
                }
            }
        } while (justSwap);
    }

    // Optimize v1 by ignoring already sorted biggest bubbles
    private void v2(Comparable[] input) {
        boolean justSwap;
        int sorted = 0; // Count number of sorted bubbles
        do {
            justSwap = false;
            for (int i = 0; i < input.length - 1 - sorted; i++) {
                if (Util.less(input[i + 1], input[i])) {
                    Util.exch(input, i, i + 1);
                    justSwap = true;
                }
            }
            sorted++;
        } while (justSwap);
    }

    // Optimize v2 by find even more sorted biggest bubbles
    private void v3(Comparable[] input) {
        boolean justSwap;
        int endIndex = input.length - 1; // end index of unsorted partition
        do {
            justSwap = false;
            int lastSwapIndex = 0;
            for (int i = 0; i <= endIndex - 1; i++) {
                if (Util.less(input[i + 1], input[i])) {
                    Util.exch(input, i, i + 1);
                    justSwap = true;
                    lastSwapIndex = i;
                }
            }
            endIndex = lastSwapIndex; // All elements in the right of lastSwapIndex is no need to swap anymore
        } while (justSwap);
    }
}
