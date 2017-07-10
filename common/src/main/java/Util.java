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
}
