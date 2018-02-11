package structures;

import java.util.Iterator;

public class RecursiveList<T> implements ListInterface<T> {

	private int size;
	private Node<T> head;

	public RecursiveList(){
		size = 0;
		head = null;
	}

	public int size(){
		return size;
	}

	public ListInterface<T> insertFirst(T elem) throws NullPointerException{
		if(elem == null){
			throw new NullPointerException();
		}
		Node<T> temp = new Node<T>(elem, head);
		head = temp;
		size++;
		return this;
	}

	public ListInterface<T> insertLast(T elem) throws NullPointerException{
		if(elem == null){
			throw new NullPointerException();
		}
		if(isEmpty()){
			insertFirst(elem);
			return this;
		}
		insertionHelper(0,elem,head,size);
		size++;
		return this;
	}

	public ListInterface<T> insertAt(int index, T elem) throws NullPointerException, IndexOutOfBoundsException{
		if(elem == null){
			throw new NullPointerException();
		}
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException();
		}
		if(index == 0){
			return insertFirst(elem);
		}
		insertionHelper(0,elem,head,index);
		size++;
		return this;
	}
	private void insertionHelper(int index, T elem, Node<T> start, int position){
		Node<T> temp = start;
		if(index+1 == position){
			temp.setNext(new Node<T>(elem,temp.getNext()));
		}
		if(index < position){
		insertionHelper(index+1,elem,temp.getNext(),position);
	}
	}
	public T removeFirst() throws IllegalStateException{
		if(isEmpty()){
			throw new IllegalStateException();
		}
		T result = head.getData();
		head = head.getNext();
		size--;
		return result;
	}
	public T removeLast() throws IllegalStateException{
		if(isEmpty()){
			throw new IllegalStateException();
		}
		T result = getLast();
		if(size == 1){
			return removeFirst();
		}
		getHelper(0,head,size-2).setNext(null);
		size--;
		return result;
	}

	@Override
	public T removeAt(int i) throws IndexOutOfBoundsException{
		if(i<0 || i >= size){
			throw new IndexOutOfBoundsException();
		}
		T result = get(i);
		if(i == 0){
			return removeFirst();
		}
		if(i == size-1){
			return removeLast();
		}
		Node<T> prev = getHelper(0,head,i-1);
		prev.setNext(prev.getNext().getNext());
		size--;
		return result;
	}

	@Override
	public T getFirst() throws IllegalStateException {
		if(isEmpty()){
			throw new IllegalStateException();
		}
		return head.getData();
	}

	@Override
	public T getLast() throws IllegalStateException{
		if(isEmpty()){
			throw new IllegalStateException();
		}
		return getHelper(0,head,size-1).getData();
	}

	@Override
	public T get(int i) throws IndexOutOfBoundsException{
		if(i < 0 || i >= size){
			throw new IndexOutOfBoundsException();
		}
		return getHelper(0, head, i).getData();
	}

	private Node<T> getHelper(int index, Node<T> start, int position){
		if(index == position){
			return start;
		}
		return getHelper(index+1, start.getNext(), position);
	}

	@Override
	public boolean remove(T elem) throws NullPointerException{
		if(elem == null){
			throw new NullPointerException();
		}
		int result = indexOf(elem);
		if(result == -1){
			return false;
		}
		else{
			removeAt(result);
			return true;
		}
	}

	@Override
	public int indexOf(T elem) throws NullPointerException{
		// TODO Auto-generated method stub
		if(elem == null){
			throw new NullPointerException();
		}
		try{
			return indexOfHelper(elem, head, 0);
		} catch(NullPointerException e) {
			return -1;
		}
	}
	private int indexOfHelper(T toFind, Node<T> toCheck, int currentIndex) throws NullPointerException{
		if(toCheck == null){
			throw new NullPointerException();
		}
		if(toCheck.getData().equals(toFind)){
			return currentIndex;
		}

		return indexOfHelper(toFind, toCheck.getNext(), currentIndex+1);
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
