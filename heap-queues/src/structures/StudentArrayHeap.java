package structures;

import java.util.Comparator;

public class StudentArrayHeap<P, V> extends AbstractArrayHeap<P, V> {

	protected StudentArrayHeap(Comparator<P> comparator) {
		super(comparator);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected int getLeftChildOf(int index) throws IndexOutOfBoundsException{
		// TODO Auto-generated method stub
		if(index < 0){
			throw new IndexOutOfBoundsException();
		}
		return index*2+1;
	}

	@Override
	protected int getRightChildOf(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if(index < 0){
			throw new IndexOutOfBoundsException();
		}
		return index*2+2;
	}

	@Override
	protected int getParentOf(int index) throws IndexOutOfBoundsException{
		// TODO Auto-generated method stub
		if(index < 1){
			throw new IndexOutOfBoundsException();
		}
		if(index%2 == 0){
			return (index-2)/2;
		} else {
			return (index-1)/2;
		}
	}

	@Override
	protected void bubbleUp(int index) {
		// TODO Auto-generated method stub
		if(index > 0){
		if(comparator.compare(heap.get(getParentOf(index)).getPriority(), heap.get(index).getPriority()) < 0){
		super.swap(getParentOf(index), index);
		bubbleUp(getParentOf(index));
		}
		}
		
	}

	@Override
	protected void bubbleDown(int index) {
		// TODO Auto-generated method stub
		if(getLeftChildOf(index) < heap.size()-1){
			int largest = getLeftChildOf(index);
			int add = 1;
			if(getRightChildOf(index) < heap.size()-1){
				if(comparator.compare(heap.get(largest).getPriority(), heap.get(getRightChildOf(index)).getPriority()) <0){
					largest = getRightChildOf(index);
					add = 2;
				}
			}
			if(comparator.compare(heap.get(index).getPriority(), heap.get(largest).getPriority()) < 0){
				super.swap(index, largest);
				bubbleDown(index+add);
			}
		}
	}
}
