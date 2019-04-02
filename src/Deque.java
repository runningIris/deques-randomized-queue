import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;

    private class Node {
        Item item;
        Node next;
        Node prev;
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

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("argument item should not be null.");
        }

        Node node = new Node();
        node.item = item;

        if (isEmpty()) {
            first = node;
            last = node;
        } else {
            node.next = first;
            first.prev = node;
            first = node;
        }
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("argument item should not be null.");
        }
        Node node = new Node();
        node.item = item;

        if (isEmpty()) {
            first = node;
            last = node;
        } else {
            node.prev = last;
            last.next = node;
            last = node;
        }
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("no item to be removed in the deque");
        }
        Item item = first.item;
        if (first.next == null) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.prev = null;
        }

        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("no item to be removed in the deque");
        }

        Item item = last.item;

        if (first.next == null) {
            first = null;
            last = null;
        } else {
            last = last.prev;
            last.next = null;
        }

        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
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
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(2);
        deque.removeLast();
        StdOut.println(deque.size());
    }
}

