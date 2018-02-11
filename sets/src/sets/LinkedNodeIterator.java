package sets;

import java.util.Iterator;
import java.util.NoSuchElementException;

class LinkedNodeIterator<E> implements Iterator<E> {
  // TODO (1) choose appropriate attributes
	private LinkedNode<E> head;

  // Constructors
  public LinkedNodeIterator(LinkedNode<E> head) {
    // TODO (2) choose appropriate parameters and do the initialization
	  this.head = head;
  }

  @Override
  public boolean hasNext() {
    // TODO (3)
	  return head!=null;
  }
  @Override
  public E next() {
	  // TODO (4)
	  if(hasNext()){
		  E cur = head.getData();
		  try{
			  head = head.getNext();
		  }
		  catch (NullPointerException e){
		  }
		  return cur;
	  }
	  else{
		  throw new NoSuchElementException();
	  }
  }
  // Leave this one alone.
  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }
}
