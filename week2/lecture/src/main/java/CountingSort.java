import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CountingSort {
    public static List<List<Integer>> sort(int n, int[] keys, String[] values) {
        List<List<Integer>> ret = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            ret.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            ret.get(keys[i]).add(i);
        }

        return ret;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] keys = new int[n];
        String[] values = new String[n];
        for (int i = 0; i < n; i++) {
            keys[i] = in.nextInt();
            values[i] = in.next();
        }

        List<List<Integer>> map = sort(n, keys, values);

        StringBuilder sb = new StringBuilder();


        for (List<Integer> wordIndice : map) {
            for (Integer wordIndex : wordIndice) {
                sb.append(wordIndex < n / 2 ? "-" : values[wordIndex]);
                sb.append(" ");
            }
        }
        System.out.println(sb.toString());
    }
}
