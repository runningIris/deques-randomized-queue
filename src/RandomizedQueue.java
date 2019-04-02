import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.NoSuchElementException;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int len = 0;

    private Item[] queue;

    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return len == 0;
    }

    public int size() {
        return len;
    }

    private void resize() {
        Item[] newQueue = null;
        if (queue.length == len) {
            // double the size
            newQueue = (Item[]) new Object[queue.length * 2];

        }

        if (queue.length > len * 2) {
            // cut half the size
            newQueue = (Item[]) new Object[queue.length / 2];
        }

        if (newQueue != null) {
            for (int i = 0; i < len; i++) {
                newQueue[i] = queue[i];
            }

            queue = newQueue;
        }

    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item should not be null in method enqueue.");
        }
        queue[len] = item;
        len++;
        resize();
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("no more items to return in the dequeue() method.");
        }

        int random = StdRandom.uniform(len);
        len--;

        Item item = queue[random];
        queue[random] = queue[len];

        resize();

        return item;
    }

    // To be optimized: binary search
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("no more items to return in the sample() method.");
        }

        int random = StdRandom.uniform(len);

        return queue[random];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        Item[] randomQueue;
        int length;
        public RandomizedQueueIterator() {
            length = len;
            randomQueue = (Item[]) new Object[length];
            for (int i = 0; i < length; i++) {
                randomQueue[i] = queue[i];
            }
            StdRandom.shuffle(randomQueue);
        }

        public boolean hasNext() {
            return length != 0;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("no more elements to return");
            }
            length--;
            Item item = randomQueue[length];
            randomQueue[length] = null;
            return item;
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<String> r = new RandomizedQueue<>();
        r.enqueue("hello");
        r.enqueue(",");
        r.enqueue(" ");
        r.enqueue("world");
        r.enqueue("!");
        r.enqueue("!");
        r.dequeue();
        r.dequeue();
        r.dequeue();
        r.dequeue();
        r.dequeue();
        r.dequeue();
        StdOut.println(r.size());
    }
}
