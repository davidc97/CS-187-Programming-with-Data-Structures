package stack;

/**
 * A {@link LinkedStack} is a stack that is implemented using a Linked List structure
 * to allow for unbounded size.
 *
 * @param <T> the elements stored in the stack
 */
public class LinkedStack<T> implements StackInterface<T> {
	private LLNode<T> head = null;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T pop() throws StackUnderflowException {
		// TODO Auto-generated method stub
		if(isEmpty()){
			throw new StackUnderflowException();
		}
			LLNode<T> temp = head;
			head = head.getNext();
			return temp.getData();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public T top() throws StackUnderflowException {
		// TODO Auto-generated method stub
		if(isEmpty()){
			throw new StackUnderflowException();
		}
		return head.getData();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return head == null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		LLNode<T> itr = head;
		int num = 0;
		while(itr != null){
			num++;
			itr = itr.getNext();
		}
		return num;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void push(T elem) {
		LLNode<T> temp = new LLNode<T>(elem);
		temp.setNext(head);
		head = temp;
		// TODO Auto-generated method stub
		
	}

}
