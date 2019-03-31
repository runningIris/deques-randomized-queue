import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.NoSuchElementException;
import java.lang.IllegalArgumentException;

public class Deque<Item> {
	
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
	
	public static void main(String[] args) {
		Deque<String> k = new Deque<String>();
		k.addFirst("hha");
		k.addFirst("haha");
		k.addFirst("heihei");
		k.addLast("heiha");
		
	}
}

