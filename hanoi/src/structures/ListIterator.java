package structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListIterator<E> implements Iterator<E> {
	
	private Node<E> head;
	
	public ListIterator(Node<E> head){
		this.head = head;
	}
	public boolean hasNext(){
		return head != null;
	}
	public E next(){
		if(hasNext()){
			E result = head.getData();
			try{
				head = head.getNext();
			} catch(NullPointerException e){}
			return result;
		}
		else{
			throw new NoSuchElementException();
		}
	}
}

	
