package data_structures;

import java.util.Iterator;

public class Queue<E extends Comparable<E>> {
	
	@SuppressWarnings("rawtypes")
	LinearList list;

	@SuppressWarnings("rawtypes")
	public Queue() {
		list = new LinearList();
	}
	
	@SuppressWarnings("unchecked")
	public void enqueue(E obj) {
		list.addFirst(obj);
	}
	    
	@SuppressWarnings("unchecked")
	public E dequeue() {
		return (E) list.removeLast();
	}
	
	public int size() {
		return list.size();
	}
	    
	public boolean isEmpty() {
		return list.isEmpty();
	}
	   
	@SuppressWarnings("unchecked")
	public E peek() {
		return (E) list.peekLast();
	}
	
	@SuppressWarnings("unchecked")
	public boolean contains(E obj) {
		return list.contains(obj);
	}
	   
	public void makeEmpty() {
		list.clear();
	}
	
	@SuppressWarnings("unchecked")
	public boolean remove(E obj) {
		if (!list.contains(obj)) {
			return false;
		}
		list.remove(obj);
		return true;
	}
	    
	@SuppressWarnings("unchecked")
	public Iterator<E> iterator(){
		return list.iterator();
	}
	
}
