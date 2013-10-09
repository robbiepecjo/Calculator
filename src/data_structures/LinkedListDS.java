/*
 * Robbie Pecjo masc1518
 * Professor Riggins 
 * Programming Assignment #1
 * 10/10/2012
 */
package data_structures;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class LinkedListDS<E> implements ListADT<E> {
	class IteratorHelper implements Iterator<E> {
		Node<E> iteratorPointer;
		public IteratorHelper() {
			iteratorPointer = head;
		}
		public boolean hasNext() {
			return iteratorPointer != null;
		}
		public E next() {
			if(!hasNext())
				throw new NoSuchElementException();
			E tmp = iteratorPointer.data;
			iteratorPointer = iteratorPointer.next;
			return tmp;
		}
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	class Node<E> {
		E data; 
		Node<E> next;
		public Node(E data) {
			this.data = data;
			next = null;
		}
	}
	
	private Node<E> head, tail; 
	private int currentSize; 
	
	public LinkedListDS() {
		head = tail = null;
		currentSize = 0;
	}
	
//  Adds the Object obj to the beginning of the list
    public void addFirst(E obj) {
    	Node<E> newNode = new Node<E>(obj);
    	if(head == null)
    			head = tail = newNode;
    	else
    		newNode.next = head; 
    	head = newNode;
    	currentSize++;
    }

//  Adds the Object obj to the end of the list 
    public void addLast(E obj) {
    	Node<E> newNode = new Node<E>(obj);
    	if(head == null)
    		head = tail = newNode;
    	else
    		tail.next = newNode;
    	tail = newNode;
		currentSize++;    	
    }

//  Removes the first Object in the list and returns it.
//  Returns null if the list is empty.
    public E removeFirst() {
    	if(head == null)
    		return null;
    	E tmp = head.data;
    	head = head.next; 
    	if(head == null) 
    		tail = null;
    	currentSize--;
    	return tmp;
    }

//  Removes the last Object in the list and returns it.
//  Returns null if the list is empty.
    public E removeLast() {
    	E tmp;
    	Node<E> prev = null, current = head;
    	if(tail == null)
    		return null;
    	while(current.next != null) {
    		prev = current;
    		current = current.next;
    	}
    	if(prev == null)
    		head = tail = null;
    	else  		
    		prev.next = null;
    	tmp = current.data;
    	tail = prev;
    	currentSize--;
    	return tmp;
    }

//  Returns the first Object in the list, but does not remove it.
//  Returns null if the list is empty.
    public E peekFirst() {
    	if(head == null)
    		return null;
		else
			return head.data;
    }

//  Returns the last Object in the list, but does not remove it.
//  Returns null if the list is empty.
    public E peekLast() {
    	if(tail == null)
    		return null;
    	else
	    	return tail.data;
    }

//  Removes the specific Object obj from the list, if it exists.
//  Returns true if the Object obj was found and removed, otherwise false
    public boolean remove(E obj) {
    	if(head == null)
    		return false;
    	Node<E> prev = null, curr = head;
    	while(curr != null && ((Comparable<E>)obj).compareTo(curr.data) != 0 ) {
    		prev = curr;
    		curr = curr.next;
    	}
    	if(curr == null)
    		return false;
    	if(prev == null)
    		head = head.next;
    	else
    		prev.next = curr.next;
    	currentSize--; 
    	return true;
    }
//  The list is returned to an empty state.
    public void makeEmpty() {
    	while(head != null){
	    	head = head.next; 
	    	currentSize--;
    	}
    	tail = null;
    }

//  Returns true if the list contains the Object obj, otherwise false
    public boolean contains(E obj) {
    	Node<E> xNode = new Node<E>(obj);
    	Node<E> current = head; 
        	if(head == null) {
        		tail = null;
        		return false;
        	}
        		while(current != null) {
        			if(current.data == xNode.data)
        				return true;
        			current = current.next;
        		}
        		if(current != xNode.data)
        			return false;
        		else
        			return true;
    }
	   

//  Returns true if the list is empty, otherwise false
    public boolean isEmpty() {
    	if(head == null && tail == null)
    		return  true;
    	else
    		return false;
    }

//  Returns true if the list is full, otherwise false
    public boolean isFull(){return false;}

//  Returns the number of Objects currently in the list.
    public int size() {return currentSize;}

//  Returns an Iterator of the values in the list, presented in
//  the same order as the list.
    public Iterator<E> iterator() {return new IteratorHelper();}
}
