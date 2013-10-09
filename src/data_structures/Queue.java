/*
 * Robbie Pecjo masc1518
 * Professor Riggins 
 * Programming Assignment #1
 * 10/10/2012
 */
package data_structures;
import java.util.Iterator;
public class Queue<E> implements Iterable<E> {
	//Queue - FIFO 
	//Similar to a line at a fast food drive through.
	private ListADT<E> list;
    public Queue(){list = new LinkedListDS<E>();}
    public void enqueue(E obj){list.addLast(obj);}
    public E dequeue(){return list.removeFirst();}
    public int size(){return list.size();}
    public boolean isEmpty(){
    	if(list.isEmpty())
    		return true;
    	return false;
    }
    public boolean isFull(){
    	if(list.isEmpty())
    		return true;
    	return false;
    }
    public E peek(){return list.peekFirst();}
    public boolean contains(E obj) 
    {
    	if(list.contains(obj))
    		return true;
    	return false;
    }
    public void makeEmpty(){list.makeEmpty();}
    public Iterator<E> iterator(){return list.iterator();}
}