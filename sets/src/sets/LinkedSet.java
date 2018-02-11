package sets;

import java.util.Iterator;

public class LinkedSet<E> implements Set<E> {
  private LinkedNode<E> head = null;

  // Constructors
  public LinkedSet() {
  }

  public LinkedSet(E e) {
    this.head = new LinkedNode<E>(e, null);
  }

  private LinkedSet(LinkedNode<E> head) {
    this.head = head;
  }

  @Override
  public int size() {
    // TODO (1)
	  int size = 0;
	  Iterator<E> itr = iterator();
	  while(itr.hasNext()){
		  itr.next();
		  size++;
	  }
    return size;
  }

  @Override
  public boolean isEmpty() {
    // TODO (2)
    return head == null;
  }

  @Override
  public Iterator<E> iterator() {
    return new LinkedNodeIterator<E>(this.head);
  }

  @Override
  public boolean contains(Object o) {
    // TODO (3)
	  Iterator<E> itr = iterator();
	  while(itr.hasNext()){
		  if(itr.next().equals(o)){
			  return true;
		  }
	  }
    return false;
  }

  @Override
  public boolean isSubset(Set<E> that) {
    // TODO (4)
	  Iterator<E> itr = iterator();	  
	  while(itr.hasNext()){
		  E cur = itr.next();
		  if(!that.contains(cur)){
			  return false;
		  }
	  }
    return true;
  }

  @Override
  public boolean isSuperset(Set<E> that) {
    // TODO (5)
	  Iterator<E> itr = that.iterator();
	  while(itr.hasNext()){
		  E cur = itr.next();
		  if(!contains(cur)){
			  return false;
		  }
	  }
    return true;
  }

  @Override
  public Set<E> adjoin(E e) {
    // TODO (6)
	  if(!contains(e)){
		  return new LinkedSet<E>(new LinkedNode<E> (e, head));
	  }
	  return this;
  }

  @Override
  public Set<E> union(Set<E> that) {
    // TODO (7)
	  Set<E> result = new LinkedSet<E>(head);
	  Iterator<E> itr = that.iterator();
	  while(itr.hasNext()){
		result = result.adjoin(itr.next());
	  }	  
    return result;
  }

  @Override
  public Set<E> intersect(Set<E> that) {
    // TODO (8)
	  Set<E> result = new LinkedSet<E>();
	  Iterator<E> itr = that.iterator();
	  while(itr.hasNext()){
		  E next = itr.next();
		  if(contains(next)){
		result = result.adjoin(next);
		  }
	  }
    return result;
  }

  @Override
  public Set<E> subtract(Set<E> that) {
    // TODO (9)
	  Set<E> result = new LinkedSet<E>();
	  Iterator<E> itr = iterator();
	  while(itr.hasNext()){
		  E next = itr.next();
		  if(!that.contains(next)){
			  result = result.adjoin(next);
		  }
	  }
	  
    return result;
  }

  @Override
  public Set<E> remove(E e) {
    // TODO (10)
	  if(!contains(e)){
		  return this;
	  }
	  Set<E> result = new LinkedSet<E>();
	  Iterator<E> itr = iterator();
	  while(itr.hasNext()){
		  E next = itr.next();
		  if(!(next == e)){
			  result = result.adjoin(next);
		  }
	  }
    return result;
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean equals(Object o) {
    if (! (o instanceof Set)) {
      return false;
    }
    Set<E> that = (Set<E>)o;
    return this.isSubset(that) && that.isSubset(this);
  }

  @Override
    public int hashCode() {
    int result = 0;
    for (E e : this) {
      result += e.hashCode();
    }
    return result;
  }
}
