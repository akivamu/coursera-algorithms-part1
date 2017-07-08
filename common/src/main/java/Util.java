import edu.princeton.cs.algs4.StdIn;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Util {
    public static void resyncStdIn() {
        try {
            Method m = StdIn.class.getDeclaredMethod("resync");
            m.setAccessible(true);
            m.invoke(null);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
