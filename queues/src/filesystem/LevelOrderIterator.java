package filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

import structures.Queue;

/**
 * An iterator to perform a level order traversal of part of a 
 * filesystem. A level-order traversal is equivalent to a breadth-
 * first search.
 * 
 * @author liberato
 *
 */
public class LevelOrderIterator extends FileIterator<File> {	
	/**
	 * Instantiate a new LevelOrderIterator, rooted at the rootNode.
	 * @param rootNode
	 * @throws FileNotFoundException if the rootNode does not exist
	 */
	private Queue<File> queue = new Queue<File>();
	
	public LevelOrderIterator(File rootNode) throws FileNotFoundException {
		// TODO 1
		if(!rootNode.exists()){
			throw new FileNotFoundException();
		}
		queue.enqueue(rootNode);
	}
	
	@Override
	public boolean hasNext() {
		// TODO 2
		return !queue.isEmpty();
	}

	@Override
	public File next() throws NoSuchElementException {
		// TODO 3
		File cur = queue.peek();
		if(hasNext()) {
			queue.dequeue();
			if(cur.listFiles() != null){
			File[] files = cur.listFiles();
			File[] sorted = new File[files.length];
			for(int i = 0;i<files.length-1;i++){
				if(files[i].compareTo(files[i+1]) <= 0){
					sorted[i] = files[i];
					sorted[i+1] = files[i+1];
				}
				else{
					sorted[i] = files[i+1];
					sorted[i+1] = files[i];
				}
			}
			for(int i =0;i<files.length;i++){
				queue.enqueue(sorted[i]);
			}
		}
		}
		return cur;
	}

	@Override
	public void remove() {
		// Leave this one alone.
		throw new UnsupportedOperationException();		
	}

}
