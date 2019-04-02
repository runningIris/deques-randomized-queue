import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.NoSuchElementException;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node first, last;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        int len = 0;
        Node current = first;
        while (current != null) {
            len++;
            current = current.next;
        }
        return len;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item should not be null in method enqueue.");
        }
        Node newNode = new Node();
        newNode.item = item;

        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {

            if (first.next == null) {
                first.next = newNode;
            }

            last.next = newNode;
            last = newNode;
        }
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("no more items to return in the dequeue() method.");
        }

        int len = size();

        if (len == 1) {
            Item item = first.item;
            first = null;
            last = null;
            return item;
        }

        int random = StdRandom.uniform(len);

        if (random == 0) {
            Item item = first.item;
            first = first.next;
            return item;
        }

        int i = 0;

        Node prev = new Node();
        prev.next = first;

        while (i < random) {
            i++;
            prev = prev.next;
        }

        Item item = prev.next.item;
        prev.next = prev.next.next;

        return item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("no more items to return in the sample() method.");
        }

        int len = size();

        if (len == 1) {
            return first.item;
        }

        int random = StdRandom.uniform(len);

        if (random == 0) {
            return first.item;
        }

        int i = 0;

        Node current = first;

        while (i < random) {
            i++;
            current = current.next;
        }

        return current.item;
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null) {
                throw new NoSuchElementException("no more items to return in the next() method.");
            }

            Item item = current.item;

            current = current.next;

            return item;
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Double> r = new RandomizedQueue<Double>();
        r.enqueue(0.7);
        r.enqueue(0.1);
        StdOut.println("de: " + r.dequeue());
        r.enqueue(0.2);
        StdOut.println("de: " + r.dequeue());
        r.enqueue(0.3);
        StdOut.println("de: " + r.dequeue());
        StdOut.println("de: " + r.dequeue());
    }
}
