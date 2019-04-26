/*
 Program #2
 Kristian Bunda
 cssc1458
 */
package data_structures;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class LinearList<E extends Comparable<E>> implements LinearListADT<E>, Iterable<E>{
	
	@SuppressWarnings("hiding")
	class Node<E>{
		E data;
		Node<E> next;	//pointer
		Node<E> prev;
		public Node (E obj) {
			data = obj;
			next = prev = null;
		}
	}
	 
	private Node<E> head;
	private Node<E> tail;
	private int currentSize;
	private int modCounter;
	
	public LinearList() {
		head = tail = null;
		currentSize = 0;
	}
	
	@Override
	public boolean addFirst(E obj) {
		if(isFull()) {
			return false;
		}
		Node<E> newNode = new Node<E>(obj);
		
		if (isEmpty()) {
		modCounter++;
		head = tail = newNode;
		}
		
		head.prev = newNode;
		newNode.next = head;	//if there is an element in the list
		head = head.prev;
		
		currentSize++;
		modCounter++;
		return true;
	}

	@Override
	public boolean addLast(E obj) {
		if (isFull()) {
			return false;
		}
		Node<E> newNode = new Node<E>(obj);
		
		if (isEmpty()) {
			modCounter++;
			head = tail = newNode;
		}
		
		tail.next = newNode;
		newNode.prev = tail;
		tail = newNode;
		
		currentSize++;
		modCounter++;
		return true;
	}

	@Override
	public E removeFirst() {
		if (isEmpty()) {
			return null;
		}
		E temp = head.data;
		if (head == tail) {
			modCounter++;
			head = tail = null;
		}
		else {
		head = head.next;
		}
		currentSize--;
		modCounter++;
		return temp;
	}

	@Override
	public E removeLast() {
		if (isEmpty()) {
			return null;
		}
		if (head == tail) {
			return removeFirst();
		}
		
		E temp = tail.data;
		tail = tail.prev;
		tail.next = null;
		currentSize--;
		modCounter++;
		return temp;
	}

	@Override
	public E remove(E obj) {
		Node<E> current = head;
		Node<E> prev = null;
		
		while (current != null) {
			if (((Comparable<E>)current.data).compareTo(obj) == 0) {
				if (current == head) {
					return removeFirst();
				}
				if (current == tail) {
					return removeLast();
				}
				modCounter++;
				currentSize--;
				prev.next = current.next;
				current.next.prev = prev;
				return current.data;
			}
			prev = current;
			current = current.next;
		}
		return null;
	}

	@Override
	public E peekFirst() {
		if (isEmpty()) {
			return null;
		}
		return head.data;
	}

	@Override
	public E peekLast() {
		if (isEmpty()) {
			return null;
		}
		return tail.data;
	}

	@Override
	public boolean contains(E obj) {
		Node<E> current = head;
		while (current != null) {
		if (((Comparable<E>)current.data).compareTo(obj) == 0) {
			return true;
		}
		current = current.next;
		}
		return false;
	}

	@Override
	public E find(E obj) {
		if (contains(obj)) {
			return obj;
		}
		return null;
	}

	@Override
	public void clear() {
		head = tail = null;
		currentSize = 0;
		modCounter++;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public int size() {
		return currentSize;
	}

	@Override
	public Iterator<E> iterator() {
		return new iteratorHelper();
	}
	Iterator<E> it = iterator();
	private class iteratorHelper implements Iterator<E>{
		private int itCounter;
		private int size;
		Node<E> position;
		
		public iteratorHelper() {
			this.itCounter = modCounter;
			this.size = 0;
			this.position = head;
		}
		
		@Override
		public boolean hasNext() {
			if (this.itCounter != modCounter) {
				throw new ConcurrentModificationException();
			}
			return this.size < currentSize;
		}

		
		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			if(this.size == 0)
			{
				this.size++;
				return position.data;
			}
			size++;
			position = position.next;
			return position.data;
		}
		
	}
}
