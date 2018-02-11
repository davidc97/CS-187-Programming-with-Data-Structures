package structures;

import java.util.Comparator;
import java.util.Iterator;

import comparators.ReverseIntegerComparator;

public class MinQueue<V> implements PriorityQueue<Integer, V> {
	
	int size = 0;
	AbstractArrayHeap<Integer, V> minHeap = new StudentArrayHeap<Integer, V>(new ReverseIntegerComparator());
	
	
	public MinQueue(){
	}

	@Override
	public PriorityQueue<Integer, V> enqueue(Integer priority, V value) throws NullPointerException{
		// TODO Auto-generated method stub
		if(priority == null || value == null){
			throw new NullPointerException();
		}
		minHeap.add(priority, value);
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
		return minHeap.remove();
	}

	@Override
	public V peek() throws IllegalStateException{
		// TODO Auto-generated method stub
		if(this.isEmpty()){
			throw new IllegalStateException();
		}
		return minHeap.peek();
	}

	@Override
	public Iterator<Entry<Integer, V>> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Integer> getComparator() {
		// TODO Auto-generated method stub
		return minHeap.getComparator();
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

