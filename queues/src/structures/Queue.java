package structures;

import java.util.NoSuchElementException;

public class Queue<T> implements UnboundedQueueInterface<T> {
	private LLNode<T> head,tail;
	private int size;
	
	public Queue() {
		head = null;
		tail = null;
		size = 0;
		// TODO 1
	}
	
	public Queue(Queue<T> other) {
		// TODO 2
		// Hint: Maybe save this one until after you finish enqueue()/dequeue()
		LLNode<T> itr = other.head;
		while(itr != null){
			enqueue(itr.getInfo());
			itr = itr.getLink();
		}
		size = other.size();
	}
	
	@Override
	public boolean isEmpty() {
		// TODO 3
		return size()==0;
	}

	@Override
	public int size() {
		// TODO 4
		return size;
	}

	@Override
	public void enqueue(T element) {
		// TODO 5;
		if(head == null){
			head = new LLNode<T>(element);
			tail = head;
			size++;
		}
		else{
			LLNode<T> temp = new LLNode<T>(element);
			tail.setLink(temp);
			tail = temp;
			size++;
		}
	}

	@Override
	public T dequeue() throws NoSuchElementException {
		// TODO 6;
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		T result = head.getInfo();
		head = head.getLink();
		size--;
		return result;
	}

	@Override
	public T peek() throws NoSuchElementException {
		// TODO 7
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		return head.getInfo();
	}

	@Override
	public UnboundedQueueInterface<T> reversed() {
		// TODO 8
		// Hint: Maybe save this one until after you finish enqueue()/dequeue()
		Stack<T> stack = new Stack<T>();
		Queue<T> copy = new Queue<T>(this);
		while(!copy.isEmpty()){
			stack.push(copy.dequeue());
		}
		while(!stack.isEmpty()){
			copy.enqueue(stack.pop());
		}
		return copy;
	}
}
