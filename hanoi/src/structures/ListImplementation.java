package structures;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImplementation<T> implements ListInterface<T> {
	private int size;
	private Node<T> head;
	private Node<T> tail;
	
	public ListImplementation(){
		size = 0;
		head = null;
		tail = null;
	}
	public Iterator<T> iterator(){
	return new ListIterator<T>(head);
	}
	
	public int size(){
		return size;
	}
	public boolean isEmpty(){
	return head == null;
	}
	
	public T get(int n) throws NoSuchElementException{
		Iterator<T> itr = iterator();
		if(n >= size || n < 0){
			throw new NoSuchElementException();
		}
		for(int i = 0;i<n;i++){
			itr.next();
			}
		return itr.next();
		}
	
	public ListInterface<T> append(T elem) throws NullPointerException{
		if(elem == null){
			throw new NullPointerException();
		}
		if(isEmpty()){
			head = new Node<T>(elem, null);
			tail = head;
			size++;
			return this;
		}
		else{
		Node<T> temp = new Node<T>(elem, null);
		tail.setNext(temp);
		tail = temp;
		size++;
		return this;
		}
	}
	

}
