import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CountingSortTest {
    @Test
    public void test1(){
        System.setIn(getClass().getResourceAsStream("input01.txt"));
        CountingSort.main(null);
    }

    @Test
    public void test2(){
        System.setIn(getClass().getResourceAsStream("input05.txt"));
        CountingSort.main(null);
    }

}
