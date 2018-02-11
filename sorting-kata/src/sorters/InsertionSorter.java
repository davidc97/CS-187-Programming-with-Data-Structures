package sorters;

import java.util.Comparator;

import structures.SwapList;

public class InsertionSorter<T> extends AbstractSorter<T> {
	
	boolean finished,moreToSearch;

	public InsertionSorter(SwapList<T> list, Comparator<T> comparator) {
		super(list, comparator);
	}

	@Override
	public SwapList<T> sort() {
		// TODO
		for(int i = 1; i < list.size(); i++){
			insertElement(0,i);
		}
		return list;
	}
	
	private void insertElement(int startIndex, int endIndex){
		finished = false;
		moreToSearch = true;
		int current = endIndex;
		while(moreToSearch && !finished){
			if(list.compare(current, current-1, comparator) < 0){
				list.swap(current, current-1);
				current--;
				moreToSearch = (current != startIndex);
			} else {
				finished = true;
			}
		}
	}
}
