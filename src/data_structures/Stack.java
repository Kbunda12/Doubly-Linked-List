package data_structures;

import java.util.Iterator;

public class Stack<E extends Comparable<E>> implements Iterable<E>{
	
	@SuppressWarnings("rawtypes")
	LinearList list;

	
	@SuppressWarnings("rawtypes")
	public Stack() {
	list = new LinearList();
	}
	 
	@SuppressWarnings("unchecked")
	public void push(E obj) {
		list.addFirst(obj);
	}
	
	@SuppressWarnings("unchecked")
	public E pop() {
		return (E) list.removeFirst();
	}

	public int size() {
		return list.size();
	}
                          
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	@SuppressWarnings("unchecked")
	public E peek() {
		return (E) list.peekFirst();
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
		if (!contains(obj)) {
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
