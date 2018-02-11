package structures;

import java.util.NoSuchElementException;

public class Stack<T> {
	LLNode<T> head;
	
	public Stack() {
		head = null;
	}
	
	public T pop() throws NoSuchElementException{
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		T result = head.getInfo();
		head = head.getLink();
		return result;
	}
	public boolean isEmpty(){
		return head ==null;
	}
	public T peek() throws NoSuchElementException{
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		return head.getInfo();
	}
	public void push(T element){
		LLNode<T> temp = new LLNode<T>(element);
		temp.setLink(head);
		head = temp;
	}

}
