import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main (String[] args) {
        int k = Integer.parseInt(args[0], 10);
        String[] arr = StdIn.readAllStrings();
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        for(int i = 0; i < arr.length; i++) {
            queue.enqueue(arr[i]);
        }

        for (int i = 0; i < k; i++) {
            StdOut.println(queue.sample());
        }
    }
}
