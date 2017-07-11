import edu.princeton.cs.algs4.StdIn;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

public class Util {
    private static final Random random = new Random();

    public static void resyncStdIn() {
        try {
            Method m = StdIn.class.getDeclaredMethod("resync");
            m.setAccessible(true);
            m.invoke(null);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void shuffle(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("argument array is null");
        } else {
            for (int i = 0; i < array.length; i++) {
                int randomIndexToSwap = i + random.nextInt(array.length - i);
                // Swap
                int tmp = array[randomIndexToSwap];
                array[randomIndexToSwap] = array[i];
                array[i] = tmp;
            }
        }
    }

    public static Integer[] generateRandomIntegerArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i;

        Util.shuffle(arr);

        Integer[] ret = new Integer[arr.length];
        for (int i = 0; i < n; i++) ret[i] = arr[i];

        return ret;
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }
}
