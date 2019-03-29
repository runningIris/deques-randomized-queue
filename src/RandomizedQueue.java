import edu.princeton.cs.algs4.*;
//import edu.princeton.cs.algs4.StdOut;
//import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> {
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
		while(current != null) {
			len++;
			current = current.next;
		}
		return len;
	}
	public void enqueue(Item item) {
		if (item == null) {
			throw new java.lang.IllegalArgumentException("item should not be null in method enqueue.");
		}
		Node newNode = new Node();
		newNode.item = item;

		if (isEmpty()) {
			first = last = newNode;
		} else {
			last.next = newNode;
			last = newNode;
		}
	}
	public Item dequeue() {
		if (first == null) {
			throw new java.util.NoSuchElementException("no more items to return in the dequeue() method.");
		}
		Node oldFirst = first;
		first = first.next;
		return oldFirst.item;
	}
	public Item sample() {
		if (first == null) {
			throw new java.util.NoSuchElementException("no more items to return in the sample() method.");
		}
		int len = size();
		int random = StdRandom.uniform(len);
		int i = 0;
		Node current = first;

		while (i < random) {
			current = current.next;
		}

		return current.item;
	}
	public java.util.Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}
	private class RandomizedQueueIterator implements java.util.Iterator<Item> {
		private Node current = first;

		public boolean hasNext() {
			return current != null;
		}
		public Item next() {
			if (current == null) {
				throw new java.util.NoSuchElementException("no more items to return in the next() method.");
			}

			Item item = current.item;

			current = current.next;

			return item;
		}
	}
	public static void main(String[] args) {
		RandomizedQueue<String> r = new RandomizedQueue<>();
		r.enqueue("hello");
		r.enqueue("world");
		java.util.Iterator<String> iterator = r.iterator();
		StdOut.println(iterator.next());
		StdOut.println(iterator.next());
	}
}
