import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.NoSuchElementException;
import java.lang.IllegalArgumentException;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
	
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
		while (removeLast() != null) {
			len++;
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
			first = last = node;
		} else {
			Node oldFirst = first;
			first = node;
			first.next = oldFirst;
		}
	}
	
	public void addLast(Item item) {
		if (item == null) {
			throw new IllegalArgumentException("argument item should not be null.");
		}
		Node node = new Node();
		node.item = item;
		if (isEmpty()) {
			first = last = node;
		} else {
			last.next = node;
		}
	}

	public Item removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException("no item to be removed in the deque");
		}
		
		Node oldFirst = first;
		
		if (oldFirst != null) {
			first = oldFirst.next;
		} else {
			first = last = null;
		}
		return oldFirst.item;
	}
	
	public Item removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException("no item to be removed in the deque");
		}
		
		Node oldLast = last;
		
		if (oldLast.next != null) {
			last = oldLast.next;
		} else {
			first = last = null;
		}
		return oldLast.item;		
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
				throw new java.util.NoSuchElementException("no more items to return in the next() method.");
			}
			Item item = current.item;
			current = current.next;
			return item;
		}

	}

	public static void main(String[] args) {
		Deque<String> k = new Deque<String>();
		k.addFirst("hha");
		k.addFirst("haha");
		k.addFirst("heihei");
		k.addLast("heiha");
		Iterator<String> iterator = k.iterator();
		StdOut.println(iterator.next());
	}
}

