import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> queue = new RandomizedQueue<>();
        for (int i = 0; i < k; i++) {
            queue.enqueue(StdIn.readString());
        }

        int n = k;
        while (!StdIn.isEmpty()) {
            n++;
            String word = StdIn.readString();
            if (StdRandom.uniform() < (double) k / n) {
                queue.dequeue();
                queue.enqueue(word);
            }
        }

        for (String str : queue) {
            System.out.println(str);
        }
    }
}
