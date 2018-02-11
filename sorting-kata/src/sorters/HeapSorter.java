package sorters;

import java.util.Comparator;

import structures.SwapList;

public class HeapSorter<T> extends AbstractSorter<T> {

	public HeapSorter(SwapList<T> list, Comparator<T> comparator) {
		super(list, comparator);
	}

	@Override
	public SwapList<T> sort() {
		// TODO
		heapify();
		for(int i = list.size()-1; i >= 1; i--){
			list.swap(0, i);
			bubbleDown(list,0,i-1);
		}
		return list;
	}
	protected void heapify() {
		for(int i = (list.size()-1)/2;i>=0;i--){
			bubbleDown(list,i,list.size()-1);
		}
	}
	protected void bubbleDown(SwapList<T> list, int start, int end){
		int leftChild = getLeftChildOf(start);
		int rightChild = getRightChildOf(start);
		if(leftChild <= end){
			int largest = leftChild;
			if(rightChild<= end){
				if(list.compare(largest, rightChild, comparator) < 0){
					largest = rightChild;
				}
			}
			if(list.compare(start, largest, comparator) < 0){
				list.swap(start, largest);
			}
			bubbleDown(list,largest,end);
		}
	}
	protected int getLeftChildOf(int index) throws IndexOutOfBoundsException{
		if(index < 0){
			throw new IndexOutOfBoundsException();
		}
		return index*2+1;
	}
	protected int getRightChildOf(int index) throws IndexOutOfBoundsException{
		if(index < 0){
			throw new IndexOutOfBoundsException();
		}
		return index*2+2;
	}
	protected int getParentOf(int index) throws IndexOutOfBoundsException{
		if(index < 0){
			throw new IndexOutOfBoundsException();
		}
		return (index-1)/2;
	}
}
