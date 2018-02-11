package structures;

import java.util.Comparator;
import java.util.Iterator;

import comparators.IntegerComparator;

public class MaxQueue<V> implements PriorityQueue<Integer, V> {

	int size = 0;
	AbstractArrayHeap<Integer, V> maxHeap= new StudentArrayHeap<Integer, V>(new IntegerComparator());
	@Override
	public PriorityQueue<Integer, V> enqueue(Integer priority, V value) throws NullPointerException{
		// TODO Auto-generated method stub
		if(priority == null || value == null){
			throw new NullPointerException();
		}
		maxHeap.add(priority, value);
		size++;
		return this;
	}

	@Override
	public V dequeue() throws IllegalStateException{
		// TODO Auto-generated method stub
		if(this.isEmpty()){
			throw new IllegalStateException();
		}
		size--;
		return maxHeap.remove();
	}

	@Override
	public V peek() throws IllegalStateException{
		// TODO Auto-generated method stub
		if(this.isEmpty()){
			throw new IllegalStateException();
		}
		return maxHeap.peek();
	}

	@Override
	public Iterator<Entry<Integer, V>> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Integer> getComparator() {
		// TODO Auto-generated method stub
		return maxHeap.getComparator();
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}
}

