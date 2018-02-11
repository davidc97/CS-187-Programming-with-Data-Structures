package sorters;

import java.util.Comparator;

import structures.SwapList;

public class QuickSorter<T> extends AbstractSorter<T> {

	public QuickSorter(SwapList<T> list, Comparator<T> comparator) {
		super(list, comparator);
	}

	@Override
	public SwapList<T> sort() {
		// TODO
		
		/*
		 * Note: When choosing a pivot, choose the element in the middle of
		 * the sub-array. That is,
		 * 
		 * pivotIndex = (firstIndex + lastIndex) / 2;
		 */
		qs(list, 0, list.size()-1);
		return list;
	}
	protected void qs(SwapList<T> list, int firstIndex, int lastIndex){
		if(firstIndex < lastIndex){
			int splitPoint = split(list, firstIndex, lastIndex);
			qs(list, firstIndex, splitPoint - 1);
			qs(list, splitPoint + 1, lastIndex);
		}
	}
	protected int split(SwapList<T> list, int firstIndex, int lastIndex){
		int pivotIndex = (firstIndex+lastIndex)/2;
		list.swap(pivotIndex, lastIndex);
		int nextSwapIndex = firstIndex;
		for (int i = firstIndex; i < lastIndex; i++){
			if(list.compare(i, lastIndex, comparator)<=0){
				list.swap(i, nextSwapIndex);
				nextSwapIndex++;
			}
		}
		list.swap(nextSwapIndex, lastIndex);
		return nextSwapIndex;
	}
}